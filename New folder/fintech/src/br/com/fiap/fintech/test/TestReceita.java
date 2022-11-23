package br.com.fiap.fintech.test;

import java.util.Optional;

import br.com.fiap.fintech.dao.ReceitaDao;
import br.com.fiap.fintech.models.Receita;

public class TestReceita {
	public static void main(String[] args) {
		save();
		getById();
		getAll();
		updateById();
		delete();
	}

	private static void save() {
		Receita receita1 = Mocks.RECEITA_MOCK;
		Receita receita2 = Mocks.RECEITA_MOCK;

		receita1.getConta().setId(1L);
		receita1.getConta().getUsuario().setId(1L);

		receita2.getConta().setId(2L);
		receita2.getConta().getUsuario().setId(2L);
		receita2.setNome("Santander");

		new ReceitaDao().save(receita1, false);
		new ReceitaDao().save(receita2, true);
	}

	private static void getById() {
		Optional<Receita> receita = new ReceitaDao().getById(2L, true);
		receita.ifPresent(System.out::println);
	}

	private static void getAll() {
		System.out.println(new ReceitaDao().getAll(true));
	}

	private static void updateById() {
		ReceitaDao receitaDao = new ReceitaDao();
		Long id = 1L;

		Receita receita = Mocks.RECEITA_MOCK;
		receita.setNome("Teste Update");
		receita.setDescricao("super descricao");
		receitaDao.updateById(id, receita, false);
		System.out.println(receitaDao.getById(id, true));
	}

	private static void delete() {
		new ReceitaDao().deleteById(1L, true);
	}
}