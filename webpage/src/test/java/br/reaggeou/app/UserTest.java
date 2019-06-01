package br.reaggeou.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.reaggeou.builder.UserBuilder;
import br.reaggeou.ted.business.UserBO;
import br.reaggeou.ted.exception.EmptyReasonException;
import br.reaggeou.ted.exception.EmptyUserEmailException;
import br.reaggeou.ted.exception.EmptyUserException;
import br.reaggeou.ted.exception.EmptyUserTelException;
import br.reaggeou.ted.exception.NonExistentUserException;
import br.reaggeou.ted.model.StatusUser;
import br.reaggeou.ted.model.User;
import br.reaggeou.ted.persistence.UserDAO;

public class UserTest {
	UserBO userBO;
	UserBuilder userBuilder;
	UserDAO userDAOMock;
	
	@Before
	public void setup() {
		userBuilder = new UserBuilder();
		
		userBuilder.setIdUser(1);
		userBuilder.setEmail("danilo@email.com");
		userBuilder.setTel("71993037767");
		userBuilder.setStatus(StatusUser.ACTIVE);
		
		User user1 = userBuilder.build();
		
		userBuilder.setIdUser(2);
		userBuilder.setEmail("stephanie@email.com");
		userBuilder.setTel("71998821098");
		userBuilder.setStatus(StatusUser.ACTIVE);
		
		User user2 = userBuilder.build();
		
		userBO = new UserBO();
		
		userDAOMock = Mockito.mock(UserDAO.class);
		
		Mockito.when(userDAOMock.validate(user1)).thenReturn(true);
		Mockito.when(userDAOMock.validate(user2)).thenReturn(false);	
	}
	
	@Test(expected = NonExistentUserException.class)
	public void nonExistentUserTest() throws NonExistentUserException {
		userBuilder.setIdUser(2);
		userBuilder.setEmail("stephanie@email.com");
		userBuilder.setTel("71998821098");
		userBuilder.setStatus(StatusUser.ACTIVE);
		
		User user = userBuilder.build();
		
		UserBO wrapperUserBO = new UserBO(userDAOMock);
		wrapperUserBO.nonExistentUser(user);
	}

	@Test
	public void emptyUserEmailTest() {
		userBuilder.setEmail("");
		userBuilder.setTel("71993037767");
		User user = userBuilder.build();
		
		Assert.assertEquals(true, userBO.emptyUserEmail(user));
	}
	
	@Test
	public void emptyUserTelTest() {
		userBuilder.setEmail("danilo@email.com");
		userBuilder.setTel("");
		User user = userBuilder.build();
		
		Assert.assertEquals(true, userBO.emptyUserTel(user));
	}
	
	@Test
	public void emptyReasonTest() {
		Assert.assertEquals(true, userBO.emptyReason(""));
	}
	
	@Test(expected = EmptyUserEmailException.class)
	public void emptyUserWithoutEmailTest() throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException {
		userBuilder.setEmail("");
		userBuilder.setTel("71993037767");
		User user = userBuilder.build();
		
		userBO.emptyUser(user);
	}
	
	@Test(expected = EmptyUserTelException.class)
	public void emptyUserWithoutTelTest() throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException {
		userBuilder.setEmail("danilo@email.com");
		userBuilder.setTel("");
		User user = userBuilder.build();
		
		userBO.emptyUser(user);
	}
	
	@Test(expected = EmptyUserEmailException.class)
	public void emptyUserNotCheckTel1Test() throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException, EmptyReasonException {
		userBuilder.setEmail("");
		userBuilder.setTel("");
		User user = userBuilder.build();
		
		userBO.emptyUser(user, "foo", true);
	}
	
	@Test(expected = EmptyReasonException.class)
	public void emptyUserNotCheckTel2Test() throws EmptyUserException, EmptyUserEmailException, EmptyUserTelException, EmptyReasonException {
		userBuilder.setEmail("danilo@email.com");
		userBuilder.setTel("");
		User user = userBuilder.build();
		
		userBO.emptyUser(user, "", true);
	}
	
}
