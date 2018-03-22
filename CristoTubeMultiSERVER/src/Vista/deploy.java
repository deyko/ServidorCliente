/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import AModelo.upServidor;
import javax.swing.SwingWorker;

/**
 *
 * @author julio
 */
public class deploy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          int portNumber=4444;
   
                upServidor S= new upServidor(portNumber);
       
    }
    
}
