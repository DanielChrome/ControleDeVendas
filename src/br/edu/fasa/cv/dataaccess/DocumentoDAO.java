package br.edu.fasa.cv.dataaccess;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.Log;
import br.edu.fasa.cv.domainmodel.Documento;

import com.db4o.ObjectSet;

public class DocumentoDAO extends DAOGenerico<Documento>{
	private Documento doc;
	public DocumentoDAO(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public List<Documento> buscaPorData(String data){
		doc = new Documento();
		doc.setDataVencimento(data);
		ObjectSet<Documento> result = db().queryByExample(doc);
		return result;
	}
	
	public List<Documento> listarTodos(){
		return db().queryByExample(Documento.class);
	}
	
	public Documento abrir(Documento doc){
		Documento d = new Documento(); 
		ObjectSet<Documento> result = db().queryByExample(doc);
		if (result.hasNext()){
			d = result.next();
		}
		return d;
	}
	
	public long proximoCodigo(){
		long id = 0;
		Log.d("CV","ID: "+id);
		if(!listarTodos().isEmpty()){
			
			Iterator<Documento> i = listarTodos().iterator();			
			while(i.hasNext()){
				id = i.next().getDocumento();
				Log.d("CV","Has Next ID: "+id);
			}
			return id+1;
		}else{
			Log.d("CV","No ID: "+id);
			return 1L;
		}	
	}
}
