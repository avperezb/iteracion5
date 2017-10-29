package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Servido {

	@JsonProperty(value = "id")
	private Long id; 
	
	@JsonProperty(value = "usuarioID")
	private Long usuarioID; 
	
	@JsonProperty(value = "restauranteID")
	private Long restauranteID;  
	
	public Servido(@JsonProperty(value = "id") Long id,
			@JsonProperty(value = "usuarioID") Long usuarioID, 
			@JsonProperty(value = "restauranteID") Long restauranteID){
		this.id = id; 
		this.usuarioID = usuarioID; 
		this.restauranteID = restauranteID; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	public Long getRestauranteID() {
		return restauranteID;
	}

	public void setRestauranteID(Long restauranteID) {
		this.restauranteID = restauranteID;
	}
}
