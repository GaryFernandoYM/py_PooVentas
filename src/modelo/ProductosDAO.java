package modelo;

import com.mysql.cj.xdevapi.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class ProductosDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Productos pro){
        //para editar la query SQL
        String sql = "INSERT INTO productos (codigo,nombre,proveedor,stock,precio) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
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
    

    public void ConsultarProveedor(JComboBox proveedor){
        String sql = "SELECT nombre FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                proveedor.addItem(rs.getString("nombre"));
            }   
        } catch (SQLException e) {
                System.out.println(e);
        }  
   }
    public List ListarProductos(){
    List<Productos> ListaPro= new ArrayList();
    String sql = "SELECT * FROM productos";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
           Productos pro = new Productos();
           pro.setId(rs.getInt("id"));
           pro.setCodigo(rs.getString("codigo"));
           pro.setNombre(rs.getString("nombre"));
           pro.setProveedor(rs.getString("proveedor"));
           pro.setStock(rs.getInt("stock"));
           pro.setPrecio(rs.getDouble("precio"));
           ListaPro.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPro;
    }
    public boolean EliminarProductos(int id){
    String sql= "DELETE FROM productos WHERE id=?";
    try {
        ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
      
    }finally{
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
    public boolean ActualizarProducto(Productos pr){
    //para ejecutar el query SQL
    String sql = "UPDATE productos SET codigo=?,nombre=?,proveedor=?,stock=?,precio=? WHERE id=?";
    try {
        ps=con.prepareStatement(sql);
        ps.setString(1,pr.getCodigo());
        ps.setString(2,pr.getNombre());
        ps.setString(3,pr.getProveedor());
        ps.setInt(4,pr.getStock());
        ps.setDouble(5,pr.getPrecio());
        ps.setInt(6,pr.getId());
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
        //cerrando la conexcion
    }finally{
        try {
            con.close();
        } catch (SQLException e) {
            //para capturar el error
            System.out.println(e.toString());
        }
    }
       
 }
    public Productos BuscarPRO(String cod){
        Productos Producto = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                Producto.setNombre(rs.getString("nombre"));
                Producto.setPrecio(rs.getDouble("precio"));
                Producto.setStock(rs.getInt("stock"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return Producto;
    }
    public DatosTienda BuscarDatos(){
        DatosTienda datos = new DatosTienda();
        String sql = "SELECT * FROM datostienda";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                datos.setId(rs.getInt("id"));
                datos.setRuc(rs.getInt("ruc"));
                datos.setNombre(rs.getString("nombre"));
                datos.setTelefono(rs.getInt("telefono"));
                datos.setDireccion(rs.getString("direccion"));
                datos.setRazon(rs.getString("Razon"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
    public boolean ActualizarDatos(DatosTienda datos){
    //para ejecutar el query SQL
    String sql = "UPDATE datostienda SET ruc=? ,nombre=?,telefono=?,direccion=?,razon=? WHERE id=?";
    try {
        ps=con.prepareStatement(sql);
        ps.setInt(1,datos.getRuc());
        ps.setString(2,datos.getNombre());
        ps.setInt(3,datos.getTelefono());
        ps.setString(4,datos.getDireccion());
        ps.setString(5,datos.getRazon());
        ps.setInt(6,datos.getId());
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
        //cerrando la conexcion
    }finally{
        try {
            con.close();
        } catch (SQLException e) {
            //para capturar el error
            System.out.println(e.toString());
        }
    }
       
 }
    
}

