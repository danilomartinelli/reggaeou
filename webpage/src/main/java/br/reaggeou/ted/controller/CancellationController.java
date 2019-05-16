package br.reaggeou.ted.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.reaggeou.ted.business.UserBO;
import br.reaggeou.ted.exception.EmptyUserException;
import br.reaggeou.ted.exception.EmptyUserEmailException;
import br.reaggeou.ted.exception.EmptyUserTelException;
import br.reaggeou.ted.exception.NonExistentUserException;
import br.reaggeou.ted.model.User;

public class CancellationController extends HttpServlet {

	private static final long serialVersionUID = 217927236822445704L;
	private static final String SUCCSSFULLY_REMOVED = "O usuário foi removido com sucesso !";

	private UserBO userBO = new UserBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		
		User user = new User();
		user.setEmail(email);
		user.setTel(tel);
		
//		String reason = request.getParameter("reason");
		
		removeUser(user, request, response);
	}

	private void removeUser(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			userBO.removeUser(user);
			request.setAttribute("SuccessfullyRemoved", SUCCSSFULLY_REMOVED);
			response.sendRedirect("index.jsp");
		} catch (EmptyUserException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cancellation.jsp").forward(request, response);
		} catch (EmptyUserEmailException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cancellation.jsp").forward(request, response);
		} catch (EmptyUserTelException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cancellation.jsp").forward(request, response);
		} catch (NonExistentUserException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cancellation.jsp").forward(request, response);
		} 
	}

}
