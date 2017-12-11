package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RentabilidadRest {

	@JsonProperty(value = "restaurante")
	private Long restaurante;
	
	@JsonProperty(value = "cantidadesVendidas")
	private Long cantidadesVendidas;
	
	@JsonProperty(value = "costoTotal")
	private Long costoTotal;
	
	@JsonProperty(value = "valorFacturado")
	private Long valorFacturado;
	
	@JsonProperty(value = "fechaInicial")
	private String fechaInicial; 
	
	@JsonProperty(value = "fechaFinal")
	private String fechaFinal;
	
	public RentabilidadRest(
			@JsonProperty(value = "restaurante") Long restaurante, 
			@JsonProperty(value = "cantidadesVendidas") Long cantidadesVendidas,
			@JsonProperty(value = "costoTotal") Long costoTotal,
			@JsonProperty(value = "valorFacturado") Long valorFacturado,
			@JsonProperty(value = "fechaInicial") String fechaI,
			@JsonProperty(value = "fechaFinal") String fechaF) {
		
		this.restaurante = restaurante;
		this.cantidadesVendidas = cantidadesVendidas;
		this.costoTotal = costoTotal;
		this.valorFacturado = valorFacturado;
		this.fechaInicial = fechaI;
		this.fechaFinal = fechaF;
	}

	public Long getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Long restaurante) {
		this.restaurante = restaurante;
	}

	public Long getCantidadesVendidas() {
		return cantidadesVendidas;
	}

	public void setCantidadesVendidas(Long cantidadesVendidas) {
		this.cantidadesVendidas = cantidadesVendidas;
	}

	public Long getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(Long costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Long getValorFacturado() {
		return valorFacturado;
	}

	public void setValorFacturado(Long valorFacturado) {
		this.valorFacturado = valorFacturado;
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
