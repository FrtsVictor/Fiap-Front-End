package br.com.fiap.fintech.test;

import java.util.Optional;

import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.models.Usuario;

public class TestUsuario {
	
	public static void main(String[] args) {
		save();
		getById();
		getAll();
		updateById();
		delete();
	}
	
	private static void save() {
		Usuario user1 = Mocks.USER_MOCK;
		Usuario user2 = Mocks.USER_MOCK;
		user2.setNome("Super Nome");

		new UsuarioDao().save(user1, true);
		new UsuarioDao().save(user2, true);
	}

	private static void getById() {
		Optional<Usuario> usuario = new UsuarioDao().getById(2L, true);
		usuario.ifPresent(System.out::println);
	}

	private static void getAll() {
		System.out.println(new UsuarioDao().getAll(true));
	}

	private static void updateById() {
		UsuarioDao usuarioDao = new UsuarioDao();
		Long id = 2L;
		usuarioDao.updateById(id, Mocks.USER_MOCK, true);
		System.out.println(usuarioDao.getById(id, true));
	}

	private static void delete() {
		new UsuarioDao().deleteById(7L, true);
	}
	
}