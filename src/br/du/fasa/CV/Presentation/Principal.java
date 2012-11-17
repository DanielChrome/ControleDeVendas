package br.du.fasa.CV.Presentation;

import br.edu.fasa.CV.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Principal extends Activity {
	Button cadastrar;
	Button listar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        cadastrar = (Button)findViewById(R.id.btcadastrar);
        listar = (Button)findViewById(R.id.btlistar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }
    
    public void abreCadastro(View v){
    	Intent i = new Intent(getApplicationContext(),CadCliente.class);
    	startActivity(i);
    }
    
    public void abreLista(View v){
    	Intent i = new Intent(getApplicationContext(),Lista.class);
    	startActivity(i);
    }
}
