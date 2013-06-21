package br.edu.fasa.cv.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ImageButton;
import android.widget.Toast;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.CategoriaDAO;
import br.edu.fasa.cv.dataaccess.ClienteDAO;
import br.edu.fasa.cv.dataaccess.DocumentoDAO;
import br.edu.fasa.cv.dataaccess.ProdutoDAO;
import br.edu.fasa.cv.dataaccess.VendaDAO;
import br.edu.fasa.cv.domainmodel.Categoria;
import br.edu.fasa.cv.domainmodel.Cliente;
import br.edu.fasa.cv.domainmodel.Documento;
import br.edu.fasa.cv.domainmodel.Produto;
import br.edu.fasa.cv.domainmodel.Venda;

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
        menu[4] = (ImageButton) findViewById(R.id.replicar);
        menu[5] = (ImageButton) findViewById(R.id.receber);
        
        //menu[4].setEnabled(false);
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
    		case R.id.replicar:
    			Replicacao r = new Replicacao();
    			r.ctx = getApplicationContext();
    			r.execute(0);
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
