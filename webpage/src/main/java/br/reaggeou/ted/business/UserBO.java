package br.reaggeou.ted.business;

import java.util.Map;

import br.reaggeou.ted.exception.EmptyCategoriesException;
import br.reaggeou.ted.exception.EmptyReasonException;
import br.reaggeou.ted.exception.EmptyUserEmailException;
import br.reaggeou.ted.exception.EmptyUserException;
import br.reaggeou.ted.exception.EmptyUserTelException;
import br.reaggeou.ted.exception.NonExistentUserException;
import br.reaggeou.ted.exception.UserAlreadyExistsException;
import br.reaggeou.ted.model.Category;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.persistence.UserDAO;

public class UserBO {

	private UserDAO userDAO;
	private static final String MESSAGE_ERROR_USER_EXIST = "Este usuário já está cadastrado";
	private static final String EMPTY_USER = "Os campos estão vazios";
	private static final String EMPTY_USER_EMAIL = "O campo email está vazio";
	private static final String EMPTY_USER_TEL = "O campo telefone está vazio";
	private static final String EMPTY_CATEGORIES = "Escolha pelo menos uma categoria";
	private static final String EMPTY_REASON = "O campo 'motivo do cancelamento' está vazio";
	
	public UserBO() {
		super();
		this.userDAO = new UserDAO();
	}
	
	public UserBO(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	public void insertUser(User user)
			throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException, UserAlreadyExistsException {
		emptyUser(user);
		validate(user);
		userDAO.insertUser(user);
	}

	public void inserTableUserCategory(User user, Category category, String[] categories)
			throws EmptyCategoriesException {
		validate(categories);
		userDAO.insertTableUserCategory(user, category);
	}

	public void changeUserStatus(User user, String reason)
			throws EmptyUserException, EmptyUserEmailException, NonExistentUserException, EmptyReasonException {
		validate(user, reason);
		userDAO.removeUserCategory(user);
		userDAO.changeUserStatus(user);
		userDAO.cancellationReasons(user, reason);
	}

	public Map<Integer, Integer> mapCategoryId() {
		return userDAO.mapCategoryId();
	}

	private Boolean checkStatusUser(User user) {
		Boolean check = false;
		for (User u : userDAO.getActiveUsers()) {
			if (user.getEmail().equalsIgnoreCase(u.getEmail())) {
				check = true;
			}
		}
		return check;
	}

	public void nonExistentUser(User user) throws NonExistentUserException {
		if (!userDAO.validate(user)) {
			throw new NonExistentUserException("Usuário não cadastrado");
		}
	}

	private void validate(User user, String reason)
			throws EmptyUserException, EmptyUserEmailException, EmptyReasonException, NonExistentUserException {
		emptyUser(user, reason, true);
		nonExistentUser(user);
	}

	private void validate(User user) throws UserAlreadyExistsException {
		if (userDAO.validate(user) && !checkStatusUser(user)) {
			throw new UserAlreadyExistsException(MESSAGE_ERROR_USER_EXIST);
		}
	}

	private void validate(String[] categories) throws EmptyCategoriesException {
		if (categories == null) {
			throw new EmptyCategoriesException(EMPTY_CATEGORIES);
		}
	}

	public void emptyUser(User user) throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException {

		if (emptyUserEmail(user) && emptyUserTel(user)) {
			throw new EmptyUserException(EMPTY_USER);
		} else if (emptyUserEmail(user)) {
			throw new EmptyUserEmailException(EMPTY_USER_EMAIL);
		} else if (emptyUserTel(user)) {
			throw new EmptyUserTelException(EMPTY_USER_TEL);
		}

	}

	public void emptyUser(User user, String reason, Boolean notCheckTel)
			throws EmptyUserException, EmptyUserEmailException, EmptyReasonException {
		if (emptyUserEmail(user)) {
			throw new EmptyUserEmailException(EMPTY_USER_EMAIL);
		} else if (emptyReason(reason)) {
			throw new EmptyReasonException(EMPTY_REASON);
		}

	}

	public Boolean emptyUserEmail(User user) {
		return user.getEmail().trim().isEmpty();
	}

	public Boolean emptyUserTel(User user) {
		return user.getTel().trim().isEmpty();
	}

	public Boolean emptyReason(String reason) {
		return reason.trim().isEmpty();
	}

}
