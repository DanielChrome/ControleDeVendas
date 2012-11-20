package br.du.fasa.CV.DomainModel;

import java.util.Date;

public class Documento {
	private String documento;
	private Date dataVencimento;
	private boolean faturado;
	private Venda venda;
	
	public Documento(){
		
	}
	
	public Documento(String documento, Date dataVencimento, boolean faturado,
			Venda venda) {
		super();
		this.documento = documento;
		this.dataVencimento = dataVencimento;
		this.faturado = faturado;
		this.venda = venda;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
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
