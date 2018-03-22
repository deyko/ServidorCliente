package Controlador;

import AModelo.ConectarBase;
import AModelo.Video;
import Controlador.Protocolo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class creaHebra extends Thread {
    private Socket socket = null;
    public static int tamaniovideo;
    
    public creaHebra(Socket socket) {
        
        super("Crea hebras");
        
        this.socket = socket;
    }
    
    @Override
    public void run() {
        
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
            
            String input = null; //entrada
            String output;
            String cadena=null;
            System.out.println(in);
            
            
            //Apartir
            input=in.readLine();
            String cadenaTi[];
            
            cadenaTi= input.split("#");
            System.out.println(cadenaTi[2]);
            
            
            System.out.println("Llega del cliente al server por el in: "+input);
            
            if("GETVIDEO".equals(cadenaTi[2])){
                String rutaBD,ruta;
                Video v= new Video();
                
                do{
                    System.out.println("entro el el primer if");
                    
                    rutaBD= v.getRuta(cadenaTi[3]);
                    
                    ruta=rutaBD;
                    System.out.println("La ruta"+ruta);
                    System.out.println("LA RUTA QUE LLEGA DE CADENA :"+rutaBD);
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
            }else if ("VIDEO_UP".equals(cadenaTi[1])){
                
                System.out.println("Entro en video up");
                System.out.println(cadenaTi[2]);
                int tamaniovideos = Integer.parseInt(cadenaTi[2]);
                String theout="PROTOCOLCRISTOTUBE1.0#OK#VIDEO_UP#PREPARED_TO_RECEIVE#1024";
                out.println(theout);
                
                
                         
                        System.out.println("El tamaño es de: " + cadenaTi[1]);
                        
                        System.out.println("Entro en DescargarVideo");
                        
                        byte [] video=null;
                        int contador=0;//FIJARSE BIEN
                        boolean videoRecibido=false;
                        String recibeUser=null;
                        String[] llegada;
                        
                        video= new byte[tamaniovideos];
                        
                        System.out.println("Me quedo en la puerta");
                        //System.out.println(in.readLine());
                        // while(!C.reciveKey()){};
                        
                        System.out.println("Paso el while de barrera");
                        
                        //userInput = C.reciveKey();
                        System.out.println("User input del cliente: " + recibeUser);
                        
                        
                        while (((recibeUser=in.readLine()))!= null && videoRecibido==false) {
                            
                            llegada=recibeUser.split("#");
                            
                            byte[] paquete=Base64.decode(llegada[3]);
                            
                            for(int i=0;i<paquete.length && contador<video.length;i++){
                                video[contador]=paquete[i];
                                contador++;
                                
                            }
                            
                            System.out.println("Contador= "+contador +" video lendth: "+video.length);
                            
                            if(contador>=video.length){
                                System.out.println("ENTROO");
                                videoRecibido=true;
                                //No sale, hago el break para forzarlo.
                                break;
                            }
                        }//ciero while
                        
                        System.out.println("Tamaño del Video: "+video.length);
                        
                        // Date dat = new Date();
                        int aleatorio = (int) (Math.random() * 7000) + 1;
                        File videof = new File("VideoDescargado"+aleatorio+".mp4");
                        
                        //Si el video existe lo borra.
                        if(videof.exists()){
                            videof.delete();
                        }
                        
                        videof.createNewFile();
                        OutputStream trans=  new FileOutputStream(videof);
                        
                        trans.write(video);
                        trans.close();
                        
                        System.out.println("Esto es el path de video: "+videof.getPath());
                
                
                
                
            }else{
            
            Protocolo Proto= new Protocolo();
            
            cadena=Proto.Protocolo(input);
            
            System.out.println("El protocolo devuelve por el out hacia cliente: "+cadena);
            out.println(cadena);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(creaHebra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(creaHebra.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Fin F
        
        //  socket.close();
        
    }
}