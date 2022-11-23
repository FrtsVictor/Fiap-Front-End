package br.com.fiap.fintech.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import br.com.fiap.fintech.models.Cartao;
import br.com.fiap.fintech.models.CategoriaLancamento;
import br.com.fiap.fintech.models.Conta;
import br.com.fiap.fintech.models.EBandeiraCartao;
import br.com.fiap.fintech.models.ETipoCartao;
import br.com.fiap.fintech.models.ETipoLancamento;
import br.com.fiap.fintech.models.Lancamento;
import br.com.fiap.fintech.models.Receita;
import br.com.fiap.fintech.models.Usuario;


public class Mocks {
    public static final Usuario USER_MOCK = new Usuario(            
            "superEmail@email",
            "Victor",
            "senha",
            LocalDateTime.now()
    );
    public static final Conta CONTA_MOCK = new Conta(
            USER_MOCK,
            "Bradesco",
            ThreadLocalRandom.current().nextDouble(0.0, 15999.00)
    );

    public static final Cartao CARTAO_MOCK = new Cartao(
            ETipoCartao.CREDITO_DEBITO, CONTA_MOCK,
            "Cartao Mercado",
            156516354151654L,
            LocalDate.now().plusMonths(10),
            EBandeiraCartao.AMERICAN_EXPRESS
    );

    public static final Receita RECEITA_MOCK = new Receita(
            "Churrasco",
            CONTA_MOCK,
            "Pagamento Julho"
    );

    public static final CategoriaLancamento CATEGORIA_LANCAMENTO_MOCK = new CategoriaLancamento("Comida", "Carne", USER_MOCK);

    public static final Lancamento LANCAMENTO_MOCK = new Lancamento(CATEGORIA_LANCAMENTO_MOCK, CONTA_MOCK, ETipoLancamento.CARTAO, 19.5, "Teste", Optional.of(CARTAO_MOCK));


    public Mocks() {
    }
}
