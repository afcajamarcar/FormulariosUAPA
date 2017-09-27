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
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;



//Notice, do not import com.mysql.jdbc.*
//or you will have problems!

public class Querys {

	private Connection conn;
	
	/*
	 * Strings for querys and database connection
	 */
	private final String dbUrl = "jdbc:mysql://UAPA03:3306/uapa_db?verifyServerCertificate=false&useSSL=true";
	private String db = "uapa_db.";
	private String personas_unal_uapa = "personas_unal_uapa";
	private String rel_estudiante_programa = "rel_estudiante_programa";
	private String consolidado_reconocimientos_estudiantiles = "consolidado_reconocimientos_estudiantiles";
	

	/**
	 * Empty constructor
	 */
	public Querys() {
	}
	
	/**
	 * Tries to connect to UAPA's database by using the given user and pass
	 * @param user
	 * @param pass
	 */
	public void connect(String user, String pass) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				if(user.length() == 0 || pass.length() == 0) {
					JOptionPane.showMessageDialog(null, "El ususario o la contrasena no pueden estar vacios.");
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
	
	public void disconnect() {
		try {
			if(conn.isClosed()) {
				JOptionPane.showMessageDialog(null, "Ya se ha desconectado");
			}else {
				conn.close();
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
				
				stmt = conn.prepareStatement("SELECT * FROM "+ db+personas_unal_uapa+" WHERE dni_persona=?" );
				stmt.setString(1, dni);
				ResultSet result = stmt.executeQuery();
				ResultSetMetaData rsmd = result.getMetaData();

				int columnsNumber = rsmd.getColumnCount();
				boolean val = result.next();
				if(!val) JOptionPane.showMessageDialog(null, "No se encontró información sobre: " +dni.toString()+ " en: " +db+personas_unal_uapa );
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
		}
	}

	public void getProgramStudent(String dni) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM "+ db+rel_estudiante_programa+" WHERE dni_estudiante=?" );
				stmt.setString(1, dni);
				ResultSet result = stmt.executeQuery();
				ResultSetMetaData rsmd = result.getMetaData();

				int columnsNumber = rsmd.getColumnCount();
				boolean val = result.next();
				if(!val) JOptionPane.showMessageDialog(null, "No se encontro informacion sobre: "+dni.toString()+" en: "+db+personas_unal_uapa);
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
		}
	}
	public void getConsolidateStudent(String dni) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM "+ db+consolidado_reconocimientos_estudiantiles+" WHERE dni_estudiante=?" );
				stmt.setString(1, dni);
				ResultSet result = stmt.executeQuery();

				
				String[][] data = toMatrix(result);
				if(data == null) JOptionPane.showMessageDialog(null, "No se encontro informacion sobre: "+dni.toString()+" en: "+db+consolidado_reconocimientos_estudiantiles);
				
				printMatrix(data);
				
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
		}
	}
	
	public String[][] toMatrix(ResultSet result){
		String[][] data = null;
		String[] row = null;
		try {
			result.last();
			int rowCount = result.getRow();
			result.beforeFirst();
			boolean val = result.next();
			ResultSetMetaData rsmd = result.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			data = new String[rowCount][columnsNumber];
			row = new String[columnsNumber];
			System.out.println(rowCount);
			while (val) {
				for (int i = 0; i < columnsNumber; i++) {
					for(int j = 0; j< rowCount; j++ ) {
						row[j] = result.getString(j+1);
						System.out.println(row[j]);
					}
					data[i+1]= row;
					//resultMatrix[i] = new String[result.getString(i)];	
				}
				val = result.next();
			}
		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return data;
	}
	
	public void printMatrix(String [][] data) {
		System.out.println(Arrays.deepToString(data));
		/*
		 *for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.println(data[i]);
			}
		} 
		 */
	}
	
	public static void main(String[] args) {
		Querys consult = new Querys();
		consult.connect("root", "UAPA2017");
		//consult.getStudent("1014261438");
		System.out.println("-----------------");
		consult.getConsolidateStudent("1000575249");
		System.out.println("-----------------");
		//consult.getProgramStudent("1014261438");
	}
}