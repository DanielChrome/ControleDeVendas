package br.edu.fasa.cv.presentation;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.ProdutoDAO;
import br.edu.fasa.cv.domainmodel.Produto;
import br.edu.fasa.cv.util.Util;

public class ListaProduto extends Activity implements OnCreateContextMenuListener{
	
	//Opções do menu de contexto
	public static final int CONTEXTMENUITEM_EDIT = 101;
	public static final int CONTEXTMENUITEM_DELETE = 102;
	public static final int REQUEST_NEW = 100;
	public static final int REQUEST_UPDATE = 101;
	ProdutoDAO pdao;
	Produto produto;
	private ListView listView;
    private AdapterProduto adapterListView;
    private List<Produto> itens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //carrega o layout onde contem o ListView
        setContentView(R.layout.lista);
        pdao = new ProdutoDAO(getApplicationContext());
        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.lista);
        //Define o Listener quando alguem clicar no item.
        registerForContextMenu(listView);
        itens = pdao.listarTodos();
        createListView();
    }

    private void createListView() {
        //Criamos nossa lista que preenchera o ListView      
        //Cria o adapter
    	adapterListView = new AdapterProduto(this, itens);
        //Define o Adapter
        listView.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para rolagem.
        listView.setCacheColorHint(Color.TRANSPARENT);
    }

    
    @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (v.getId() == listView.getId()) {
			menu.add(0, CONTEXTMENUITEM_EDIT, 1, R.string.ctx_edit);
			menu.add(0, CONTEXTMENUITEM_DELETE, 2, R.string.ctx_delet);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		super.onContextItemSelected(item);

		AdapterView.AdapterContextMenuInfo info
				= (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		
		final Produto produto = adapterListView.getItem(info.position);

		if (item.getItemId() == CONTEXTMENUITEM_EDIT) {
			
			Bundle b = new Bundle();
			Intent i = new Intent();
			b.putString("descricao", produto.getDescricao());
			b.putInt("estoque", produto.getEstoque());
			b.putDouble("valor", produto.getValor());
			i.putExtras(b);
			setResult(CONTEXTMENUITEM_EDIT,i);
			finish();
				
		} else if (item.getItemId() == CONTEXTMENUITEM_DELETE) {

			// Ouvinte de clique para confirmação de exclusão
			OnClickListener positiveListener = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					pdao.deletar(produto);
					dialog.dismiss();
					Util.toast(getApplicationContext(), "Produto excluido com sucesso!");
					itens = pdao.listarTodos();
					createListView();
				}
			};

			// Ouvinte de clique para cancelamento de exclusão
			OnClickListener negativeListener = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

				}
			};
			
			// this --> getContextApplication não funciona!
			// O diálogo deve estar vinculado a esta (this) activity
			AlertDialog dialog = Util.createDialog(this,
					R.string.delete_title, R.string.delete_message,
					R.string.sim, positiveListener,
					R.string.nao, negativeListener);
		
			Log.d(this.getClass().getName(), "AlertDialog criado!");
			dialog.show();
		}
		return true;
	}
}
