package br.com.fiap.fintech.models;

import java.util.HashMap;
import java.util.Map;

public enum EBandeiraCartao {

    MASTERCARD(1), VISA(2), ELO(3), AMERICAN_EXPRESS(4), HIPERCARD(5);

    private final int value;
    private static final Map<Object, Object> map = new HashMap<>();

    EBandeiraCartao(int value) {
        this.value = value;
    }

    static {
        for (EBandeiraCartao bandeiraCartao : EBandeiraCartao.values()) {
            map.put(bandeiraCartao.value, bandeiraCartao);
        }
    }

    public static EBandeiraCartao valueOf(int pageType) {
        return (EBandeiraCartao) map.get(pageType);
    }

    public int getValue() {
        return value;
    }

}
