package br.edu.fasa.cv.presentation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.ClienteDAO;
import br.edu.fasa.cv.dataaccess.DocumentoDAO;
import br.edu.fasa.cv.dataaccess.ProdutoDAO;
import br.edu.fasa.cv.dataaccess.VendaDAO;
import br.edu.fasa.cv.dataaccess.VendaProdutoDAO;
import br.edu.fasa.cv.domainmodel.Cliente;
import br.edu.fasa.cv.domainmodel.Documento;
import br.edu.fasa.cv.domainmodel.Produto;
import br.edu.fasa.cv.domainmodel.Venda;
import br.edu.fasa.cv.domainmodel.VendaProduto;
import br.edu.fasa.cv.util.Util;

public class CadVenda extends Activity implements LocationListener{
	ProdutoDAO pdao;
	ClienteDAO cdao;
	DocumentoDAO ddao;
	Documento doc;
	VendaProdutoDAO vpdao;
	VendaDAO vdao;
	Produto produto;
	Venda venda;
	VendaProduto vp;
	ArrayAdapter<Cliente> clientes;
	ArrayAdapter<VendaProduto> apd;
	List<VendaProduto> listaprodutos;
	Spinner cliente, prazo;
	ListView listviewprodutos;
	TextView total;
	String mopcao;
	ImageButton[] opcao = new ImageButton[8];
	static LocationManager lm;
	String lon,lat;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cad_venda);
		
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
		
		cliente = (Spinner) findViewById(R.id.vnd_spcliente);
		prazo = (Spinner) findViewById(R.id.vnd_spformapgto);
		listviewprodutos = (ListView) findViewById(R.id.vnd_listprd);
		total = (TextView) findViewById(R.id.vnd_txtotal);
		opcao[0] = (ImageButton) findViewById(R.id.vnd_incluir);
		opcao[1] = (ImageButton) findViewById(R.id.vnd_alterar);
		opcao[2] = (ImageButton) findViewById(R.id.vnd_pesquisar);
		opcao[3] = (ImageButton) findViewById(R.id.vnd_confirmar);
		opcao[4] = (ImageButton) findViewById(R.id.vnd_cancelar);
		opcao[5] = (ImageButton) findViewById(R.id.vnd_voltar);
		opcao[6] = (ImageButton) findViewById(R.id.vnd_incluirprd);
		opcao[7] = (ImageButton) findViewById(R.id.vnd_removerprd);
		produto = new Produto();
		venda = new Venda();
		ddao = new DocumentoDAO(getApplicationContext());
		pdao = new ProdutoDAO(getApplicationContext());
		cdao = new ClienteDAO(getApplicationContext());
		vdao = new VendaDAO(getApplicationContext());
		vpdao = new VendaProdutoDAO(getApplicationContext());

		if (cdao.listarTodos().size() == 0) {
			Util.toast(getApplicationContext(),
					"Cadastre no mínimo um cliente para realizar uma venda!");
			finish();
		} else if (pdao.listarTodos().size() == 0) {
			Util.toast(getApplicationContext(),
					"Cadastre no mínimo um produto para realizar uma venda!");
			finish();
		} else {
			clientes = new ArrayAdapter<Cliente>(getApplicationContext(),
					android.R.layout.simple_spinner_item,
					cdao.listarTodosOrdemNome());
			listaprodutos = new ArrayList<VendaProduto>();
			Log.d("CV", "Cliente " + clientes.getCount());
			cliente.setAdapter(clientes);
			habilitaDesabilitaEditText(false);
			habilitaDesabilitaMenu(true, true, true, false, false, true, false,
					false);
			preencheListaProduto();
		}
		total.setText("0");
	}

	public void preencheListaProduto() {
		AdapterVndProduto avp = new AdapterVndProduto(this, listaprodutos);
		listviewprodutos.setAdapter(avp);
	}

	public void salvar() {
		Iterator<VendaProduto> i = listaprodutos.iterator();
		Log.d("DB4O", "Tentando salvar");
		try {
			venda.setId(vdao.proximoCodigo());
			venda.setCliente((Cliente) cliente.getSelectedItem());
			venda.setDataVenda(Calendar.getInstance().getTime());
			vdao.salvar(venda);
			while (i.hasNext()) {
				vp = i.next();
				vp.setVenda(venda);
				vpdao.salvar(vp);
				produto = pdao.abrir(vp.getProduto());
				produto.setEstoque(produto.getEstoque() - vp.getQuantidade());
				pdao.salvar(produto);
			}
			geraReceber();
			Util.toast(this,
					"Venda salva com sucesso!\nCódigo: " + venda.getId());
			habilitaDesabilitaMenu(true, true, true, false, false, true, false,
					false);
			habilitaDesabilitaEditText(false);
		} catch (Exception e) {
			Log.d("DB4O", "Erro ao salvar" + e);
			Util.toast(this, "Erro ao salvar venda!");
		}
	}

	public void executaOpcao(View v) {
		switch (v.getId()) {
		case R.id.vnd_incluir:
			mopcao = "I";
			habilitaDesabilitaEditText(true);
			habilitaDesabilitaMenu(false, false, false, true, true, true, true,
					true);
			venda.setId(vdao.proximoCodigo());
			cliente.requestFocus();
			break;
		case R.id.vnd_alterar:
			mopcao = "A";
			habilitaDesabilitaMenu(false, false, false, true, true, false,
					true, true);
			habilitaDesabilitaEditText(true);
			break;
		case R.id.vnd_pesquisar:
			mopcao = "P";
			if (vdao.listarTodos().size() > 0) {
				startActivityForResult(new Intent(this, ListaVenda.class), 1);
			} else {
				Util.toast(getApplicationContext(), "Nenhuma venda cadastrada!");
			}

			break;
		case R.id.vnd_confirmar:
			if ("I".equals(mopcao)) {
				Log.d("CV", "Entrou no I");
				if (!listaprodutos.isEmpty()) {
					venda = new Venda();
					salvar();
				} else {
					Util.toast(getApplicationContext(),
							"Lista de produtos não pode ficar vazia!");
				}
			} else if ("A".equals(mopcao)) {
				if (!listaprodutos.isEmpty()) {
					salvar();
				} else {
					Util.toast(getApplicationContext(),
							"Lista de produtos não pode ficar vazia!");
				}
			}

			break;
		case R.id.vnd_cancelar:
			if ("I".equals(mopcao)) {
				listaprodutos.clear();
				preencheListaProduto();
			}
			habilitaDesabilitaEditText(false);
			habilitaDesabilitaMenu(true, true, true, false, false, true, false,
					false);
			break;
		case R.id.vnd_voltar:
			finish();
			break;
		case R.id.vnd_incluirprd:
			Intent i = new Intent(getApplicationContext(), InserirProduto.class);
			startActivityForResult(i, 2);
			break;
		case R.id.vnd_removerprd:
			finish();
			break;
		}
	}

	public void habilitaDesabilitaEditText(Boolean estado) {
		cliente.setEnabled(estado);
		prazo.setEnabled(estado);
		listviewprodutos.setEnabled(estado);
		if (!estado) {
			listaprodutos.clear();
			preencheListaProduto();
		}
	}

	public void habilitaDesabilitaMenu(Boolean incluir, Boolean alterar,
			Boolean pesquisar, Boolean confirmar, Boolean cancelar,
			Boolean voltar, Boolean incprod, Boolean remprod) {
		opcao[0].setEnabled(incluir); // incluir
		opcao[1].setEnabled(alterar); // alterar
		opcao[2].setEnabled(pesquisar); // pesquisar
		opcao[3].setEnabled(confirmar); // confirmar
		opcao[4].setEnabled(cancelar); // cancelar
		opcao[5].setEnabled(voltar); // voltar
		opcao[6].setEnabled(incprod); // incluir produto
		opcao[7].setEnabled(remprod); // remover produto
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == InserirProduto.MENU_CONFIRMA) {
			Bundle b = data.getExtras();
			vp = new VendaProduto();
			produto.setDescricao(b.getString("descricao"));
			produto.setEstoque(b.getInt("estoque"));
			produto.setValor(b.getDouble("valor"));
			vp.setProduto(pdao.abrir(produto));
			vp.setVenda(vdao.abrir(venda));
			vp.setQuantidade(b.getInt("quantidade"));
			listaprodutos.add(vp);
			preencheListaProduto();
			total.setText(Double.toString(Double.parseDouble(total.getText().toString()) + vp.getTotal()));
		} else if (resultCode == ListaVenda.CONTEXTMENUITEM_EDIT) {
			habilitaDesabilitaMenu(false, true, true, false, false, true, true,
					true);
			mopcao = "A";
			Bundle b = data.getExtras();
			habilitaDesabilitaEditText(true);
			venda.setId(b.getLong("ID"));
			vdao.abrir(venda);
			cliente.setSelection(clientes.getPosition(venda.getCliente()));
			listaprodutos = vpdao.buscaPorVenda(venda);
			preencheListaProduto();
			cliente.requestFocus();
		} else {
			habilitaDesabilitaMenu(true, true, true, false, false, true, false,
					false);
		}
	}

	public static String formata(double x) {
		return String.format("%.2f", x);
	}

	public void geraReceber() {
		Log.d("CV", "Posicao: " + prazo.getSelectedItemPosition());
		int vezes = prazo.getSelectedItemPosition();
		int parcelas = vezes;
		Date dataatual = Calendar.getInstance().getTime();
		if (vezes > 0) {
			while (vezes >= 1) {
				doc = new Documento();
				doc.setVenda(venda);
				doc.setDocumento(ddao.proximoCodigo());
				doc.setFaturado(false);
				doc.setValor(Double.parseDouble(total.getText().toString())
						/ parcelas);
				dataatual.setMonth(dataatual.getMonth() + 1);
				doc.setDataVencimento(dataatual);
				vezes--;
				try {
					ddao.salvar(doc);
				} catch (Exception e) {
					Util.toast(getApplicationContext(),
							"Erro ao gravar documento " + doc.getDocumento());
				}
			}
		} else {
			doc = new Documento();
			doc.setVenda(venda);
			doc.setDocumento(ddao.proximoCodigo());
			doc.setFaturado(true);
			doc.setDataVencimento(Calendar.getInstance().getTime());
			try {
				ddao.salvar(doc);
			} catch (Exception e) {
				Util.toast(getApplicationContext(), "Erro ao gravar documento "
						+ doc.getDocumento());
			}
		}

	}
	//Metodos para pegar posicionamento GPS
	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		// Salva valores no banco.
		lon = Double.toString(loc.getLongitude());
		lat = Double.toString(loc.getLatitude());
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
