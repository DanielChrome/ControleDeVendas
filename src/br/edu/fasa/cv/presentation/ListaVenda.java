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
import br.edu.fasa.cv.dataaccess.VendaDAO;
import br.edu.fasa.cv.domainmodel.Venda;
import br.edu.fasa.cv.util.Util;

public class ListaVenda extends Activity implements OnCreateContextMenuListener{
	
	//Op��es do menu de contexto
	public static final int CONTEXTMENUITEM_EDIT = 101;
	public static final int CONTEXTMENUITEM_DELETE = 102;
	public static final int REQUEST_NEW = 100;
	public static final int REQUEST_UPDATE = 101;
	VendaDAO vdao;
	Venda venda;
	private ListView listView;
    private AdapterVenda adapterListView;
    private List<Venda> itens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //carrega o layout onde contem o ListView
        setContentView(R.layout.lista);
        vdao = new VendaDAO(getApplicationContext());
        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.lista);
        //Define o Listener quando alguem clicar no item.
        registerForContextMenu(listView);
        itens = vdao.listarTodos();
        createListView();
    }

    private void createListView() {
        //Criamos nossa lista que preenchera o ListView      
        //Cria o adapter
    	adapterListView = new AdapterVenda(this, itens);
        //Define o Adapter
        listView.setAdapter(adapterListView);
        //Cor quando a lista � selecionada para rolagem.
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
		
		final Venda venda = adapterListView.getItem(info.position);

		if (item.getItemId() == CONTEXTMENUITEM_EDIT) {
			
			Bundle b = new Bundle();
			Intent i = new Intent();
			b.putLong("ID", venda.getId());
			i.putExtras(b);
			setResult(CONTEXTMENUITEM_EDIT,i);
			finish();
				
		} else if (item.getItemId() == CONTEXTMENUITEM_DELETE) {

			// Ouvinte de clique para confirma��o de exclus�o
			OnClickListener positiveListener = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					vdao.deletar(venda);
					dialog.dismiss();
					Util.toast(getApplicationContext(), "Produto excluido com sucesso!");
					itens = vdao.listarTodos();
					createListView();
				}
			};

			// Ouvinte de clique para cancelamento de exclus�o
			OnClickListener negativeListener = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

				}
			};
			
			// this --> getContextApplication n�o funciona!
			// O di�logo deve estar vinculado a esta (this) activity
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
