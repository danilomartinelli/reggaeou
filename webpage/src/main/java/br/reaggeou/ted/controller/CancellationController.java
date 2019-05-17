package br.reaggeou.ted.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.reaggeou.ted.business.UserBO;
import br.reaggeou.ted.exception.EmptyUserException;
import br.reaggeou.ted.exception.EmptyReasonException;
import br.reaggeou.ted.exception.EmptyUserEmailException;
import br.reaggeou.ted.exception.EmptyUserTelException;
import br.reaggeou.ted.exception.NonExistentUserException;
import br.reaggeou.ted.model.StatusUser;
import br.reaggeou.ted.model.User;

public class CancellationController extends HttpServlet {

	private static final long serialVersionUID = 217927236822445704L;
	private static final String SUCCESSFULLY_REMOVED = "O usuário foi removido com sucesso !";

	private UserBO userBO = new UserBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		User user = new User();
		user.setEmail(email);
		user.setStatus(StatusUser.CANCELED);
		String reason = request.getParameter("reason");
		
		changeUserStatus(user, request, response, reason);
	}

	private void changeUserStatus(User user, HttpServletRequest request, HttpServletResponse response, String reason) throws ServletException, IOException {
		try {
			userBO.changeUserStatus(user, reason);
			request.setAttribute("SuccessfullyRemoved", SUCCESSFULLY_REMOVED);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (EmptyUserException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cancellation.jsp").forward(request, response);
		} catch (EmptyUserEmailException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cancellation.jsp").forward(request, response);
		} catch (NonExistentUserException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cancellation.jsp").forward(request, response);
		} catch (EmptyReasonException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("cancellation.jsp").forward(request, response);
		} 
	}

}
