/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: ProductoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package vos;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa una arreglo de Producto
 * @author 
 */
public class ListaRentabilidades {
	
	/**
	 * List con los Productos
	 */
	@JsonProperty(value="rentabilidades")
	private List<RentabilidadRestaurante> rentabilidades;
	
	/**
	 * Constructor de la clase ListaProductos
	 * @param Productos - Productos para agregar al arreglo de la clase
	 */
	public ListaRentabilidades( @JsonProperty(value="rentabilidades")List<RentabilidadRestaurante> rentabilidades){
		this.rentabilidades = rentabilidades;
	}

	/**
	 * Método que retorna la lista de Productos
	 * @return  List - List con los Productos
	 */
	public List<RentabilidadRestaurante> getRentabilidades() {
		return rentabilidades;
	}

	/**
	 * Método que asigna la lista de Productos que entra como parametro
	 * @param  Productos - List con los Productos ha agregar
	 */
	public void setRentabilidades(List<RentabilidadRestaurante> rentabilidades) {
		this.rentabilidades = rentabilidades;
	}
	
}