package br.edu.fasa.cv.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.CategoriaDAO;
import br.edu.fasa.cv.dataaccess.ProdutoDAO;
import br.edu.fasa.cv.domainmodel.Categoria;
import br.edu.fasa.cv.domainmodel.Produto;
import br.edu.fasa.cv.util.Util;

public class CadProduto extends Activity {
	ProdutoDAO pdao;
	CategoriaDAO cdao;
	Produto produto;
	ArrayAdapter<Categoria> categorias;
	Spinner categoria;
	EditText descricao, estoque, valor;
	String mopcao;
	ImageButton[] opcao = new ImageButton[6];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cad_produto);

		descricao = (EditText)    findViewById(R.id.prd_eddescricao);
		estoque   = (EditText)    findViewById(R.id.prd_edestoque);
		valor     = (EditText)    findViewById(R.id.prd_edvalor);
		categoria = (Spinner)     findViewById(R.id.prd_spcategoria);
		opcao[0]  = (ImageButton) findViewById(R.id.prd_incluir);
		opcao[1]  = (ImageButton) findViewById(R.id.prd_alterar);
		opcao[2]  = (ImageButton) findViewById(R.id.prd_pesquisar);
		opcao[3]  = (ImageButton) findViewById(R.id.prd_confirmar);
		opcao[4]  = (ImageButton) findViewById(R.id.prd_cancelar);
		opcao[5]  = (ImageButton) findViewById(R.id.prd_voltar);
		produto = new Produto();
		pdao = new ProdutoDAO(getApplicationContext());
		cdao = new CategoriaDAO(getApplicationContext());
		categorias = new ArrayAdapter<Categoria>(getApplicationContext(),
				android.R.layout.simple_spinner_item, cdao.listarTodos());
		categoria.setAdapter(categorias);
		habilitaDesabilitaEditText(false);
		habilitaDesabilitaMenu(true, true, true, false, false, true);
	}

	public void Salvar() {

		Log.d("DB4O", "Tentando salvar");
		try {
			produto.setDescricao(descricao.getText().toString());
			produto.setEstoque(Integer.parseInt(estoque.getText().toString()));
			produto.setValor(Double.parseDouble(valor.getText().toString()));
			produto.setCategoria((Categoria) categoria.getSelectedItem());
			pdao.salvar(produto);
			Util.toast(this,
					"Produto " + produto.getDescricao() + " salvo com sucesso!")
					.show();
			habilitaDesabilitaMenu(true, true, true, false, false, true);
			habilitaDesabilitaEditText(false);
		} catch (Exception e) {
			Log.d("DB4O", "Erro ao salvar" + e);
			Util.toast(this, "Erro ao salvar produto!").show();
		}
	}

	public void executaOpcao(View v) {
		switch (v.getId()) {
		case R.id.prd_incluir:
			mopcao = "I";
			habilitaDesabilitaEditText(true);
			habilitaDesabilitaMenu(false, false, false, true, true, false);
			descricao.requestFocus();
			break;
		case R.id.prd_alterar:
			mopcao = "A";
			habilitaDesabilitaMenu(false, false, false, true, true, false);
			habilitaDesabilitaEditText(true);
			break;
		case R.id.prd_pesquisar:
			mopcao = "P";
			habilitaDesabilitaMenu(false, true, true, false, false, true);
			startActivityForResult(new Intent(this, ListaProduto.class), 1);
			break;
		case R.id.prd_confirmar:
			if ("I".equals(mopcao)) {
				Log.d("CV", "Entrou no I");
				if (Util.validaCampo(this, descricao, "Nome")) {
					produto = new Produto();
					Salvar();
				}
			} else if ("A".equals(mopcao)) {
				if (Util.validaCampo(this, descricao, "Nome")) {
					Salvar();
				}
			}

			break;
		case R.id.prd_cancelar:
			if ("I".equals(mopcao)) {
				descricao.setText("");
				estoque.setText("");
				valor.setText("");
				categoria.setId(-1);
			}
			habilitaDesabilitaEditText(false);
			habilitaDesabilitaMenu(true, true, true, false, false, true);
			break;
		case R.id.prd_voltar:
			finish();
			break;
		}
	}

	public void habilitaDesabilitaEditText(Boolean estado) {
		Util.setEstado(descricao, estado);
		Util.setEstado(estoque, estado);
		Util.setEstado(valor, estado);
		categoria.setEnabled(estado);
		if (!estado) {
			descricao.setText("");
			estoque.setText("");
			valor.setText("");
			categoria.setId(-1);
		}
	}

	public void habilitaDesabilitaMenu(Boolean incluir, Boolean alterar,
			Boolean pesquisar, Boolean confirmar, Boolean cancelar,
			Boolean voltar) {
		opcao[0].setEnabled(incluir); // incluir
		opcao[1].setEnabled(alterar); // alterar
		opcao[2].setEnabled(pesquisar); // pesquisar
		opcao[3].setEnabled(confirmar); // confirmar
		opcao[4].setEnabled(cancelar); // cancelar
		opcao[5].setEnabled(voltar); // voltar
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == ListaProduto.CONTEXTMENUITEM_EDIT) {
			mopcao = "A";
			Bundle b = data.getExtras();
			habilitaDesabilitaEditText(true);
			habilitaDesabilitaMenu(false, false, false, true, true, true);
			produto.setDescricao((b.getString("descricao")));
			produto.setEstoque(b.getInt("estoque"));
			produto.setValor(b.getDouble("valor"));
			pdao.abrir(produto);
			descricao.setText(produto.getDescricao());
			estoque.setText(Integer.toString(produto.getEstoque()));
			valor.setText(Double.toString(produto.getValor()));
			categoria.setSelection(categorias.getPosition(produto
					.getCategoria()));
			descricao.requestFocus();
		}
		else{
    		habilitaDesabilitaMenu(true, true, true, false, false, true);
    	}
	}
}
