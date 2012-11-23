package br.edu.fasa.cv.dataaccess;

import java.util.List;

import android.content.Context;
import br.edu.fasa.cv.domainmodel.Produto;

import com.db4o.ObjectSet;

public class ProdutoDAO extends DAOGenerico<Produto>{
	private Produto p;
	public ProdutoDAO(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public List<Produto> buscaPorNome(String descricao){
		List<Produto> lista;
		p = new Produto();
		p.setDescricao(descricao);
		ObjectSet<Produto> result = db().queryByExample(p);
		lista = result;
		return lista;
	}
	
	public Produto abrir(Produto cat){
		Produto p = new Produto(); 
		ObjectSet<Produto> result = db().queryByExample(cat);
		if (result.hasNext()){
			p = result.next();
		}
		return p;
	}
	
	public List<Produto> listarTodos(){
		return db().queryByExample(Produto.class);
	}
	
}
