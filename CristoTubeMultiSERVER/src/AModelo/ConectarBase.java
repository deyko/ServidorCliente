
package AModelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectarBase {
    
    //Atributos
    public Connection co;
    public Statement stm;
    public ResultSet rs;
    //Constructor por defecto
    public ConectarBase() throws SQLException{
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            co = DriverManager.getConnection("jdbc:mysql://52.19.19.65/cristotube?user=testpsp&password=@,2,golfoPSP123abcd!");
            stm=co.createStatement();

            System.out.println("¡Base de datos conectada con éxito!");
            
        }catch(ClassNotFoundException exc){
            System.out.println("No se ha conectado a la BSD");
            
        }/*finally{
        stm.close(); //Cerramos la conexion a la BSD
        }*/
        
    }
    
    //Devuelve un resultset con el resultado de la consulta!
      
    /*
    public boolean registrarUsuario(Usuario U){
        
        boolean registrado=false;
        
        try{
            String query="INSERT INTO  Usuario(NOMBRE, APELLIDO1, APELLIDO2, DNI, LOGIN, PASS, email)"+" VALUES ('"+U.getNombre()+"','"+U.getApellido1()+"','"+U.getApellido2()+"',"
                    + "'"+U.getDNI()+"','"+U.getLogin()+"','"+U.getPass()+"','"+U.getEmail()+"')";
            
            stm.executeUpdate(query);
            registrado=true;
            
        }catch(SQLException e){
            System.out.println("Usuario no registrado.");
            registrado=false;
        }finally{
            try {
                stm.close();
            } catch (SQLException ex) {
                
            }
        }
        return registrado;
    }
    
    public boolean comprobarLogin(String login,String pass) throws SQLException{
        
        boolean encuentra=false;
        rs=stm.executeQuery("select * from Usuario");
        
        while(rs.next() && encuentra==false){
            if(login.equals(rs.getString("Login"))){
                if(pass.equals(rs.getString("Pass"))){
                    encuentra=true;
                }
            }
            
        }
        return encuentra;
    }
    
    public String devuelveDatosDeLogin(String login) throws SQLException{
        
        rs=stm.executeQuery("select * from Usuario");
        String cadenita=null;
        
        while(rs.next()){
            if(login.equals(rs.getString("Login"))){
                //PROTOCOLCRISTOTUBE1.0#OK#USER_LOGGED#DNI#NAME#LASTNAME1#LASTNAME2#LOGIN#EMAIL
                cadenita=rs.getString("DNI") + "#" + rs.getString("Nombre") + "#" + rs.getString("Apellido1") + "#" + rs.getString("Apellido2")+ "#" + rs.getString("Login")+ "#" + rs.getString("email");
            }
        }
        
        return cadenita;
        
    }
    
    //MODIFICAR PARA QUE SEA MENOS LIO
    
    public String getallVideos () throws SQLException{
        String cadena=null;
        
        String consulta="select * from Usuario u ,Video v where v.id_usuario=u.ID_user";
        System.out.println("Holi");
        rs=stm.executeQuery(consulta);
        
        System.out.println("Usuarios y videos de dichos usuarios: (Falta enviar todos, no solo uno) ");
        System.out.println("________");
        while (rs.next()){
            cadena= "#"  + rs.getString("Login")+ "@" + rs.getString("id_Video")+ "&" + rs.getString("titulo")+ "&" + rs.getString("descripcion");
            
            
            System.out.println(cadena);
            
        }
        return cadena;
    }
    */
    
    
}

