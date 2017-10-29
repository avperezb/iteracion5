package vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoMesa {

	/**
	 * id del pedido
	 */
	@JsonProperty(value = "id")
	private Long id;
	
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
	 * lista de ids del producto
	 */
	@JsonProperty(value = "idsProductos")
	private ArrayList<Long> idsProductos;
	
	/**
	 * lista de cantidades correspondiente a la lisa de idsProductos
	 */
	@JsonProperty(value = "cantidades")
	private ArrayList<Integer> cantidades;
	
	/**
	 * lista de ids de los menus
	 */
	@JsonProperty(value = "idsMenus")
	private ArrayList<Long> idsMenus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ArrayList<Long> getIdsProductos() {
		return idsProductos;
	}

	public void setIdsProductos(ArrayList<Long> idsProductos) {
		this.idsProductos = idsProductos;
	}

	public ArrayList<Integer> getCantidades() {
		return cantidades;
	}

	public void setCantidades(ArrayList<Integer> cantidades) {
		this.cantidades = cantidades;
	}

	public ArrayList<Long> getIdsMenus() {
		return idsMenus;
	}

	public void setIdsMenus(ArrayList<Long> idsMenus) {
		this.idsMenus = idsMenus;
	}
	
	
}
