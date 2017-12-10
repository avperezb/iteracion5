package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto {

	/**
	 * id de un producto
	 */
	private Long id; 
	
	/**
	 * nombre de un producto
	 */
	@JsonProperty(value = "nombre")
	private String nombre; 
	
	/**
	 * tiempo de preparacion de un producto
	 */
	@JsonProperty(value = "tiempoPreparacion")
	private Long tiempoPreparacion; 
	
	/**
	 * descripcion del producto en espaniol
	 */
	@JsonProperty(value = "descripcion")
	private String descripcion; 
	
	/**
	 * descripcion del producto en ingles
	 */
	@JsonProperty(value = "traduccion")
	private String traduccion; 
	
	/**
	 * clasificacion de un producto
	 */
	private Long clasificacion; 
	
	/**
	 * tipo de producto
	 */
	private Long tipo; 

	public Producto(@JsonProperty(value = "id") Long id, 
			@JsonProperty(value = "nombre") String nombre,
			@JsonProperty(value = "tiempoPreparacion") Long tiempoPreparacion, 
			@JsonProperty(value = "descripcion") String descripcion,
			@JsonProperty(value = "traduccion") String traduccion, 
			@JsonProperty(value = "clasificacion") Long clasificacion,
			@JsonProperty(value = "tipo") Long tipo){
		this.id=id; 
		this.nombre = nombre; 
		this.tiempoPreparacion = tiempoPreparacion; 
		this.descripcion = descripcion; 
		this.traduccion = traduccion; 
		this.clasificacion  = clasificacion; 
		this.tipo = tipo; 

	}
	
	public Producto (@JsonProperty(value = "nombre") String nombre,
			@JsonProperty(value = "tiempoPreparacion") Long tiempoPreparacion, 
			@JsonProperty(value = "descripcion") String descripcion,
			@JsonProperty(value = "traduccion") String traduccion){
		
		this.nombre = nombre; 
		this.tiempoPreparacion = tiempoPreparacion; 
		this.descripcion = descripcion; 
		this.traduccion = traduccion; 		
		
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

	public Long getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(Long tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcionn) {
		this.descripcion = descripcionn;
	}

	public String getTraduccion() {
		return traduccion;
	}

	public void setTraduccion(String traduccionn) {
		this.traduccion = traduccionn;
	}

	public Long getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Long clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

}
