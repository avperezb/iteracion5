package vos;

import java.util.ArrayList;
import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoMesa {

	/**
	 * id del pedido
	 */
	@JsonProperty(value = "idPedido")
	private Long idPedido;
	
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
	
	@JsonProperty(value="numMesa")
	private int numMesa;

	public PedidoMesa(
			@JsonProperty(value = "idPedido") Long idPedido,
			@JsonProperty(value = "idRestaurante") Long idRestaurante,
			@JsonProperty(value = "idUsuario") Long idUsuario, @JsonProperty(value="numMesa") int mesa)
{
		this.idPedido = idPedido; 
		this.idRestaurante = idRestaurante;
		this.idUsuario = idUsuario;
		this.numMesa = mesa;
	}
	
	public Long getId() {
		return idPedido;
	}

	public void setId(Long id) {
		this.idPedido = id;
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
	public int getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}	
	
}
