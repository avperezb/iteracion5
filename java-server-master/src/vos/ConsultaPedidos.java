package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConsultaPedidos 
{

	@JsonProperty(value = "idRestaurante")
	private Long idRestaurante;
	
	@JsonProperty(value = "dineroTotal")
	private Long dineroTotal;
	
	@JsonProperty(value = "cantidad")
	private Long cantidad;
	
	@JsonProperty(value = "idProducto")
	private Long idProducto;
	
	@JsonProperty(value = "tipo")
	private String tipo;
	
	public ConsultaPedidos(@JsonProperty(value = "idRestaurante") Long idRestaurante, @JsonProperty(value = "dineroTotal")Long dineroTotal,
			@JsonProperty(value = "cantidad") Long cantidad,@JsonProperty(value = "idProducto") Long idProducto, @JsonProperty(value = "tipo") String tipo)
	{
		this.idRestaurante = idRestaurante;
		this.cantidad = cantidad;
		this.dineroTotal = dineroTotal;
		this.idProducto = idProducto;
		this.tipo = tipo;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public Long getDineroTotal() {
		return dineroTotal;
	}

	public void setDineroTotal(Long dineroTotal) {
		this.dineroTotal = dineroTotal;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
