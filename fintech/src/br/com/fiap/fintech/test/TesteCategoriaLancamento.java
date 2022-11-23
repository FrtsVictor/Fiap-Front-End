package br.com.fiap.fintech.test;

import java.util.Optional;

import br.com.fiap.fintech.dao.CategoriaLancamentoDao;
import br.com.fiap.fintech.models.CategoriaLancamento;

public class TesteCategoriaLancamento {

	public static void main(String[] args) {
		create();
		getById();
		getAll();
		updateById();
		delete();
	}

	private static void create() {
		CategoriaLancamento categoria1 = Mocks.CATEGORIA_LANCAMENTO_MOCK;
		CategoriaLancamento categoria2 = Mocks.CATEGORIA_LANCAMENTO_MOCK;

		categoria1.getUsuario().setId(1L);
		categoria2.getUsuario().setId(2L);
		categoria2.setNome("Estudos");
		categoria2.setDescricao("Livros");

		new CategoriaLancamentoDao().save(categoria1, false);
		new CategoriaLancamentoDao().save(categoria2, true);
	}

	private static void getById() {
		Optional<CategoriaLancamento> conta = new CategoriaLancamentoDao().getById(2L, true);
		conta.ifPresent(System.out::println);
	}

	private static void getAll() {
		System.out.println(new CategoriaLancamentoDao().getAll(true));
	}

	private static void updateById() {
		CategoriaLancamentoDao categoriaLancamentoDao = new CategoriaLancamentoDao();
		Long id = 2L;

		CategoriaLancamento categoria = Mocks.CATEGORIA_LANCAMENTO_MOCK;
		categoria.setNome("Teste Update");
		categoria.setDescricao("teste descricao");
		categoria.getUsuario().setId(2L);

		categoriaLancamentoDao.updateById(id, categoria, false);
		System.out.println(categoriaLancamentoDao.getById(id, true));
	}

	private static void delete() {
		new CategoriaLancamentoDao().deleteById(1L, true);
	}
}