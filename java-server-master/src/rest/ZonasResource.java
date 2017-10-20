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
import vos.Zona;

@Path("servicios")
public class ZonasResource extends RotondAndesServices{
	
	@GET
	@Path("zonas/{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getZona( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Zona z = tm.buscarZonaPorId(id);
			return Response.status( 200 ).entity( z ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@POST
	@Path("/usuarios/{id: \\d+}/zonas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addZona(@PathParam("id") Long id, Zona zona) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try 
		{
			tm.agregarZona(id, zona);
		} 
		catch (Exception e)
		{	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}

		return Response.status(200).entity(zona).build();
	}
	@GET
	@Path("/zonas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getZonas(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Zona> zonas;
		try
		{
			zonas = tm.darZonas();
			return Response.status( 200 ).entity( zonas ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}

	}

}
