package br.edu.fasa.cv.domainmodel;

public class VendaProduto {
	private Venda venda;
	private Produto produto;
	private int quantidade;
	
	public VendaProduto(){
		
	}
	
	public VendaProduto(Venda venda, Produto produto, int quantidade) {
		super();
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getTotal() {
		return produto.getValor()*quantidade;
	}
	
}
