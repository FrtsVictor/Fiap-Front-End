package br.com.fiap.fintech.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Cartao extends Base {

    private static final long serialVersionUID = -3774868029231672427L;
    private ETipoCartao tipoCartao;
    private Conta conta;
    private String nome;
    private Long numero;
    private LocalDate validade;
    private EBandeiraCartao bandeira;    

    public Cartao() {
        super();
    }
    
    public Cartao(Long id, ETipoCartao tipoCartao, Conta conta, String nome, Long numero, LocalDate validade,
                  EBandeiraCartao bandeira, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id);
        this.tipoCartao = tipoCartao;
        this.conta = conta;
        this.nome = nome;
        this.numero = numero;
        this.validade = validade;
        this.bandeira = bandeira;        
    }

    public Cartao(ETipoCartao tipoCartao, Conta conta, String nome, Long numero, LocalDate validade, EBandeiraCartao bandeira) {
        this.tipoCartao = tipoCartao;
        this.conta = conta;
        this.nome = nome;
        this.numero = numero;
        this.validade = validade;
        this.bandeira = bandeira;
    }    

    public ETipoCartao getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(ETipoCartao tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public EBandeiraCartao getBandeira() {
        return bandeira;
    }

    public void setBandeira(EBandeiraCartao bandeira) {
        this.bandeira = bandeira;
    }

    @Override
    public String toString() {
        return "Cartao [tipoCartao=" + tipoCartao + ", conta=" + conta + ", nome=" + nome + ", numero=" + numero
                + ", validade=" + validade + ", bandeira=" + bandeira + ", id=" + id + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(bandeira, conta, nome, numero, tipoCartao, validade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cartao other = (Cartao) obj;
        return bandeira == other.bandeira && Objects.equals(conta, other.conta) && Objects.equals(nome, other.nome)
                && Objects.equals(numero, other.numero) && tipoCartao == other.tipoCartao
                && Objects.equals(validade, other.validade);
    }

}
