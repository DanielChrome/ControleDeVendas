package br.du.fasa.CV.DomainModel;

import java.util.Date;
import java.util.List;

public class Venda {
	private Cliente cliente;
	private List<Produto> produtos;
	private Date data_venda;
	
	public Venda(){
		
	}

	public Venda(Cliente cliente, List<Produto> produtos, Date data_venda) {
		super();
		this.cliente = cliente;
		this.produtos = produtos;
		this.data_venda = data_venda;
	}



	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Date getData_venda() {
		return data_venda;
	}

	public void setData_venda(Date data_venda) {
		this.data_venda = data_venda;
	}
	
	/**
	 * Adiciona um item a lista de produtos.
	 * @param p Produto a ser adicionado
	 */
	public void addItem(Produto p){
		produtos.add(p);
	}
	
	/**
	 * retorna um item da lista pelo seu indice
	 * @param i indice do item na lista
	 * @return retorna um Produto da lista;
	 */
	public Produto getItem(int i){
		return produtos.get(i);
	}
	
	/**
	 * remove um item da lista pelo seu indice
	 * @param i indice do item a ser removido
	 */
	public void delItem(int i){
		produtos.remove(i);
	}
	
}
