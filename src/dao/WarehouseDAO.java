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
import entity.Medic;
import entity.Warehouse;

public class WarehouseDAO {
	private Connection myConn;

	public WarehouseDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB workhouse connection success");
	}

	public List<Warehouse> readAll() throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM workhouse");
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Warehouse> readAllWithCountMoreThenZero() throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM workhouse WHERE count>0");
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Warehouse> readAllByIdProduct(Long id_product) throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("SELECT * FROM workhouse WHERE medic = ?");
			myStmt.setLong(1, id_product);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Warehouse> search(String productName) throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			productName = "%" + productName + "%";
			myStmt = myConn.prepareStatement(
					"SELECT * FROM workhouse WHERE medic in (SELECT id FROM medic WHERE name LIKE ?)");
			myStmt.setString(1, productName);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	public List<Warehouse > searchByDeadDateDay(int day) throws Exception {
		List<Warehouse > list = new ArrayList<Warehouse >();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {

			myStmt = myConn.prepareStatement("SELECT * FROM workhouse WHERE DATE_ADD(date_getting, INTERVAL ? DAY) > date_dead");
			myStmt.setInt(1, day);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Warehouse entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("INSERT INTO workhouse"
					+ " (medic, count,date_getting,date_dead,price_getting, price_selling)" + " values ( ?, ?, ?, ?, ?, ?)");
			myStmt.setLong(1, entity.getMedic().getId());
			myStmt.setInt(2, entity.getCount());
			myStmt.setDate(3, entity.getDateGetting());
			myStmt.setDate(4, entity.getDateDead());
			myStmt.setFloat(5, entity.getPriceGetting());
			myStmt.setFloat(6, entity.getPricaeSelling());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Warehouse> read(Long id) throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM workhouse WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Warehouse entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement(
					"UPDATE workhouse SET medic=?, count=? , date_getting=?, date_dead=?,price_getting=?, price_selling=? WHERE id=?");
			myStmt.setLong(1, entity.getMedic().getId());
			myStmt.setInt(2, entity.getCount());
			myStmt.setDate(3, entity.getDateGetting());
			myStmt.setDate(4, entity.getDateDead());
			myStmt.setFloat(5, entity.getPriceGetting());
			myStmt.setFloat(6, entity.getPricaeSelling());
			myStmt.setLong(7, entity.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM workhouse WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Warehouse convertRowToEntity(ResultSet myRs) throws SQLException {
		Long id = myRs.getLong("id");
		Long id_medic = myRs.getLong("medic");
		int count = myRs.getInt("count");
		Date dategetting = myRs.getDate("date_getting");
		Date datedead = myRs.getDate("date_dead");
		Float pricegetting = myRs.getFloat("price_getting");
		Float priceselling = myRs.getFloat("price_selling");
		Medic medic = null;
		try {
			medic = ApplicationController.MedicController.getDAO().read(id_medic).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Warehouse temp = new Warehouse(medic, count, dategetting, datedead, pricegetting, priceselling);
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
