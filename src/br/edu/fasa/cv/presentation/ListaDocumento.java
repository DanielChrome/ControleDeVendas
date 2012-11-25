package br.edu.fasa.cv.presentation;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import br.edu.fasa.cv.dataaccess.DocumentoDAO;
import br.edu.fasa.cv.domainmodel.Documento;
import br.edu.fasa.cv.util.Util;

public class ListaDocumento extends Activity implements
		OnCreateContextMenuListener {

	// Opções do menu de contexto
	public static final int CONTEXTMENUITEM_FAT = 101;
	public static final int REQUEST_NEW = 100;
	public static final int REQUEST_UPDATE = 101;
	DocumentoDAO ddao;
	Documento documento,docselect;
	private ListView listView;
	private AdapterDocumento adapterListView;
	private List<Documento> itens;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// carrega o layout onde contem o ListView
		setContentView(R.layout.lista);
		ddao = new DocumentoDAO(getApplicationContext());
		// Pega a referencia do ListView
		listView = (ListView) findViewById(R.id.lista);
		// Define o Listener quando alguem clicar no item.
		registerForContextMenu(listView);
		itens = ddao.listaAVencer();
		createListView();
	}

	private void createListView() {
		// Criamos nossa lista que preenchera o ListView
		// Cria o adapter
		adapterListView = new AdapterDocumento(this, itens);
		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para rolagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (v.getId() == listView.getId()) {
			menu.add(0, CONTEXTMENUITEM_FAT, 1, R.string.faturar);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		super.onContextItemSelected(item);

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		docselect = adapterListView.getItem(info.position);

		if (item.getItemId() == CONTEXTMENUITEM_FAT) {
			// Ouvinte de clique para confirmação de exclusão
			OnClickListener positiveListener = new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					docselect = ddao.abrir(docselect);
					docselect.setFaturado(true);
					ddao.salvar(docselect);
					dialog.dismiss();
					Util.toast(getApplicationContext(),
							"Documento faturado com sucesso!").show();
					itens = ddao.listaAVencer();
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
			AlertDialog dialog = Util.createDialog(this, R.string.fatura_title,
					R.string.fatura_message, R.string.sim, positiveListener,
					R.string.nao, negativeListener);

			Log.d(this.getClass().getName(), "AlertDialog criado!");
			dialog.show();

		}
		return true;
	}
}
