package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Restaurante {

	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "nombre")
	private String nombre; 
	@JsonProperty(value = "tipo")
	private Integer tipo; 
	@JsonProperty(value = "urlPW")
	private String urlPW; 
	@JsonProperty(value = "idRepresentante")
	private Long idRepresentante; 

	public Restaurante(@JsonProperty(value = "id") Long id,
			@JsonProperty(value= "nombre") String nombre, 
			@JsonProperty(value = "tipo") Integer tipo,
			@JsonProperty(value= "urlPW") String urlPW, 
			@JsonProperty(value= "idRepresentante") Long idRepresentante){
		this.id = id; 
		this.nombre = nombre; 
		this.tipo = tipo; 
		this.urlPW = urlPW; 
		this.idRepresentante = idRepresentante; 
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getUrlPW() {
		return urlPW;
	}

	public void setUrlPW(String urlPW) {
		this.urlPW = urlPW;
	}

	public Long getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Long idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

}
