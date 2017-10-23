package entities;

/**
 * Entitie that represents a person in the table personas_unal_uapa
 * Must be changed if the table organization or parameters change
 * @author UAPA03
 *
 */
public class Person {
	

	String identifier;
	String dniPersona;
	String tipoDniPersona;
	String nombres;
	String apellidos;
	String nombreCompleto;
	String usuarioUnal;
	
	
	public Person(String identifier, String dniPersona, String tipoDniPersona, String nombres, String apellidos, String usuarioUnal) {
		this.identifier = identifier;
		this.dniPersona = dniPersona;
		this.tipoDniPersona = tipoDniPersona;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.usuarioUnal = usuarioUnal;
		this.nombreCompleto = nombres + " " + apellidos;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDniPersona() {
		return dniPersona;
	}

	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}

	public String getTipoDniPersona() {
		return tipoDniPersona;
	}

	public void setTipoDniPersona(String tipoDniPersona) {
		this.tipoDniPersona = tipoDniPersona;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuarioUnal() {
		return usuarioUnal;
	}

	public void setUsuarioUnal(String usuarioUnal) {
		this.usuarioUnal = usuarioUnal;
	}

	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombres, String apellidos) {
		this.nombreCompleto = nombres + " " + apellidos;
	}

	@Override
	public String toString() {
		return "Person [identifier=" + identifier + ", dniPersona=" + dniPersona + ", tipoDniPersona=" + tipoDniPersona
				+ ", nombres=" + nombres + ", apellidos=" + apellidos + ", nombreCompleto=" + nombreCompleto
				+ ", usuarioUnal=" + usuarioUnal + "]";
	}
	
	
}
