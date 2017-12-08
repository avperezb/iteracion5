package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.Ingrediente;
import vos.Mesa;


@Path("{idUsuario: \\d+}/mesas")
public class RotondAndesMesaServices {

/**
 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
 */
@Context
private ServletContext context;

/**
 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
 */
private String getPath() {
	return context.getRealPath("WEB-INF/ConnectionData");
}


private String doErrorMessage(Exception e){
	return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
}

@GET
@Produces({ MediaType.APPLICATION_JSON })
public Response getMesas() {

	RotondAndesTM tm = new RotondAndesTM(getPath());
	List<Mesa> mesas;
	try {
		mesas = tm.darMesas();
	} catch (Exception e) {
		return Response.status(500).entity(doErrorMessage(e)).build();
	}
	return Response.status(200).entity(mesas).build();
}

@GET
@Path( "{id: \\d+}" )
@Produces( { MediaType.APPLICATION_JSON } )
public Response getMesa( @PathParam( "id" ) int id)
{
	RotondAndesTM tm = new RotondAndesTM( getPath( ) );
	try
	{
		Mesa v = tm.darMesaPorId(id);
		return Response.status( 200 ).entity( v ).build( );			
	}
	catch( Exception e )
	{
		return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
	}
}


@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response addMesa(@PathParam("idUsuario") Long idUsuario,Mesa mesa) {
	RotondAndesTM tm = new RotondAndesTM(getPath());
	try {
		tm.addMesa(idUsuario,mesa);
	} catch (Exception e) {
		return Response.status(500).entity(doErrorMessage(e)).build();
	}
	return Response.status(200).entity(mesa).build();
}

//@PUT
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public Response updateIngrediente(@PathParam("idUsuario") String idUsuario,Ingrediente Ingrediente) {
//	
//	RotondAndesTM tm = new RotondAndesTM(getPath());
//	
//	try {
//		tm.updateIngrediente(idUsuario,Ingrediente);
//	} catch (Exception e) {
//		return Response.status(500).entity(doErrorMessage(e)).build();
//	}
//	return Response.status(200).entity(Ingrediente).build();
//}

@DELETE
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response deleteMesa(@PathParam("idUsuario") Long idUsuario, Mesa mesa) {
	
	RotondAndesTM tm = new RotondAndesTM(getPath());
	try {
		tm.deleteMesa(idUsuario, mesa);
	} catch (Exception e) {
		return Response.status(500).entity(doErrorMessage(e)).build();
	}
	return Response.status(200).entity(mesa).build();
}


}