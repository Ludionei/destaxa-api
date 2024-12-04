package com.destaxa.api.infrastructure.protocol;

public class ISO8583 {

	private String codigo;
	private String mapa;
	private String valorTransacao;
	private String dataHoraTransmissao;
	private String nsu;
	private String horaLocalTransacao;
	private String dataLocalTransacao;
	private String codigoEstabelecimento;

	public String getCodigo() {

		return codigo;
	}

	public void setCodigo(String codigo) {

		this.codigo = codigo;
	}

	public String getMapa() {

		return mapa;
	}

	public void setMapa(String mapa) {

		this.mapa = mapa;
	}

	public String getValorTransacao() {

		return valorTransacao;
	}

	public void setValorTransacao(String valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getDataHoraTransmissao() {
		return dataHoraTransmissao;
	}

	public void setDataHoraTransmissao(String dataHoraTransmissao) {
		this.dataHoraTransmissao = dataHoraTransmissao;
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	public String getHoraLocalTransacao() {
		return horaLocalTransacao;
	}

	public void setHoraLocalTransacao(String horaLocalTransacao) {
		this.horaLocalTransacao = horaLocalTransacao;
	}

	public String getDataLocalTransacao() {
		return dataLocalTransacao;
	}

	public void setDataLocalTransacao(String dataLocalTransacao) {
		this.dataLocalTransacao = dataLocalTransacao;
	}

	public String getCodigoEstabelecimento() {

		return codigoEstabelecimento;
	}

	public void setCodigoEstabelecimento(String codigoEstabelecimento) {
		this.codigoEstabelecimento = codigoEstabelecimento;
	}

}