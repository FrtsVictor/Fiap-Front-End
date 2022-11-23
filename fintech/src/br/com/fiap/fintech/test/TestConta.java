package br.com.fiap.fintech.test;

import java.util.Optional;

import br.com.fiap.fintech.dao.ContaDao;
import br.com.fiap.fintech.models.Conta;

public class TestConta {

	public static void main(String[] args) {
		save();
		getById();
		getAll();
		updateById();
		delete();
	}

	private static void save() {
		Conta conta1 = Mocks.CONTA_MOCK;
		Conta conta2 = Mocks.CONTA_MOCK;

		conta1.getUsuario().setId(1L);
		conta2.getUsuario().setId(2L);
		conta2.setNome("Santander");
		conta2.setSaldo(199.50);

		new ContaDao().save(conta1, false);
		new ContaDao().save(conta2, true);
	}

	private static void getById() {
		Optional<Conta> conta = new ContaDao().getById(2L, true);
		conta.ifPresent(System.out::println);
	}

	private static void getAll() {
		System.out.println(new ContaDao().getAll(true));
	}

	private static void updateById() {
		ContaDao contaDao = new ContaDao();
		Long id = 1L;

		Conta conta = Mocks.CONTA_MOCK;
		conta.setNome("Teste Update");
		conta.setSaldo(1500.99);
		conta.getUsuario().setId(2L);

		contaDao.updateById(id, conta, false);
		System.out.println(contaDao.getById(id, true));
	}

	private static void delete() {
		new ContaDao().deleteById(1L, true);
	}
}