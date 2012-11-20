package br.edu.fasa.cv.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.ClienteDAO;
import br.edu.fasa.cv.domainmodel.Cliente;

public class CadCliente extends Activity {
	Cliente cliente;
	ClienteDAO cdao;
	EditText nome,endereco,telefone;
	ImageButton salvar,cancelar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrocliente);
        nome  = (EditText)findViewById(R.id.ednome);
        endereco = (EditText) findViewById(R.id.edendereco);
        telefone = (EditText) findViewById(R.id.edtelefone);
        salvar   = (ImageButton)findViewById(R.id.btsalvar);
        cancelar = (ImageButton)findViewById(R.id.btcancelar);
        cliente = new Cliente();
        cdao = new ClienteDAO(getApplicationContext());
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cadastro, menu);
        return true;
    }
    
    public void Salvar(View v){
    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    	Log.d("DB4O", "Tentando salvar");
    	try{
    		cliente.setNome(nome.getText().toString());
    		cliente.setEndereco(endereco.getText().toString());
    		cliente.setTelefone(telefone.getText().toString());
    		cdao.salvar(cliente);    		
    		alertDialog.setTitle("Cadastro de Clientes");
    		alertDialog.setMessage("Cliente "+ cliente.getNome() + "salvo com sucesso");
    		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    		   public void onClick(DialogInterface dialog, int which) {
    		      finish();
    		   }
    		});
    		alertDialog.show();
    	}catch(Exception e){    		
    		Log.d("DB4O", "Erro ao salvo"+e);
    		alertDialog.setTitle("Erro ao salvar");
    		alertDialog.setMessage("Houve um erro ao salvar o cliente.");
    		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    		   public void onClick(DialogInterface dialog, int which) {
    		      finish();
    		   }
    		});
    		alertDialog.show();
    	}
    }
    
    public void Cancelar(View v){
    	finish();
    }
}
