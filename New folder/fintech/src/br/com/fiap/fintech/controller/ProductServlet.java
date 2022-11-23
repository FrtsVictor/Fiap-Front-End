package br.com.fiap.fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double value = Double.parseDouble(request.getParameter("value"));
		
		System.out.println(name + " " + quantity + " "  + value);
		
		request.setAttribute("productName", name);
		request.setAttribute("productQuantity", quantity);
		request.setAttribute("productValue", value);
		request.getRequestDispatcher("success.jsp").forward(request, response);		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println("Id: " + id);	
		request.setAttribute("productId", id);
		request.setAttribute("productName", "Caneta Vermelha");
		request.getRequestDispatcher("search-product.jsp").forward(request, response);
	}	
}
