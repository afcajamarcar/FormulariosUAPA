/**
 * Developed by: UAPA
 * 18/09/2017
 */
package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



//Notice, do not import com.mysql.jdbc.*
//or you will have problems!

public class LoadDriver {

	private Connection conn;
	private final String dbUrl = "jdbc:mysql://UAPA03:3306/uapa_db?verifyServerCertificate=false&useSSL=true";
	private String db = "uapa_db."; 

	/**
	 * Empty constructor
	 */
	public LoadDriver() {
	}
	/**
	 * Tries to connect to a remote database by using the given user and pass
	 * @param user
	 * @param pass
	 */
	public void connect(String user, String pass) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				if(user.length() == 0 || pass.length() == 0) {
					JOptionPane.showMessageDialog(null, "El ususario o la contrase�a no pueden estar vacios.");
				}		
				conn = DriverManager.getConnection(dbUrl,user,pass);
			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void isConnected() {
		try {
			if(conn.isClosed()) {
				JOptionPane.showMessageDialog(null, "No conectado a la base de datos");
			}
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	//QUERYS
	public void getStudent(String dni) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT * FROM "+ db+"personas_unal_uapa"+" WHERE dni_persona=?" );
				stmt.setString(1, dni);
				ResultSet result = stmt.executeQuery();
				ResultSetMetaData rsmd = result.getMetaData();

				int columnsNumber = rsmd.getColumnCount();
				while (result.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
						if (i > 1) System.out.print(",  ");
						String columnValue = result.getString(i);
						System.out.print(columnValue + " " + rsmd.getColumnName(i));
					}
					System.out.println("");
				}
				JOptionPane.showMessageDialog(null, "No hay informacion");
			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		} 
		finally {
			try {
				if (stmt != null) { stmt.close(); }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) { conn.close(); }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void getProgramStudent(String dni) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM "+ db+"rel_estudiante_programa"+" WHERE dni_estudiante=?" );
				stmt.setString(1, dni);
				ResultSet result = stmt.executeQuery();
				ResultSetMetaData rsmd = result.getMetaData();

				int columnsNumber = rsmd.getColumnCount();
				while (result.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
						if (i > 1) System.out.print(",  ");
						String columnValue = result.getString(i);
						System.out.print(columnValue + " " + rsmd.getColumnName(i));
					}
					System.out.println("");
				}
				JOptionPane.showMessageDialog(null, "No hay informacion");
			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		} 
		finally {
			try {
				if (stmt != null) { stmt.close(); }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) { conn.close(); }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void getConsolidateStudent(String dni) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM "+ db+"consolidado_reconocimientos_estudiantiles"+" WHERE dni_estudiante=?" );
				stmt.setString(1, dni);
				ResultSet result = stmt.executeQuery();
				ResultSetMetaData rsmd = result.getMetaData();

				int columnsNumber = rsmd.getColumnCount();
				boolean val = result.next();
				if(!val) JOptionPane.showMessageDialog(null, "No hay informacion");
				while (val) {
					for (int i = 1; i <= columnsNumber; i++) {
						if (i > 1) System.out.print(",  ");
						String columnValue = result.getString(i);
						System.out.print(columnValue + " " + rsmd.getColumnName(i));
					}
					val = result.next();
					System.out.println("");
				}
				
			}catch(SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		} 
		finally {
			try {
				if (stmt != null) { stmt.close(); }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) { conn.close(); }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		LoadDriver load = new LoadDriver();

		load.connect("root", "UAPA2017");
		load.getConsolidateStudent("1000178793");

	}
}