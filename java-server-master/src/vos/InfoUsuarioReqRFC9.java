package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class InfoUsuarioReqRFC9 {
	@JsonProperty(value = "idUsuario")
	private Long idUsuario; 
	
	@JsonProperty(value = "correo")
	private String correo; 
	
	@JsonProperty(value = "rol")
	private String rol; 
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "nombreProducto")
	private String nombreProducto;
	
	@JsonProperty(value = "clasificacion")
	private String clasificacion;
	
	public InfoUsuarioReqRFC9(@JsonProperty(value = "idUsuario") Long idUsuario, @JsonProperty(value = "correo") String correo, 
			@JsonProperty(value = "rol") String rol, @JsonProperty(value = "nombre") String nombre, 
			@JsonProperty(value = "nombreProducto") String nombreProducto, @JsonProperty(value = "clasificacion") String clasificacion){
		this.idUsuario = idUsuario;
		this.correo = correo; 
		this.rol = rol;
		this.nombre = nombre; 
		this.nombreProducto = nombreProducto;
		this.clasificacion = clasificacion; 
	}


	public Long getidUsuario() {
		return idUsuario;
	}

	public void setId(Long id) {
		this.idUsuario = id;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	
}
