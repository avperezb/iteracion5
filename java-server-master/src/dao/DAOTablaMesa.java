/**
 * 
 */
package dao;

/**
 * @author av.perezb
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Mesa;

public class DAOTablaMesa {

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOTablaIngredientes
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaMesa() {
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexion que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}

	public ArrayList<Mesa> darMesas() throws SQLException, Exception {
		ArrayList<Mesa> mesas = new ArrayList<Mesa>();

		String sql = "SELECT * FROM MESA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int numero = rs.getInt("NUMMESA");
			int capacidad=rs.getInt("CAPACIDAD");
			Long zona=rs.getLong("IDZONA");

			mesas.add(new Mesa(numero, capacidad, zona));
		}
		return mesas;
	}

	public Mesa darMesaPorId(int numero)throws SQLException, Exception {

		Mesa mesa = null;

		String sql = "SELECT * FROM MESA WHERE NUMMESA= "+numero;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int num = rs.getInt("NUMMESA");
			int capacidad=rs.getInt("CAPACIDAD");
			Long zona=rs.getLong("IDZONA");
			mesa = new Mesa(num,capacidad, zona);
		}
		return mesa;
	}

	public void addMesa(Mesa me) throws SQLException, Exception {

		Mesa prueba = darMesaPorId(me.getNumMesa());
		if(prueba!=null)
			throw new Exception ("Una mesa con el número "+me.getNumMesa()+" ya existe.");
		
		String sql = "INSERT INTO MESA VALUES ('";
		sql += me.getNumMesa()+ "','";
		sql += me.getIdZona()+ "','";
		sql += me.getCapacidad()+"')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	public void deleteMesa(Mesa mesa) throws SQLException, Exception {

		String sql = "DELETE FROM MESA";
		sql += " WHERE NUMMESA = " + mesa.getNumMesa()+"'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void updateMesa(Mesa mesa) throws SQLException, Exception {

		String sql = "UPDATE MESA SET CAPACIDAD='";
		sql += mesa.getCapacidad()+ "', IDZONA='";
		sql += mesa.getIdZona();
		sql += " WHERE NUMMESA = " + mesa.getNumMesa()+"'";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}

