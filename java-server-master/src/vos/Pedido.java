package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido {
	/**
	 * id del pedido
	 */
	@JsonProperty(value = "id")
	private Long id;
	/**
	 * fecha del pedido 
	 */
	@JsonProperty(value = "fecha")
	private Date fecha; 

	/**
	 * id del restaurante
	 */
	@JsonProperty(value = "idRestaurante")
	private Long idRestaurante;
	/**
	 * id del usuario que pidió
	 */
	@JsonProperty(value = "idUsuario")
	private Long idUsuario; 
	/**
	 * cantidad del pedido
	 */
	@JsonProperty(value = "cantidad")
	private int cantidad; 
	
	public Pedido(@JsonProperty(value = "id") Long id, @JsonProperty(value = "fecha") Date fecha, @JsonProperty(value = "idRestaurante") Long idRestaurante,
	@JsonProperty(value = "idUsuario") Long idUsuario, @JsonProperty(value = "cantidad") int cantidad){
		this.id = id; 
		this.fecha = fecha;
		this.idRestaurante = idRestaurante;
		this.idUsuario = idUsuario;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
