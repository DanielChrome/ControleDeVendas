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
import br.edu.fasa.cv.dataaccess.CategoriaDAO;
import br.edu.fasa.cv.domainmodel.Categoria;
import br.edu.fasa.cv.util.Util;

public class ListaCategoria extends Activity implements OnCreateContextMenuListener{
	
	//Opções do menu de contexto
	public static final int CONTEXTMENUITEM_EDIT = 101;
	public static final int CONTEXTMENUITEM_DELETE = 102;
	public static final int REQUEST_NEW = 100;
	public static final int REQUEST_UPDATE = 101;
	CategoriaDAO cdao;
	Categoria cateogiroa;
	private ListView listView;
    private AdapterCategoria adapterListView;
    private List<Categoria> itens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //carrega o layout onde contem o ListView
        setContentView(R.layout.lista);
        cdao = new CategoriaDAO(getApplicationContext());
        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.lista);
        //Define o Listener quando alguem clicar no item.
        registerForContextMenu(listView);
        itens = cdao.listarTodosOrdemDescricao();
        createListView();
    }

    private void createListView() {
        //Criamos nossa lista que preenchera o ListView      
        //Cria o adapter
    	adapterListView = new AdapterCategoria(this, itens);
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
		
		final Categoria categoria = adapterListView.getItem(info.position);

		if (item.getItemId() == CONTEXTMENUITEM_EDIT) {
			
			Bundle b = new Bundle();
			Intent i = new Intent();
			b.putString("descricao", categoria.getDescricao());
			b.putBoolean("subcategoria", categoria.getSubcategoria());
			i.putExtras(b);
			setResult(CONTEXTMENUITEM_EDIT,i);
			finish();
				
		} else if (item.getItemId() == CONTEXTMENUITEM_DELETE) {

			// Ouvinte de clique para confirmação de exclusão
			OnClickListener positiveListener = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					cdao.deletar(categoria);
					dialog.dismiss();
					Util.toast(getApplicationContext(), "Categoria excluida com sucesso!");
					itens = cdao.listarTodos();
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
