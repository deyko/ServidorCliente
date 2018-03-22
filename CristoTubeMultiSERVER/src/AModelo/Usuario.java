
package AModelo;


import AModelo.ConectarBase;
import java.sql.SQLException;
import java.util.Objects;

public class Usuario {
    
    
    private String Nombre;
    private String Apellido1;
    private String Apellido2;
    private String DNI;
    private String Pass;
    private String Login;
    private String email;
    
    /** BASE DATOS **/
    private static ConectarBase C;
    
//Por defecto
    public Usuario() throws SQLException {
        
        this.C = new ConectarBase();
        this.Nombre = "Vacio";
        this.Apellido1 = "Vacio";
        this.Apellido2 = "Vacio";
        this.DNI = "Vacio";
        this.Pass = "Vacio";
        this.Login = "Vacio";
    }
    
    
    public Usuario(String dni, String Nombre,String Apellido1,String Apellido2,String Login, String Pass, String email) throws SQLException {
        this.C = new ConectarBase();
        this.Nombre = Nombre;
        this.Apellido1 = Apellido1;
        this.Apellido2 = Apellido2;
        this.email = email;
        this.DNI = dni;
        this.Pass = Pass;
        this.Login = Login;
    }
    
    
    public String getNombre() {
        return Nombre;
    }
    
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getApellido1() {
        return Apellido1;
    }
    
    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }
    
    public String getApellido2() {
        return Apellido2;
    }
    
    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }
    
    public String getDNI() {
        return DNI;
    }
    
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
    public String getPass() {
        return Pass;
    }
    
    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    public String getLogin() {
        return Login;
    }
    
    public void setLogin(String Login) {
        this.Login = Login;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 29 * hash + Objects.hashCode(this.Nombre);
        hash = 29 * hash + Objects.hashCode(this.Apellido1);
        hash = 29 * hash + Objects.hashCode(this.Apellido2);
        hash = 29 * hash + Objects.hashCode(this.DNI);
        hash = 29 * hash + Objects.hashCode(this.Pass);
        hash = 29 * hash + Objects.hashCode(this.Login);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Apellido1, other.Apellido1)) {
            return false;
        }
        if (!Objects.equals(this.Apellido2, other.Apellido2)) {
            return false;
        }
        if (!Objects.equals(this.DNI, other.DNI)) {
            return false;
        }
        if (!Objects.equals(this.Pass, other.Pass)) {
            return false;
        }
        if (!Objects.equals(this.Login, other.Login)) {
            return false;
        }
        return true;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "Nombre=" + Nombre + ", Apellido1=" + Apellido1 + ", Apellido2=" + Apellido2 + ", DNI=" + DNI + ", Pass=" + Pass + ", Login=" + Login + ", email=" + email + '}';
    }
    
    
    /** Metodos de la clase static **/
    
    public static void getallUsuarios() throws SQLException{
        
        String cadena=null;
        
        String consulta="select * from usuarios u ,videos v where v.IDUSUARIO=u.id";
        C.rs=C.stm.executeQuery(consulta);
        
        System.out.println("Usuarios y videos de dichos usuarios: (Falta enviar todos, no solo uno) ");
        System.out.println("________");
        
        while (C.rs.next()){
            cadena= "#"  + C.rs.getString("Login")+ "@" + C.rs.getString("IDVIDEO")+ "&" + C.rs.getString("titulo")+ "&" + C.rs.getString("descripcion");
            
            System.out.println(cadena);
            
        }
    }
    
    public static boolean registrarUsuario(Usuario U){
        
        boolean registrado=false;
        
        try{
            String query="INSERT INTO  usuarios(NAME, LASTNAME1, LASTNAME2, DNI, LOGIN, PASSWORD)"+" VALUES ('"+U.getNombre()+"','"+U.getApellido1()+"','"+U.getApellido2()+"',"
                    + "'"+U.getDNI()+"','"+U.getLogin()+"','"+U.getPass()+"')";
            
            C.stm.executeUpdate(query);
            registrado=true;
            System.out.println(registrado);
        }catch(SQLException e){
            System.out.println("Usuario no registrado.");
            registrado=false;
        }finally{
            try {
                C.stm.close();
            } catch (SQLException ex) {
                
            }
        }
        return registrado;
    }
    
    public static boolean comprobarLogin(String login,String pass) throws SQLException{
        
        boolean encuentra=false;
        C.rs=C.stm.executeQuery("select * from usuarios");
        
        while(C.rs.next() && encuentra==false){
            if(login.equals(C.rs.getString("Login"))){
                if(pass.equals(C.rs.getString("PASSWORD"))){
                    encuentra=true;
                }
            }
            
        }
        return encuentra;
    }
    
    public String devuelveDatosDeLogin(String login) throws SQLException{
        
        C.rs=C.stm.executeQuery("select * from usuarios");
        String cadenita=null;
        
        while(C.rs.next()){
            if(login.equals(C.rs.getString("Login"))){
                //PROTOCOLCRISTOTUBE1.0#OK#USER_LOGGED#DNI#NAME#LASTNAME1#LASTNAME2#LOGIN#EMAIL
                cadenita=C.rs.getString("DNI") + "#" + C.rs.getString("NAME") + "#" + C.rs.getString("LASTNAME1") + "#" + C.rs.getString("LASTNAME2")+ "#" + C.rs.getString("Login");
            }
        }
        
        return cadenita;
        
    }
                     //DUDA SI MOVER A LA CLASE VIDEO Usuario : getallVideos
    
    public static String getallVideos (String login) throws SQLException{
        String cadena = null,resultado = "";
        
        String consulta="SELECT * FROM usuarios u, videos v where u.id=v.IDUSUARIO";
        
        System.out.println(consulta);
        C.rs=C.stm.executeQuery(consulta);
        
        System.out.println("Usuarios y videos de dichos usuarios: (Falta enviar todos, no solo uno) ");
        System.out.println("________");
        while (C.rs.next()){
            //Quité el # por el tema del null del resultado, funciona perfeccctiiiisiisisimo!
            
            cadena="#"+ C.rs.getString("Login")+ "@" + C.rs.getString("IDVIDEO")+ "&" + C.rs.getString("titulo")+ "&" + C.rs.getString("descripcion");
            resultado=resultado+cadena;
            
            System.out.println(cadena);
            
        }
        System.out.println(resultado);
        return resultado;
    }
    
  
    
}
