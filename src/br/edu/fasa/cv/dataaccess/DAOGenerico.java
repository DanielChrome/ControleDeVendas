package br.edu.fasa.cv.dataaccess;

import android.content.Context;

public class DAOGenerico<T> extends DBControler{
	
	public DAOGenerico(Context ctx) {
		super(ctx);
	}

	public void salvar(T obj){
		db().store(obj);
		db().commit();
	}
	
	public void deletar(T obj){
		db().delete(obj);
		db().commit();
	}
}
