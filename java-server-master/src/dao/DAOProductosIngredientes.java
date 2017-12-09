package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.CantidadProductoRestaurante;
import vos.ConsultaPedidos;
import vos.EquivalenciaIngrediente;
import vos.EquivalenciaProducto;
import vos.Ingrediente;
import vos.Pedido;
import vos.PedidoMesa;
import vos.Producto;

public class DAOProductosIngredientes {


	/**
	 * Arraylits de recursos que se usan para la ejecuci√≥n de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexi√≥n a la base de datos
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexi√≥n que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	/**
	 * Metodo que, usando la conexi√≥n a la base de datos, saca todos los videos de la base de datos
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

	public ArrayList<PedidoMesa> darPedidosPorMesa(Long idMesa) throws SQLException, Exception {


		String sql = "SELECT * FROM USUARIO_PEDIDO_PRODUCTOS WHERE NUMMESA ="+idMesa;
		Long idRestaurante = null;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		ArrayList<PedidoMesa> pedidos = null;
		while (rs.next()) {
			Long idPed = rs.getLong("ID_PEDIDO");
			Long idProd = rs.getLong("ID_PRODUCTO_RESTAURANTE");
			Long idUsuario = rs.getLong("ID_USUARIO");
			int cantidad = rs.getInt("CANTIDAD");	

			int numMesa = rs.getInt("NUMMESA");
			String sql2 = "SELECT ID_RESTAURANTE FROM RESTAURANTES_PRODUCTOS WHERE ID_PRODUCTO ="+ idProd;
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			recursos.add(ps2);
			ResultSet rs2 = ps2.executeQuery();

			while(rs2.next())
			{
				idRestaurante = rs2.getLong("ID_RESTAURANTE");
			}


			pedidos.add(new PedidoMesa(idPed, idRestaurante, idUsuario, numMesa));
		}
		return pedidos;

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

	public void addEquivalenciaIngrediente(EquivalenciaIngrediente equivalencia) throws SQLException, Exception {

		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

		String sql = "SELECT * FROM INGREDIENTES WHERE ID = "+equivalencia.getIdIngrediente()+" OR ID = "+equivalencia.getIdEquiIngrediente();

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



		if(ingredientes.size() < 1)
		{
			throw new Exception("uno de los ingredientes no existe");
		}
		else
		{
			String sqlTres = "INSERT INTO EQUIVALENCIAS_PRODUCTOS (ID_PRODUCTO1, ID_PRODUCTO2) VALUES ("+equivalencia.getIdIngrediente()+", "+equivalencia.getIdEquiIngrediente() +")";

			PreparedStatement prepStmtTres = conn.prepareStatement(sqlTres);
			recursos.add(prepStmtTres);
			prepStmt.executeQuery();
		}

	}

	public void addEquivalenciaProducto(EquivalenciaProducto equivalencia) throws SQLException, Exception {

		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTOS WHERE ID ="+equivalencia.getIdProducto()+" OR ID = "+equivalencia.getIdEquivalencia();

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



		if(productos.size() < 1)
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


	public void addCantidadProducto (CantidadProductoRestaurante infoCantidad) throws SQLException, Exception
	{
		String sql = "UPDATE RESTAURANTES_PRODUCTOS " + 
				" SET CANTIDAD = "+infoCantidad.getCantidad()+ 
				" WHERE ID_RESTAURANTE = "+ infoCantidad.getIdRestaurante()+ " AND ID_PRODUCTO = "+infoCantidad.getIdProducto(); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

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

		boolean productoJson = false;
		if(pedido.getIdProducto()!=null)
			productoJson = true;
		boolean menuJson = false;
		if(pedido.getIdMenu()!=null)
			menuJson = true;

		boolean tansaccionCancelada = false;
		boolean existePedido = false;

		String sqlExiste = "SELECT ID FROM PEDIDOS WHERE ID = "+ pedido.getIdPedido();


		PreparedStatement prepStmtConsulta = conn.prepareStatement(sqlExiste);
		recursos.add(prepStmtConsulta);
		ResultSet rsconsulta = prepStmtConsulta.executeQuery();
		while (rsconsulta.next()) {

			Long id = rsconsulta.getLong("ID");
			existePedido = (id != null);
		}


		if (!existePedido)
		{
			String sql = "INSERT INTO PEDIDOS VALUES (";
			sql += pedido.getIdPedido() + ",";
			sql += "SYSDATE,";
			sql += "'N')";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

			tansaccionCancelada = true;
		}

		if(productoJson || menuJson) {


			if(productoJson) {
				
				
				String sqlHallarCantidadDelProductoRestaurante ="select CANTIDAD from RESTAURANTES_PRODUCTOS WHERE ID_RESTAURANTE="+pedido.getIdRestaurante()+" AND ID_PRODUCTO="+pedido.getIdProducto();
				//--------------------------------------
				// Hace consulta para saber las existencias del producto que quiere pedir
				PreparedStatement prepStmtCantidad = conn.prepareStatement(sqlHallarCantidadDelProductoRestaurante);
				recursos.add(prepStmtCantidad);
				ResultSet rs = prepStmtCantidad.executeQuery();
				int a = 0;
				while(rs.next()) {
					a = rs.getInt("CANTIDAD");
				}

				if(a >= pedido.getCantidad()) { //compara si hay existencias suficientes
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<4");
					String sqlHallarIDDelProductoRestaurante ="select ID from RESTAURANTES_PRODUCTOS WHERE ID_RESTAURANTE="+pedido.getIdRestaurante()+" AND ID_PRODUCTO="+pedido.getIdProducto();
					PreparedStatement prepStmt1 = conn.prepareStatement(sqlHallarIDDelProductoRestaurante);
					recursos.add(prepStmt1);
					ResultSet rs1 = prepStmt1.executeQuery();
					int aa = 0;
					while(rs1.next()) {
						aa = rs1.getInt("ID");
					}
					
					
					//------------------------------
					// Inserta el pedido del producto
					String sql2 = "INSERT INTO USUARIO_PEDIDO_PRODUCTOS VALUES(";
					sql2 += pedido.getIdPedido()+",";
					sql2 += aa + ",";
					sql2 += pedido.getIdUsuario()+",";
					sql2 += pedido.getCantidad()+",'N',";
					sql2 += pedido.getNumMesa()+",";
					sql2 += "'ACEPTADO')";
					
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<6");
					PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
					recursos.add(prepStmt2);
					prepStmt2.executeQuery();
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<7");
					//---------------------------
					// Restar existencias
					String sqlRestarExistencias = "UPDATE RESTAURANTES_PRODUCTOS SET CANTIDAD= "+(a-pedido.getCantidad()) + " WHERE ID="+ aa;
					PreparedStatement prepStmtRestar = conn.prepareStatement(sqlRestarExistencias);
					recursos.add(prepStmtRestar);
					prepStmtRestar.executeQuery();
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<8");
				}
				else {
					// elimina el pedido que se creÛ en vano
					throw new Exception("no hay productos suficientes");
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<9");
				}
			}
			//-----------------------------------------------------------
			// ahora mira a ver si hay men˙s en el pedido
			if(menuJson) {
				//				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<10");
				String sqlHallarCantidadDelMenu ="select CANTIDAD from MENUS WHERE ID = "+pedido.getIdMenu();
				PreparedStatement prepStmtMenu1 = conn.prepareStatement(sqlHallarCantidadDelMenu);
				recursos.add(prepStmtMenu1);
				ResultSet rsMenu = prepStmtMenu1.executeQuery();
				//				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<11");
				ArrayList<Integer> aaa = new ArrayList<Integer>();
				while(rsMenu.next()) {
					Integer cantidad = rsMenu.getInt("CANTIDAD");
					aaa.add(cantidad);
				}
				//				System.out.println(aaa.get(0)+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<CANTIDAD QUE HAY EN ESE ID");
				if(aaa.get(0)>=pedido.getCantidad()) {
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<12");
					//------------------------------
					// Inserta el pedido del producto
					String sqlInsertarMenu = "INSERT INTO USUARIO_PEDIDOS_MENUS VALUES(";
					sqlInsertarMenu += pedido.getIdUsuario()+",";
					sqlInsertarMenu += pedido.getIdPedido() + ",";
					sqlInsertarMenu += pedido.getIdMenu()+",";
					sqlInsertarMenu += pedido.getCantidad()+",'N')";

					PreparedStatement prepStmtInsertarMenu = conn.prepareStatement(sqlInsertarMenu);
					recursos.add(prepStmtInsertarMenu);
					prepStmtInsertarMenu.executeQuery();
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<13");


					//---------------------------
					// Restar existencias
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+(aaa.get(0)-pedido.getCantidad())+"nuevA CANTIDAD");
					Integer nuevaCantidad = (aaa.get(0)-pedido.getCantidad());
					String sqlRestarExistenciasMenu = "UPDATE MENUS SET CANTIDAD= "+ nuevaCantidad + " WHERE ID="+ pedido.getIdMenu();
					PreparedStatement prepStmtRestarMenu = conn.prepareStatement(sqlRestarExistenciasMenu);
					recursos.add(prepStmtRestarMenu);
					prepStmtRestarMenu.executeQuery();
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<14");

				}
				else {
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<15");

					// elimina el pedido que se creÛ en vano
					throw new Exception("no hay menus suficientes");
				}
			}
		}
		else {
			if (tansaccionCancelada)
			{
				String sqlRollback = "ROLLBACK";
				PreparedStatement prepStmtRollback = conn.prepareStatement(sqlRollback);
				recursos.add(prepStmtRollback);
				prepStmtRollback.executeQuery();
			}
			throw new Exception("No est· enviando productos ni men˙s para pedir");
		}
	}

	
	
	//RF18 
	
	
	
	public void addPedidoTransaccionDistribuida(Pedido pedido)throws SQLException, Exception{

		boolean productoJson = false;
		if(pedido.getIdProducto()!=null)
			productoJson = true;
		boolean menuJson = false;
		if(pedido.getIdMenu()!=null)
			menuJson = true;

		boolean tansaccionCancelada = false;
		boolean existePedido = false;

		String sqlExiste = "SELECT ID FROM PEDIDOS WHERE ID = "+ pedido.getIdPedido();


		PreparedStatement prepStmtConsulta = conn.prepareStatement(sqlExiste);
		recursos.add(prepStmtConsulta);
		ResultSet rsconsulta = prepStmtConsulta.executeQuery();
		while (rsconsulta.next()) {

			Long id = rsconsulta.getLong("ID");
			existePedido = (id != null);
		}


		if (!existePedido)
		{
			String sql = "INSERT INTO PEDIDOS VALUES (";
			sql += pedido.getIdPedido() + ",";
			sql += "SYSDATE,";
			sql += "'N')";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

			tansaccionCancelada = true;
		}

		if(productoJson || menuJson) {


			if(productoJson) {
				
				
				String sqlHallarCantidadDelProductoRestaurante ="select CANTIDAD from RESTAURANTES_PRODUCTOS WHERE ID_RESTAURANTE="+pedido.getIdRestaurante()+" AND ID_PRODUCTO="+pedido.getIdProducto();
				//--------------------------------------
				// Hace consulta para saber las existencias del producto que quiere pedir
				PreparedStatement prepStmtCantidad = conn.prepareStatement(sqlHallarCantidadDelProductoRestaurante);
				recursos.add(prepStmtCantidad);
				ResultSet rs = prepStmtCantidad.executeQuery();
				int a = 0;
				while(rs.next()) {
					a = rs.getInt("CANTIDAD");
				}

				if(a >= pedido.getCantidad()) { //compara si hay existencias suficientes
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<4");
					String sqlHallarIDDelProductoRestaurante ="select ID from RESTAURANTES_PRODUCTOS WHERE ID_RESTAURANTE="+pedido.getIdRestaurante()+" AND ID_PRODUCTO="+pedido.getIdProducto();
					PreparedStatement prepStmt1 = conn.prepareStatement(sqlHallarIDDelProductoRestaurante);
					recursos.add(prepStmt1);
					ResultSet rs1 = prepStmt1.executeQuery();
					int aa = 0;
					while(rs1.next()) {
						aa = rs1.getInt("ID");
					}
					
					
					//------------------------------
					// Inserta el pedido del producto
					String sql2 = "INSERT INTO USUARIO_PEDIDO_PRODUCTOS VALUES(";
					sql2 += pedido.getIdPedido()+",";
					sql2 += aa + ",";
					sql2 += pedido.getIdUsuario()+",";
					sql2 += pedido.getCantidad()+",'N',";
					sql2 += pedido.getNumMesa()+",";
					sql2 += "'ACEPTADO')";
					
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<6");
					PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
					recursos.add(prepStmt2);
					prepStmt2.executeQuery();
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<7");
					//---------------------------
					// Restar existencias
					String sqlRestarExistencias = "UPDATE RESTAURANTES_PRODUCTOS SET CANTIDAD= "+(a-pedido.getCantidad()) + " WHERE ID="+ aa;
					PreparedStatement prepStmtRestar = conn.prepareStatement(sqlRestarExistencias);
					recursos.add(prepStmtRestar);
					prepStmtRestar.executeQuery();
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<8");
				}
				else {
					// elimina el pedido que se creÛ en vano
					throw new Exception("no hay productos suficientes");
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<9");
				}
			}
			//-----------------------------------------------------------
			// ahora mira a ver si hay men˙s en el pedido
			if(menuJson) {
				//				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<10");
				String sqlHallarCantidadDelMenu ="select CANTIDAD from MENUS WHERE ID = "+pedido.getIdMenu();
				PreparedStatement prepStmtMenu1 = conn.prepareStatement(sqlHallarCantidadDelMenu);
				recursos.add(prepStmtMenu1);
				ResultSet rsMenu = prepStmtMenu1.executeQuery();
				//				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<11");
				ArrayList<Integer> aaa = new ArrayList<Integer>();
				while(rsMenu.next()) {
					Integer cantidad = rsMenu.getInt("CANTIDAD");
					aaa.add(cantidad);
				}
				//				System.out.println(aaa.get(0)+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<CANTIDAD QUE HAY EN ESE ID");
				if(aaa.get(0)>=pedido.getCantidad()) {
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<12");
					//------------------------------
					// Inserta el pedido del producto
					String sqlInsertarMenu = "INSERT INTO USUARIO_PEDIDOS_MENUS VALUES(";
					sqlInsertarMenu += pedido.getIdUsuario()+",";
					sqlInsertarMenu += pedido.getIdPedido() + ",";
					sqlInsertarMenu += pedido.getIdMenu()+",";
					sqlInsertarMenu += pedido.getCantidad()+",'N')";

					PreparedStatement prepStmtInsertarMenu = conn.prepareStatement(sqlInsertarMenu);
					recursos.add(prepStmtInsertarMenu);
					prepStmtInsertarMenu.executeQuery();
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<13");


					//---------------------------
					// Restar existencias
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+(aaa.get(0)-pedido.getCantidad())+"nuevA CANTIDAD");
					Integer nuevaCantidad = (aaa.get(0)-pedido.getCantidad());
					String sqlRestarExistenciasMenu = "UPDATE MENUS SET CANTIDAD= "+ nuevaCantidad + " WHERE ID="+ pedido.getIdMenu();
					PreparedStatement prepStmtRestarMenu = conn.prepareStatement(sqlRestarExistenciasMenu);
					recursos.add(prepStmtRestarMenu);
					prepStmtRestarMenu.executeQuery();
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<14");

				}
				else {
					//					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<15");

					// elimina el pedido que se creÛ en vano
					throw new Exception("no hay menus suficientes");
				}
			}
		}
		else {
			if (tansaccionCancelada)
			{
				String sqlRollback = "ROLLBACK";
				PreparedStatement prepStmtRollback = conn.prepareStatement(sqlRollback);
				recursos.add(prepStmtRollback);
				prepStmtRollback.executeQuery();
			}
			throw new Exception("No est· enviando productos ni men˙s para pedir");
		}
	}

	
	
	
	//--------------------------------------------------------------
	//-------RFC8

	public ArrayList<ConsultaPedidos> consultarVentas(Long id, String cargo) throws SQLException, Exception
	{
		ArrayList<ConsultaPedidos> infoVentas = new ArrayList<ConsultaPedidos>();

		if(cargo.equals("Admin"))
		{
			// productos
			String sqlProductos = "SELECT * FROM USUARIO_PEDIDO_PRODUCTOS INNER JOIN RESTAURANTES_PRODUCTOS "
					+ "ON USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE = RESTAURANTES_PRODUCTOS.ID";


			PreparedStatement prepStmtProductos = conn.prepareStatement(sqlProductos);
			recursos.add(prepStmtProductos);
			ResultSet rsProductos = prepStmtProductos.executeQuery();

			while (rsProductos.next()) {
				String tipo = "Prod";
				Long idRestaurante = rsProductos.getLong("ID_RESTAURANTE");
				Long cantidad = rsProductos.getLong("CANTIDAD");
				Long dineroTotal = rsProductos.getLong("PRECIO") * cantidad;
				Long idProducto = rsProductos.getLong("ID_PRODUCTO");			

				infoVentas.add(new ConsultaPedidos(idRestaurante, dineroTotal, cantidad, idProducto, tipo));
			}		
			//menus
			String sqlMenus = "SELECT * FROM USUARIO_PEDIDOS_MENUS INNER JOIN MENUS "
					+ "ON USUARIO_PEDIDOS_MENUS.ID_MENU = MENUS.ID";


			PreparedStatement prepStmtMenus = conn.prepareStatement(sqlMenus);
			recursos.add(prepStmtMenus);
			ResultSet rsMenus = prepStmtMenus.executeQuery();

			while (rsMenus.next()) {
				String tipo = "Menu";
				Long idRestaurante = rsMenus.getLong("RESTAURANTE_ID");
				Long cantidad = rsMenus.getLong("CANTIDAD");
				Long dineroTotal = rsMenus.getLong("PRECIO") * cantidad;
				Long idProducto = rsMenus.getLong("ID");			

				infoVentas.add(new ConsultaPedidos(idRestaurante, dineroTotal, cantidad, idProducto, tipo));
			}

		}
		else
		{
			// productos RESTAURANTE
			String sqlProductos = "SELECT * FROM USUARIO_PEDIDO_PRODUCTOS INNER JOIN RESTAURANTES_PRODUCTOS "
					+ "ON USUARIO_PEDIDO_PRODUCTOS.ID_PRODUCTO_RESTAURANTE = RESTAURANTES_PRODUCTOS.ID AND RESTAURANTES_PRODUCTOS.ID_RESTAURANTE = "+ id;


			PreparedStatement prepStmtProductos = conn.prepareStatement(sqlProductos);
			recursos.add(prepStmtProductos);
			ResultSet rsProductos = prepStmtProductos.executeQuery();

			while (rsProductos.next()) {
				String tipo = "Prod";
				Long idRestaurante = rsProductos.getLong("ID_RESTAURANTE");
				Long cantidad = rsProductos.getLong("CANTIDAD");
				Long dineroTotal = rsProductos.getLong("PRECIO") * cantidad;
				Long idProducto = rsProductos.getLong("ID_PRODUCTO");			

				infoVentas.add(new ConsultaPedidos(idRestaurante, dineroTotal, cantidad, idProducto, tipo));
			}		
			//menus RESTAURANTE
			String sqlMenus = "SELECT * FROM USUARIO_PEDIDOS_MENUS INNER JOIN MENUS "
					+ "ON USUARIO_PEDIDOS_MENUS.ID_MENU = MENUS.ID AND MENUS.RESTAURANTE_ID = "+id;


			PreparedStatement prepStmtMenus = conn.prepareStatement(sqlMenus);
			recursos.add(prepStmtMenus);
			ResultSet rsMenus = prepStmtMenus.executeQuery();

			while (rsMenus.next()) {
				String tipo = "Menu";
				Long idRestaurante = rsMenus.getLong("RESTAURANTE_ID");
				Long cantidad = rsMenus.getLong("CANTIDAD");
				Long dineroTotal = rsMenus.getLong("PRECIO") * cantidad;
				Long idProducto = rsMenus.getLong("ID");			

				infoVentas.add(new ConsultaPedidos(idRestaurante, dineroTotal, cantidad, idProducto, tipo));
			}
		}


		return infoVentas;

	}

	public void RF15(ArrayList<Pedido> pedidosMesa) throws SQLException, Exception {

		for (int i = 0; i < pedidosMesa.size(); i++) {

			Pedido actual = pedidosMesa.get(i);
			addPedido(actual);
		}
	}

}
