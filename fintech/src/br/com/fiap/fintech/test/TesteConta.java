package br.com.fiap.fintech.test;

import java.util.Optional;

import br.com.fiap.fintech.dao.CartaoDao;
import br.com.fiap.fintech.models.Cartao;
import br.com.fiap.fintech.models.EBandeiraCartao;

public class TesteConta {

	public static void main(String[] args) {

		create();
		getById();
		getAll();
		updateById();
		delete();
	}

	private static void create() {
		Cartao cartao1 = Mocks.CARTAO_MOCK;
		Cartao cartao2 = Mocks.CARTAO_MOCK;

		cartao1.getConta().getUsuario().setId(1L);
		cartao1.getConta().setId(3L);
		cartao2.setNome("Santander");
		cartao2.getConta().setSaldo(199.50);
		cartao2.getConta().setId(2L);
		cartao2.getConta().getUsuario().setId(2L);
		cartao2.setNumero(cartao2.getNumero() + 2);

		new CartaoDao().save(cartao1, false);
		new CartaoDao().save(cartao2, true);
	}

	private static void getById() {
		Optional<Cartao> conta = new CartaoDao().getById(1L, true);
		conta.ifPresent(System.out::println);
	}

	private static void getAll() {
		System.out.println(new CartaoDao().getAll(true));
	}

	private static void updateById() {
		CartaoDao contaDao = new CartaoDao();
		Long id = 1L;

		Cartao conta = Mocks.CARTAO_MOCK;
		conta.setNome("Teste Update");
		conta.setBandeira(EBandeiraCartao.AMERICAN_EXPRESS);
		conta.setNumero(494984658451123L);

		contaDao.updateById(id, conta, false);
		System.out.println(contaDao.getById(id, true));
	}

	private static void delete() {
		new CartaoDao().deleteById(1L, true);
	}
}