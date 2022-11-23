package br.com.fiap.fintech.test;

import java.util.Optional;

import br.com.fiap.fintech.dao.LancamentoDao;
import br.com.fiap.fintech.models.Lancamento;

class TestLancamento {

	public static void main(String[] args) {
		save();
		getById();
		getAll();
		updateById();
		delete();
	}

	private static void save() {
		Lancamento lancamento = Mocks.LANCAMENTO_MOCK;
		Lancamento lancamento2 = Mocks.LANCAMENTO_MOCK;

		lancamento.getConta().getUsuario().setId(1L);
		lancamento.getConta().setId(3L);
		lancamento2.getConta().setSaldo(199.50);
		lancamento2.getConta().setId(2L);
		lancamento2.getConta().getUsuario().setId(2L);	

		new LancamentoDao().save(lancamento, false);
		new LancamentoDao().save(lancamento2, true);
	}

	private static void getById() {
		Optional<Lancamento> lancamento = new LancamentoDao().getById(2L, true);
		lancamento.ifPresent(System.out::println);
	}

	private static void getAll() {
		System.out.println(new LancamentoDao().getAll(true));
	}

	private static void updateById() {
		LancamentoDao laancamentoDao = new LancamentoDao();
		Long id = 1L;

		Lancamento lancamento = Mocks.LANCAMENTO_MOCK;
		lancamento.setDescricao("Teste Update");
		laancamentoDao.updateById(id, lancamento, false);
		System.out.println(laancamentoDao.getById(id, true));
	}

	private static void delete() {
		new LancamentoDao().deleteById(1L, true);
	}

}