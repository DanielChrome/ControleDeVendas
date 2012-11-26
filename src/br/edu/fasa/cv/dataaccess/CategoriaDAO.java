package br.edu.fasa.cv.dataaccess;

import java.util.List;

import android.content.Context;
import br.edu.fasa.cv.domainmodel.Categoria;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class CategoriaDAO extends DAOGenerico<Categoria>{
	private Categoria c;
	public CategoriaDAO(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public List<Categoria> buscaPorNome(String descricao){
		c = new Categoria();
		c.setDescricao(descricao);
		ObjectSet<Categoria> result = db().queryByExample(c);
		return result;
	}
	
	public List<Categoria> listarTodosOrdemDescricao(){
		Query query=db().query();
		query.constrain(Categoria.class);
		query.descend("descricao").orderAscending();
		ObjectSet<Categoria> result=query.execute();
		return result;
	}
	
	public List<Categoria> listarTodos(){
		return db().queryByExample(Categoria.class);
	}
	
	public Categoria abrir(Categoria cat){
		Categoria c = new Categoria(); 
		ObjectSet<Categoria> result = db().queryByExample(cat);
		if (result.hasNext()){
			c = result.next();
		}
		return c;
	}
	
	public List<Categoria> listaCategoriaPai(){
		Categoria c = new Categoria(null,false,null);
		return db().queryByExample(c);
	}
}
