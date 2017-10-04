package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PreferenciaUsuarioCategoria {

	@JsonProperty(value = "usuarioID")
	private Long usuarioID; 
	
	@JsonProperty(value = "calsificacionID")
	private Long clasificacionID;
	
	public PreferenciaUsuarioCategoria(@JsonProperty(value = "usuarioID") Long usuarioID, 
			@JsonProperty(value = "clasificacionID") Long clasificacionID){
		this.usuarioID = usuarioID;
		this.clasificacionID = clasificacionID; 
	}

	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	public Long getClasificacionID() {
		return clasificacionID;
	}

	public void setClasificacionID(Long clasificacionID) {
		this.clasificacionID = clasificacionID;
	}
	
	
}
