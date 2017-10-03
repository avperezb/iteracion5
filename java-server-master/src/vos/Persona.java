package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Persona {

	/**
	 * id de la persona
	 */
	@JsonProperty(value = "id")
	private Long id; 
	/**
	 * nombre de la persona
	 */
	@JsonProperty(value = "nombre")
	private String nombre; 
	/**
	 * edad de la persona
	 */
	@JsonProperty(value = "edad")
	private Integer edad; 
	/**
	 * fecha de nacimiento de la persna
	 */
	@JsonProperty(value = "fechaNacim")
	private Date fechaNacim; 
	/**
	 * sexo de una persona
	 */
	@JsonProperty(value = "sexo")
	private char sexo; 
	/**
	 * apellidos de una persona
	 */
	@JsonProperty(value = "apellidos")
	private String apellidos; 
	
	public Persona(@JsonProperty(value = "id") Long id, 
			@JsonProperty(value= "nombre") String nombre, 
			@JsonProperty(value = "edad") Integer edad,
			@JsonProperty(value = "fechaNacim") Date fechaNacim,
			@JsonProperty(value = "sexo") char sexo,
			@JsonProperty(value = "apellidos") String apellidos){
		this.id = id; 
		this.nombre = nombre; 
		this.edad = edad; 
		this.fechaNacim = fechaNacim; 
		this.sexo = sexo; 
		this.apellidos = apellidos; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaNacim() {
		return fechaNacim;
	}

	public void setFechaNacim(Date fechaNacim) {
		this.fechaNacim = fechaNacim;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
}
