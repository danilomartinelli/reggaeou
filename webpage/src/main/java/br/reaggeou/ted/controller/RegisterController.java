package br.reaggeou.ted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.reaggeou.ted.business.UserBO;
import br.reaggeou.ted.exception.EmptyUserException;
import br.reaggeou.ted.exception.EmptyUserEmailException;
import br.reaggeou.ted.exception.EmptyUserTelException;
import br.reaggeou.ted.exception.UserAlreadyExistsException;
import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.User;

@WebServlet("/RegisterServlet")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = -1459803249702823686L;
	private UserBO userBO = new UserBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// Poss�vel solu��o

		Integer[] categories = new Integer[5];
		Integer counter = 0;
		
		for (Map.Entry<Integer, Integer> entry : userBO.mapCategoryId().entrySet()) {
			categories[counter] = entry.getValue();
			counter++;
		}
		
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] categories = request.getParameterValues("category");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");

		User user = new User();
		user.setEmail(email);
		user.setTel(tel);
		
		request.getSession().setAttribute("user", user);
		registerUser(request, response, user);
		
		for(String categoryID : categories) {
			try {
				userBO.inserTableUserCategory(user, categoryID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("admin/successfully_registered.jsp");
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response, User user)
			throws IOException, ServletException {

		try {
			userBO.insertUser(user);
		} catch (EmptyUserException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (EmptyUserEmailException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (EmptyUserTelException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (UserAlreadyExistsException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
