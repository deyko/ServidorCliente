
package Controlador;

import AModelo.Usuario;
import AModelo.Video;
import static Controlador.creaHebra.tamaniovideo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Protocolo {
    
    private static Usuario U;
    private static Video V;
    String[] strings;
    
    
    public Protocolo() throws SQLException {
        //El constructor por defecto se conecta a la base de datos.
        this.U = new Usuario();
        this.V= new Video();
    }
    
    public String Protocolo(String cadena) throws SQLException {
        String cadenita=null;
        
      
        strings = cadena.split("#");
        
        System.out.println("Llega:"+cadena);
        
        if(strings[1].equals("REGISTER")){
            boolean registrado=false;
            
            Usuario U= new Usuario(strings[2],strings[3],strings[4],strings[5],strings[6],strings[7],strings[8]);
            
            System.out.println("Usuario creado (Debugg):");
            
            System.out.println( U.toString());
            System.out.println("Pendiente de inserción en la BD, y asi devolver confirmación:");
            
            registrado=U.registrarUsuario(U);
            
            if(registrado==true){//Cambiar sin los +
                cadenita="PROTOCOLCRISTOTUBE1.0" + "#" + "REGISTER" + "#" + "OK" + "#" + "REGISTERED";
                
            }else{
                cadenita="PROTOCOLCRISTOTUBE1.0" + "#" + "REGISTER" + "#" + "ERROR" + "#" + "CANT_REGISTER";
            }
            
            System.out.println(cadenita);
            
            
        }else if(strings[1].equals("LOGIN")){
            boolean logueado=false;
            logueado=U.comprobarLogin(strings[2], strings[3]);
            
            System.out.println("Inicio sesión de: " +strings[2]+ " , "+strings[3]+" (Debugg)...");
            if(logueado==true){
                cadenita="PROTOCOLCRISTOTUBE1.0" + "#" + "OK" + "#" + "USER_LOGGED"+ "#" +U.devuelveDatosDeLogin(strings[2]);
            }else{
                cadenita="PROTOCOLCRISTOTUBE1.0" + "#" + "ERROR" + "#" + "BAD_LOGIN";
            }
            
        }else if(strings[2].equals("GET_ALL")){
            cadenita="PROTOCOLCRISTOTUBE1.0#GET_ALL_RESPONSE#" + strings[1] +U.getallVideos(strings[1]);
            
        }else if(strings[2].equals("GETVIDEO")){ //PROTOCOLCRISTOTUBE1.0#LOGIN#GETVIDEO#ID_VIDEO
            
          cadenita= V.encontrarVideo(strings[3]);
           
            
          
        }
        
        System.out.println("Al protocolo strings [2] llega= "+strings[2]);
        //Recuerda que ha sido un problema por el if que no cogiera el get_all!
        
        
        return cadenita;
        
    }
    
    public void enviarVideo(String [] cadenaTi,PrintWriter out,BufferedReader in) throws SQLException, FileNotFoundException, IOException{
        
       
                String rutaBD,ruta;
                Video vi= new Video();
                String input;
                
                do{
                    System.out.println("entro el el primer if");
                    
                    rutaBD= vi.getRuta(cadenaTi[3]);
                    ruta="/Users/julio/Desktop/CristoTubeMultiServer/"+rutaBD;
                    System.out.println("La ruta"+ruta);
                    File video = new File(ruta);
                    
                    String idVideo =cadenaTi[3];
                    System.out.println("LLEGO AKI:");
                    String  theOutput="PROTOCOLCRISTOTUBE1.0#OK#VIDEO_FOUND#"+cadenaTi[3]+"#"+video.length()+"#1024";
                    System.out.println("LLEGO AKI:");
                    tamaniovideo=(int) video.length();
                    System.out.println("Tamanio del video"+tamaniovideo);
                    out.println(theOutput);
                    System.out.println("LLEGO AKI2:");
                    input=in.readLine();
                    System.out.println("LLEGO AKI2/2:");
                    System.out.println(input);
                    cadenaTi= input.split("#");
                    System.out.println("LLEGO AKI3:");
                    System.out.println("Lectrua::"+input);
                    
                    if("PREPARED_TO_RECEIVE".equals(cadenaTi[3])){
                        System.out.println("Entrando en prepare");
                        System.out.println(ruta);
                        
                        if(ruta!= null){
                            
                           
                            int cont=0;
                            FileInputStream trans = new FileInputStream(video);//Tratar de forma binaria el video
                            
                            int tamano_paquete=1024;
                            byte paquete []= new byte[tamano_paquete];
                            
                            while(cont<video.length()){
                                System.out.println(cont+ " de "+ video.length());
                                trans.read(paquete); //lee los proximos 1024 byts
                                theOutput= "PROTOCOLCRISTOTUBE1.0#OK#"+idVideo+"#"+Base64.encode(paquete);
                                
                                System.out.println("Entro el el while: "+theOutput);
                                
                                
                                //Enviamos al cliente
                                out.println(theOutput);
                                cont=cont+tamano_paquete;
                                System.out.println(cont+ " de "+ video.length());
                            }
                            System.out.println("Video enviado por completo!");
                        }
                        
                    }
                    /*
                    if(!socket.isClosed())
                    socket.close();*/
                }while((input=in.readLine())!=null);
            }
    
}
