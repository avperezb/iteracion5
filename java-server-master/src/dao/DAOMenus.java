package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Menu;

public class DAOMenus {
	
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
	public DAOMenus() {
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
	
	public ArrayList<Menu> darMenus() throws SQLException, Exception{
		
		ArrayList<Menu> menus = new ArrayList<Menu>();
		
		String sql = "SELECT * FROM MENUS";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			Long id = Long.parseLong(rs.getString("ID"));
			int tiempoPreparacion = rs.getInt("TIEMPO_PREPARACION");
			String descripcionEsp = rs.getString("DESCRIPCION_ESP");
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			Long restauranteId = Long.parseLong(rs.getString("RESTAURANTE_ID"));
			double precio = rs.getInt("PRECIO");
			double costo = rs.getInt("COSTO");
			menus.add(new Menu(id, tiempoPreparacion, descripcionEsp, descripcionEng, restauranteId, precio, costo));
		}
		return menus;
	}
	
	/**
	 * Metodo que busca el/los videos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Menu> buscarMenusPorId(String identificador) throws SQLException, Exception {
		ArrayList<Menu> menus = new ArrayList<Menu>();

		String sql = "SELECT * FROM MENUS WHERE NAME ='" + identificador + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = Long.parseLong(rs.getString("ID"));
			int tiempoPreparacion = rs.getInt("TIEMPO_PREPARACION");
			String descripcionEsp = rs.getString("DESCRIPCION_ESP");
			String descripcionEng = rs.getString("DESCRIPCION_ENG");
			Long restauranteId = Long.parseLong(rs.getString("RESTAURANTE_ID"));
			double precio = rs.getInt("PRECIO");
			double costo = rs.getInt("COSTO");
			menus.add(new Menu(id, tiempoPreparacion, descripcionEsp, descripcionEng, restauranteId, precio, costo));
		}
		return menus;
	}

	/**
	 * Metodo que agrega el video que entra como parametro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addMenu(Menu menu) throws SQLException, Exception {

		String sql = "INSERT INTO MENUS VALUES (";
		sql += menu.getId() + ",'";
		sql += menu.getTiempoPreparacion() + "',";
		sql += menu.getDescripcionEsp() + ",";
		sql += menu.getDescripcionEng() + ",";
		sql += menu.getIdRestaurante() + ",";
		sql += menu.getPrecio() + ",";
		sql += menu.getCosto() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
}
