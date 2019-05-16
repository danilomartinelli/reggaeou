package br.reaggeou.ted.business;

import java.sql.SQLException;
import java.util.Map;

import br.reaggeou.ted.exception.EmptyUserException;
import br.reaggeou.ted.exception.EmptyUserEmailException;
import br.reaggeou.ted.exception.EmptyUserTelException;
import br.reaggeou.ted.exception.NonExistentUserException;
import br.reaggeou.ted.exception.UserAlreadyExistsException;
import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.persistence.UserDAO;

public class UserBO {

	private UserDAO userDAO = new UserDAO();
	private static final String MESSAGE_ERROR_USER_EXIST = "Este usu�rio j� est� cadastrado";
	private static final String EMPTY_USER = "Os campos est�o vazio";
	private static final String EMPTY_USER_EMAIL = "O campo email est� vazio";
	private static final String EMPTY_USER_TEL = "O campo telefone est� vazio";

	public void insertUser(User user)
			throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException, UserAlreadyExistsException {
		emptyUser(user);
		validate(user);
		userDAO.insertUser(user);
	}
	
	public void inserTableUserCategory(User user, String categoryID) throws SQLException {
		userDAO.insertTableUserCategory(user, categoryID);
	}

	public void removeUser(User user)
			throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException, NonExistentUserException {
		emptyUser(user);
		nonExistentUser(user);
		userDAO.removeUserCategory(user);
		userDAO.removeUser(user);
	}

	public Map<Integer, String> mapCategory() {
		return userDAO.mapCategory();
	}
	
	public Map<Integer, Integer> mapCategoryId() {
		return userDAO.mapCategoryId();
	}

	private void emptyUser(User user) throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException {

		if (emptyUserEmail(user) && emptyUserTel(user)) {
			throw new EmptyUserException(EMPTY_USER);
		} else {
			if (emptyUserEmail(user)) {
				throw new EmptyUserEmailException(EMPTY_USER_EMAIL);
			} else if (emptyUserTel(user)) {
				throw new EmptyUserTelException(EMPTY_USER_TEL);
			}
		}

	}

	private Boolean emptyUserEmail(User user) {
		return user.getEmail().trim().isEmpty();
	}

	private Boolean emptyUserTel(User user) {
		return user.getTel().trim().isEmpty();
	}

	private void nonExistentUser(User user) throws NonExistentUserException {
		if (!userDAO.validate(user)) {
			throw new NonExistentUserException("Usu�rio n�o cadastrado");
		}
	}

	private void validate(User user) throws UserAlreadyExistsException {
		if (userDAO.validate(user)) {
			throw new UserAlreadyExistsException(MESSAGE_ERROR_USER_EXIST);
		}
	}

}
