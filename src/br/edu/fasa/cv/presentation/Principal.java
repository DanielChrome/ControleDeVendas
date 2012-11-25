package br.edu.fasa.cv.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ImageButton;
import br.edu.fasa.cv.R;

public class Principal extends Activity implements
    OnCreateContextMenuListener{
    ImageButton[] menu = new ImageButton[6];
	public static final int CONTEXTMENUITEM_NEW = 100;
	public static final int CONTEXTMENUITEM_LIST = 101;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        menu[0] = (ImageButton) findViewById(R.id.cadvenda);
        menu[1] = (ImageButton) findViewById(R.id.cadproduto);
        menu[2] = (ImageButton) findViewById(R.id.cadcliente);
        menu[3] = (ImageButton) findViewById(R.id.cadcategoria);
        menu[4] = (ImageButton) findViewById(R.id.relatorios);
        menu[5] = (ImageButton) findViewById(R.id.receber);
        
        menu[4].setEnabled(false);
    }
    
    public void executaOpcao(View v){
    	Intent i = new Intent(this,Principal.class);;
    	switch(v.getId()){
    		case R.id.cadvenda:
    			i = new Intent(this,CadVenda.class);
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
    		case R.id.receber:
    			i = new Intent(this,ListaDocumento.class);
    			break;
    		default:
    			i = new Intent(this,Principal.class);
    	}
    	startActivity(i);
    }
    
    
}
