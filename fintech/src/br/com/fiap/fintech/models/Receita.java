package br.com.fiap.fintech.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Receita extends Base {

	private static final long serialVersionUID = -4874207507209433424L;
	private String nome;
	private Conta conta;
	private String descricao;

	public Receita() {
		super();
	}
	public Receita(Long id, String nome, Conta conta, String descricao, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
		super(id, dataCriacao, dataAtualizacao);
		this.nome = nome;
		this.conta = conta;
		this.descricao = descricao;
	}

	public Receita(String nome, Conta conta, String descricao) {
		this.nome = nome;
		this.conta = conta;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Receita [nome=" + nome + ", conta=" + conta + ", descricao=" + descricao + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(conta, descricao, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receita other = (Receita) obj;
		return Objects.equals(conta, other.conta) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(nome, other.nome);
	}

}
