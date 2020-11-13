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

import entity.MedicType;

public class MedicTypeDAO {
	private Connection myConn;

	public MedicTypeDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB medicType connection success");
	}

	public List<MedicType> readAll() throws Exception {
		List<MedicType> list = new ArrayList<MedicType>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM type_medic");
			while (myRs.next()) {
				MedicType tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<MedicType> search(String name) throws Exception {
		List<MedicType> list = new ArrayList<MedicType>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name = "%" + name + "%";
			myStmt = myConn.prepareStatement("SELECT * FROM type_medic WHERE type LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				MedicType tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(MedicType entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("insert into type_medic" + " (type)" + " values (?)");
			myStmt.setString(1, entity.getType());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<MedicType> read(Long id) throws Exception {
		List<MedicType> list = new ArrayList<MedicType>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM type_medic WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				MedicType tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(String typeNew, String typeOld) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE type_medic SET type=? WHERE type=?");
			myStmt.setString(1, typeNew);
			myStmt.setString(2, typeOld);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(String type) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM type_medic WHERE type=?");
			myStmt.setString(1, type);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private MedicType convertRowToEntity(ResultSet myRs) throws SQLException {
		String type = myRs.getString("type");
		MedicType temp = new MedicType(type);
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
