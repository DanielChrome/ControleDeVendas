package br.edu.fasa.cv.dataaccess;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import br.edu.fasa.cv.domainmodel.Venda;
import br.edu.fasa.cv.domainmodel.VendaProduto;

import com.db4o.ObjectSet;

public class VendaProdutoDAO extends DAOGenerico<VendaProduto>{
	private VendaProduto p;
	public VendaProdutoDAO(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	public List<VendaProduto> buscaPorVenda(Venda venda){
		p = new VendaProduto();
		p.setVenda(venda);  
		return db().queryByExample(p);
	}
	
	public VendaProduto abrir(VendaProduto cat){
		VendaProduto p = new VendaProduto(); 
		ObjectSet<VendaProduto> result = db().queryByExample(cat);
		if (result.hasNext()){
			p = result.next();
		}
		return p;
	}
	
	public List<VendaProduto> listarTodos(){
		return db().queryByExample(VendaProduto.class);
	}
	
	public Double getTotalVenda(Venda v){
		Double total = 0D;	
		List<VendaProduto> l = buscaPorVenda(v);
		Iterator<VendaProduto> i = l.iterator();
		while(i.hasNext()){
			total = total + i.next().getTotal();
		}
		return total;
	}
	
}
