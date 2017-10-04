package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PreferenciaUsuarioZona {

	@JsonProperty(value = "usuarioID")
	private Long usuarioID; 
	
	@JsonProperty(value = "zonaID")
	private Long zonaID; 
	
	public PreferenciaUsuarioZona(@JsonProperty(value = "usuarioID") Long usuarioID, 
			@JsonProperty(value = "zonaID") Long zonaID){
		this.usuarioID = usuarioID; 
		this.zonaID = zonaID; 
	}

	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	public Long getZonaID() {
		return zonaID;
	}

	public void setZonaID(Long zonaID) {
		this.zonaID = zonaID;
	}
	
	
}
