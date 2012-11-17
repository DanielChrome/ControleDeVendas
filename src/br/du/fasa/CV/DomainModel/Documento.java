package br.du.fasa.CV.DomainModel;

import java.util.Date;

public class Documento {
	private String documento;
	private Date data_vencinento;
	private boolean faturado;
	private Venda venda;
	
	public Documento(){
		
	}
	
	public Documento(String documento, Date data_vencinento, boolean faturado,
			Venda venda) {
		super();
		this.documento = documento;
		this.data_vencinento = data_vencinento;
		this.faturado = faturado;
		this.venda = venda;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getData_vencinento() {
		return data_vencinento;
	}

	public void setData_vencinento(Date data_vencinento) {
		this.data_vencinento = data_vencinento;
	}

	public boolean isFaturado() {
		return faturado;
	}

	public void setFaturado(boolean faturado) {
		this.faturado = faturado;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
