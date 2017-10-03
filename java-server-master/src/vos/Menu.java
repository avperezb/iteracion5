package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Menu {
	/**
	 * id del Menu
	 */
	@JsonProperty(value = "id")
	private Long id;
	/**
	 * tiempo de preparación 
	 */
	@JsonProperty(value = "tiempoPreparacion")
	private Long tiempoPreparacion; 
	/**
	 * descripcion en espaniol 
	 */
	@JsonProperty(value = "descripcionEsp")
	private String descripcionEsp; 
	/**
	 * descripcion en ingles
	 */
	@JsonProperty(value = "descripcionEng")
	private String descripcionEng; 
	/**
	 * id del restaurante
	 */
	@JsonProperty(value = "idRestaurante")
	private Long idRestaurante; 
	/**
	 * precio
	 */
	@JsonProperty(value = "precio")
	private Double precio; 
	/**
	 * costo
	 */
	@JsonProperty(value = "costo")
	private Double costo; 

	public Menu(@JsonProperty(value = "id") Long id, @JsonProperty(value = "tiempoPreparacion") Long tiempoPreparacion,
			@JsonProperty(value = "descripcionEsp") String descripcionEsp, @JsonProperty(value = "descripcionEng") String descripcionEng,
			@JsonProperty(value = "idRestaurante") Long idRestaurante, @JsonProperty(value = "precio") Double precio, 
			@JsonProperty(value = "costo") Double costo){
		this.id = id; 
		this.tiempoPreparacion = tiempoPreparacion; 
		this.descripcionEsp = descripcionEsp; 
		this.descripcionEng = descripcionEng; 
		this.precio = precio; 
		this.costo = costo; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTiempoPreparacion() {
		return tiempoPreparacion;
	}

	public void setTiempoPreparacion(Long tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public String getDescripcionEsp() {
		return descripcionEsp;
	}

	public void setDescripcionEsp(String descripcionEsp) {
		this.descripcionEsp = descripcionEsp;
	}

	public String getDescripcionEng() {
		return descripcionEng;
	}

	public void setDescripcionEng(String descripcionEng) {
		this.descripcionEng = descripcionEng;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}


}
