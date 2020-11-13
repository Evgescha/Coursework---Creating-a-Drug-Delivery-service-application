package entity;

public class Users extends AbstractEntity {
	private String fio;
	private String adres;
	private String phone;
	private String role;

	public Users() {
		super();
	}

	
	public Users(String fio, String adres, String phone, String role) {
		super();
		this.fio = fio;
		this.adres = adres;
		this.phone = phone;
		this.role = role;
	}


	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return adres;
	}

	public void setEmail(String email) {
		this.adres = email;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	@Override
	public String toString() {
		return fio;
	}

}
