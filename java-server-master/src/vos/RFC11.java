package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC11 {
	
	@JsonProperty(value = "diaDeLaSemana")
	private String diaDeLaSemana; 
	
	@JsonProperty(value = "nombreProductoMasConsumido")
	private String nombreProductoMasConsumido; 
	
	
	@JsonProperty(value = "nombreProductoMenosConsumido")
	private String nombreProductoMenosConsumido; 
	
	@JsonProperty(value = "cantidadMaximaProducto")
	private int cantidadMaximaProducto; 
	
	@JsonProperty(value = "cantidadMinimaProducto")
	private int cantidadMinimaProducto; 
	
	@JsonProperty(value = "nombreRestMasFrec")
	private String nombreRestMasFrec; 
	
	@JsonProperty(value = "nombreRestMenosFrec")
	private String nombreRestMenosFrec;

	
	
	public RFC11(@JsonProperty(value = "diaDeLaSemana") String diaDeLaSemana, @JsonProperty(value = "nombreProductoMasConsumido") String nombreProductoMasConsumido,@JsonProperty(value = "nombreProductoMenosConsumido") String nombreProductoMenosConsumido ,@JsonProperty(value = "cantidadMaximaProducto") int cantidadMaximaProducto,
			@JsonProperty(value = "cantidadMinimaProducto") int cantidadMinimaProducto, @JsonProperty(value = "nombreRestMasFrec") String nombreRestMasFrec,@JsonProperty(value = "nombreRestMenosFrec") String nombreRestMenosFrec) {
		
		this.diaDeLaSemana = diaDeLaSemana;
		this.nombreProductoMasConsumido = nombreProductoMasConsumido;
		this.cantidadMaximaProducto = cantidadMaximaProducto;
		this.cantidadMinimaProducto = cantidadMinimaProducto;
		this.nombreRestMasFrec = nombreRestMasFrec;
		this.nombreRestMenosFrec = nombreRestMenosFrec;
		this.nombreProductoMenosConsumido = nombreProductoMenosConsumido; 
	}


	public String getDiaDeLaSemana() {
		return diaDeLaSemana;
	}

	
	public void setDiaDeLaSemana(String diaDeLaSemana) {
		this.diaDeLaSemana = diaDeLaSemana;
	}

	
	public String getNombreProductoMasConsumido() {
		return nombreProductoMasConsumido;
	}
	

	public void setNombreProductoMasConsumido(String nombreProductoMasConsumido) {
		this.nombreProductoMasConsumido = nombreProductoMasConsumido;
	}
	

	public int getCantidadMaximaProducto() {
		return cantidadMaximaProducto;
	}
	

	public void setCantidadMaximaProducto(int cantidadMaximaProducto) {
		this.cantidadMaximaProducto = cantidadMaximaProducto;
	}
	

	public int getCantidadMinimaProducto() {
		return cantidadMinimaProducto;
	}
	

	public void setCantidadMinimaProducto(int cantidadMinimaProducto) {
		this.cantidadMinimaProducto = cantidadMinimaProducto;
	}
	

	public String getNombreRestMasFrec() {
		return nombreRestMasFrec;
	}
	

	public void setNombreRestMasFrec(String nombreRestMasFrec) {
		this.nombreRestMasFrec = nombreRestMasFrec;
	}
	

	public String getNombreRestMenosFrec() {
		return nombreRestMenosFrec;
	}

	public void setNombreRestMenosFrec(String nombreRestMenosFrec) {
		this.nombreRestMenosFrec = nombreRestMenosFrec;
	}


	public String getNombreProductoMenosConsumido() {
		return nombreProductoMenosConsumido;
	}


	public void setNombreProductoMenosConsumido(String nombreProductoMenosConsumido) {
		this.nombreProductoMenosConsumido = nombreProductoMenosConsumido;
	} 
	
	
}
