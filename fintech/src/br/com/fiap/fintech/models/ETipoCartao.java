package br.com.fiap.fintech.models;

import java.util.HashMap;
import java.util.Map;

public enum ETipoCartao {
	CREDITO(1), DEBITO(2), CREDITO_DEBITO(3);

	private final int value;
	private static final Map<Object, Object> map = new HashMap<>();

	ETipoCartao(int value) {
		this.value = value;
	}

	static {
		for (ETipoCartao tipoCartao : ETipoCartao.values()) {
			map.put(tipoCartao.value, tipoCartao);
		}
	}

	public static ETipoCartao valueOf(int pageType) {
		return (ETipoCartao) map.get(pageType);
	}

	public int getValue() {
		return value;
	}
}
