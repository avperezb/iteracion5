package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ingrediente {
	/**
	 * id del ingrediente
	 */
	@JsonProperty(value = "id")
	private Long id;
	/**
	 * nombre del ingrediente 
	 */
	@JsonProperty(value = "nombre")
	private String nombre; 
	/**
	 * descripcion en español del ingrediente
	 */
	@JsonProperty(value = "descripcionEsp")
	private String descricionEsp;
	/**
	 * descripcion en ingles del ingrediente 
	 */
	@JsonProperty(value = "descripcionEng")
	private String descripcionEng; 


	/**
	 * Metodo constructor de la clase ingrediente
	 * <b>post: </b> Crea el ingrediento con los valores que entran como parametro
	 * @param id - Id del ingrediente.
	 * @param nombre - Nombre del ingrediente. nombre != null
	 * @param descripcionEsp - descripcion del ingrediente en Espaniol.
	 * @param descripcionEng - descripcion del ingrediente en Ingles. 
	 */
	public Ingrediente(@JsonProperty(value = "id") Long id, @JsonProperty(value = "nombre") String nombre, @JsonProperty(value = "descripcionEsp") String descripcionEsp, @JsonProperty(value = "descripcionEng") String descripcionEng){
		this.id = id; 
		this.nombre = nombre; 
		this.descricionEsp = descripcionEsp; 
		this.descripcionEng = descripcionEng;
	}

	/**
	 * metodo getter del atributo id
	 * @return el id del producto
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Metodo setter del atributo id <b>post: </b> el id del ingrediente
	 * ha sido cambiado con el valor que entra como parametro
	 * @param id - id del ingrediente.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * metodo getter del atributo nombre
	 * @return el nombre del ingrediente 
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo setter del atributo nombre <b>post: </b> el nombre del ingrediente
	 * ha sido combiado con el valor que entra como parametro 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo getter del atributo descripcionEsp
	 * @return la descripcion en espaniol del ingrediente
	 */
	public String getDescricionEsp() {
		return descricionEsp;
	}
	/**
	 * Metodo setter del atributo descripcionEsp <b>post: </b> la descripcion en espaniol del ingrediente
	 * ha sido combiado con el valor que entra como parametro 
	 * @param descripcionEsp
	 */
	public void setDescricionEsp(String descricionEsp) {
		this.descricionEsp = descricionEsp;
	}
	/**
	 * Metodo getter del atributo descripcionEng
	 * @return la descripcion en ingles del ingrediente
	 */
	public String getDescripcionEng() {
		return descripcionEng;
	}
	/**
	 * Metodo setter del atributo descripcionEng <b>post: </b> la descripcion en ingles del ingrediente
	 * ha sido combiado con el valor que entra como parametro 
	 * @param descripcionEng
	 */
	public void setDescripcionEng(String descripcionEng) {
		this.descripcionEng = descripcionEng;
	}
}
