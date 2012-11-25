package br.edu.fasa.cv.domainmodel;

import java.util.Date;

public class Documento {
	private long documento;
	private Date datavencimento;
	private boolean faturado;
	private Venda venda;
	private Double valor;
	
	public Documento(){
		
	}
	
	public Documento(long documento, Date dataVencimento, boolean faturado,
			Venda venda,Double valor) {
		super();
		this.documento = documento;
		this.datavencimento = dataVencimento;
		this.faturado = faturado;
		this.venda = venda;
		this.valor = valor;
	}

	public long getDocumento() {
		return documento;
	}

	public void setDocumento(long documento) {
		this.documento = documento;
	}

	public Date getDataVencimento() {
		return datavencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.datavencimento = dataVencimento;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
