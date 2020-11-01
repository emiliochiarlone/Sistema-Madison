package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLStuff {
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/madison_db?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USUARIO = "root";
    private static final String CLAVE = "1234";
    private Statement statement;
    private ResultSet result;
    private Connection connection;
    
    static { // se va a ejecutar al cargar la aplicación para evitar tener que llamar el metodo para cargar el controlador
    	try {
    		 Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();

        }
    }
    
    public MySQLStuff() {
    	try {
    		this.conectar();
			this.statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public Mensaje conectar() {     
        try {
            this.connection = DriverManager.getConnection(URL, USUARIO, CLAVE);
            return Mensaje.listo;
        }  catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
            return Mensaje.error;
        }
    }
    
    public Mensaje newQuery(String query) {
    	try {
			this.result = statement.executeQuery(query);
			return Mensaje.listo;
		} catch (SQLException e) {
			e.printStackTrace();
			return Mensaje.error;
		}
    }
    
    public Mensaje close() {
    	try {
			if (this.result != null) {
				this.result.close();
			}
			if (this.statement != null) {
				this.statement.close();
			}
			if (this.connection != null) {
				this.connection.close();
			}
			return Mensaje.listo;
		} catch (Exception e2) {
			e2.printStackTrace();
			return Mensaje.error;
		}
    }
    
	public Statement getStatement() {
		return statement;
	}
	
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	public ResultSet getResult() {
		return result;
	}
	
	public void setResult(ResultSet result) {
		this.result = result;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
