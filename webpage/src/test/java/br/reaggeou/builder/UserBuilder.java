package br.reaggeou.builder;

import br.reaggeou.ted.model.StatusUser;
import br.reaggeou.ted.model.User;

public class UserBuilder {
	private Integer idUser;
	private String email;
	private String tel;
	private StatusUser status;
	
	public UserBuilder() {
		super();
		this.idUser = 1;
		this.email = "";
		this.tel = "";
		this.status = StatusUser.ACTIVE;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setStatus(StatusUser status) {
		this.status = status;
	}

	public User build() {
		User user = new User();
		user.setIdUser(idUser);
		user.setEmail(email);
		user.setTel(tel);
		user.setStatus(status);
		
		return user;
	}


}
