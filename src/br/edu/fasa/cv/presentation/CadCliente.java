package br.edu.fasa.cv.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.ClienteDAO;
import br.edu.fasa.cv.domainmodel.Cliente;
import br.edu.fasa.cv.util.Util;

public class CadCliente extends Activity {
	Cliente cliente;
	ClienteDAO cdao;
	EditText nome,endereco,telefone;
	String mopcao;
	ImageButton[] opcao = new ImageButton[6];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_cliente);
        
        nome     = (EditText)findViewById(R.id.cli_nome);
        endereco = (EditText) findViewById(R.id.cli_edendereco);
        telefone = (EditText) findViewById(R.id.cli_edtelefone);
        opcao[0] = (ImageButton) findViewById(R.id.cli_incluir);
        opcao[1] = (ImageButton) findViewById(R.id.cli_alterar);
        opcao[2] = (ImageButton) findViewById(R.id.cli_pesquisar);
        opcao[3] = (ImageButton) findViewById(R.id.cli_confirmar);
        opcao[4] = (ImageButton) findViewById(R.id.cli_cancelar);
        opcao[5] = (ImageButton) findViewById(R.id.cli_voltar);
        cliente = new Cliente();
        cdao = new ClienteDAO(getApplicationContext());
        habilitaDesabilitaEditText(false);
        habilitaDesabilitaMenu(true, true, true, false, false, true);                 
    }

    public void Salvar(){
    	
    	Log.d("DB4O", "Tentando salvar");
    	try{
    		cliente.setNome(nome.getText().toString());
    		cliente.setEndereco(endereco.getText().toString());
    		cliente.setTelefone(telefone.getText().toString());
    		cdao.salvar(cliente);    		
    		Util.toast(this, "Cliente "+ cliente.getNome()+ " salvo com sucesso!").show();
    		habilitaDesabilitaMenu(true, true, true, false, false, true);
    		habilitaDesabilitaEditText(false);
    	}catch(Exception e){    		
    		Log.d("DB4O", "Erro ao salvar"+e);
    		Util.toast(this, "Erro ao salvar cliente!").show();
    	}
    }
    
    public void executaOpcao(View v){
    	switch(v.getId()){
    		case R.id.cli_incluir:
    			mopcao = "I";
    			habilitaDesabilitaEditText(true);
    			habilitaDesabilitaMenu(false, false, false, true, true, false);
    			nome.requestFocus();
    			break;
    		case R.id.cli_alterar:
    			mopcao = "A";
    			habilitaDesabilitaMenu(false, false, false, true, true, false);
    			habilitaDesabilitaEditText(true);
    			break;
    		case R.id.cli_pesquisar:
    			mopcao = "P";
    			habilitaDesabilitaMenu(false, true, true, false, false, true);
    			startActivityForResult(new Intent(this,ListaCliente.class),1);
    			break;
    		case R.id.cli_confirmar:
    			if ("I".equals(mopcao)){
    				Log.d("CV", "Entrou no I");
    				if(Util.validaCampo(this, nome, "Nome")){
    					cliente = new Cliente();
    					Salvar();
    				}   				
    			}else if("A".equals(mopcao)){
    				if(Util.validaCampo(this, nome, "Nome")){
    					Salvar();
    				} 
    			}
    					
    			break;
    		case R.id.cli_cancelar:
    			if("I".equals(mopcao)){
    				nome.setText("");
    				endereco.setText("");
    				telefone.setText("");
    			}
    			habilitaDesabilitaEditText(false);
    	        habilitaDesabilitaMenu(true, true, true, false, false, true);
    			break;
    		case R.id.cli_voltar:
    			finish();
    			break;	
    	}
    }
    
    public void habilitaDesabilitaEditText(Boolean estado){
    	Util.setEstado(endereco,estado);
        Util.setEstado(telefone,estado);
        Util.setEstado(nome,estado);
        if(!estado){
        	endereco.setText("");
        	nome.setText("");
        	telefone.setText("");
        }
    }
    
    public void habilitaDesabilitaMenu(Boolean incluir,Boolean alterar,Boolean pesquisar,
    								   Boolean confirmar,Boolean cancelar,Boolean voltar){
        opcao[0].setEnabled(incluir); //incluir
		opcao[1].setEnabled(alterar); //alterar
		opcao[2].setEnabled(pesquisar); //pesquisar
		opcao[3].setEnabled(confirmar);  //confirmar 
		opcao[4].setEnabled(cancelar);  //cancelar
		opcao[5].setEnabled(voltar); //voltar
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode==ListaCliente.CONTEXTMENUITEM_EDIT){
    		mopcao = "A";
    		Bundle b = data.getExtras();
    		habilitaDesabilitaEditText(true);
    		habilitaDesabilitaMenu(false, false, false, true, true, true);
    		cliente.setNome(b.getString("nome"));
    		cliente.setEndereco(b.getString("endereco"));
    		cliente.setTelefone(b.getString("telefone"));
    		nome.setText(cliente.getNome());
    		endereco.setText(cliente.getEndereco());
    		telefone.setText(cliente.getTelefone());
    		cliente = cdao.abrir(cliente);
    		nome.requestFocus();
    	}
    	else{
    		habilitaDesabilitaMenu(true, true, true, false, false, true);
    	}
    }
}
