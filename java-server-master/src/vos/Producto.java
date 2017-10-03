package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto {

	/**
	 * id de un producto
	 */
	@JsonProperty(value = "id")
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
	@JsonProperty(value = "descripcionEsp")
	private String decripcionEsp; 
	/**
	 * descripcion del producto en ingles
	 */
	@JsonProperty(value = "descripcionEng")
	private String descripcionEng; 
	/**
	 * clasificacion de un producto
	 */
	@JsonProperty(value = "clasificacion")
	private Long clasificacion; 
	/**
	 * tipo de producto
	 */
	@JsonProperty(value = "tipo")
	private Long tipo; 

	public Producto(@JsonProperty(value = "id") Long id, 
			@JsonProperty(value = "nombre") String nombre,
			@JsonProperty(value = "tiempoPreparacion") Long tiempoPreparacion, 
			@JsonProperty(value = "descripcionEsp") String descripcionEsp,
			@JsonProperty(value = "descripcionEng") String descripcionEng, 
			@JsonProperty(value = "clasificacion") Long clasificacion,
			@JsonProperty(value = "tipo") Long tipo){
		this.id=id; 
		this.nombre = nombre; 
		this.tiempoPreparacion = tiempoPreparacion; 
		this.decripcionEsp = descripcionEsp; 
		this.descripcionEng = descripcionEng; 
		this.clasificacion  = clasificacion; 
		this.tipo = tipo; 

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

	public String getDecripcionEsp() {
		return decripcionEsp;
	}

	public void setDecripcionEsp(String decripcionEsp) {
		this.decripcionEsp = decripcionEsp;
	}

	public String getDescripcionEng() {
		return descripcionEng;
	}

	public void setDescripcionEng(String descripcionEng) {
		this.descripcionEng = descripcionEng;
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
