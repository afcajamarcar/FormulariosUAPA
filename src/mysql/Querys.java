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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import entities.Person;

//Notice, do not import com.mysql.jdbc.*
//or you will have problems!

public class Querys {

	private Connection conn;
	
	/**
	 * Strings with database name and port
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
	 * @return true if the connection is active, false otherwise
	 */
	public boolean isConnected() {
		if(conn == null) {
			JOptionPane.showMessageDialog(null, "No conectado a la base de datos");
			return false;
		}
		return true;
	}
	
	/**
	 * Closes de conection with the database
	 */
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
	 * @return all data from a certain table
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
	
	/**
	 * Query for inserting a new person into personas_unal_uapa table.
	 * @param dni_persona
	 * @param tipo_dni_persona
	 * @param nombres
	 * @param apellidos
	 */
	public void addPerson(String dni_persona, String tipo_dni_persona, String nombres, String apellidos, String usuarioUnal) {
		isConnected();
		Statement stmt = null;
		try {
			try {
				stmt = conn.createStatement();
				int result = stmt.executeUpdate("INSERT INTO " +db+ "personas_unal_uapa "+
				"(dni_persona, tipo_dni_persona, nombres, apellidos, nombre_completo, usuario_unal)"+
						"VALUES("+"'"+dni_persona+"',"+"'"+tipo_dni_persona+"',"+"'"+nombres+"',"+"'"+apellidos+"',"+"'"+nombres+" "+apellidos+"',"+"'"+usuarioUnal+"')"
						);
				if(result == 0) {
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
	
	/**
	 * Query for obtaining all the data (id_pais and pais) from v_paises
	 * @return An array with al the countries in said table 
	 */
	public String[] getCountry() {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT id_pais,pais FROM "+ db +"v_paises");
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
	 * Performs a query where the names of all tables in uapa_db database are returned
	 * It's done in order to fill programmatically drop lists in the form
	 * @return All tables names in uapa_db.
	 */
	public String[] getTableNames() {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT TABLE_NAME \r\n" + 
						"FROM INFORMATION_SCHEMA.TABLES\r\n" + 
						"WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='uapa_db';");
				ResultSet result = stmt.executeQuery();
				String[][] tables = toMatrix(result);
				String[] tables_fil = new String[tables.length-1];
				for (int i = 1; i < tables.length; i++) {
					tables_fil[i-1] = tables[i][0]; 
				}
				return tables_fil;
				
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
	 * Gets Caracter values from reconocimientos.
	 * @return Array of characters from awards.
	 */
	public String[] getCaracter() {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT caracter FROM " +db+"reconocimientos;");
				ResultSet result = stmt.executeQuery();
				Set<String> s = new HashSet<String>(64);
				int aux = 0;
				String[][] recos = toMatrix(result);
				for (int i = 1; i < recos.length; i++) {
					s.add(recos[i][0]); 
				}
				String[] recos_fil = new String[s.size()];
				for (String caracter : s) {
					recos_fil[aux] = caracter;
					aux+=1;
				}
				return recos_fil;
				
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
	 * Gets Ambitos values from reconocimientos.
	 * @return Array of areas from awards.
	 */
	public String[] getAmbito() {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT ambito_reconocimiento FROM " +db+"reconocimientos;");
				ResultSet result = stmt.executeQuery();
				Set<String> s = new HashSet<String>(64);
				int aux = 0;
				String[][] areas = toMatrix(result);
				for (int i = 1; i < areas.length; i++) {
					s.add(areas[i][0]); 
				}
				String[] areas_fil = new String[s.size()+1];
				for (String caracter : s) {
					areas_fil[aux] = caracter;
					aux+=1;
				}
				areas_fil[areas_fil.length-1] = "Otro"; //Added in case someone wants to add a new area 
				return areas_fil;
				
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
	
	public void addAward(String cod_reconocimiento, String tipo_reconocimiento, String nombre_reconocimiento, String ambito_reconocimiento, String caracter, String institucion_otorga, String pais_institucion) {
		isConnected();
		Statement stmt = null;
		try {
			try {
				stmt = conn.createStatement();
				int result = stmt.executeUpdate("INSERT INTO " +db+ "reconocimientos "+
				"(cod_reconocimiento, tipo_reconocimiento, nombre_reconocimiento, ambito_reconocimiento, caracter, institucion_otorga, pais_institucion)"+
						"VALUES("+"'"+cod_reconocimiento+"',"+"'"+tipo_reconocimiento+"',"+"'"+nombre_reconocimiento+"',"+"'"+ambito_reconocimiento+"',"+"'"+caracter+"' ,"+"'"+institucion_otorga+"' ,"+"'"+pais_institucion+"')"
						);
				if(result == 0) {
					JOptionPane.showMessageDialog(null, "No se pudo a�adir a: " +cod_reconocimiento+" "+nombre_reconocimiento);
				}else {
					JOptionPane.showMessageDialog(null, cod_reconocimiento+" "+nombre_reconocimiento+ " ha sido a�adido.");
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

	public String[] getPeriod() {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT periodo FROM "+ db +"consolidado_reconocimientos_estudiantiles");
				ResultSet result = stmt.executeQuery();
				String[][] periods = toMatrix(result);
				Set<String> s = new HashSet<String>(64);
				for (int i = 1; i < periods.length; i++) {
					s.add(periods[i][0]); 
				}
				String[] periods_fil = s.toArray(new String[s.size()]);
				ArrayList<String> temp = new ArrayList<>(); 
				for (int i = 0; i < periods_fil.length; i++) {
					temp.add(periods_fil[i]);
				}
				Collections.sort(temp, new Comparator<String>() {
				    public int compare(String o1, String o2) {
				        Integer i1 = Integer.parseInt(o1.split("-")[0]);
				        Integer i2 = Integer.parseInt(o2.split("-")[0]);
				        return (i1 > i2 ? 1 : (i1 == i2 ? 0 : -1));
				    }
				});
				
				String[] periods_final = temp.toArray(new String[temp.size()]);
				
				return periods_final;
				
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
	
	public String[] getProgram() {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT programa FROM "+ db +"programas");
				ResultSet result = stmt.executeQuery();
				String[][] periods = toMatrix(result);
				Set<String> s = new HashSet<String>(64);
				for (int i = 1; i < periods.length; i++) {
					s.add(periods[i][0]); 
				}
				String[] periods_fil = s.toArray(new String[s.size()]);
				ArrayList<String> temp = new ArrayList<>(); 
				for (int i = 0; i < periods_fil.length; i++) {
					temp.add(periods_fil[i]);
				}
				Collections.sort(temp);
				return temp.toArray(new String[temp.size()]);
				
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
	
	public String[] getProgramForConsol(String dni, String period) {
		isConnected();
		PreparedStatement stmt = null;
		try {
			try {
				stmt = conn.prepareStatement("SELECT programa FROM "+ db+"programas WHERE cod_programa IN (SELECT cod_programa FROM "+db+"consolidado_reconocimientos_estudiantiles WHERE dni_estudiante=? AND periodo=?)");
				stmt.setString(1, dni);
				stmt.setString(2, period);
				ResultSet result = stmt.executeQuery();
				String[][] programs = toMatrix(result);
				String[] toReturn = new String[programs.length-1];
				int iter = 0;
				for (int i = 1; i < programs.length; i++) {
					toReturn[iter]=programs[i][0];
				}

				return toReturn;
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
	
	public void updatePerson(Person person) {
		isConnected();
		Statement stmt = null;
		try {
			try {
				
				stmt = conn.createStatement();
				stmt.executeUpdate("UPDATE "+ db+ "personas_unal_uapa SET dni_persona = '"+person.getDniPersona()
				+"', tipo_dni_persona= '"+person.getTipoDniPersona()+"', nombres = '"+person.getNombres()+"', apellidos = '"+person.getApellidos()
				+"', nombre_completo = '"+person.getNombreCompleto()+"', usuario_unal = '"+person.getUsuarioUnal()+"' WHERE dni_persona = '"+person.getIdentifier()+"'");
				
				
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
	
	/**
	 * Prints a two dimensional String array
	 * @param a two dimensional array of Strings, data
	 */
	public void printMatrix(String [][] data) {
		System.out.println(Arrays.deepToString(data));		
	}
}