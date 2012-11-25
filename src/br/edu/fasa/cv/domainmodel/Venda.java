package br.edu.fasa.cv.domainmodel;

import java.util.Date;

public class Venda {
	private long id;
	private Cliente cliente;
	private Date dataVenda;
	
	public Venda(){
		
	}

	public Venda(long id,Cliente cliente, Date dataVenda) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.dataVenda = dataVenda;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
}
