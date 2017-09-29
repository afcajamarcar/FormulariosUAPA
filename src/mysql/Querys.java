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
	//private String personas_unal_uapa = "personas_unal_uapa";
	private String estudiantes = "estudiantes";
	private String rel_estudiante_programa = "rel_estudiante_programa";
	private String consolidado_reconocimientos_estudiantiles = "consolidado_reconocimientos_estudiantiles";
	private String reconocimientos = "reconocimientos";
	

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
	/*
	 * Query 
	 */
	public void getStudent(String dni) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM "+ db+estudiantes+" WHERE dni_estudiante=?" );
				stmt.setString(1, dni);
				ResultSet result = stmt.executeQuery();

				
				String[][] data = toMatrix(result);
				if(data == null) JOptionPane.showMessageDialog(null, "No se encontro informacion sobre: "+dni.toString()+" en: "+db+estudiantes);
				
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

	public void getProgramStudent(String dni) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM "+ db+rel_estudiante_programa+" WHERE dni_estudiante=?" );
				stmt.setString(1, dni);
				ResultSet result = stmt.executeQuery();

				
				String[][] data = toMatrix(result);
				if(data == null) JOptionPane.showMessageDialog(null, "No se encontro informacion sobre: "+dni.toString()+" en: "+db+rel_estudiante_programa);
				
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
	
	//TODO not extracting full query
	public void getAwards() {
		isConnected();
		//ArrayList<String> rec_lst = new ArrayList<String>();
		
		PreparedStatement stmt = null;
		try {
			try {
				stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM "+ db+reconocimientos );
				ResultSet result = stmt.executeQuery();
				
				String[][] data = toMatrix(result);
				if(data == null) JOptionPane.showMessageDialog(null, "No se encontro informacion en: "+db+reconocimientos);
				
				printMatrix(data);
				result.close();
				
				/*
				while(result.next()) {
					
					String a = result.getString("cod_reconocimiento");
					String b = result.getString("tipo_reconocimiento");
					String c = result.getString("nombre_reconocimiento");
					String d = result.getString("ambito_reconocimiento");
					String e = result.getString("caracter");
					String f = result.getString("institucion_otorga");
					String g = result.getString("pais_institucion");
					
					String rs = a+", "+b+", "+c+", "+d+", "+e+", "+f+", "+g;
					
					rec_lst.add(rs);
					
				}
				
				System.out.println("Results: "+rec_lst.size());
				
				for (String string : rec_lst) {
					System.out.println(string);
				}
				*/
								
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
			System.out.println("Total results: "+rowCount);
			
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
		
	public static void main(String[] args) {
		Querys consult = new Querys();

		
	}
}