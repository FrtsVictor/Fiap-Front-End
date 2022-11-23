package br.com.fiap.fintech.models;

import java.util.HashMap;
import java.util.Map;

public enum ETipoLancamento {
    DINHEIRO(1), CARTAO(2);

    private final int value;
    private static final Map<Object, Object> map = new HashMap<>();

    ETipoLancamento(int value) {
        this.value = value;
    }

    static {
        for (ETipoLancamento tipoLancamento : ETipoLancamento.values()) {
            map.put(tipoLancamento.value, tipoLancamento);
        }
    }

    public static ETipoLancamento valueOf(int pageType) {
        return (ETipoLancamento) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
