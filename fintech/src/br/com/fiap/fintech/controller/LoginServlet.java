package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.bean.UserSession;
import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.models.Usuario;
import br.com.fiap.fintech.singleton.Encryptor;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 5186309628478844636L;

	private UsuarioDao usuarioDao;

	public LoginServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			usuarioDao = DaoFactory.getUsuarioDao();
		} catch (Exception e) {
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			boolean isLogedIn = doLogin(request);

			if (isLogedIn ) {				
				request.getRequestDispatcher("main.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Usuario ou senha inválidos");
				request.getSession().setAttribute("user_session", new UserSession("email", "senha", 1L));
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean doLogin(HttpServletRequest request) {
		try {
			String email = request.getParameter("email");
			String encryptedPassword = Encryptor.encrypt(request.getParameter("password"));
			Optional<Usuario> usuario = usuarioDao.getByEmail(email, true);

			if (usuario.isPresent() && usuario.get().getSenha().equals(encryptedPassword)) {
				request.getSession().setAttribute("user_session",
						new UserSession(usuario.get().getEmail(), usuario.get().getNome(), usuario.get().getId()));
				
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
