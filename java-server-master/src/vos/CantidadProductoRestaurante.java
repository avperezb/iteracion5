package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class CantidadProductoRestaurante 
{

	@JsonProperty(value = "idRestaurante")
	private Long idRestaurante;
	
	
	@JsonProperty(value = "idProducto")
	private Long idProducto;
	
	
	@JsonProperty(value = "cantidad")
	private Long cantidad;
	
	
	public CantidadProductoRestaurante (@JsonProperty(value = "idRestaurante") Long idRestaurante,@JsonProperty(value = "idProducto") Long idProducto,@JsonProperty(value = "cantidad") Long cantidad) {
		
		this.cantidad = cantidad;
		this.idProducto = idProducto;
		this.idRestaurante= idRestaurante;
	}


	public Long getIdRestaurante() {
		return idRestaurante;
	}


	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}


	public Long getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}


	public Long getCantidad() {
		return cantidad;
	}


	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
