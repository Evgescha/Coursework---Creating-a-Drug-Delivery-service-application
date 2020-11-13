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

import controller.ApplicationController;
import entity.Medic;
import entity.MedicType;


public class MedicDAO {
	private Connection myConn;

	public MedicDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB medic connection success");
	}

	public List<Medic > readAll() throws Exception {
		List<Medic > list = new ArrayList<Medic >();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM medic");
			while (myRs.next()) {
				Medic tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Medic > search(String name) throws Exception {
		List<Medic > list = new ArrayList<Medic >();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name= "%" + name+ "%";
			myStmt = myConn.prepareStatement("SELECT * FROM medic WHERE name LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Medic tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	

	public void create(Medic entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("insert into medic" + " (name, size_type,type_medic)" + " values (?, ?,?)");
			myStmt.setString(1, entity.getName());
			myStmt.setString(2, entity.getsizeType());
			myStmt.setString(3, entity.getType().getType());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Medic > read(Long id) throws Exception {
		List<Medic > list = new ArrayList<Medic >();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM medic WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				Medic tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Medic entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE medic SET name=?, size_type=?, type_medic=? WHERE id=?");
			myStmt.setString(1, entity.getName());
			myStmt.setString(2, entity.getsizeType());
			myStmt.setString(3, entity.getType().getType());
			myStmt.setLong(4, entity.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM medic WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Medic  convertRowToEntity(ResultSet myRs) throws SQLException {
		Long id = myRs.getLong("id");
		String name= myRs.getString("name");
		String size_type= myRs.getString("size_type");
		String type_medic= myRs.getString("type_medic");

		Medic temp = new Medic (name, new MedicType(type_medic),size_type);
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
