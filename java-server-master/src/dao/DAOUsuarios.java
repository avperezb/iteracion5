package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cancelado;
import vos.Usuario;
import vos.UsuarioClientePref;

public class DAOUsuarios {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOUsuarios(){
		recursos = new ArrayList<>();
	}

	public void cerrarRecursos(){
		for(Object ob: recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	public void setConnection(Connection conn){
		this.conn = conn;
	}

	public ArrayList<Usuario> darUsuarios() throws SQLException, Exception{

		ArrayList<Usuario> usuarios = new ArrayList<>();

		String sql = "SELECT * FROM USUARIO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = (Long) rs.getLong("ID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			String infoRol = rs.getString("INFO_ROL");
			String nombre = rs.getString("NOMBRE");

			usuarios.add(new Usuario(idUsuario, correo, rol, infoRol, nombre));
		}
		return usuarios;
	}

	public ArrayList<UsuarioClientePref> darUsuarioPreferencias() throws SQLException, Exception{

		ArrayList<UsuarioClientePref> usuarios = new ArrayList<>();

		String sql = "SELECT * FROM USUARIO INNER JOIN PREFERENCIA_USUARIO_PRECIO ON USUARIO.ROL = 'Cliente'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = (Long) rs.getLong("ID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			String infoRol = rs.getString("INFO_ROL");
			String nombre = rs.getString("NOMBRE");
			Long preferencia = (Long) rs.getLong("PRECIO");

			usuarios.add(new UsuarioClientePref(idUsuario, correo, rol, infoRol, nombre, preferencia));
		}
		return usuarios;
	}

	public Usuario buscarUsuarioPorID(Long id) throws SQLException{
		Usuario usuario = null;

		String sql = "SELECT * FROM USUARIO WHERE ID = '" + id +"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long idUsuario = (Long) rs.getLong("ID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			String infoRol = rs.getString("INFO_ROL");
			String nombre = rs.getString("NOMBRE");
			usuario = new Usuario(idUsuario, correo, rol, infoRol, nombre);
		}

