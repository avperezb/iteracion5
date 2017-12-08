package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.Servido;

public class DAOServidos {
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
	public DAOServidos() {
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

//	public ArrayList<Servido> darServidos() throws SQLException{
//		ArrayList<Servido> servidos = new ArrayList<>();
//
//		String sql = "SELECT * FROM MENUS";
//
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		ResultSet rs = prepStmt.executeQuery();
//
//		while (rs.next()) {
//			Long id = Long.parseLong(rs.getString("ID"));
//			Long idUsuario = rs.getLong("USUARIOS_ID");
//			Long idRestaurante = rs.getLong("RESTAURANTES_ID");
//			Date fecha = rs.getDate("FECHA");
//			servidos.add(new Servido(id, idUsuario, idRestaurante));
//		}
//		return servidos;
//	}
	
//	public void addServido(Servido servido) throws SQLException, Exception {
//
//		String sql = "INSERT INTO SERVIDOS(ID, USUARIOS_ID, RESTAURANTES_ID, FECHA) VALUES (";
//		sql += servido.getId() + ",";
//		sql += servido.getUsuarioID() + ",";
//		sql += servido.getRestauranteID() + ",";
//		sql += "TO_DATE(" + servido.getFecha().getDay() + "/" + servido.getFecha().getMonth() + "/" + servido.getFecha().getYear() + ", 'DD/MM/YYYY'))";
//		
//
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//
//	}
	
//	public Servido darServido(Long idBuscado) throws SQLException{
//		
//		Servido servido = null;
//		String sql = "SELECT * FROM SERVIDOS WHERE ID = " + idBuscado;
//
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		ResultSet rs = prepStmt.executeQuery();
//		
//		while (rs.next()) {
//			Long id = Long.parseLong(rs.getString("ID"));
//			Long idUsuario = rs.getLong("USUARIOS_ID");
//			Long idRestaurante = rs.getLong("RESTAURANTES_ID");
//			Date fecha = rs.getDate("FECHA");
//			servido = new Servido(id, idUsuario, idRestaurante, fecha);
//		}
//		return servido;
//	}
	
	public void servirPedido(Servido servido)throws SQLException {
		String sqlBorrarProd = "UPDATE USUARIO_PEDIDO_PRODUCTOS SET SERVIDO='S' WHERE ID_PEDIDO = "+servido.getId();
		
		PreparedStatement prepStmt1 = conn.prepareStatement(sqlBorrarProd);
		recursos.add(prepStmt1);
		prepStmt1.executeQuery();
		
		String sqlBorrarMenu = "UPDATE USUARIO_PEDIDOS_MENUS SET SERVIDO='S' WHERE ID_PEDIDO = "+servido.getId();
		
		PreparedStatement prepStmt2 = conn.prepareStatement(sqlBorrarMenu);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
		
		String sqlActualizarServido = "UPDATE PEDIDOS SET SERVIDO='S' WHERE ID = "+servido.getId();
		
		PreparedStatement prepStmt3 = conn.prepareStatement(sqlActualizarServido);
		recursos.add(prepStmt3);
		prepStmt3.executeQuery();
		
//		String sqlInsertarServido = "INSERT INTO SERVIDOS VALUES (";
//			sqlInsertarServido += servido.getId() + ",";
//			sqlInsertarServido += servido.get	
	}
	
	public void servirMesa(Servido servidoMesa) throws SQLException{
		for(int i=0;i<3;i++) {
			Servido nuevo = new Servido(servidoMesa.getId()+i, servidoMesa.getUsuarioID(), servidoMesa.getRestauranteID());
			servirPedido(nuevo);
		}
	}
}