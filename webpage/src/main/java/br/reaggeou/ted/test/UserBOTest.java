package br.reaggeou.ted.test;

import org.junit.After;
import org.junit.Before;

import br.reaggeou.ted.business.UserBO;

public class UserBOTest {
	
	private static final String BEFORE = "Iniciado o teste";
	private static final String AFTER = "Teste finalizado";
	
	
	@Before
	public void before() {
		System.out.println(BEFORE);
	}
	
	@After
	public void after() {
		System.out.println(AFTER);
	}
	
}
