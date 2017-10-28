package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdk.net.NetworkPermission;
import vos.EquivalenciaProducto;
import vos.Ingrediente;
import vos.Pedido;
import vos.Producto;
import vos.Video;

public class DAOProductosIngredientes {


	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOProductosIngredientes() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los videos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM INGREDIENTE;
	 * @return Arraylist con los videos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ingrediente> darIngredientes() throws SQLException, Exception {
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

		String sql = "SELECT * FROM INGREDIENTES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			String descipcionEsp = rs.getString("DESCRIPCION_ESP");			
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			ingredientes.add(new Ingrediente(id, name, descipcionEsp, descripcionEng));
		}
		return ingredientes;
	}

	public ArrayList<Producto> darProductos() throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			Long tiempoPreparacion = rs.getLong("TIEMPO_PREPARACIO");
			String descipcionEsp = rs.getString("DESCRIPCION_ESP");			
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			Long clasificacion = rs.getLong("CLASIFICACION");
			Long tipo = rs.getLong("TIPO");

			productos.add(new Producto(id, name,tiempoPreparacion,descipcionEsp,descripcionEng,clasificacion,tipo));
		}
		return productos;
	}

	public ArrayList<Producto> darProductosRestaurante(Long idRes) throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT ID_PRODUCTO, COSTO, PRECIO, NOMBRE, TIEMPO_PREPARACIO, DESCRIPCION_ESP, DESCRIPCION_ENG,CLASIFICACION,TIPO FROM (SELECT * FROM RESTAURANTES_PRODUCTOS A INNER JOIN PRODUCTOS P ON A.ID_PRODUCTO = P.ID) WHERE ID_RESTAURANTE = "+idRes;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id = rs.getLong("ID_PRODUCTO");
			Long tiempoPreparacion = rs.getLong("TIEMPO_PREPARACIO");
			String descipcionEsp = rs.getString("DESCRIPCION_ESP");			
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			Long clasificacion = rs.getLong("CLASIFICACION");
			Long tipo = rs.getLong("TIPO");

			productos.add(new Producto(id, name,tiempoPreparacion,descipcionEsp,descripcionEng,clasificacion,tipo));
		}
		return productos;
	}

	public ArrayList<Producto> darProductosCategoria(Long idCat) throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS WHERE CLASIFICACION ="+idCat;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			Long tiempoPreparacion = rs.getLong("TIEMPO_PREPARACIO");
			String descipcionEsp = rs.getString("DESCRIPCION_ESP");			
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			Long clasificacion = rs.getLong("CLASIFICACION");
			Long tipo = rs.getLong("TIPO");

			productos.add(new Producto(id, name,tiempoPreparacion,descipcionEsp,descripcionEng,clasificacion,tipo));
		}
		return productos;
	}

	public ArrayList<Producto> darProductosPrecios(Long pMenor, Long pMayor) throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT ID_PRODUCTO, COSTO, PRECIO, NOMBRE, TIEMPO_PREPARACIO, DESCRIPCION_ESP, DESCRIPCION_ENG,CLASIFICACION,TIPO FROM (SELECT * FROM RESTAURANTES_PRODUCTOS A INNER JOIN PRODUCTOS P ON A.ID_PRODUCTO = P.ID) WHERE PRECIO >" +pMenor+ "AND PRECIO <"+pMayor;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id = rs.getLong("ID_PRODUCTO");
			Long tiempoPreparacion = rs.getLong("TIEMPO_PREPARACIO");
			String descipcionEsp = rs.getString("DESCRIPCION_ESP");			
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			Long clasificacion = rs.getLong("CLASIFICACION");
			Long tipo = rs.getLong("TIPO");

			productos.add(new Producto(id, name,tiempoPreparacion,descipcionEsp,descripcionEng,clasificacion,tipo));
		}
		return productos;
	}
	/**
	 * Metodo que agrega el video que entra como parametro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addIngrediente(Ingrediente ingrediente) throws SQLException, Exception {

		String sql = "INSERT INTO INGREDIENTES VALUES (";
		sql += ingrediente.getId() + ",'";
		sql += ingrediente.getNombre() + "','";
		sql += ingrediente.getDescricionEsp() + "','";
		sql += ingrediente.getDescripcionEng() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	public void addProducto(Producto producto) throws SQLException, Exception {

		String sql = "INSERT INTO PRODUCTOS VALUES (";
		sql += producto.getId() + ",'";
		sql += producto.getNombre() + "',";
		sql += producto.getTiempoPreparacion() + ",'";
		sql += producto.getDecripcionEsp() + "','";
		sql += producto.getDescripcionEng() + "',";
		sql += producto.getClasificacion() + ",";
		sql += producto.getTipo() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void addEquivalenciaProducto(EquivalenciaProducto equivalencia) throws SQLException, Exception {

		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS WHERE CLASIFICACION ="+equivalencia.getIdProducto();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			Long tiempoPreparacion = rs.getLong("TIEMPO_PREPARACIO");
			String descipcionEsp = rs.getString("DESCRIPCION_ESP");			
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			Long clasificacion = rs.getLong("CLASIFICACION");
			Long tipo = rs.getLong("TIPO");

			productos.add(new Producto(id, name,tiempoPreparacion,descipcionEsp,descripcionEng,clasificacion,tipo));
		}
		
		
		
		String sqlDos = "SELECT * FROM PRODUCTOS WHERE CLASIFICACION ="+equivalencia.getIdEquivalencia();

		PreparedStatement prepStmtDos = conn.prepareStatement(sqlDos);
		recursos.add(prepStmtDos);
		ResultSet rsDos = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rsDos.getString("NOMBRE");
			Long id = rsDos.getLong("ID");
			Long tiempoPreparacion = rsDos.getLong("TIEMPO_PREPARACIO");
			String descipcionEsp = rsDos.getString("DESCRIPCION_ESP");			
			String descripcionEng = rsDos.getString("DESCRIPCION_ENG");
			Long clasificacion = rsDos.getLong("CLASIFICACION");
			Long tipo = rsDos.getLong("TIPO");

			productos.add(new Producto(id, name,tiempoPreparacion,descipcionEsp,descripcionEng,clasificacion,tipo));
		}
		
		if(productos.size() > 1)
		{
			throw new Exception("uno de los productos no existe");
		}
		
		if (productos.get(0).getClasificacion() == productos.get(1).getClasificacion())
		{
			String sqlTres = "INSERT INTO EQUIVALENCIAS_PRODUCTOS (ID_PRODUCTO1, ID_PRODUCTO2) VALUES ("+equivalencia.getIdProducto()+", "+equivalencia.getIdEquivalencia() +")";

			PreparedStatement prepStmtTres = conn.prepareStatement(sqlTres);
			recursos.add(prepStmtTres);
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("no se puede registrar la equivalencia porque los productos no son de la misma categoria");
		}
		
	}

	public ArrayList<Producto> RFC4() throws SQLException, Exception{
		ArrayList<Producto> respuesta = new ArrayList<>();

		String sql = "SELECT * FROM (SELECT ID_PRODUCTO, MAX(CANTIDAD_SERVIDO)"
				+ " FROM (SELECT ID_PRODUCTO, COUNT(ID_RESTAURANTE)AS CANTIDAD_SERVIDO"
				+ " FROM (SELECT A.ID AS ID_PRODUCTO, REST.ID_RESTAURANTE FROM PRODUCTOS"
				+ " A INNER JOIN RESTAURANTES_PRODUCTOS REST ON A.ID = REST.ID_PRODUCTO)"
				+ " GROUP BY ID_PRODUCTO) GROUP BY ID_PRODUCTO) INNER JOIN PRODUCTOS ON ID_PRODUCTO = PRODUCTOS.ID";
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();


		while (rs.next()) {
			Long idProducto = rs.getLong("ID");
			String name = rs.getString("NOMBRE");
			Long tiempoPreparacion = rs.getLong("TIEMPO_PREPARACIO");
			String descipcionEsp = rs.getString("DESCRIPCION_ESP");			
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			Long clasificacion = rs.getLong("CLASIFICACION");
			Long tipo = rs.getLong("TIPO");

			respuesta.add(new Producto(idProducto, name,tiempoPreparacion,descipcionEsp,descripcionEng,clasificacion,tipo));
		}
		return respuesta;
	}
	
	public void addPedido(Pedido pedido)throws SQLException, Exception{
		String sql = "INSERT INTO PEDIDOS VALUES (";
		sql += ingrediente.getId() + ",'";
		sql += ingrediente.getNombre() + "','";
		sql += ingrediente.getDescricionEsp() + "','";
		sql += ingrediente.getDescripcionEng() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}