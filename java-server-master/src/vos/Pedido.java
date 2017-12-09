package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido {
	/**
	 * id del pedido
	 */
	@JsonProperty(value = "id")
	private Long id;
//	/**
//	 * fecha del pedido 
//	 */
//	@JsonProperty(value = "fecha")
//	private Date fecha; 

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
	 * id del producto
	 */
	@JsonProperty(value = "idProducto")
	private Long idProducto;
	/**
	 * id del menu
	 */
	@JsonProperty(value = "idMenu")
	private Long idMenu;
	/**
	 * cantidad del pedido
	 */
	@JsonProperty(value = "cantidad")
	private Integer cantidad;
	
	@JsonProperty(value="numMesa")
	private int numMesa;
	
	@JsonProperty(value="estadoPedido")
	private String estadoPedido;

	
	public Pedido(
			@JsonProperty(value = "id") Long id, 
//			@JsonProperty(value = "fecha") Date fecha,
			@JsonProperty(value = "idRestaurante") Long idRestaurante,
			@JsonProperty(value = "idUsuario") Long idUsuario, 
			@JsonProperty(value = "cantidad") int cantidad, 
			@JsonProperty(value = "idProducto") Long idProducto, 
			@JsonProperty(value = "idMenu") Long idMenu, @JsonProperty(value="numMesa") int mesa)
{
		this.id = id; 
//		this.fecha = fecha;
		this.idRestaurante = idRestaurante;
		this.idUsuario = idUsuario;
		this.cantidad = cantidad;
		this.idProducto = idProducto;
		this.numMesa = mesa;
		this.idMenu = idMenu;
		this.estadoPedido = null;
		
	}

	public Pedido(Pedido pedido) {
		
		this.id = pedido.getIdPedido(); 
//		this.fecha = fecha;
		this.idRestaurante = pedido.getIdRestaurante();
		this.idUsuario = pedido.getIdUsuario();
		this.cantidad = pedido.getCantidad();
		this.idProducto = pedido.getIdProducto();
		this.numMesa = pedido.getNumMesa();
		this.idMenu = pedido.getIdMenu();
	}

	public Long getIdPedido() {
		return id;
	}

	public void setIdPedido(Long id) {
		this.id = id;
	}

//	public Date getFecha() {
//		return fecha;
//	}
//
//	public void setFecha(Date fecha) {
//		this.fecha = fecha;
//	}

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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}
	public int getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}	

	
	
}