package Ejercicio;

import java.sql.*;
import java.util.ArrayList;

public class ConexionDB {

	public static final String conexionMariaDB="jdbc:mariadb://localhost:3306/DagrazaAlonsoMiguel_Tarea4";
	public static final String conexionMySQL="jdbc:mysql://localhost:3306/DagrazaAlonsoMiguel_Tarea4";
	
	//Generar lista con los libros de la BBDD
	public static ArrayList<Libros> getLibrosBBDD() {
		ArrayList<Libros> libros = new ArrayList<Libros>();
		try {
			Connection conexion = DriverManager.getConnection(conexionMariaDB,"root","");
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT * FROM LIBROS");
			
			while(resultado.next()) {
				
				Libros libro = new Libros(resultado.getInt("idLibros"), resultado.getString("nombre"),resultado.getString("autor"),resultado.getString("descripcion"),resultado.getString("pais"),resultado.getString("codigo"));
				
				libros.add(libro);
			}
			
			resultado.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return libros;
	}
	
	
	//Metodo para conectarse a la base de datos y hacer INSERT
	public static void addLibroBBDD(String nombre, String autor, String descripcion, String pais, String codigo) {
		
		PreparedStatement consultaPreparada;
		try {
			Connection conexion = DriverManager.getConnection(ConexionDB.conexionMariaDB,"root","");
			consultaPreparada = conexion.prepareStatement("INSERT INTO Libros(NOMBRE, AUTOR, DESCRIPCION, PAIS, CODIGO) VALUES (?, ?, ?, ?, ?)");
		
			consultaPreparada.setString(1, nombre);
			consultaPreparada.setString(2, autor);
			consultaPreparada.setString(3, descripcion);
			consultaPreparada.setString(4, pais);
			consultaPreparada.setString(5, codigo);
			
			//Ejecutar consulta
			consultaPreparada.executeUpdate();
			
			
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		//Metodo para conectarse a la base de datos y hacer UPDATE
		public static void updateLibroBBDD(int idLibro, String nombre, String autor, String descripcion, String pais, String codigo) {
			
			PreparedStatement consultaPreparada;
			try {
				Connection conexion = DriverManager.getConnection(ConexionDB.conexionMariaDB,"root","");
				consultaPreparada = conexion.prepareStatement("UPDATE Libros SET NOMBRE=?, AUTOR=?, DESCRIPCION=?, PAIS=?, CODIGO=? WHERE IDLIBROS=?");
			
				consultaPreparada.setString(1, nombre);
				consultaPreparada.setString(2, autor);
				consultaPreparada.setString(3, descripcion);
				consultaPreparada.setString(4, pais);
				consultaPreparada.setString(5, codigo);
				consultaPreparada.setInt(6, idLibro);
				
				//Ejecutar consulta
				consultaPreparada.executeUpdate();
				
				
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		//Metodo para conectarse a la base de datos y hacer DELETE
		public static void deleteLibroBBDD(int idLibro) {
			
			PreparedStatement consultaPreparada;
			try {
				Connection conexion = DriverManager.getConnection(ConexionDB.conexionMariaDB,"root","");
				consultaPreparada = conexion.prepareStatement("DELETE FROM Libros WHERE IDLIBROS=?");
				consultaPreparada.setInt(1, idLibro);
				
				//Ejecutar consulta
				consultaPreparada.executeUpdate();
				
				
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//Metodo para conectarse a la base de datos y hacer SELECT personalizado
				public static ArrayList<Libros> searchLibroBBDD(String nombre, boolean optNombre, String autor, boolean optAutor,
						String descripcion, boolean optDescripcion, String pais, boolean optPais, String codigo, boolean optCodigo) {
					ArrayList<Libros> libros = new ArrayList<Libros>();
					String consultaS = "SELECT * FROM Libros WHERE";
					
					
					//Preparar consulta
					if(!nombre.isEmpty()) {
						if(optNombre)
							consultaS+=" Nombre LIKE '%"+nombre+"%'";
						else
							consultaS+=" Nombre LIKE '"+nombre+"'";
					}
						
					
					if(!autor.isEmpty()) {
						if(consultaS.charAt(consultaS.length()-1) != 'E')
							consultaS+=" AND";
									
						if(optAutor)
							consultaS+=" Autor LIKE '%"+autor+"%'";
						else
							consultaS+=" Autor LIKE '"+autor+"'";
					}
						
					
					if(!descripcion.isEmpty()) {
						if(consultaS.charAt(consultaS.length()-1) != 'E')
							consultaS+=" AND";
					
						if(optDescripcion)
							consultaS+=" Descripcion LIKE '%"+descripcion+"%'";
						else
							consultaS+=" Descripcion LIKE '"+descripcion+"'";
					}
						
					
					if(!pais.isEmpty()) {
						if(consultaS.charAt(consultaS.length()-1) != 'E')
							consultaS+=" AND";
					
						if(optPais)
							consultaS+=" Pais LIKE '%"+pais+"%'";
						else
							consultaS+=" Pais LIKE '"+pais+"'";
					}
						
					
					if(!codigo.isEmpty()) {
						if(consultaS.charAt(consultaS.length()-1) != 'E')
							consultaS+=" AND";
					
						if(optCodigo)
							consultaS+=" Codigo LIKE '%"+codigo+"%'";
						else
							consultaS+=" Codigo LIKE '"+codigo+"'";
					}
						
					
					//Proteccion
					if(nombre.isEmpty() && autor.isEmpty() && descripcion.isEmpty() && pais.isEmpty() && codigo.isEmpty()) {
						consultaS = "SELECT * FROM Libros";
					}
					
					PreparedStatement consultaPreparada;
					try {
						Connection conexion = DriverManager.getConnection(ConexionDB.conexionMariaDB,"root","");
						Statement consulta = conexion.createStatement();
						ResultSet resultado = consulta.executeQuery(consultaS);
						
						while(resultado.next()) {
							
							Libros libro = new Libros(resultado.getInt("idLibros"), resultado.getString("nombre"),resultado.getString("autor"),resultado.getString("descripcion"),resultado.getString("pais"),resultado.getString("codigo"));
							
							libros.add(libro);
						}
						
						resultado.close();
						conexion.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return libros;
					
				}
	
		
		/*
		 * 
		
		//DELETE
		consulta = "DELETE FROM Libros WHERE TO_ID=3";
		resultadoI = sentencia.executeUpdate(consulta);
		System.out.println("Resultado delete: " + resultadoI);
		
		
		//UPDATE
		consulta = "UPDATE Libros SET TO_NOMBRE='Vida cruela' WHERE TO_ID=6";
		resultadoI = sentencia.executeUpdate(consulta);
		System.out.println("Resultado update: " + resultadoI);
		 
		 * 
		 */
}
