package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class EquivalenciaIngrediente 
{

	@JsonProperty(value = "idIngrediente")
	private Long idIngrediente;
	
	@JsonProperty(value = "idEquiIngrediente")
	private Long idEquiIngrediente;
	
	
	public EquivalenciaIngrediente(@JsonProperty(value = "idIngrediente") Long idIngrediente,@JsonProperty(value = "idEquiIngrediente") Long idEquiIngrediente) 
	{
		this.idIngrediente = idIngrediente;
		this.idEquiIngrediente= idEquiIngrediente;
	}


	public Long getIdIngrediente() {
		return idIngrediente;
	}


	public void setIdIngrediente(Long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}


	public Long getIdEquiIngrediente() {
		return idEquiIngrediente;
	}


	public void setIdEquiIngrediente(Long idEquiIngrediente) {
		this.idEquiIngrediente = idEquiIngrediente;
	}
		
}
