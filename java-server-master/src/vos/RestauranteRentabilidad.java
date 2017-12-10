package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RestauranteRentabilidad {

	@JsonProperty(value = "idBusqueda")
	private Long idBusqueda;
	@JsonProperty(value = "fechaInicial")
	private String fechaInicial;
	@JsonProperty(value = "fechaFinal")
	private String fechaFinal;

	public RestauranteRentabilidad(@JsonProperty(value = "idBusqueda") Long idBusqueda,
			@JsonProperty(value= "fechaInicial") String fechaInicial,
			@JsonProperty(value = "fechaFinal") String fechaFinal){
		this.idBusqueda = idBusqueda; 
		this.fechaInicial = fechaInicial; 
		this.fechaFinal = fechaFinal;
	}

	public Long getIdBusqueda() {
		return idBusqueda;
	}

	public void setIdRestaurante(Long idBusqueda) {
		this.idBusqueda = idBusqueda;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

}