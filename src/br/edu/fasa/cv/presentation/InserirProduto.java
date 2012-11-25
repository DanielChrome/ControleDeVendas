package br.edu.fasa.cv.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.ProdutoDAO;
import br.edu.fasa.cv.domainmodel.Produto;

public class InserirProduto extends Activity {
	public static final int MENU_CONFIRMA = 1;
	
	ProdutoDAO pdao;
	Produto produto;
	ArrayAdapter<Produto> produtos;
	Spinner spprodutos;
	EditText quantidade;
	TextView valor,estoque;
	String mopcao;
	ImageButton[] opcao = new ImageButton[3];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inserirproduto);

		quantidade = (EditText)    findViewById(R.id.iprd_edquantidade);
		estoque   = (TextView)    findViewById(R.id.iprd_txestoque);
		valor     = (TextView)    findViewById(R.id.iprd_txvalor);
		spprodutos = (Spinner)     findViewById(R.id.iprd_spproduto);
		opcao[0]  = (ImageButton) findViewById(R.id.iprd_confirmar);
		opcao[1]  = (ImageButton) findViewById(R.id.iprd_cancelar);
		opcao[2]  = (ImageButton) findViewById(R.id.iprd_voltar);
		produto = new Produto();
		pdao = new ProdutoDAO(getApplicationContext());
		produtos = new ArrayAdapter<Produto>(getApplicationContext(),
				android.R.layout.simple_spinner_item, pdao.listarTodos());
		spprodutos.setAdapter(produtos);
		estoque.setText(Integer.toString(((Produto)spprodutos.getSelectedItem()).getEstoque()));
		valor.setText(Double.toString(((Produto)spprodutos.getSelectedItem()).getValor()));
		habilitaDesabilitaMenu(true, true, true);
		
		spprodutos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adp, View view,
					int position, long id) {
				estoque.setText(Integer.toString(((Produto)spprodutos.getSelectedItem()).getEstoque()));
				valor.setText(Double.toString(((Produto)spprodutos.getSelectedItem()).getValor()));	
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void executaOpcao(View v) {
		switch (v.getId()) {
		case R.id.iprd_confirmar:
			Bundle b = new Bundle();
			Intent i = new Intent();
			b.putString("descricao", ((Produto)spprodutos.getSelectedItem()).getDescricao());
			b.putDouble("valor", ((Produto)spprodutos.getSelectedItem()).getValor());
			b.putInt("estoque", ((Produto)spprodutos.getSelectedItem()).getEstoque());
			b.putInt("quantidade",Integer.parseInt(quantidade.getText().toString()));
			i.putExtras(b);
			setResult(MENU_CONFIRMA,i);
			finish();
			break;
		case R.id.iprd_cancelar:
			quantidade.setText("");
			estoque.setText("");
			valor.setText("");
			break;
		case R.id.iprd_voltar:
			finish();
			break;
		}
	}

	

	public void habilitaDesabilitaMenu(Boolean confirmar, Boolean cancelar,
			Boolean voltar) {
		opcao[0].setEnabled(confirmar); // confirmar
		opcao[1].setEnabled(cancelar); // cancelar
		opcao[2].setEnabled(voltar); // voltar
	}
}
