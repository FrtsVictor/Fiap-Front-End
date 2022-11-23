package br.com.fiap.fintech.bean;

import java.io.Serializable;
import java.util.Objects;

public class UserSession implements Serializable {
	
	private static final long serialVersionUID = 7328250735612176972L;
	private String email;
	private String nome;
	private Long id;

	public UserSession() {
		super();
	}

	/**
	 * @param email
	 * @param nome
	 * @param id
	 */
	public UserSession(String email, String nome, Long id) {
		super();
		this.email = email;
		this.nome = nome;
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSession other = (UserSession) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}	
	
}
