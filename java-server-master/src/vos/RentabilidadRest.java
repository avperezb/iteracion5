package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RentabilidadRest {

	@JsonProperty(value = "restaurante")
	private String restaurante;
	
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
			@JsonProperty(value = "restaurante") String restaurante, 
			@JsonProperty(value = "cantidadesVendidas") Long cantidadesVendidas,
			@JsonProperty(value = "costoTotal") Long costoTotal,
			@JsonProperty(value = "valorFacturado") Long valorFacturado) {
		
		this.restaurante = restaurante;
		this.cantidadesVendidas = cantidadesVendidas;
		this.costoTotal = costoTotal;
		this.valorFacturado = valorFacturado;	
		
	}

	public String getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(String restaurante) {
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
