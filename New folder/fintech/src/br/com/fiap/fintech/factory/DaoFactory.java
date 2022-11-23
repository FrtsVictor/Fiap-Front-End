package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.ContaDao;
import br.com.fiap.fintech.dao.LancamentoDao;
import br.com.fiap.fintech.dao.ReceitaDao;
import br.com.fiap.fintech.dao.UsuarioDao;

public class DaoFactory {
	
	public static ContaDao getContaDao (){
		return new ContaDao();
	}
	
	public static  LancamentoDao getLancamentoDao (){
		return new LancamentoDao();
	}

	public static  ReceitaDao getReceitaDao (){
		return new ReceitaDao();
	}

	public static  UsuarioDao getUsuarioDao (){
		return new UsuarioDao();
	}
}
