package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import vos.Cancelado;
import vos.RestauranteRangoFechas;
import vos.RestauranteRentabilidad;
import vos.Usuario;
import vos.UsuarioClientePref;
import vos.InfoUsuarioReqRFC9;
import vos.RentabilidadRest;

public class DAOUsuarios {

	private ArrayList<Object> recursos;

	private Connection conn;

	private ArrayList<RentabilidadRest> restaurantes;

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
			Long idUsuario = rs.getLong("ID");
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
			Long idUsuario = rs.getLong("ID");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			String infoRol = rs.getString("INFO_ROL");
			String nombre = rs.getString("NOMBRE");
			Long preferencia = rs.getLong("PRECIO");

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
			Long idUsuario = rs.getLong("ID");
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
			Long idUsuario = rs.getLong("ID");
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

				/**	//.-------------------sumar existencias

				if(menuOProd.equals("P")) {
					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<15");

				String sqlSumarExistenciasProd = "UPDATE RESTAURANTES_PRODUCTOS SET CANTIDAD= CANTIDAD+"+ cantidadAReestablecer + " WHERE ID="+ a.get(0);
				PreparedStatement prepStmtSumarProd = conn.prepareStatement(sqlSumarExistenciasProd);
				recursos.add(prepStmtSumarProd);
				prepStmtSumarProd.executeQuery();
				}
				if(menuOProd.equals("M")) {
					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<16");

					String sqlSumarExistenciasMenu = "UPDATE MENUS SET CANTIDAD= CANTIDAD+"+ cantidadAReestablecer + " WHERE ID="+ aa.get(0);
					PreparedStatement prepStmtSumarMenu = conn.prepareStatement(sqlSumarExistenciasMenu);
					recursos.add(prepStmtSumarMenu);
					prepStmtSumarMenu.executeQuery();
				}**/
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

