package br.edu.fasa.cv.domainmodel;

public class Produto {
	private String descricao;
	private int estoque;
	private double valor;
	private Categoria categoria;
	
	public Produto(){
		
	}
	
	public Produto(String descricao, int estoque, Double valor,
			Categoria categoria) {
		super();
		this.descricao = descricao;
		this.estoque = estoque;
		this.valor = valor;
		this.categoria = categoria;
	}



	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
	
}
