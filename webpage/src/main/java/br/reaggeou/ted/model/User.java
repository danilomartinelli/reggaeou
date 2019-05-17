package br.reaggeou.ted.model;

public class User {

	private Integer idUser;
	private String email;
	private String tel;
	private StatusUser status;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public StatusUser getStatus() {
		return status;
	}

	public void setStatus(StatusUser status) {
		this.status = status;
	}
	
}
