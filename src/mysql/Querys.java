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
import java.util.Arrays;

import javax.swing.JOptionPane;

//Notice, do not import com.mysql.jdbc.*
//or you will have problems!

public class Querys {

	private Connection conn;
	
	/*
	 * Strings for querys and database connection
	 */
	private final String dbUrl = "jdbc:mysql://UAPA03:3306/uapa_db?verifyServerCertificate=false&useSSL=true";
	private String db = "uapa_db.";	

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
	
	/**
	 * Checks the state of the connection
	 */
	public boolean isConnected() {
		if(conn == null) {
			JOptionPane.showMessageDialog(null, "No conectado a la base de datos");
			return false;
		}
		return true;
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
	
	/**
	 * Unique query for consulting data from uapa's db.
	 * @param field_name
	 * @param field_value
	 * @param table
	 * @return
	 */
	public String[][] getSomeFromTable(String field_name, String field_value, String table) {
		isConnected();
		PreparedStatement stmt = null;
		
		try {
			try {
				stmt = conn.prepareStatement("SELECT * FROM "+ db+table+" WHERE "+field_name+"=?" );
				stmt.setString(1, field_value);
				ResultSet result = stmt.executeQuery();

				
				String [][] data = toMatrix(result);
				if(data == null) JOptionPane.showMessageDialog(null, "No se encontro informacion sobre: "+field_value.toString()+" en: "+db+table);
				
				return data;
				
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
		return null;
	}
	
	/**
	 * Query for absolute consults
	 * @param table
	 */
	public String[][] getAllFromTable(String table) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT * FROM "+ db+table );
				ResultSet result = stmt.executeQuery();

				
				String[][] data = toMatrix(result);
				if(data == null) JOptionPane.showMessageDialog(null, "No se encontro informacion en: "+db+table);
				
				return data;
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
		return null;
	}
	
	/**
	 * Query for displaying the column names of a given table, used for fieldbox mostly.
	 * @param table
	 * @return
	 */
	public String[] getColumnNames(String table) {
		isConnected();
		PreparedStatement stmt = null;
		String[] columnNames = null; 
		try {
			try {
				if(table == "Seleccionar tabla...") {
					return new String[0];
				}
				stmt = conn.prepareStatement("SELECT * FROM "+ db+table );
				ResultSet result = stmt.executeQuery();
				ResultSetMetaData rsmd = result.getMetaData();
				
				columnNames = new String[rsmd.getColumnCount()];
				 
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					columnNames[i] = rsmd.getColumnName(i+1);
				}
				
				
				if(columnNames.length == 0) JOptionPane.showMessageDialog(null, "No se encontro informacion en: "+db+table);
				
				return columnNames;
				
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
		return null;

	}
	public void addPerson(String dni_persona, String tipo_dni_persona, String nombres, String apellidos) {
		isConnected();
		Statement stmt = null;
		try {
			try {
				stmt = conn.createStatement();
				int result = stmt.executeUpdate("INSERT INTO " +db+ "personas_unal_uapa "+
				"(dni_persona, tipo_dni_persona, nombres, apellidos, nombre_completo)"+
						"VALUES("+"'"+dni_persona+"',"+"'"+tipo_dni_persona+"',"+"'"+nombres+"',"+"'"+apellidos+"',"+"'"+nombres+" "+apellidos+"')"
						);
				if(result != 0) {
					JOptionPane.showMessageDialog(null, "No se pudo a�adir a: " +nombres+" "+apellidos);
				}else {
					JOptionPane.showMessageDialog(null, nombres+" "+apellidos+ " ha sido a�adido.");
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
	public String[] getCountry() {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT id_pais,pais FROM uapa_db.v_paises");
				ResultSet result = stmt.executeQuery();
				String[][] countries = toMatrix(result);
				String[] countries_fil = new String[countries.length-1];
				for (int i = 1; i < countries.length; i++) {
					countries_fil[i-1] = countries[i][0] +"-"+countries[i][1]; 
				}
				return countries_fil;
				
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
		return null;
	}
	
	/**
	 * TODO Query for AddAwardFrame 
	 */
	
	/**
	 * Puts the resultset into a matrix
	 * @param result
	 * @return
	 */
	public String[][] toMatrix(ResultSet result){
		String[][] data = null;
		String[] row = null;
		String[] row_names = null;
		try {
			result.last();
			int rowCount = result.getRow();
			result.beforeFirst();
			boolean val = result.next();
			
			ResultSetMetaData rsmd = result.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			data = new String[rowCount+1][columnsNumber];
			row_names = new String[columnsNumber];
			
			while (val) {
				int j = 0;
				row = new String[columnsNumber];
				while(j < columnsNumber) {
					row[j] = result.getString(j+1);
					row_names[j] = rsmd.getColumnName(j+1);
					j++;
				}
				data[result.getRow()] = row;
				val = result.next();
			}
			data[0] = row_names;

		}catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return data;
	}
	
	public void printMatrix(String [][] data) {
		System.out.println(Arrays.deepToString(data));		
	}
}