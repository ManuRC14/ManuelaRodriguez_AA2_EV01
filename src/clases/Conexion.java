package clases;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Conexion {
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    public Conexion (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/registro","root","Manu14071");
            System.out.println("Conexion db");
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar BD");
        }
    }
    public int Rusuario(String Nombre_Completo, String Usuario, String Correo, String Contraseña){
        int respuesta=0;
        try {
            ps=cn.prepareStatement(" insert into formulario(Nombre_Completo, Usuario, Correo, Contraseña)values(?,?,?,?)");
            ps.setString(1, Nombre_Completo);
            ps.setString(2, Usuario);
            ps.setString(3, Correo);
            ps.setString(4, Contraseña);
            respuesta=ps.executeUpdate();
            System.out.println("Usuario registrado correctamente");
        } catch (SQLException e){
            System.out.println("Error al registrar"+e.getMessage());
        }
        return respuesta;
    }
    
    public int Acusuario (String Nombre_Completo, String Usuario, String Correo, String Contraseña, String id){
        int respuesta=0;
        try {
            ps=cn.prepareStatement(" update formulario set Nombre_Completo=?, Usuario=?, Correo=?, Contraseña=? where id_Usuario=?");
            ps.setString(1, Nombre_Completo);
            ps.setString(2, Usuario);
            ps.setString(3, Correo);
            ps.setString(4, Contraseña);
            ps.setString(5, id);
            respuesta=ps.executeUpdate();
            System.out.println("Datos modificados");
        } catch(Exception e){
            System.out.println("Error al modicar datos");
        }
        return respuesta;
    }
    
    public int Elusuario(String id){
        int respuesta=0;
        try {
            ps=cn.prepareStatement(" delete from formulario where id_Usuario=?");
            ps.setString(1, id);
            respuesta=ps.executeUpdate();
            System.out.println("Usuario eliminado");
        } catch (Exception e) {
            System.out.println("Error al eliminar");
        }
        return respuesta;
    }
    
    public ArrayList<UsuariosGetSet> ListaUsuarios(){
        ArrayList<UsuariosGetSet> respuesta=new ArrayList<>();
        try {
            ps=cn.prepareStatement("select * from usuario");
            rs=ps.executeQuery();
            while (rs.next()){
                UsuariosGetSet usuario=new UsuariosGetSet();
                usuario.setId(rs.getString("id_Usuario"));
                usuario.setNombre(rs.getString("Nombre_Completo"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContraseña(rs.getString("Contraseña"));
                respuesta.add(usuario);
            }
        } catch (Exception e) {
        }
        return respuesta;
    }
}
//jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL