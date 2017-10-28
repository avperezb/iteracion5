package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class EquivalenciaProducto 
{

	@JsonProperty(value = "idProducto")
	private Long idProducto;
	
	
	@JsonProperty(value = "idEquivalencia")
	private Long idEquivalencia;
	
	public EquivalenciaProducto(@JsonProperty(value = "idProducto") Long idProducto,@JsonProperty(value = "idEquivalencia") Long idEquivalencia)
	{
		this.idProducto = idProducto;
		this.idEquivalencia = idEquivalencia;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdEquivalencia() {
		return idEquivalencia;
	}

	public void setIdEquivalencia(Long idEquivalencia) {
		this.idEquivalencia = idEquivalencia;
	}
	
	
	
}
