package br.edu.fasa.cv.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import br.edu.fasa.cv.R;

public class Principal extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }
    
    public void executaOpcao(View v){
    	Intent i = new Intent(this,Principal.class);;
    	switch(v.getId()){
    		case R.id.cadvenda:
    			//i = new Intent(this,CadCliente.class);
    			break;
    		case R.id.cadproduto:
    			i = new Intent(this,CadProduto.class);
    			break;
    		case R.id.cadcliente:
    			i = new Intent(this,CadCliente.class);
    			break;
    		case R.id.cadcategoria:
    			i = new Intent(this,CadCategoria.class);
    			break;
    		case R.id.relatorios:
    			//i = new Intent(this,CadCliente.class);
    			break;
    		case R.id.configuracoes:
    			//i = new Intent(this,CadCliente.class);
    			break;
    		default:
    			i = new Intent(this,Principal.class);
    	}
    	startActivity(i);
    }
}
