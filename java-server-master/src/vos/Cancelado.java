package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cancelado {

	@JsonProperty(value = "id")
	private Long id; 
	
	public Cancelado(@JsonProperty(value = "id") Long id){
		this.id = id; 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
