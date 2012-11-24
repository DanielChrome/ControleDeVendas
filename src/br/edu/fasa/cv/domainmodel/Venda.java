package br.edu.fasa.cv.domainmodel;

public class Venda {
	private long id;
	private Cliente cliente;
	private String dataVenda;
	
	public Venda(){
		
	}

	public Venda(long id,Cliente cliente, String dataVenda) {
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

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
}
