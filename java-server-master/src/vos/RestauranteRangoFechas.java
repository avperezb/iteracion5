package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RestauranteRangoFechas {

	@JsonProperty(value = "idRestaurante")
	private Long idRestaurante;
		@JsonProperty(value = "fechaInicial")
		private String fechaInicial; 
		@JsonProperty(value = "fechaFinal")
		private String fechaFinal;
	@JsonProperty(value = "ordAgrup")
	private String ordAgrup;

	public RestauranteRangoFechas(
			@JsonProperty(value = "idRestaurante") Long idRestaurante,
			@JsonProperty(value= "fechaInicial") String fechaInicial,
			@JsonProperty(value = "fechaFinal") String fechaFinal,
			@JsonProperty(value = "ordAgrup") String ordAgrup){
		this.idRestaurante = idRestaurante; 
		this.fechaInicial = fechaInicial; 
		this.fechaFinal = fechaFinal;
		this.ordAgrup = ordAgrup;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
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

	public String getOrdAgrup() {
		return ordAgrup;
	}

	public void setOrdAgrup(String ordAgrup) {
		this.ordAgrup = ordAgrup;
	}
}