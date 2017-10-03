package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {
 
	@JsonProperty(value = "id")
	private Long id; 
	
	@JsonProperty(value = "correo")
	private String correo; 
	
	@JsonProperty(value = "rol")
	private String rol; 
	
	@JsonProperty(value = "infoRol")
	private String infoRol;
	
	@JsonProperty(value = "nombre")
	private String nombre; 
	
	public Usuario(@JsonProperty(value = "id") Long id, @JsonProperty(value = "correo") String correo, 
			@JsonProperty(value = "rol") String rol, @JsonProperty(value = "infoRol") String infoRol, 
			@JsonProperty(value = "nombre") String nombre){
		this.id = id; 
		this.correo = correo; 
		this.rol = rol; 
		this.infoRol = infoRol; 
		this.nombre = nombre; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getInfoRol() {
		return infoRol;
	}

	public void setInfoRol(String infoRol) {
		this.infoRol = infoRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
