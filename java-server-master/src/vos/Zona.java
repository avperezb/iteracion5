package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Zona {

	@JsonProperty(value = "id")
	private Long id; 
	@JsonProperty(value = "capacidadPersonas")
	private Integer capacidadPersonas; 
	@JsonProperty(value = "handicap")
	private char handicap; 
	@JsonProperty(value = "condicionesTecnicas")
	private String condicionesTecnicas; 

	public Zona(@JsonProperty(value = "id") Long id,
			@JsonProperty(value = "capacidadPersonas") Integer capacidadPersonas, 
			@JsonProperty(value = "handicap") char handicap, 
			@JsonProperty(value = "condicionesTecnicas") String condicionesTecnicas){
		this.id=id; 
		this.capacidadPersonas = capacidadPersonas; 
		this.handicap = handicap; 
		this.condicionesTecnicas = condicionesTecnicas; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCapacidadPersonas() {
		return capacidadPersonas;
	}

	public void setCapacidadPersonas(Integer capacidadPersonas) {
		this.capacidadPersonas = capacidadPersonas;
	}

	public char getHandicap() {
		return handicap;
	}

	public void setHandicap(char handicap) {
		this.handicap = handicap;
	}

	public String getCondicionesTecnicas() {
		return condicionesTecnicas;
	}

	public void setCondicionesTecnicas(String condicionesTecnicas) {
		this.condicionesTecnicas = condicionesTecnicas;
	}


}
