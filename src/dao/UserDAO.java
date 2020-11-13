package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import entity.Users;


public class UserDAO {
	private Connection myConn;

	public UserDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB USER connection success");
	}

	public List<Users > readAll() throws Exception {
		List<Users > list = new ArrayList<Users >();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM users");
			while (myRs.next()) {
				Users tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Users > searchByFIO(String name) throws Exception {
		List<Users > list = new ArrayList<Users >();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name= "%" + name+ "%";
			myStmt = myConn.prepareStatement("SELECT * FROM Users WHERE fio LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Users tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	public Users searchByFIOAndPhone(String fio, String phone) throws Exception {
		List<Users > list = new ArrayList<Users >();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = myConn.prepareStatement("SELECT * FROM Users WHERE fio=? AND phone=?");
			myStmt.setString(1, fio);
			myStmt.setString(2, phone);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Users tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list.size()==0?null:list.get(0);
			
		} finally {
			close(myStmt, myRs);
		}
	}
	public void create(Users entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("insert into Users" + " (fio, adres, phone, role)" + " values (?, ?,?,?)");
			myStmt.setString(1, entity.getFio());
			myStmt.setString(2, entity.getAdres());
			myStmt.setString(3, entity.getPhone());
			myStmt.setString(4, entity.getRole());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Users > read(Long id) throws Exception {
		List<Users > list = new ArrayList<Users >();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM Users WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				Users tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Users entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE Users SET fio=?, adres=?, phone=? WHERE id=?");
			myStmt.setString(1, entity.getFio());
			myStmt.setString(2, entity.getAdres());
			myStmt.setString(3, entity.getPhone());
			myStmt.setLong(4, entity.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM Users WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Users  convertRowToEntity(ResultSet myRs) throws SQLException {
		Long id = myRs.getLong("id");
		String fio= myRs.getString("fio");
		String adres= myRs.getString("adres");
		String phone= myRs.getString("phone");
		String role= myRs.getString("role");

		Users temp = new Users (fio, adres, phone, role);
		temp.setId(id);
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
}
