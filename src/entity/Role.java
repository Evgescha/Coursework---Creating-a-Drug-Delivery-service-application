package entity;

public class Role extends AbstractEntity{
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Role(String role) {
		super();
		this.role = role;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return role;
	}
	
	
}
