package br.com.fiap.fintech.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class Lancamento extends Base {
    
	private static final long serialVersionUID = -7382158814516920244L;
	private CategoriaLancamento categoriaLancamento;
    private Conta conta;
    private ETipoLancamento tipoLancamento;
    private Double valor;
    private String descricao;
    private Optional<Cartao> cartao;

    public Lancamento() {
		super();
	}

	public Lancamento(Long id, CategoriaLancamento categoriaLancamento, Conta conta, ETipoLancamento tipoLancamento, Double valor, String descricao, Optional<Cartao> cartao) {
        super(id);
        this.categoriaLancamento = categoriaLancamento;
        this.conta = conta;
        this.tipoLancamento = tipoLancamento;
        this.valor = valor;
        this.descricao = descricao;
        this.cartao = cartao;
    }

    public Lancamento(Long id, CategoriaLancamento categoriaLancamento, Conta conta, ETipoLancamento tipoLancamento, Double valor, String descricao, Optional<Cartao> cartao, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(id, dataCriacao, dataAtualizacao);
        this.categoriaLancamento = categoriaLancamento;
        this.conta = conta;
        this.tipoLancamento = tipoLancamento;
        this.valor = valor;
        this.descricao = descricao;
        this.cartao = cartao;
    }

    public Lancamento(CategoriaLancamento categoriaLancamento, Conta conta, ETipoLancamento tipoLancamento, Double valor, String descricao, Optional<Cartao> cartao) {
        this.categoriaLancamento = categoriaLancamento;
        this.conta = conta;
        this.tipoLancamento = tipoLancamento;
        this.valor = valor;
        this.descricao = descricao;
        this.cartao = cartao;
    }

    public Optional<Cartao> getCartao() {
        return cartao;
    }

    public CategoriaLancamento getCategoriaLancamento() {
        return categoriaLancamento;
    }

    public void setCategoriaLancamento(CategoriaLancamento categoriaLancamento) {
        this.categoriaLancamento = categoriaLancamento;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public ETipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(ETipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cartao, categoriaLancamento, conta, descricao, tipoLancamento, valor);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		return Objects.equals(cartao, other.cartao) && Objects.equals(categoriaLancamento, other.categoriaLancamento)
				&& Objects.equals(conta, other.conta) && Objects.equals(descricao, other.descricao)
				&& tipoLancamento == other.tipoLancamento && Objects.equals(valor, other.valor);
	}
    
    
}
