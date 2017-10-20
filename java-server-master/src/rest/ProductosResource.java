package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.Ingrediente;
import vos.Producto;

@Path("servicios")
public class ProductosResource extends RotondAndesServices{
	
	/**
	 * Metodo que expone servicio REST usando GET que da todos los videos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
	 * @return Json con todos los videos de la base de datos o json con 
	 * el error que se produjo
	 */
	@GET
	@Path("/ingredientes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getIngredientes() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Ingrediente> ingredientes;
		try {
			ingredientes = tm.darIngredientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ingredientes).build();
	}

	@GET
	@Path("/productos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductos() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
	@GET
	@Path("/productos/restaurantes/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductosRestaurante(@PathParam("id") Long id) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosRestaurante(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}

	@GET
	@Path("/productos/categorias/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductosCategoria(@PathParam("id") Long id) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosCategoria(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}

	@GET
	@Path("/productos/precio/{precioMenor: \\d+}/{precioMayor: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductosPrecios(@PathParam("precioMenor") Long pMenor, @PathParam("precioMayor") Long pMayor) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosPrecio(pMenor, pMayor);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
	@POST
	@Path("/usuarios/{id: \\d+}/ingredientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addIngrediente(@PathParam("id") Long id, Ingrediente ingrediente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try 
		{
			tm.addIngrediente(id, ingrediente);
		} 
		catch (Exception e)
		{	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}

		return Response.status(200).entity(ingrediente).build();
	}

	@POST
	@Path("/usuarios/{id: \\d+}/productos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProductos(@PathParam("id") Long id, Producto producto) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try 
		{
			tm.addProducto(id, producto);
		} 
		catch (Exception e)
		{	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}

		return Response.status(200).entity(producto).build();
	}
	
	@GET
	@Path("/productos/mas-ofrecidos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProductosMasOfrecidos(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Producto> ofrecidos;
		try
		{
			ofrecidos = tm.darPoductosMasOfrecidos();
			return Response.status( 200 ).entity( ofrecidos ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

}
