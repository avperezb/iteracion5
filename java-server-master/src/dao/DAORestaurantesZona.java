package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.RFC11;
import vos.Restaurante;
import vos.Zona;

public class DAORestaurantesZona {

	private ArrayList<Object> recursos;

	private Connection conn; 

	public DAORestaurantesZona(){
		recursos = new ArrayList<Object>(); 
	}

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

	public void setConn(Connection con){
		this.conn = con;
	}
	/**
	 * GET Restaurantes
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<Restaurante> darRestaurantes() throws SQLException, Exception {
		ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
		String sql = "SELECT * FROM RESTAURANTES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID"); 
			String nombre = rs.getString("NOMBRE"); 
			Integer tipo = rs.getInt("TIPO"); 
			String urlPW = rs.getString("URL_PW"); 
			Long idRepresentante = rs.getLong("ID_REPRESENTANTE"); 
			restaurantes.add(new Restaurante(id, nombre, tipo, urlPW, idRepresentante)); 
		}
		return restaurantes;
	}

	/**
	 * GET por id de un Restaurante
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Restaurante buscarRestaurantePorId(Long id) throws SQLException, Exception 
	{
		Restaurante restaurante = null;

		String sql = "SELECT * FROM RESTAURANTES WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("ID"); 
			String nombre = rs.getString("NOMBRE"); 
			Integer tipo = rs.getInt("TIPO"); 
			String  urlPW = rs.getString("URL_PW"); 
			Long idRepresentante = rs.getLong("ID_REPRESENTANTE"); 
			restaurante = new Restaurante(id2, nombre, tipo, urlPW, idRepresentante); 
		}
		return restaurante;
	}


	/**
	 * POST Restaurante
	 * @param restaurante
	 * @throws SQLException
	 * @throws Exception
	 */
	public void addRestaurante(Restaurante restaurante) throws SQLException, Exception {

		String sql = "INSERT INTO RESTAURANTES VALUES (";
		sql += restaurante.getId() + ",'";
		sql += restaurante.getNombre() + "',";
		sql += restaurante.getTipo() + "',"; 
		sql += restaurante.getUrlPW() + "',";
		sql += restaurante.getIdRepresentante()+ ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	/**
	 * GET zonas
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<Zona> darZonas() throws SQLException, Exception {
		ArrayList<Zona> zonas = new ArrayList<Zona>();
		String sql = "SELECT * FROM ZONA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID"); 
			Integer capacidadPersonas = rs.getInt("CAPACIDAD_PERSNAS"); 
			char handicap = rs.getString("HANDICAP").charAt(0); 
			String condicionesTecnicas = rs.getString("CONDICIONES_TECNICAS"); 
			zonas.add(new Zona(id, capacidadPersonas, handicap, condicionesTecnicas)); 
		}
		return zonas;
	}
	/**
	 * GET zona por id
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Zona buscarZonaPorId(Long id) throws SQLException, Exception 
	{
		Zona zona = null;

		String sql = "SELECT * FROM ZONA WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("ID"); 
			Integer capacidadPersonas = rs.getInt("CAPACIDAD_PERSNAS"); 
			char handicap = rs.getString("HANDICAP").charAt(0); 
			String condicionesTecnicas = rs.getString("CONDICIONES_TECNICAS"); 
			zona = new Zona(id2, capacidadPersonas, handicap, condicionesTecnicas);
		}
		return zona;
	}
	/**
	 * PUT zona
	 * @param zona
	 * @throws SQLException
	 * @throws Exception
	 */

	public void addZona(Zona zona) throws SQLException, Exception {

		String sql = "INSERT INTO ZONA(ID, CAPACIDAD_PERSNAS, HANDICAP, CONDICIONES_TECNICAS) VALUES (";
		sql += zona.getId() + ",'";
		sql += zona.getCapacidadPersonas() + "','";
		sql += zona.getHandicap() + "','"; 
		sql += zona.getCondicionesTecnicas() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	public ArrayList<RFC11> darInfoRFC11() throws SQLException{
		ArrayList<RFC11> listica = new ArrayList<>(); 

		String sql = "SELECT * FROM (select DIA, max(CANTIDAD) as maxi, max(NOMBRE) keep (dense_rank first order by CANTIDAD desc) as producto from (SELECT  NOMBRE, count(PRODUCTOS.ID)as CANTIDAD, TO_CHAR(FECHA,'DAY') as DIA FROM ((PEDIDOS INNER JOIN USUARIO_PEDIDO_PRODUCTOS ON  PEDIDOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PEDIDO) INNER JOIN RESTAURANTES_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE) INNER JOIN PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = PRODUCTOS.ID group by PRODUCTOS.NOMBRE, TO_CHAR(FECHA,'DAY'))group by DIA) A INNER JOIN(select DIA, MIN(CANTIDAD) as MINI, MIN(NOMBRE) keep (dense_rank first order by CANTIDAD ASC) as producto from (SELECT NOMBRE, count(PRODUCTOS.ID)as CANTIDAD, TO_CHAR(FECHA,'DAY') as DIA FROM ((PEDIDOS INNER JOIN USUARIO_PEDIDO_PRODUCTOS ON  PEDIDOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PEDIDO) INNER JOIN RESTAURANTES_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE) INNER JOIN PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = PRODUCTOS.ID group by PRODUCTOS.NOMBRE, TO_CHAR(FECHA,'DAY'))group by DIA) B ON A.DIA = B.DIA";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		
		while(rs.next() ){
			
			String diaDeLaSemana = rs.getString("DIA"); 
			String nombreProductoMasConsumido = rs.getString("PRODUCTO"); 
			String nombreProductoMenosConsumido = rs.getString(6); 
			int cantidadMaximaProducto = rs.getInt("MAXI"); 
			int cantidadMinimaProducto = rs.getInt("MINI"); 
			listica.add(new RFC11(diaDeLaSemana, nombreProductoMasConsumido, nombreProductoMenosConsumido, cantidadMaximaProducto, cantidadMinimaProducto, null, null)); 
			
		}
		

		String sql2 = "SELECT * FROM (select DIA, max(CANTIDAD) as maxi, max(NOMBRE) keep (dense_rank first order by CANTIDAD desc) as RESTAURANTE "
				+ "from (SELECT  NOMBRE, count(RESTAURANTES.ID)as CANTIDAD, TO_CHAR(FECHA,'DAY') as DIA FROM ((PEDIDOS "
				+ "INNER JOIN USUARIO_PEDIDO_PRODUCTOS ON  PEDIDOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PEDIDO) "
				+ "INNER JOIN RESTAURANTES_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE) "
				+ "INNER JOIN RESTAURANTES ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = RESTAURANTES.ID group by RESTAURANTES.NOMBRE, TO_CHAR(FECHA,'DAY'))"
				+ "group by DIA) A "
				+ "INNER JOIN "
				+ "(select DIA, MIN(CANTIDAD) as MINI, MIN(NOMBRE) keep (dense_rank first order by CANTIDAD ASC) as RESTAURANTE "
				+ "from (SELECT NOMBRE, count(RESTAURANTES.ID)as CANTIDAD, TO_CHAR(FECHA,'DAY') as DIA FROM ((PEDIDOS "
				+ "INNER JOIN USUARIO_PEDIDO_PRODUCTOS ON  PEDIDOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PEDIDO) "
				+ "INNER JOIN RESTAURANTES_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE) "
				+ "INNER JOIN RESTAURANTES ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = RESTAURANTES.ID group by RESTAURANTES.NOMBRE, TO_CHAR(FECHA,'DAY'))"
				+ "group by DIA) B ON A.DIA = B.DIA"; 
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		ResultSet rs2 = prepStmt2.executeQuery();
		int c = 0; 
		while(rs2.next()){
			String nombreRestMasFrec = rs2.getString("RESTAURANTE"); 
			String nombreRestMenosFrec = rs2.getString(6); 
			listica.get(c).setNombreRestMasFrec(nombreRestMasFrec);
			listica.get(c).setNombreRestMenosFrec(nombreRestMenosFrec);
			c++; 
		}
		return listica; 

	}
	
	
	

}
