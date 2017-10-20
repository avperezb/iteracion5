package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.PreferenciaUsuarioCategoria;
import vos.PreferenciaUsuarioPrecio;
import vos.PreferenciaUsuarioZona;

@Path("servicios")
public class PreferenciasResource extends RotondAndesServices{
	
	@GET
	@Path("/preferencias/categorias")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPreferenciasCategorias(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PreferenciaUsuarioCategoria> preferencias;
		try
		{
			preferencias = tm.darPreferenciasCategoria();
			return Response.status( 200 ).entity( preferencias ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	@GET
	@Path("/preferencias/precios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPreferenciasPrecios(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PreferenciaUsuarioPrecio> preferencias;
		try
		{
			preferencias = tm.darPreferenciasPrecio();
			return Response.status( 200 ).entity( preferencias ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	@GET
	@Path("/preferencias/zonas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPreferenciasZonas(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PreferenciaUsuarioZona> preferencias;
		try
		{
			preferencias = tm.darPreferenciasZona();
			return Response.status( 200 ).entity( preferencias ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	@POST
	@Path("/usuarios/{id: \\d+}/preferencias/categorias")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPreferenciaCategoria(@PathParam("id") Long id, PreferenciaUsuarioCategoria preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarPreferenciaCategoria(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}

	@POST
	@Path("/usuarios/{id: \\d+}/preferencias/precios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPreferenciaPrecio(@PathParam("id") Long id, PreferenciaUsuarioPrecio preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarPreferenciaPrecio(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}

	@POST
	@Path("/usuarios/{id: \\d+}/preferencias/zonas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPreferenciaZona(@PathParam("id") Long id, PreferenciaUsuarioZona preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarPreferenciaZona(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}

	@PUT
	@Path("/usuarios/{id: \\d+}/preferencias/categorias/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePreferenciaCategoria(@PathParam("id") Long id, PreferenciaUsuarioCategoria preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.updatePreferenciaCategoria(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}

	@PUT
	@Path("/usuarios/{id: \\d+}/preferencias/precio/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePreferenciaPrecio(@PathParam("id") Long id, PreferenciaUsuarioPrecio preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.updatePreferenciaPrecio(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}

	@PUT
	@Path("/usuarios/{id: \\d+}/preferencias/zonas/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePreferenciaZona(@PathParam("id") Long id, PreferenciaUsuarioZona preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.updatePreferenciaZona(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}

}
