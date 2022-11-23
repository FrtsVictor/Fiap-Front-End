package br.com.fiap.fintech.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class CategoriaLancamento extends Base {

    private static final long serialVersionUID = 1134262366045942829L;
    private String nome;
    private String descricao;

    private Usuario usuario;

    public CategoriaLancamento() {
    }

    /**
     * @param id
     * @param nome
     * @param descricao
     */
    public CategoriaLancamento(Long id, String nome, String descricao, Usuario usuario, LocalDateTime dataCriacao,
                               LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
        this.usuario = usuario;
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaLancamento(String nome, String descricao, Usuario usuario) {
        super();
        this.usuario = usuario;
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "CategoriaLancamento [nome=" + nome + ", descricao=" + descricao + " id=" + id + "] " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CategoriaLancamento that = (CategoriaLancamento) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome, descricao);
    }
}
