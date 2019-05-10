package br.reaggeou.ted.business;

import java.util.List;

import br.reaggeou.ted.model.Event;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.persistence.UserDAO;

public class UserBO {
	
	private UserDAO userDAO = new UserDAO();
	
	public void insertUser(User user) {
		userDAO.insertUser(user);
	}
	
	public List<Event> listEvents() {
		return null;
	}
	
}
