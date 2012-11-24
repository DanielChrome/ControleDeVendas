package br.edu.fasa.cv.domainmodel;

public class Documento {
	private long documento;
	private String dataVencimento;
	private boolean faturado;
	private Venda venda;
	
	public Documento(){
		
	}
	
	public Documento(long documento, String dataVencimento, boolean faturado,
			Venda venda) {
		super();
		this.documento = documento;
		this.dataVencimento = dataVencimento;
		this.faturado = faturado;
		this.venda = venda;
	}

	public long getDocumento() {
		return documento;
	}

	public void setDocumento(long documento) {
		this.documento = documento;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
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