	public ArrayList<InfoUsuarioReqRFC9> consultarConsumoV1(Long id, RestauranteRangoFechas restauranteRangoFechas, String privacidad) throws SQLException, Exception{
		//agrupamientos y ordenamientos
		/**
		 * 1=ordenamiento por datos del cliente
		 * 2=ordenamiento por producto
		 * 3=ordenamiento por tipo de producto
		 */
		String ordenamiento="";
		if(restauranteRangoFechas.getOrdAgrup().equals("1")) {
			ordenamiento=" ORDER BY ID_USUARIO";
		}else if(restauranteRangoFechas.getOrdAgrup().equals("2")) {
			ordenamiento=" ORDER BY NOMBRE_PRODUCTO";
		}else if(restauranteRangoFechas.getOrdAgrup().equals("3")) {
			ordenamiento=" ORDER BY CLASIFICACION";
		}
		ArrayList<InfoUsuarioReqRFC9> resp = new ArrayList<>();
		String sql = "SELECT USUARIO.ID AS ID_USUARIO, USUARIO.CORREO AS CORREO, USUARIO.ROL AS ROL, USUARIO.NOMBRE AS USUARIO_NOMBRE, PRODUCTOS.NOMBRE AS NOMBRE_PRODUCTO, PRODUCTOS.CLASIFICACION AS CLASIFICACION FROM (((USUARIO INNER JOIN USUARIO_PEDIDO_PRODUCTOS ON USUARIO.ID = USUARIO_PEDIDO_PRODUCTOS.ID_USUARIO) \r\n" + 
				"INNER JOIN RESTAURANTES_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE)\r\n" + 
				"INNER JOIN PEDIDOS ON PEDIDOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PEDIDO) INNER JOIN PRODUCTOS ON PRODUCTOS.ID = RESTAURANTES_PRODUCTOS.ID_PRODUCTO\r\n" + 
				"WHERE RESTAURANTES_PRODUCTOS.ID_RESTAURANTE ="+ restauranteRangoFechas.getIdRestaurante() +"AND \r\n" + 
				"FECHA > to_date('"+restauranteRangoFechas.getFechaInicial()+"', 'DD/MM/YYYY') \r\n" + 
				"AND FECHA < to_date('"+restauranteRangoFechas.getFechaFinal()+"', 'DD/MM/YYYY')\r\n" + 
				ordenamiento;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			Long idUsuario = rs.getLong("ID_USUARIO");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			String nombre = rs.getString("USUARIO_NOMBRE");
			String nombreProducto = rs.getString("NOMBRE_PRODUCTO");
			String clasificacion = rs.getString("CLASIFICACION");

			resp.add(new InfoUsuarioReqRFC9(idUsuario, correo, rol, nombre, nombreProducto, clasificacion));
		}
		return resp;
	}

	public ArrayList<InfoUsuarioReqRFC9> consultarConsumoV2(Long id, RestauranteRangoFechas restauranteRangoFechas, String privacidad) throws SQLException, Exception{
		//agrupamientos y ordenamientos
		/**
		 * 1=ordenamiento por datos del cliente
		 * 2=ordenamiento por producto
		 * 3=ordenamiento por tipo de producto
		 */
		String ordenamiento="";
		if(restauranteRangoFechas.getOrdAgrup().equals("1")) {
			ordenamiento=" ORDER BY ID_USUARIO";
		}else if(restauranteRangoFechas.getOrdAgrup().equals("2")) {
			ordenamiento=" ORDER BY NOMBRE_PRODUCTO";
		}else if(restauranteRangoFechas.getOrdAgrup().equals("3")) {
			ordenamiento=" ORDER BY CLASIFICACION";
		}
		ArrayList<InfoUsuarioReqRFC9> resp = new ArrayList<>();
		String sql = "SELECT USUARIO.ID AS ID_USUARIO, USUARIO.CORREO AS CORREO, USUARIO.ROL AS ROL, USUARIO.NOMBRE AS USUARIO_NOMBRE, PRODUCTOS.NOMBRE AS NOMBRE_PRODUCTO, PRODUCTOS.CLASIFICACION AS CLASIFICACION FROM (((USUARIO INNER JOIN USUARIO_PEDIDO_PRODUCTOS ON USUARIO.ID = USUARIO_PEDIDO_PRODUCTOS.ID_USUARIO) \r\n" + 
				"INNER JOIN RESTAURANTES_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE)\r\n" + 
				"INNER JOIN PEDIDOS ON PEDIDOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PEDIDO) INNER JOIN PRODUCTOS ON PRODUCTOS.ID = RESTAURANTES_PRODUCTOS.ID_PRODUCTO\r\n" + 
				"WHERE RESTAURANTES_PRODUCTOS.ID_RESTAURANTE !="+ restauranteRangoFechas.getIdRestaurante() +"AND \r\n" + 
				"FECHA > to_date('"+restauranteRangoFechas.getFechaInicial()+"', 'DD/MM/YYYY') \r\n" + 
				"AND FECHA < to_date('"+restauranteRangoFechas.getFechaFinal()+"', 'DD/MM/YYYY')\r\n" + 
				ordenamiento;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			Long idUsuario = rs.getLong("ID_USUARIO");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			String nombre = rs.getString("USUARIO_NOMBRE");
			String nombreProducto = rs.getString("NOMBRE_PRODUCTO");
			String clasificacion = rs.getString("CLASIFICACION");

			resp.add(new InfoUsuarioReqRFC9(idUsuario, correo, rol, nombre, nombreProducto, clasificacion));
		}
		return resp;
	}

	public ArrayList<InfoUsuarioReqRFC9> getAnalisis(RestauranteRangoFechas restauranteRangoFechas) throws SQLException, Exception{
		ArrayList<InfoUsuarioReqRFC9> resp = new ArrayList<>();
		String sql = "SELECT USUARIO.ID AS ID_USUARIO, PEDIDOS.FECHA AS FECHA, RESTAURANTES_PRODUCTOS.ID_RESTAURANTE AS ID_RESTAURANTE, USUARIO.CORREO AS CORREO, USUARIO.ROL AS ROL, USUARIO.NOMBRE AS USUARIO_NOMBRE, PRODUCTOS.NOMBRE AS NOMBRE_PRODUCTO, PRODUCTOS.CLASIFICACION AS CLASIFICACION FROM (((USUARIO INNER JOIN USUARIO_PEDIDO_PRODUCTOS ON USUARIO.ID = USUARIO_PEDIDO_PRODUCTOS.ID_USUARIO) \r\n" + 
				"				INNER JOIN RESTAURANTES_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE) \r\n" + 
				"				INNER JOIN PEDIDOS ON PEDIDOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PEDIDO) INNER JOIN PRODUCTOS ON PRODUCTOS.ID = RESTAURANTES_PRODUCTOS.ID_PRODUCTO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			Long idUsuario = rs.getLong("ID_USUARIO");
			String fecha = rs.getString("FECHA");
			Long idRestaurante = rs.getLong("ID_RESTAURANTE");
			String correo = rs.getString("CORREO");
			String rol = rs.getString("ROL");
			String nombre = rs.getString("USUARIO_NOMBRE");
			String nombreProducto = rs.getString("NOMBRE_PRODUCTO");
			String clasificacion = rs.getString("CLASIFICACION");

			System.out.println(fecha+">>>>>>>>>>>>>>> FECHA");

			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date dateOracle = format.parse(fecha);
			Date dateInicio = format.parse(restauranteRangoFechas.getFechaInicial());
			Date dateFinal = format.parse(restauranteRangoFechas.getFechaFinal());

			if(restauranteRangoFechas.getIdRestaurante()==idRestaurante && dateInicio.before(dateOracle)&&dateFinal.after(dateOracle)) {
				resp.add(new InfoUsuarioReqRFC9(idUsuario, correo, rol, nombre, nombreProducto, clasificacion));
				System.out.println(resp.size()+"TAMA�O RESP");
			}
		}
		return resp;
	}

	public ArrayList<Usuario> buenosClientes(Long id) throws SQLException, Exception{
		ArrayList<Usuario> resp = new ArrayList<>();
		ArrayList<Usuario> restaurantes = new ArrayList<>();
		if(id==1) {
			String sql = "SELECT USU.NOMBRE ,ID_USUARIO, CORREO, ROL FROM (SELECT * FROM(SELECT * FROM \r\n" + 
					"(SELECT * FROM PEDIDOS INNER JOIN USUARIO_PEDIDO_PRODUCTOS \r\n" + 
					"ON PEDIDOS.ID = USUARIO_PEDIDO_PRODUCTOS.ID_PEDIDO ) A INNER JOIN RESTAURANTES_PRODUCTOS  ON RESTAURANTES_PRODUCTOS.ID = A.ID_PRODUCTO_RESTAURANTE WHERE RESTAURANTES_PRODUCTOS.PRECIO > 24000) B\r\n" + 
					"INNER JOIN PRODUCTOS ON PRODUCTOS.ID = B.ID_PRODUCTO) C\r\n" + 
					"INNER JOIN USUARIO USU ON C.ID_USUARIO = USU.ID";
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				Long idUsuario = rs.getLong("ID_USUARIO");
				String correo = rs.getString("CORREO");
				String rol = rs.getString("ROL");
				String nombre = rs.getString("NOMBRE");			
				resp.add(new Usuario(idUsuario, correo, rol, null, nombre));
			}
		}
		return resp;
	}

	public ArrayList<RentabilidadRest> RFC14 (Long idPersona, RestauranteRentabilidad renta, Long idCosa) throws SQLException, Exception {

		/**
		 * 1 = zona
		 * 2 = producto
		 * 3 = categoria
		 */

		ArrayList<RentabilidadRest> restaurantes = new ArrayList<>();
		Long sumaCantidades = (long) 0;
		Long sumaCosto = (long) 0;
		Long sumaPrecio = (long) 0;

		if (!buscarUsuarioPorID(idPersona).getRol().equals("Admin")){
			throw new Exception("Este usuario no est� autorizado");
		}
		else {

			if(idCosa == 1) {

				String sqlZONA = "SELECT RESTAURANTES_PRODUCTOS.ID_RESTAURANTE, SUM(USUARIO_PEDIDO_PRODUCTOS.CANTIDAD),SUM(COSTO) AS SUMCOSTO, SUM(PRECIO) AS SUMPRECIO FROM PEDIDOS LEFT JOIN ((RESTAURANTES_PRODUCTOS LEFT JOIN ZONA_RESTAURANTES ON RESTAURANTES_PRODUCTOS.ID_RESTAURANTE=ZONA_RESTAURANTES.ID_RESTAURANTE) LEFT JOIN USUARIO_PEDIDO_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE) ON PEDIDOS.ID=RESTAURANTES_PRODUCTOS.ID_PRODUCTO WHERE USUARIO_PEDIDO_PRODUCTOS.ESTADO_PEDIDO ='ACEPTADO' AND ZONA_RESTAURANTES.ID_ZONA = " + renta.getIdBusqueda() + " AND PEDIDOS.FECHA BETWEEN to_date('"+renta.getFechaInicial()+"', 'DD/MM/YYYY') \r\n" 
						+ "AND to_date('"+renta.getFechaFinal()+"','DD/MM/YYYY')\r\n"
						+ "GROUP BY RESTAURANTES_PRODUCTOS.ID_RESTAURANTE"; 

				PreparedStatement prepStmt = conn.prepareStatement(sqlZONA);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();
				while (rs.next()) {

					Long idRest = rs.getLong("ID_RESTAURANTE");
					sumaCantidades += rs.getLong("SUM(USUARIO_PEDIDO_PRODUCTOS.CANTIDAD)");
					sumaCosto += rs.getLong("SUMCOSTO");
					sumaPrecio += rs.getLong("SUMPRECIO");
					String fechaInicial = renta.getFechaInicial();
					String fechaFinal = renta.getFechaFinal();
					restaurantes.add(new RentabilidadRest(idRest, sumaCantidades, sumaCosto, sumaPrecio, fechaInicial, fechaFinal));

				}
			}			
			else if(idCosa == 2) {
				String sqlPRODUCTO = "SELECT RESTAURANTES_PRODUCTOS.ID_RESTAURANTE, SUM(USUARIO_PEDIDO_PRODUCTOS.CANTIDAD),SUM(COSTO) AS SUMCOSTO, SUM(PRECIO) AS SUMPRECIO FROM PEDIDOS LEFT JOIN ((RESTAURANTES_PRODUCTOS LEFT JOIN USUARIO_PEDIDO_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = RESTAURANTES_PRODUCTOS.ID_PRODUCTO) LEFT JOIN USUARIO_PEDIDO_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE) ON PEDIDOS.ID=RESTAURANTES_PRODUCTOS.ID_PRODUCTO WHERE USUARIO_PEDIDO_PRODUCTOS.ESTADO_PEDIDO ='ACEPTADO' AND USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE = " + renta.getIdBusqueda() +" AND PEDIDOS.FECHA BETWEEN to_date('"+renta.getFechaInicial()+"', 'DD/MM/YYYY') \r\n"
						+"AND to_date('"+renta.getFechaFinal()+"','DD/MM/YYYY')\r\n"
						+"GROUP BY RESTAURANTES_PRODUCTOS.ID_RESTAURANTE";

				PreparedStatement prepStmt = conn.prepareStatement(sqlPRODUCTO);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();
				while (rs.next()) {

					Long idRest = rs.getLong("ID_RESTAURANTE");
					sumaCantidades += rs.getLong("SUM(USUARIO_PEDIDO_PRODUCTOS.CANTIDAD)");
					sumaCosto += rs.getLong("SUMCOSTO");
					sumaPrecio += rs.getLong("SUMPRECIO");
					String fechaInicial = renta.getFechaInicial();
					String fechaFinal = renta.getFechaFinal();
					restaurantes.add(new RentabilidadRest(idRest, sumaCantidades, sumaCosto, sumaPrecio, fechaInicial, fechaFinal));

				}
			}
			else if (idCosa == 3) {

				String sqlCATEGORIA = "SELECT * FROM PEDIDOS LEFT JOIN (RESTAURANTES_PRODUCTOS LEFT JOIN USUARIO_PEDIDO_PRODUCTOS ON RESTAURANTES_PRODUCTOS.ID_PRODUCTO = USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE) ON PEDIDOS.ID = RESTAURANTES_PRODUCTOS.ID_PRODUCTO"
						+ "WHERE USUARIO_PEDIDO_PRODUCTOS.ESTADO_PEDIDO ='ACEPTADO' AND PEDIDOS.FECHA BETWEEN";

				PreparedStatement prepStmt = conn.prepareStatement(sqlCATEGORIA);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();
				while (rs.next()) {

					Long idRest = rs.getLong("ID_RESTAURANTE");
					sumaCantidades += rs.getLong("SUM(USUARIO_PEDIDO_PRODUCTOS.CANTIDAD)");
					sumaCosto += rs.getLong("SUMCOSTO");
					sumaPrecio += rs.getLong("SUMPRECIO");
					String fechaInicial = renta.getFechaInicial();
					String fechaFinal = renta.getFechaFinal();
					restaurantes.add(new RentabilidadRest(idRest, sumaCantidades, sumaCosto, sumaPrecio, fechaInicial, fechaFinal));

				}
			}
		}
		return restaurantes;
	}
	
	public ArrayList<RentabilidadRest> auxiliar ( ArrayList<RentabilidadRest> restaurantes)
	{

		ArrayList<RentabilidadRest> restaurantes2 = new ArrayList<>();

		Long v1 = (long)0;
		Long v2 = (long)0;
		Long v3 = (long)0;

		for (int i = 0; i < restaurantes.size(); i++) {

			v1 = restaurantes.get(i).getCantidadesVendidas();
			v2 = restaurantes.get(i).getCostoTotal();
			v3 = restaurantes.get(i).getValorFacturado();
			for (int j = 1; j < restaurantes.size(); j++) {

				if (restaurantes.get(i).getRestaurante() == restaurantes.get(j).getRestaurante()) {

					v1 += restaurantes.get(j).getCantidadesVendidas();
					v2 += restaurantes.get(j).getCostoTotal();
					v3 += restaurantes.get(j).getValorFacturado();
				}
			}
			restaurantes2.add(new RentabilidadRest(restaurantes.get(i).getRestaurante(), v1, v2, v3, restaurantes.get(i).getFechaInicial(), restaurantes.get(i).getFechaFinal()));
		}
		return restaurantes2;
	}
}
