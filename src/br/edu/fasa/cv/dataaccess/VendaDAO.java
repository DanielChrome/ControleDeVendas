package br.edu.fasa.cv.dataaccess;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.Log;
import br.edu.fasa.cv.domainmodel.Venda;

import com.db4o.ObjectSet;

public class VendaDAO extends DAOGenerico<Venda>{
	private Venda v;
	public VendaDAO(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public List<Venda> buscaPorData(Date data){
		v = new Venda();
		v.setDataVenda(data);
		ObjectSet<Venda> result = db().queryByExample(v);
		return result;
	}
	
	public List<Venda> listarTodos(){
		return db().queryByExample(Venda.class);
	}
	
	public Venda abrir(Venda venda){
		Venda v = new Venda(); 
		ObjectSet<Venda> result = db().queryByExample(venda);
		if (result.hasNext()){
			v = result.next();
		}
		return v;
	}
	
	public long proximoCodigo(){
		long id = 0;
		Log.d("CV","ID: "+id);
		if(!listarTodos().isEmpty()){
			
			Iterator<Venda> i = listarTodos().iterator();			
			while(i.hasNext()){
				id = i.next().getId();
				Log.d("CV","Has Next ID: "+id);
			}
			return id+1;
		}else{
			Log.d("CV","No ID: "+id);
			return 1L;
		}	
	}
}
