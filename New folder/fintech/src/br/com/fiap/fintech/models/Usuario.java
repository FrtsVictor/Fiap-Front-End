package br.com.fiap.fintech.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario extends Base implements Serializable {

    private static final long serialVersionUID = 4994341828221056802L;    
    private String email;
    private String nome;
    private String senha;

    public Usuario() {
        super();
    }

    public Usuario(Long id, String email, String nome, String senha, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String email, String nome, String senha, LocalDateTime dataCriacao) {
        super(dataCriacao);
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", email=" + email + ", nome=" + nome + ", senha=" + senha
                + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email) && Objects.equals(nome, usuario.nome) && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, nome, senha);
    }
}
