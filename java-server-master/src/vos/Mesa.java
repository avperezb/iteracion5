package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Mesa {

	@JsonProperty(value="numMesa")	
	private int numMesa;

	@JsonProperty(value="capacidad")
	private int capacidad;

	@JsonProperty(value="idZona")
	private Long idZona;

	public Mesa(@JsonProperty(value="numMesa") int numeroMesa, @JsonProperty(value="capacidad") int cap, @JsonProperty(value="idZona") Long idZ)
	{
		super();
		this.numMesa=numeroMesa;
		this.capacidad=cap;	
		this.idZona = idZ;
	}

	public int getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int cap) {
		this.capacidad = cap;
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public Mesa getMesa(int num)
	{
		Mesa mesa = null;

		if (this.numMesa == num)
		{
			mesa = this;
		}
		return mesa;
	}

}

