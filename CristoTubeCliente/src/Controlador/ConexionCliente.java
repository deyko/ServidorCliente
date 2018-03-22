
package Controlador;


import Vista.verVideo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.File;
import static java.io.FileDescriptor.in;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 *
 * @author julio
 */
public class ConexionCliente {
    
    Socket echoSocket ;
    PrintWriter out ;
    BufferedReader in;
    BufferedReader stdIn;
    
    //Constructor
    
    public ConexionCliente(String hostName,int portNumber){
        
        try  {
            //Crea el socket
            echoSocket = new Socket(hostName, portNumber);
            //Conexiones
            out =new PrintWriter(echoSocket.getOutputStream(), true);
            in =new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            stdIn =new BufferedReader(new InputStreamReader(System.in));
            
        } catch (UnknownHostException e) {
            System.err.println("No conozco el nombre del host: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo conectar con el hostName: " +
                    hostName);
            System.exit(1);
        }
    }
    
    
    //Envia la cadena  de datos creada apartir del registro
    public void sendKey(String cadena){
        System.out.println("Llega al sendKey: "+cadena);
        out.println(cadena);
    }
    
    //Recibe una cadena de datos del PrintWriter del servidor.
    public String reciveKey() throws IOException{
        String output;
        output=in.readLine();
        System.out.println("Llega al reciveKey: "+output);
        return output;
    }
    
    
    
    //Descargar Video
    public void descargarVideo(int tamanio_video) throws IOException{
        
        System.out.println("Entro en DescargarVideo");
        
        byte [] video=null;
        int cont=0;//FIJARSE BIEN
        
        String userInput=null;
        String salida[];
        
        video= new byte[tamanio_video];
        
        System.out.println("Me quedo en la puerta");
        //System.out.println(in.readLine());
        while(!in.ready()){};
        
        System.out.println("Paso el while de barrera");
        
        userInput = in.readLine();
        System.out.println("User input del cliente: " + userInput);
        
        
        while (((userInput=in.readLine()))!= null) {
            
            salida=userInput.split("#");
            
            byte[] paquete=Base64.decode(salida[3]);
            
            for(int i=0;i< paquete.length && cont<video.length;i++){
                video[cont]=paquete[i];
                cont++;
                
            }
        }//ciero while
        
        System.out.println("Tamaño del Video: "+video.length);
        Date dat = new Date();
        File videof = new File("video..."+dat.getTime()+".mp4");
        
        //Si el video existe lo borra.
        if(videof.exists()){
            videof.delete();
        }
        
        videof.createNewFile();
        OutputStream a=  new FileOutputStream(videof);
        //  for(int i=0;i<video.length;i++){
        a.write(video);
        a.close();
        System.out.println("Esto es el path de video: "+videof.getPath());
        verVideo vista= new verVideo(videof.getPath());
        
        vista.setVisible(true);
        
    }
    
    public void subirVideo(String rutaBD,File video, String cadenaTi[]) throws IOException{
        
        System.out.println("Entro en subvideo");
        String input = null; //entrada
        String output;
        String cadena=null;
        
      
        System.out.println(cadenaTi[2]);
        
        
        System.out.println("Llega del cliente al server por el in: "+input);
        
        String ruta;
      
        
        do{
            System.out.println("entro el el primer if");

            ruta="/Users/julio/Desktop/CristoTubeMultiServer/"+rutaBD;
            System.out.println("La ruta"+ruta);

            String idVideo =cadenaTi[3];
            System.out.println("LLEGO AKI:");
            String  theOutput="PROTOCOLCRISTOTUBE1.0#OK#VIDEO_FOUND#"+cadenaTi[3]+"#"+video.length()+"#1024";
            System.out.println("LLEGO AKI:");
           int tamaniovideo=(int) video.length();
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
                System.out.println("Entrando en To RECEIVE");
                System.out.println(ruta);
                
                if(ruta!= null){
                    System.out.println("entro en el if");
                    int cuenta=0;
                    FileInputStream str = new FileInputStream(video);//Tratar de forma binaria el video
                    
                    int tamano_paquete=1024;
                    byte paquete []= new byte[tamano_paquete];
                    
                    while(cuenta<video.length()){
                        System.out.println(cuenta+ " de "+ video.length());
                        str.read(paquete); //lee los proximos 1024 byts
                        theOutput= "PROTOCOLCRISTOTUBE1.0#OK#"+idVideo+"#"+Base64.encode(paquete);
                        
                        System.out.println("Entro el el while: "+theOutput);
                        
                        
                        //Enviamos al cliente
                        out.println(theOutput);
                        cuenta=cuenta+tamano_paquete;
                        System.out.println(cuenta+ " de "+ video.length());
                    }
                    System.out.println("FIN LOCOO");
                }
                
            }
            /*
            if(!socket.isClosed())
            socket.close();*/
        }while((input=in.readLine())!=null);
    }
    
    //Fin
    
    
    
}

