package br.com.fiap.fintech.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Conta extends Base {

	private static final long serialVersionUID = -3252798431313919977L;
	private Usuario usuario;
	private String nome;
	private Double saldo;

	public Conta() {
		super();
	}

	public Conta(Usuario usuario, String nome, Double saldo) {
		this.usuario = usuario;
		this.nome = nome;
		this.saldo = saldo;
	}

	public Conta(Long id, Usuario usuario, String nome, Double saldo, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
		super(id, dataCriacao, dataAtualizacao);
		this.usuario = usuario;
		this.nome = nome;
		this.saldo = saldo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", usuario=" + usuario + ", nome=" + nome + ", saldo=" + saldo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, saldo, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(saldo, other.saldo)
				&& Objects.equals(usuario, other.usuario);
	}

}
