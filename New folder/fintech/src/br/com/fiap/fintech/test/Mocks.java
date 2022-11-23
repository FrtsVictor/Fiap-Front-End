package br.com.fiap.fintech.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import br.com.fiap.fintech.models.Conta;
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

    public static final Receita RECEITA_MOCK = new Receita(
            "Churrasco",
            CONTA_MOCK,
            "Pagamento Julho"
    );    

    public static final Lancamento LANCAMENTO_MOCK = new Lancamento("Conta Luz", "Conta de luz atrasada", 179.50, LocalDate.now(), CONTA_MOCK);

    public Mocks() {
    }
}
