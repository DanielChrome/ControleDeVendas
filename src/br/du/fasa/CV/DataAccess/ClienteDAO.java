package br.du.fasa.CV.DataAccess;

import java.util.List;

import android.content.Context;
import br.du.fasa.CV.DomainModel.Cliente;

import com.db4o.ObjectSet;

public class ClienteDAO extends DAOGenerico<Cliente>{
	private Cliente c;
	public ClienteDAO(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public Cliente buscaPorNome(String nome){
		c = new Cliente();
		c.setNome(nome);
		ObjectSet<Cliente> result = db().queryByExample(c);
		if(result.hasNext()){
			c = result.next();
		}
		return c;
	}
	
	public List<Cliente> listarTodos(){
		return db().queryByExample(Cliente.class);
	}
	
}
