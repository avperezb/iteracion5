package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Usuario;

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
}
