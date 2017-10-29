/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package tm;

import java.io.File;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import dao.DAOPreferencias;
import dao.DAOProductosIngredientes;
import dao.DAORestaurantesZona;
import dao.DAOServidos;
import dao.DAOUsuarios;
import vos.Cancelado;
import vos.CantidadProductoRestaurante;
import vos.EquivalenciaIngrediente;
import vos.EquivalenciaProducto;
import vos.Ingrediente;
import vos.Pedido;
import vos.PedidoMesa;
import vos.PreferenciaUsuarioCategoria;
import vos.PreferenciaUsuarioPrecio;
import vos.PreferenciaUsuarioZona;
import vos.Producto;
import vos.Usuario;
import vos.UsuarioClientePref;
import vos.Video;
import vos.Zona;
import vos.Restaurante;
import vos.Servido;

/**
 * Transaction Manager de la aplicacion (TM)
 * Fachada en patron singleton de la aplicacion
 * @author Monitores 2017-20
 */
public class RotondAndesTM {


	/**
	 * Atributo estatico que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * conexion a la base de datos
	 */
	private Connection conn;


	/**
	 * Metodo constructor de la clase VideoAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logica de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto VideoAndesTM, se inicializa el path absoluto del archivo de conexion y se
	 * inicializa los atributos que se usan par la conexion a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public RotondAndesTM(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/**
	 * Metodo que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexion a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que  retorna la conexion a la base de datos
	 * @return Connection - la conexion a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////


	/**
	 * Metodo que modela la transaccion que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la busqueda
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List<Ingrediente> darIngredientes() throws Exception {
		List<Ingrediente> ingredientes;
		DAOProductosIngredientes daoIngredientes = new DAOProductosIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			ingredientes = daoIngredientes.darIngredientes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ingredientes;
	}

	public List<Producto> darProductos() throws Exception {
		List<Producto> productos;
		DAOProductosIngredientes daoIngredientes = new DAOProductosIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			productos = daoIngredientes.darProductos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<Producto> darProductosRestaurante(Long id) throws Exception {
		List<Producto> productos;
		DAOProductosIngredientes daoIngredientes = new DAOProductosIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			productos = daoIngredientes.darProductosRestaurante(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<Producto> darProductosCategoria(Long id) throws Exception {
		List<Producto> productos;
		DAOProductosIngredientes daoIngredientes = new DAOProductosIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			productos = daoIngredientes.darProductosCategoria(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public List<Producto> darProductosPrecio(Long pMenor,Long pMayor) throws Exception {
		List<Producto> productos;
		DAOProductosIngredientes daoIngredientes = new DAOProductosIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			productos = daoIngredientes.darProductosPrecios(pMenor, pMayor);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return productos;
	}

	public void addIngrediente(Long id, Ingrediente ingrediente) throws Exception{
		// TODO Auto-generated method stub
		DAOProductosIngredientes daoIngredientes = new DAOProductosIngredientes();
		DAOUsuarios daoUsuarios = new DAOUsuarios();

		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn); 	
			daoUsuarios.setConnection(conn);
			Usuario encargado = daoUsuarios.buscarUsuarioPorID(id);
			if(encargado.getRol().equals("Restaurante"))
			{
				daoIngredientes.addIngrediente(ingrediente);
				conn.commit();				
			}
			else 
			{
				throw new Exception("el usuario no tiene permisos");
			}
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addProducto(Long id, Producto producto) throws Exception {
		DAOProductosIngredientes daoProductos = new DAOProductosIngredientes();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			daoUsuarios.setConnection(conn);
			Usuario encargado = daoUsuarios.buscarUsuarioPorID(id);
			if(encargado.getRol().equals("Restaurante"))
			{
				daoProductos.addProducto(producto);
				conn.commit();				
			}
			else 
			{
				throw new Exception("el usuario no tiene permisos");
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void agregarEquivalenciaIngrediente(Long id, EquivalenciaIngrediente equi) throws Exception
	{
		DAOProductosIngredientes daoIngredientes = new DAOProductosIngredientes();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoIngredientes.setConn(conn);
			daoUsuarios.setConnection(conn);
			Usuario encargado = daoUsuarios.buscarUsuarioPorID(id);
			if(encargado.getRol().equals("Restaurante"))
			{
				daoIngredientes.addEquivalenciaIngrediente(equi);
				conn.commit();				
			}
			else 
			{
				throw new Exception("el usuario no tiene permisos");
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoIngredientes.cerrarRecursos();
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void agregarEquivalenciaProducto(Long id, EquivalenciaProducto equi) throws Exception
	{
		DAOProductosIngredientes daoProductos = new DAOProductosIngredientes();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			daoUsuarios.setConnection(conn);
			Usuario encargado = daoUsuarios.buscarUsuarioPorID(id);
			if(encargado.getRol().equals("Restaurante"))
			{
				daoProductos.addEquivalenciaProducto(equi);
				conn.commit();				
			}
			else 
			{
				throw new Exception("el usuario no tiene permisos");
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	public void agregarCantidadProducto(Long id, CantidadProductoRestaurante canti) throws Exception
	{
		DAOProductosIngredientes daoProductos = new DAOProductosIngredientes();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			daoUsuarios.setConnection(conn);
			Usuario encargado = daoUsuarios.buscarUsuarioPorID(id);
			if(encargado.getRol().equals("Restaurante"))
			{
				daoProductos.addCantidadProducto(canti);
				conn.commit();				
			}
			else 
			{
				throw new Exception("el usuario no tiene permisos");
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}





	/*-------------------------------------------------------------------------------------------------- */
	public Zona buscarZonaPorId(Long id) throws Exception {
		Zona zona;
		DAORestaurantesZona daoZonas = new DAORestaurantesZona(); 
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zona = daoZonas.buscarZonaPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return zona;
	}

