package br.reaggeou.ted.business;

import java.util.Map;

import br.reaggeou.ted.exception.UserAlreadyExists;
import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.persistence.UserDAO;

public class UserBO {

	private UserDAO userDAO = new UserDAO();
	private static final String MESSAGE_ERROR_USER_EXIST = "Este usuário já está cadastrado";

	public void insertUser(User user, Category category) throws UserAlreadyExists {
		validate(user);
		userDAO.insertUser(user, category);
	}

	public Map<Integer, String> mapCategory() {
		return userDAO.mapCategory();
	}
	
	private void validate(User user) throws UserAlreadyExists {
		if (userDAO.validate(user)) {
			throw new UserAlreadyExists(MESSAGE_ERROR_USER_EXIST);
		}
	}

}
