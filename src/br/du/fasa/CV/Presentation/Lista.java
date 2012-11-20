package br.du.fasa.CV.Presentation;

import java.util.List;

import br.du.fasa.CV.DomainModel.Cliente;
import br.du.fasa.CV.DataAccess.ClienteDAO;
import br.edu.fasa.CV.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Lista extends Activity implements OnItemClickListener{
	ClienteDAO cdao;
	Cliente cliente;
	private ListView listView;
    private AdapterListView adapterListView;
    private List<Cliente> itens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //carrega o layout onde contem o ListView
        setContentView(R.layout.lista);
        cdao = new ClienteDAO(getApplicationContext());
        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.listacliente);
        //Define o Listener quando alguem clicar no item.
        listView.setOnItemClickListener(this);
        itens = cdao.listarTodos();
        createListView();
    }

    private void createListView() {
        //Criamos nossa lista que preenchera o ListView      
        //Cria o adapter
        adapterListView = new AdapterListView(this, itens);

        //Define o Adapter
        listView.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        //Pega o item que foi selecionado.
        Cliente item = adapterListView.getItem(arg2);
        //Demostração
        Toast.makeText(this, "Você Clicou em: " + item.getNome() + "\n Telefone: "+item.getTelefone(), Toast.LENGTH_LONG).show();
    }
}
