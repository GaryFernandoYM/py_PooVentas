/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
           
    //metodo para registrar proveedor
public boolean RegistrarProveedor (Proveedor pr){
    //para editar la query SQL
    String sql = "INSERT INTO proveedor (ruc,nombre,telefono,correo,direccion) VALUES (?,?,?,?,?)";
    try {
        con = cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setInt(1, pr.getRuc());
        ps.setString(2, pr.getNombre());
        ps.setInt(3, pr.getTelefono());
        ps.setString(4, pr.getCorreo());
        ps.setString(5, pr.getDireccion());
        ps.execute();
        return true;
            
        } catch (SQLException e) {
            System.out.println(e);
            return false;
            //cerrando la conexcion
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    
       
    }
    public List ListarProveedor(){
        List<Proveedor> ListarPR = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getInt("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setCorreo(rs.getString("correo"));
                pr.setDireccion(rs.getString("direccion"));
                ListarPR.add(pr);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    return ListarPR;
    }

    public boolean EliminarProveedor(int id){
    String sql= "DELETE FROM proveedor WHERE id=?";
    try {
        con = cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e);
        return false;
      
    }finally{
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
    public boolean ActualizarProveedor(Proveedor pr){
    //para ejecutar el query SQL
    String sql = "UPDATE proveedor SET ruc=?,nombre=?,telefono=?,correo=?,direccion=? WHERE id=?";
    try {
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setInt(1,pr.getRuc());
        ps.setString(2,pr.getNombre());
        ps.setInt(3,pr.getTelefono());
        ps.setString(4,pr.getCorreo());
        ps.setString(5,pr.getDireccion());
        ps.setInt(6,pr.getId());
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e);
        return false;
        //cerrando la conexcion
    }finally{
        try {
            con.close();
        } catch (SQLException e) {
            //para capturar el error
            System.out.println(e);
        }
    }
       
        
}

}
