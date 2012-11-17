package br.du.fasa.CV.DomainModel;

public class Categoria {
	private String descricao;
	private Categoria categoriaPai;
	
	public Categoria(String descricao, Categoria categoriaPai) {
		super();
		this.descricao = descricao;
		this.categoriaPai = categoriaPai;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
	
}
