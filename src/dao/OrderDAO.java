package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import controller.ApplicationController;
import controller.UserOrderController;
import entity.Medic;
import entity.Order;
import entity.Users;

public class OrderDAO {
	private Connection myConn;

	public OrderDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB ORDER connection success");
	}

	public List<Order> readAll() throws Exception {
		List<Order> list = new ArrayList<Order>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM medicinedeliverysimulator.order");
			while (myRs.next()) {
				Order tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Order> search(String productName) throws Exception {
		List<Order> list = new ArrayList<Order>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			productName = "%" + productName + "%";
			myStmt = myConn.prepareStatement(
					"SELECT * FROM medicinedeliverysimulator.order WHERE users in (SELECT id from users where fio LIKE ?)");
			myStmt.setString(1, productName);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Order tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Order entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into medicinedeliverysimulator.order"
					+ " (users, medic, count, date_getting, is_got)" + " values (?, ?, ?, ?,?)");
			myStmt.setLong(1, entity.getUsers().getId());
			myStmt.setLong(2, entity.getMedic().getId());
			myStmt.setLong(3, entity.getCount());
			myStmt.setDate(4, entity.getDate());
			myStmt.setBoolean(5, entity.isGot());
			myStmt.executeUpdate();

			
		} finally {
			close(myStmt);
		}
	}

	public List<Order> read(Long id) throws Exception {
		List<Order> list = new ArrayList<Order>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM medicinedeliverysimulator.order WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Order tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Order entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE medicinedeliverysimulator.order SET users=?, medic=?, count=?, date_getting=?,is_got=?  WHERE id=?");
			myStmt.setLong(1, entity.getUsers().getId());
			myStmt.setLong(2, entity.getMedic().getId());
			myStmt.setLong(3, entity.getCount());
			myStmt.setDate(4, entity.getDate());
			myStmt.setBoolean(5, entity.isGot());
			myStmt.setLong(6, entity.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			Order delivery = read(id).get(0);

			myStmt = myConn.prepareStatement("DELETE FROM medicinedeliverysimulator.order WHERE id=?");

			myStmt.setLong(1, id);
			myStmt.executeUpdate();			
		} finally {
			close(myStmt);
		}
	}

	private Order convertRowToEntity(ResultSet myRs) throws SQLException {
		Medic medic = null;
		
		Users users = null;
		Order temp = null;
		try {
			Long id = myRs.getLong("id");
			Long id_users = myRs.getLong("users");
			Long id_medic = myRs.getLong("medic");
			int count = myRs.getInt("count");
			Date dategetting = myRs.getDate("date_getting");
			Boolean isGot = myRs.getBoolean("is_got");

			users = ApplicationController.UsersController.getDAO().read(id_users).get(0);
			medic = ApplicationController.MedicController.getDAO().read(id_medic).get(0);

			temp = new Order(users, medic,count, dategetting, isGot);
			temp.setId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);
	}

	public List<Order> searchMyUserAndMedic(String medic,boolean isGot) throws SQLException {
		List<Order> list = new ArrayList<Order>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			medic = "%" + medic + "%";
			myStmt = myConn.prepareStatement(
					"SELECT * FROM medicinedeliverysimulator.order WHERE is_got=? and users=? and medic in (SELECT id from medic where name LIKE ?)");
			myStmt.setBoolean(1, isGot);
			myStmt.setLong(2, UserOrderController.user.getId());
			myStmt.setString(3, medic);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Order tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
}
