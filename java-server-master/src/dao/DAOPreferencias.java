package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.PreferenciaUsuarioCategoria;
import vos.PreferenciaUsuarioPrecio;
import vos.PreferenciaUsuarioZona;

public class DAOPreferencias {

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
	public DAOPreferencias() {
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


	public ArrayList<PreferenciaUsuarioCategoria> darPreferenciasCategorias() throws SQLException{
		ArrayList<PreferenciaUsuarioCategoria> preferencias = new ArrayList<>();

		String sql = "SELECT * FROM PREFERENCIA_USUARIO_CATEGORIA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = rs.getLong("USUARIO_ID");
			Long idCategoria = rs.getLong("CLASIFICACION_ID");
			preferencias.add(new PreferenciaUsuarioCategoria(idUsuario, idCategoria));
		}
		return preferencias;
	}

	public ArrayList<PreferenciaUsuarioZona> darPreferenciasZonas() throws SQLException{
		ArrayList<PreferenciaUsuarioZona> preferencias = new ArrayList<>();

		String sql = "SELECT * FROM PREFERENCIA_USUARIO_ZONA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = rs.getLong("USUARIO_ID");
			Long idZona = rs.getLong("ZONA_ID");
			preferencias.add(new PreferenciaUsuarioZona(idUsuario, idZona));

		}
		return  preferencias;
	}

	public ArrayList<PreferenciaUsuarioPrecio> darPreferenciasPrecios() throws SQLException{
		ArrayList<PreferenciaUsuarioPrecio> preferencias = new ArrayList<>();

		String sql = "SELECT * FROM PREFERENCIA_USUARIO_PRECIO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = rs.getLong("USUARIO_ID");
			Double precio = rs.getDouble("PRECIO");
			preferencias.add(new PreferenciaUsuarioPrecio(idUsuario, precio));
		}
		return preferencias;
	}

	public PreferenciaUsuarioPrecio buscarPreferenciasPrecios(Long id) throws SQLException{
		PreferenciaUsuarioPrecio preferencia = null;

		String sql = "SELECT * FROM PREFERENCIA_USUARIO_PRECIO ";
		sql += "WHERE USUARIO_ID = " + "'" + id + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = rs.getLong("USUARIO_ID");
			Double precio = rs.getDouble("PRECIO");
			preferencia = new PreferenciaUsuarioPrecio(idUsuario, precio);
		}
		return preferencia;
	}

	public PreferenciaUsuarioZona buscarPreferenciasZonas(Long id) throws SQLException{
		PreferenciaUsuarioZona preferencia = null;

		String sql = "SELECT * FROM PREFERENCIA_USUARIO_ZONA ";
		sql += "WHERE USUARIO_ID = " + "'" + id + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = rs.getLong("USUARIO_ID");
			Long idZona = rs.getLong("ZONA_ID");
			preferencia = new PreferenciaUsuarioZona(idUsuario, idZona);

		}
		return preferencia;
	}

	public PreferenciaUsuarioCategoria buscarPreferenciasClasificaciones(Long id) throws SQLException{
		PreferenciaUsuarioCategoria preferencia = null;

		String sql = "SELECT * FROM PREFERENCIA_USUARIO_CATEGORIA ";
		sql += "WHERE USUARIO_ID = " + "'" + id + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = rs.getLong("USUARIO_ID");
			Long idCategoria = rs.getLong("CLASIFICACION_ID");
			preferencia = new PreferenciaUsuarioCategoria(idUsuario, idCategoria);
		}
		return preferencia;
	}

	public void addPreferenciaCategoria(PreferenciaUsuarioCategoria pref) throws SQLException{

		String sql = "INSERT INTO PREFERENCIA_USUARIO_CATEGORIA (USUARIO_ID, CLASIFICACION_ID) VALUES (";
		sql += pref.getUsuarioID() + ",'";
		sql += pref.getClasificacionID() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void addPreferenciaZona(PreferenciaUsuarioZona pref) throws SQLException{
		String sql = "INSERT INTO PREFERENCIA_USUARIO_ZONA (USUARIO_ID, ZONA_ID) VALUES (";
		sql += pref.getUsuarioID() + ",'";
		sql += pref.getZonaID() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void addPreferenciaPrecio(PreferenciaUsuarioPrecio pref) throws SQLException{
		String sql = "INSERT INTO PREFERENCIA_USUARIO_PRECIO (USUARIO_ID, PRECIO) VALUES (";
		sql += pref.getUsuarioID() + ",";
		sql += pref.getPrecio() + ")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void updatePreferenciaCategoria(PreferenciaUsuarioCategoria newPref) throws SQLException{

		String sql = "UPDATE PREFERENCIA_USUARIO_CATEGORIA SET ";
		sql += "CLASIFICACION_ID='" + newPref.getClasificacionID() + "',";
		sql += " WHERE USUARIO_ID = " + newPref.getUsuarioID();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void updatePreferenciaZona(PreferenciaUsuarioZona newPref) throws SQLException{

		String sql = "UPDATE PREFERENCIA_USUARIO_ZONA SET ";
		sql += "ZONA_ID='" + newPref.getZonaID() + "',";
		sql += " WHERE USUARIO_ID = " + newPref.getUsuarioID();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void updatePreferenciaPrecio(PreferenciaUsuarioPrecio newPref) throws SQLException{

		String sql = "UPDATE PREFERENCIA_USUARIO_PRECIO SET ";
		sql += "PRECIO='" + newPref.getPrecio() + "',";
		sql += " WHERE USUARIO_ID = " + newPref.getUsuarioID();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deletePreferenciaCategoria(PreferenciaUsuarioCategoria pref) throws SQLException{

		String sql = "DELETE FROM PREFERENCIA_USUARIO_CATEGORIA";
		sql += " WHERE ID = " + pref.getUsuarioID();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deletePreferenciaZona(PreferenciaUsuarioZona pref) throws SQLException{

		String sql = "DELETE FROM PREFERENCIA_USUARIO_ZONA";
		sql += " WHERE ID = " + pref.getUsuarioID();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deletePreferenciaPrecio(PreferenciaUsuarioPrecio pref) throws SQLException{

		String sql = "DELETE FROM PREFERENCIA_USUARIO_PRECIO";
		sql += " WHERE ID = " + pref.getUsuarioID();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
