package br.reaggeou.ted.test;

import org.junit.*;

import br.reaggeou.ted.model.User;
import br.reaggeou.ted.persistence.UserDAO;

public class UserDAOTest {
	
	private static final String BEFORE = "Iniciado o teste";
	private static final String AFTER = "Teste finalizado";
	private UserDAO userDAO = new UserDAO();
	
	@Before
	public void before() {
		System.out.println(BEFORE);
	}
	
	@After
	public void after() {
		System.out.println(AFTER);
	}
	
}
