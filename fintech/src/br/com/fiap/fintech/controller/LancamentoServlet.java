package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.bean.UserSession;
import br.com.fiap.fintech.dao.LancamentoDao;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.models.Lancamento;

@WebServlet("/lancamento")
public class LancamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LancamentoDao lancamentoDao;

	@Override
	public void init() throws ServletException {
		super.init();
		lancamentoDao = DaoFactory.getLancamentoDao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		request.setAttribute("list_lancamento", getLancamentos(request));
		request.getRequestDispatcher("read-expenses.jsp").forward(request, response);	
	}

	private List<Lancamento> getLancamentos(HttpServletRequest request) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("user_session");
		return lancamentoDao.getAllByUserId(userSession.getId(), false);
	}
}