		return usuario;
	}

	public void addUsuario(Usuario usuario) throws SQLException{
		String sql = "INSERT INTO USUARIO(ID, CORREO, ROL, INFO_ROL, NOMBRE) VALUES (";
		sql += usuario.getId() + ",'";
		sql += usuario.getCorreo() + "','";
		sql += usuario.getRol() + "','";
		sql += usuario.getInfoRol() + "','";
		sql += usuario.getNombre() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deleteUsuario(Usuario usuario) throws SQLException{
		String sql = "DELETE FROM USUARIO";
		sql += " WHERE ID = " + usuario.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public ArrayList<Usuario> darAdministradores() throws SQLException, Exception{

		ArrayList<Usuario> usuarios = new ArrayList<>();

		String sql = "SELECT * FROM USUARIO WHERE ROL LIKE 'Admin'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long idUsuario = (Long) rs.getLong("ID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			String infoRol = rs.getString("INFO_ROL");
			String nombre = rs.getString("NOMBRE");

			usuarios.add(new Usuario(idUsuario, correo, rol, infoRol, nombre));
		}
		return usuarios;
	}

	public void cancelarPedido(Long id, Cancelado cancelado)throws SQLException, Exception {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<1");
//		int cantidadAReestablecer = 0;
//		String menuOProd = "";
		long idPedido = cancelado.getId();
		String sqlBuscarUsuarioQuePidio1 = "SELECT ID_USUARIO, CANTIDAD FROM USUARIO_PEDIDO_PRODUCTOS WHERE ID_PEDIDO= "+idPedido;
		PreparedStatement prepStmt1 = conn.prepareStatement(sqlBuscarUsuarioQuePidio1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();
		ArrayList a = new ArrayList();
//		ArrayList b = new ArrayList();
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<2");
		while(rs1.next()) {
			long idUsuario = rs1.getLong("ID_USUARIO");
			int cantidad1= rs1.getInt("CANTIDAD");
			a.add(idUsuario);
//			b.add(cantidad1);
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<3");
		String sqlBuscarUsuarioQuePidio2 = "SELECT ID_USUARIO, CANTIDAD FROM USUARIO_PEDIDOS_MENUS WHERE ID_PEDIDO= "+idPedido;
		PreparedStatement prepStmt2 = conn.prepareStatement(sqlBuscarUsuarioQuePidio2);
		recursos.add(prepStmt2);
		ResultSet rs2 = prepStmt2.executeQuery();
		ArrayList aa = new ArrayList();
//		ArrayList bb = new ArrayList();
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<4");
		while(rs2.next()) {
			long idUsuario = rs2.getLong("ID_USUARIO");
			int cantidad2 = rs2.getInt("CANTIDAD");
			aa.add(idUsuario);
//			bb.add(cantidad2);
		}
//		cantidadAReestablecer = (int) (b.get(0)!=null?b.get(0):bb.get(0));
//		menuOProd = (String) (b.get(0)!=null?"M":"P");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<5");
		long idUsuario = (long) ((a.get(0)!=null) ? a.get(0):aa.get(0));
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<usuario"+idUsuario);

		String sqlComprobarServido = "SELECT SERVIDO FROM PEDIDOS WHERE ID= "+cancelado.getId();
		PreparedStatement prepStmtComprobar = conn.prepareStatement(sqlComprobarServido);
		recursos.add(prepStmtComprobar);
		ResultSet rscomp = prepStmtComprobar.executeQuery();
		ArrayList<String> aaaa = new ArrayList<String>();
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<6");
		while(rscomp.next()) {
			String estado = rscomp.getString("SERVIDO");
			aaaa.add(estado);
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<7");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< id param"+id);
		if(idUsuario==id) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<8");
			if(aaaa.get(0).equals("N")) {
				
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<9");
				String sqlBorrarProd = "DELETE FROM USUARIO_PEDIDO_PRODUCTOS WHERE ID_PEDIDO=" + cancelado.getId();
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<10");
				PreparedStatement prepStmt3 = conn.prepareStatement(sqlBorrarProd);
				recursos.add(prepStmt3);
				prepStmt3.executeQuery();
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<11");
				String sqlBorrarMenu = "DELETE FROM USUARIO_PEDIDOS_MENUS WHERE ID_PEDIDO=" + cancelado.getId();
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<12");
				PreparedStatement prepStmt4 = conn.prepareStatement(sqlBorrarMenu);
				recursos.add(prepStmt4);
				prepStmt4.executeQuery();
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<13");
				String sqlActualizarCancelado = "UPDATE PEDIDOS SET SERVIDO='C' WHERE ID = "+cancelado.getId();
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<14");
				PreparedStatement prepStmt5 = conn.prepareStatement(sqlActualizarCancelado);
				recursos.add(prepStmt5);
				prepStmt5.executeQuery();
				
//				//.-------------------sumar existencias
//				
//				if(menuOProd.equals("P")) {
//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<15");
//
//				String sqlSumarExistenciasProd = "UPDATE RESTAURANTES_PRODUCTOS SET CANTIDAD= CANTIDAD+"+ cantidadAReestablecer + " WHERE ID="+ a.get(0);
//				PreparedStatement prepStmtSumarProd = conn.prepareStatement(sqlSumarExistenciasProd);
//				recursos.add(prepStmtSumarProd);
//				prepStmtSumarProd.executeQuery();
//				}
//				if(menuOProd.equals("M")) {
//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<16");
//
//					String sqlSumarExistenciasMenu = "UPDATE MENUS SET CANTIDAD= CANTIDAD+"+ cantidadAReestablecer + " WHERE ID="+ aa.get(0);
//					PreparedStatement prepStmtSumarMenu = conn.prepareStatement(sqlSumarExistenciasMenu);
//					recursos.add(prepStmtSumarMenu);
//					prepStmtSumarMenu.executeQuery();
//				}
			}
			else {
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<15");
				throw new Exception("El producto ya fue servido");
			}
		}
		else {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<16");
			throw new Exception("Usuario sin permisos para cancelar pedido");
		}



	}
}
