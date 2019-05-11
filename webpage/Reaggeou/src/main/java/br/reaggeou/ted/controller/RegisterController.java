package br.reaggeou.ted.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.reaggeou.ted.business.UserBO;
import br.reaggeou.ted.exception.UserAlreadyExists;
import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.User;

@WebServlet("/RegisterServlet")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = -1459803249702823686L;
	private UserBO userBO = new UserBO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mapCategory", userBO.mapCategory());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String category = request.getParameter("category");
		String tel = request.getParameter("tel");
		
		Category ctg = new Category();
		ctg.setName(category);
		
		User user = new User();
		user.setEmail(email);
		user.setTel(tel);
		
		try {
			userBO.insertUser(user, ctg);
			response.sendRedirect("successfully_registered.jsp");
		} catch (UserAlreadyExists e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response); 
		}
	}

}
