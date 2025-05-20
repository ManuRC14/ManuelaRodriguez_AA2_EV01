
import java.sql.*;
public class Conexion {
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    public Conexion (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Manu14071");
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
            System.out.println("Error al registrar");
        }
        return respuesta;
    }
}
//jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL