package br.edu.fasa.cv.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import br.edu.fasa.cv.R;
import br.edu.fasa.cv.dataaccess.CategoriaDAO;
import br.edu.fasa.cv.domainmodel.Categoria;
import br.edu.fasa.cv.util.Util;

public class CadCategoria extends Activity {
	CategoriaDAO cdao;
	Categoria categoria;
	ArrayAdapter<Categoria> catpai;
	Spinner categoriapai;
	CheckBox subcategoria;
	EditText descricao;
	String mopcao;
	ImageButton[] opcao = new ImageButton[6];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_categoria);
        
        descricao    = (EditText)     findViewById(R.id.cat_etdescricao);
        categoriapai = (Spinner)      findViewById(R.id.cat_spcategoriapai);
        subcategoria = (CheckBox)     findViewById(R.id.cat_cksubcategoria);
        opcao[0]     = (ImageButton)  findViewById(R.id.cat_incluir);
        opcao[1]     = (ImageButton)  findViewById(R.id.cat_alterar);
        opcao[2]     = (ImageButton)  findViewById(R.id.cat_pesquisar);
        opcao[3]     = (ImageButton)  findViewById(R.id.cat_confirmar);
        opcao[4]     = (ImageButton)  findViewById(R.id.cat_cancelar);
        opcao[5]     = (ImageButton)  findViewById(R.id.cat_voltar);
        categoria = new Categoria();
        cdao = new CategoriaDAO(getApplicationContext());
        catpai = new ArrayAdapter<Categoria>(getApplicationContext(), 
				   android.R.layout.simple_spinner_dropdown_item,
				   cdao.listaCategoriaPai());
        categoriapai.setAdapter(catpai);
        categoriapai.setSelection(-1);
        habilitaDesabilitaEditText(false);
        habilitaDesabilitaMenu(true, true, true, false, false, true);
        
        subcategoria.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
		    	if (subcategoria.isChecked()){
		    		categoriapai.setEnabled(true);
		    	}
		    	else{
		    		categoriapai.setEnabled(false);
		    	}
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cadastro, menu);
        return true;
    }
    
    public void Salvar(){
    	
    	Log.d("DB4O", "Tentando salvar");
    	try{
    		categoria.setDescricao(descricao.getText().toString());
    		categoria.setSubcategoria(subcategoria.isChecked());
    		if (categoria.getSubcategoria()){
    			categoria.setCategoriaPai((Categoria)categoriapai.getSelectedItem());
    		}else{
    			categoria.setCategoriaPai(null);
    		}   			
    		cdao.salvar(categoria);    		
    		Util.toast(this, "Categoria "+ categoria.getDescricao()+ " salva com sucesso!").show();
    		habilitaDesabilitaMenu(true, true, true, false, false, true);
    		habilitaDesabilitaEditText(false);
    	}catch(Exception e){    		
    		Log.d("DB4O", "Erro ao salvar"+e);
    		Util.toast(this, "Erro ao salvar categoria!").show();
    	}
    }
    
    public void executaOpcao(View v){
    	switch(v.getId()){
    		case R.id.cat_incluir:
    			mopcao = "I";
    			habilitaDesabilitaEditText(true);
    			habilitaDesabilitaMenu(false, false, false, true, true, false);
    			descricao.requestFocus();
    			break;
    		case R.id.cat_alterar:
    			mopcao = "A";
    			habilitaDesabilitaMenu(false, false, false, true, true, false);
    			habilitaDesabilitaEditText(true);
    			break;
    		case R.id.cat_pesquisar:
    			mopcao = "P";
    			habilitaDesabilitaMenu(false, true, true, false, false, true);
    			startActivityForResult(new Intent(this,ListaCategoria.class),1);
    			break;
    		case R.id.cat_confirmar:
    			if ("I".equals(mopcao)){
    				Log.d("CV", "Entrou no I");
    				if(Util.validaCampo(this, descricao, "Nome")){
    					Salvar();
    				}   				
    			}else if("A".equals(mopcao)){
    				if(Util.validaCampo(this, descricao, "Nome")){
    					Salvar();
    				} 
    			}
    					
    			break;
    		case R.id.cat_cancelar:
    			if("I".equals(mopcao)){
    				descricao.setText("");
    				subcategoria.setChecked(false);
    				categoriapai.setSelection(-1);
    			}
    			habilitaDesabilitaEditText(false);
    	        habilitaDesabilitaMenu(true, true, true, false, false, true);
    			break;
    		case R.id.cat_voltar:
    			finish();
    			break;	
    	}
    }
    
    public void habilitaDesabilitaEditText(Boolean estado){
    	Util.setEstado(descricao,estado);
        subcategoria.setEnabled(estado);
        categoriapai.setEnabled(estado);
        if(!estado){
        	descricao.setText("");
			subcategoria.setChecked(false);
			categoriapai.setId(-1);
			catpai = new ArrayAdapter<Categoria>(getApplicationContext(), 
					   android.R.layout.simple_spinner_item,
					   cdao.listaCategoriaPai());
	        categoriapai.setAdapter(catpai);
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
    	if (resultCode==ListaCategoria.CONTEXTMENUITEM_EDIT){
    		mopcao = "A";
    		Bundle b = data.getExtras();
    		habilitaDesabilitaEditText(true);
    		habilitaDesabilitaMenu(false, false, false, true, true, true);
    		categoria.setDescricao((b.getString("descricao")));
    		categoria.setSubcategoria(b.getBoolean("subcategoria"));
    		categoria = cdao.abrir(categoria);
    		descricao.setText(categoria.getDescricao());
    		subcategoria.setChecked(categoria.getSubcategoria());
    		if (categoria.getSubcategoria()){
    			categoriapai.setSelection(catpai.getPosition(categoria.getCategoriaPai()));	
    		}
    		descricao.requestFocus();
    	}
    }
}
