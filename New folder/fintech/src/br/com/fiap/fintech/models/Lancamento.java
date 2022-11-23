package br.com.fiap.fintech.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Lancamento extends Base {

	private static final long serialVersionUID = -7382158814516920244L;
	private Conta conta;
	private Double valor;
	private String descricao;
	private String titulo;
	private LocalDate data;

	public Lancamento() {
		super();
	}

	/**
	 * @param id
	 * @param conta
	 * @param valor
	 * @param descricao
	 */
	public Lancamento(Long id, Conta conta, Double valor, String descricao) {
		super(id);
		this.conta = conta;
		this.valor = valor;
		this.descricao = descricao;
	}

	/**
	 * @param id
	 * @param conta
	 * @param valor
	 * @param descricao
	 * @param titulo
	 * @param data
	 */
	public Lancamento(String titulo, String descricao, Double valor, LocalDate data, Conta conta) {
		super();
		this.conta = conta;
		this.valor = valor;
		this.descricao = descricao;
		this.titulo = titulo;
		this.data = data;
	}
	
	public Lancamento(Long id, String titulo, String descricao, Double valor, LocalDate data, Conta conta, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
		super(id, dataCriacao, dataAtualizacao);
		this.conta = conta;
		this.valor = valor;
		this.descricao = descricao;
		this.titulo = titulo;
		this.data = data;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
