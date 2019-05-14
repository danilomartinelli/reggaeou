package br.reaggeou.ted.business;

import java.util.Map;

import br.reaggeou.ted.exception.EmptyUser;
import br.reaggeou.ted.exception.EmptyUserEmail;
import br.reaggeou.ted.exception.EmptyUserTel;
import br.reaggeou.ted.exception.UserAlreadyExists;
import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.persistence.UserDAO;

public class UserBO {

	private UserDAO userDAO = new UserDAO();
	private static final String MESSAGE_ERROR_USER_EXIST = "Este usuário já está cadastrado";
	private static final String EMPTY_USER = "Os campos estão vazio";
	private static final String EMPTY_USER_EMAIL = "O campo email está vazio";
	private static final String EMPTY_USER_TEL = "O campo telefone está vazio";

	public void insertUser(User user, Category category)
			throws EmptyUser, EmptyUserEmail, EmptyUserTel, UserAlreadyExists {
		emptyUser(user);
		validate(user);
		userDAO.insertUser(user, category);
	}

	public Map<Integer, String> mapCategory() {
		return userDAO.mapCategory();
	}

	private void emptyUser(User user) throws EmptyUser, EmptyUserEmail, EmptyUserTel {

		if (emptyUserEmail(user) && emptyUserTel(user)) {
			throw new EmptyUser(EMPTY_USER);
		} else {
			if (emptyUserEmail(user)) {
				throw new EmptyUserEmail(EMPTY_USER_EMAIL);
			} else if (emptyUserTel(user)) {
				throw new EmptyUserTel(EMPTY_USER_TEL);
			}
		}

	}

	private Boolean emptyUserEmail(User user) {
		return user.getEmail().trim().isEmpty();
	}

	private Boolean emptyUserTel(User user) {
		return user.getTel().trim().isEmpty();
	}

	private void validate(User user) throws UserAlreadyExists {
		if (userDAO.validate(user)) {
			throw new UserAlreadyExists(MESSAGE_ERROR_USER_EXIST);
		}
	}

}
