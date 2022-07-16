package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dash
 */
public class Conexion {
    
    Connection con;
        
    public Connection getConnection(){
        try {   
        String myBD="jdbc:mysql://localhost:3308/sistemaventaspy";
        con=DriverManager.getConnection(myBD,"root", "");
            if(con!=null){
                System.out.println("se establecio la conexion");
            } else{
                System.out.print("no existe conexion");
            }  
        }catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "No hay conexion a db");         
        }           
        return con;
    }
    
    public static void main(String[] args) {
        Conexion cx = new Conexion();
        cx.getConnection();
    }
    
}
