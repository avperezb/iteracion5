package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Ingrediente;
import vos.Restaurante;
import vos.Video;

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
			Long idRepresentante = rs.getLong("ID_RPRESENTANTE"); 
			restaurantes.add(new Restaurante(id, nombre, tipo, urlPW, idRepresentante)); 
		}
		return restaurantes;
	}
	
	/**
	 * RF3
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
	 * 
	 */
	
}
