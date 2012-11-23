package br.edu.fasa.cv.dataaccess;

import java.util.List;

import android.content.Context;
import br.edu.fasa.cv.domainmodel.Cliente;

import com.db4o.ObjectSet;

public class ClienteDAO extends DAOGenerico<Cliente>{
	private Cliente c;
	public ClienteDAO(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public List<Cliente> buscaPorNome(String nome){
		c = new Cliente();
		c.setNome(nome);
		ObjectSet<Cliente> result = db().queryByExample(c);
		return result;
	}
	
	public List<Cliente> listarTodos(){
		return db().queryByExample(Cliente.class);
	}
	
	public Cliente abrir(Cliente cli){
		Cliente c = new Cliente(); 
		ObjectSet<Cliente> result = db().queryByExample(cli);
		if (result.hasNext()){
			c = result.next();
		}
		return c;
	}
}