	public List<Usuario> darUsuarios() throws Exception{
		List<Usuario> usuarios;
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConnection(conn);
			usuarios = daoUsuarios.darUsuarios();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuarios;
	}

	public List<Restaurante> darRestaurantes() throws Exception{
		List<Restaurante> restaurantes;
		DAORestaurantesZona daoRestaurantes = new DAORestaurantesZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurantes.setConn(conn);
			restaurantes = daoRestaurantes.darRestaurantes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRestaurantes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return restaurantes;
	}

	public void agregarUsuario(Usuario usuario) throws Exception{
		// TODO Auto-generated method stub
		DAOUsuarios daoUsuarios = new DAOUsuarios();

		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConnection(conn); 			
			daoUsuarios.addUsuario(usuario);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public List<Usuario> darAdministradores() throws Exception{
		List<Usuario> usuarios;
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConnection(conn);
			usuarios = daoUsuarios.darAdministradores();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuarios;
	}

	public List<UsuarioClientePref> darUsuarioPreferencias() throws Exception{
		List<UsuarioClientePref> usuarios;
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConnection(conn);
			usuarios = daoUsuarios.darUsuarioPreferencias();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuarios;
	}

	public Usuario buscarUsuarioPorID(Long id) throws Exception {
		Usuario usuario;
		DAOUsuarios daoUsuario = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuario.setConnection(conn);
			usuario = daoUsuario.buscarUsuarioPorID(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuario.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuario;
	}

	public void agregarCliente(Long id, Usuario usuario) throws Exception{
		DAOUsuarios daoUsuarios = new DAOUsuarios();

		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConnection(conn); 			
			Usuario encargado = daoUsuarios.buscarUsuarioPorID(id);
			if(encargado.getRol().equals("Admin")){
				daoUsuarios.addUsuario(usuario);
				conn.commit();
			}else{
				throw new Exception("No tiene permisos para realizar esta acción");
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void agregarZona(Long id, Zona zona) throws Exception{
		// TODO Auto-generated method stub
		DAORestaurantesZona daoRestaurantes = new DAORestaurantesZona();
		DAOUsuarios daoUsuarios = new DAOUsuarios();

		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoRestaurantes.setConn(conn); 	
			daoUsuarios.setConnection(conn);
			Usuario encargado = daoUsuarios.buscarUsuarioPorID(id);
			if(encargado.getRol().equals("Admin"))
			{
				daoRestaurantes.addZona(zona);
				conn.commit();				
			}
			else 
			{
				throw new Exception("el usuario no tiene permisos");
			}
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRestaurantes.cerrarRecursos();
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	public List<Zona> darZonas() throws Exception{
		List<Zona> zonas;
		DAORestaurantesZona daoZonas = new DAORestaurantesZona();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoZonas.setConn(conn);
			zonas = daoZonas.darZonas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoZonas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return zonas;
	}


	public void agregarRestaurante(Long id, Restaurante rest) throws Exception{
		DAORestaurantesZona daoRest = new DAORestaurantesZona();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try {
			this.conn = darConexion();
			daoRest.setConn(conn);
			Usuario encargado = daoUsuarios.buscarUsuarioPorID(id);
			if(encargado.getRol().equals("Admin"))
			{
				daoRest.addRestaurante(rest);
				conn.commit();			
			}
			else 
			{
				throw new Exception("el usuario no tiene permisos");
			}
		} catch(SQLException e){
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException: "+ e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try{
				daoRest.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception){
				System.err.println("SQLException closing resources: "+ exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void updatePreferenciaCategoria(Long id,PreferenciaUsuarioCategoria categoria) throws Exception {
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			if(daoUsuarios.buscarUsuarioPorID(id).getInfoRol().equals("Admin")){
				daoPreferencias.setConn(conn);
				daoPreferencias.updatePreferenciaCategoria(categoria);
			}
			else{
				throw new Exception("el usuario no tiene permisos");
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public List<PreferenciaUsuarioCategoria> darPreferenciasCategoria() throws Exception{
		List<PreferenciaUsuarioCategoria> preferencias;
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferencias.setConn(conn);
			preferencias = daoPreferencias.darPreferenciasCategorias();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return preferencias;
	}

	public List<PreferenciaUsuarioPrecio> darPreferenciasPrecio() throws Exception{
		List<PreferenciaUsuarioPrecio> preferencias;
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferencias.setConn(conn);
			preferencias = daoPreferencias.darPreferenciasPrecios();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return preferencias;
	}

	public List<PreferenciaUsuarioZona> darPreferenciasZona() throws Exception{
		List<PreferenciaUsuarioZona> preferencias;
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoPreferencias.setConn(conn);
			preferencias = daoPreferencias.darPreferenciasZonas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return preferencias;
	}

	public void agregarPreferenciaCategoria(Long id, PreferenciaUsuarioCategoria preferencia) throws Exception{
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try {
			this.conn = darConexion();
			daoPreferencias.setConn(conn);
			daoUsuarios.setConnection(conn);

			Usuario usuario = daoUsuarios.buscarUsuarioPorID(id);
			if(usuario.getId() == preferencia.getUsuarioID()){
				daoPreferencias.addPreferenciaCategoria(preferencia);
				conn.commit();
			}else{
				throw new Exception("No tiene permisos para realizar esta acci�n");
			}
		} catch(SQLException e){
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException: "+ e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try{
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception){
				System.err.println("SQLException closing resources: "+ exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void agregarPreferenciaPrecio(Long id, PreferenciaUsuarioPrecio preferencia) throws Exception{
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try {
			this.conn = darConexion();
			daoPreferencias.setConn(conn);
			daoUsuarios.setConnection(conn);

			Usuario usuario = daoUsuarios.buscarUsuarioPorID(id);
			if(usuario.getId() == preferencia.getUsuarioID()){
				daoPreferencias.addPreferenciaPrecio(preferencia);
				conn.commit();
			}else{
				throw new Exception("No tiene permisos para realizar esta acci�n");
			}
		} catch(SQLException e){
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException: "+ e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try{
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception){
				System.err.println("SQLException closing resources: "+ exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void agregarPreferenciaZona(Long id, PreferenciaUsuarioZona preferencia) throws Exception{
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try {
			this.conn = darConexion();
			daoPreferencias.setConn(conn);
			daoUsuarios.setConnection(conn);

			Usuario usuario = daoUsuarios.buscarUsuarioPorID(id);
			if(usuario.getId() == preferencia.getUsuarioID()){
				daoPreferencias.addPreferenciaZona(preferencia);
				conn.commit();
			}else{
				throw new Exception("No tiene permisos para realizar esta acci�n");
			}
		} catch(SQLException e){
			System.err.println("SQLException: "+e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException: "+ e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try{
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception){
				System.err.println("SQLException closing resources: "+ exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como parametro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updatePreferenciaPrecio(Long id, PreferenciaUsuarioPrecio preferencia) throws Exception {
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			if(daoUsuarios.buscarUsuarioPorID(id).getInfoRol().equals("Admin")){
				daoPreferencias.setConn(conn);
				daoPreferencias.updatePreferenciaPrecio(preferencia);
			}
			else{
				throw new Exception("el usuario no tiene permisos");
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * Metodo que modela la transaccion que actualiza el video que entra como parametro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como parametro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updatePreferenciaZona(Long id, PreferenciaUsuarioZona preferencia) throws Exception {
		DAOPreferencias daoPreferencias = new DAOPreferencias();
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			if(daoUsuarios.buscarUsuarioPorID(id).getInfoRol().equals("Admin")){
				daoPreferencias.setConn(conn);
				daoPreferencias.updatePreferenciaZona(preferencia);
			}
			else{
				throw new Exception("el usuario no tiene permisos");
			}

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public List<Producto> darPoductosMasOfrecidos() throws Exception{
		List<Producto> ofrecidos;
		DAOProductosIngredientes daoProductos = new DAOProductosIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			ofrecidos = daoProductos.RFC4();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ofrecidos;
	}

	public void addPedido(Pedido pedido) throws Exception {
		DAOProductosIngredientes daoProductos = new DAOProductosIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductos.setConn(conn);
			daoProductos.addPedido(pedido);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void servirPedido(Servido servido, Long id) throws Exception{
		DAOServidos daoServidos = new DAOServidos();
		DAOUsuarios daoUsuario = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoServidos.setConn(conn);
			daoUsuario.setConnection(conn);
			if(daoUsuario.buscarUsuarioPorID(id).getRol().equals("Restaurante")) {
				daoServidos.servirPedido(servido);
				conn.commit();
			}
			else 
			{
				throw new Exception("El usuario no tiene permisos");
			}
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoServidos.cerrarRecursos();
				daoUsuario.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void cancelarPedido(Long id, Cancelado cancelado) throws Exception {
		DAOUsuarios daoUsuarios = new DAOUsuarios();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoUsuarios.setConnection(conn);
			daoUsuarios.cancelarPedido(id, cancelado);
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void addPedidoMesa(PedidoMesa pedidoMesa) throws Exception{
		DAOProductosIngredientes daoProductosIngredientes = new DAOProductosIngredientes();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			daoProductosIngredientes.setConn(conn);
			daoProductosIngredientes.addPedidoMesa(pedidoMesa);
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoProductosIngredientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
}