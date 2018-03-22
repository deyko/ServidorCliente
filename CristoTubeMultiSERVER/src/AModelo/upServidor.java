/*
* Hace UP al servidor
*/
package AModelo;



import Controlador.creaHebra;
import Controlador.creaHebra;
import java.net.*;
import java.io.*;
import javax.swing.JTextArea;

public class upServidor  {

    public upServidor (int portNumber){
        
        boolean listening = true;
        int i=0;
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            
            while (listening) {
                
                new creaHebra(serverSocket.accept()).start();
                i++;
                
               // printt.append("\nHan entrado ya: "+i+ " clientes.");
                
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
