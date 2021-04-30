package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion{
    
    /*private final String url = "jdbc:postgresql://172.16.0.39:5432/ot-utn";
    private final String user = "postgres";
    private final String password = "majotiti";*/
    
    private final String url = "jdbc:postgresql://192.168.1.54:5432/ot-utn";
    private final String user = "postgres";
    private final String password = "2010Anlis";
    private Connection con = null;
    
    public Connection getConexion(){
        //Primero: registro el driver de postgresql
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) 
        {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al conectar con BBDD. \nConsulte con el administrador");
        }
        return con;
    }
}