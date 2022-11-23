package br.com.fiap.fintech.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Base implements Serializable {

	private static final long serialVersionUID = 15665464521L;
	
	protected Long id;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;

	protected Base(Long id) {
		super();
		this.id = id;
	}

	protected Base(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	protected Base(Long id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	protected Base() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Base{" +
				"id=" + id +
				", dataCriacao=" + dataCriacao +
				", dataAtualizacao=" + dataAtualizacao +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Base other = (Base) obj;
		return Objects.equals(id, other.id);
	}
	
}
