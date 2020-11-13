package entity;

import java.sql.Date;

public class Order extends AbstractEntity{
	
	private Users users;
	private Medic medic;
	private int count;
	private Date date;
	private boolean isGot;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(Users users, Medic medic, int count, Date date, boolean isGot) {
		super();
		this.users = users;
		this.medic = medic;
		this.count = count;
		this.date = date;
		this.isGot = isGot;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Medic getMedic() {
		return medic;
	}
	public void setMedic(Medic medic) {
		this.medic = medic;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isGot() {
		return isGot;
	}
	public void setGot(boolean isGot) {
		this.isGot = isGot;
	}
	@Override
	public String toString() {
		return "users=" + users + ", medic=" + medic + ", count=" + count + ", date=" + date + ", isGot=" + isGot;
	}
	
}
