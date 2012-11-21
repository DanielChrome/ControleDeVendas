package br.edu.fasa.cv.domainmodel;

public class Categoria {
	private String descricao;
	private Boolean subcategoria;
	private Categoria categoriaPai;
	
	public Categoria(String descricao, Boolean subcategoria,Categoria categoriaPai) {
		super();
		this.descricao = descricao;
		this.categoriaPai = categoriaPai;
		this.subcategoria = subcategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Boolean subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
	
}
