package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PreferenciaUsuarioPrecio {

	@JsonProperty(value = "usuarioID")
	private Long usuarioID; 
	
	@JsonProperty(value = "precio")
	private Double precio;
	
	public PreferenciaUsuarioPrecio(@JsonProperty(value = "usuarioID") Long usuarioID,
			@JsonProperty(value = "precio") Double precio){
		this.usuarioID = usuarioID; 
		this.precio = precio; 
	}

	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
}
