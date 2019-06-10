package br.reaggeou.ted.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.reaggeou.ted.business.UserBO;
import br.reaggeou.ted.exception.EmptyCategoriesException;
import br.reaggeou.ted.exception.EmptyUserEmailException;
import br.reaggeou.ted.exception.EmptyUserException;
import br.reaggeou.ted.exception.EmptyUserTelException;
import br.reaggeou.ted.exception.UserAlreadyExistsException;
import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.StatusUser;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.persistence.EventDAO;

@WebServlet("/RegisterServlet")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = -1459803249702823686L;
	private UserBO userBO = new UserBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] categories = request.getParameterValues("category");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");

		User user = new User();
		user.setEmail(email);
		user.setTel(tel);
		user.setStatus(StatusUser.ACTIVE);

		request.getSession().setAttribute("user", user);
		registerUser(request, response, user, categories);
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response, User user, String[] categories)
			throws IOException, ServletException {

		try {
				userBO.validateEmpty(user, categories);
				userBO.insertUser(user);
				insertCategories(request, response, user, categories);
			response.sendRedirect("admin/successfully_registered.jsp");
		} catch (EmptyUserException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("index.jsp");
		} catch (EmptyUserEmailException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("index.jsp");
		} catch (EmptyUserTelException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("index.jsp");
		} catch (UserAlreadyExistsException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("index.jsp");
		} catch (EmptyCategoriesException e) {
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("index.jsp");
		}

	}

	private void insertCategories(HttpServletRequest request, HttpServletResponse response, User user,
			String[] categories) throws ServletException, IOException {
		for (String categoryID : categories) {
				Category category = new Category();
				category.setIdCategory(Integer.parseInt(categoryID));
				userBO.inserTableUserCategory(user, category, categories);
		}
//		EventDAO eventDAO = new EventDAO();
//		eventDAO.sendEmail(user, categories);
	}

}
