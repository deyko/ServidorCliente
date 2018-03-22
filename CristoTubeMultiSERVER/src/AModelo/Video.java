
package AModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class Video {
    
    private int ID_user;
    private int ID_video;
    private String rutaServer;
    private String titulo;
    private String fecha;
    private String descripcion;
    
      /** BASE DATOS **/
    private static ConectarBase C;
    
     public Video() throws SQLException {
      this.C = new ConectarBase();
    }
    
    public Video(int ID_user, int ID_video, String rutaServer, String titulo, String descripcion) throws SQLException {
        this.ID_user = ID_user;
        this.ID_video = ID_video;
        this.rutaServer = rutaServer;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.C = new ConectarBase();
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public int getID_video() {
        return ID_video;
    }

    public void setID_video(int ID_video) {
        this.ID_video = ID_video;
    }

    public String getRutaServer() {
        return rutaServer;
    }

    public void setRutaServer(String rutaServer) {
        this.rutaServer = rutaServer;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Video{" + "ID_user=" + ID_user + ", ID_video=" + ID_video + ", rutaServer=" + rutaServer + ", titulo=" + titulo + ", fecha=" + fecha + ", descripcion=" + descripcion + '}';
    }
    
    
        
      
    
    
    public static String encontrarVideo(String id_video) throws SQLException{
        String ruta = null;
        String resultado=null;
        String id_v = null;
         String ruta2;
        String consulta="SELECT * FROM videos where IDVIDEO="+id_video;
        
        System.out.println(consulta);
        
        C.rs=C.stm.executeQuery(consulta);
         while (C.rs.next()){
            ruta = C.rs.getString("rutaserver");
            id_v= C.rs.getString("IDVIDEO");
            System.out.println(ruta);
        }
        //Esta creado por el tema del salvado de archivos en mac.
        
        ruta2="/Users/julio/Desktop/CristoTubeMultiServer/"+ruta;
         
        File video = new File(ruta2);
        
        resultado="PROTOCOLCRISTOTUBE1.0#OK#VIDEO_FOUND#"+id_v+"#"+video.length()+"#1024";

        System.out.println(resultado);
        
        return resultado;
        
        
        
        
    }
    
     public static String pruebas(String id_video) throws SQLException, FileNotFoundException{
        String ruta = null;
        String resultado=null;
        String id_v = null;
        
        String consulta="SELECT * FROM videos where idVideo="+id_video;
        
        System.out.println(consulta);
        
        C.rs=C.stm.executeQuery(consulta);
         while (C.rs.next()){
            ruta = C.rs.getString("rutaserver");
            id_v= C.rs.getString("idVideo");
            System.out.println(ruta);
        }
        
        File video = new File(ruta);
        
        resultado="PROTOCOLCRISTOTUBE1.0#OK#VIDEO_FOUND#"+id_v+"#"+video.length()+"#1024";

        System.out.println(resultado);
        String caca="PREPARED_TO_RECEIVE";
        
        if(caca=="PREPARED_TO_RECEIVE"){
            
            FileInputStream steam= new FileInputStream(video);
            
            byte [] transferencia;
            
            
            
        }
        
        
        
        
        
        
        
        
        return resultado;  

    }
     
     
     
     public static String getRuta(String id_video) throws SQLException{
         String ruta = null;
     
        
        String consulta="SELECT * FROM videos where IDVIDEO="+id_video;
        
        System.out.println(consulta);
        
        C.rs=C.stm.executeQuery(consulta);
         while (C.rs.next()){
            ruta = C.rs.getString("rutaserver");
            
            System.out.println(ruta);
        }
         return ruta;
     }
    
}