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
	
	public Pedido(@JsonProperty(value = "id") Long id, @JsonProperty(value = "fecha") Date fecha){
		this.id = id; 
		this.fecha = fecha; 
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
	
	
}
