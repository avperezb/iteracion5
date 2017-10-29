package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RegistroProductosEquivalencias 
{

	@JsonProperty(value = "idUsuario")
	private Long idUsuario;
	
	@JsonProperty(value = "idProducto")
	private Long idProducto;
	
	public RegistroProductosEquivalencias(@JsonProperty(value = "idUsuario") Long idUsuario, @JsonProperty(value = "idProducto") Long idProducto)
	{
		this.idProducto = idProducto;
		this.idUsuario = idUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	
	
}
