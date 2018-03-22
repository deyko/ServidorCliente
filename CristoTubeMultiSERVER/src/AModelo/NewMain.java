/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package AModelo;

import AModelo.Usuario;
import Controlador.Protocolo;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 *
 * @author julio
 */
public class NewMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        String nombre="CACANOMBR222E",apellido="CACAAPELLIDO",apellido2="CACAPELLIDO2",pass="vacio",login="vacio",dni="CACADNI1",email="CACAEMAIL";
        boolean caca=false;
        
        
        Usuario u= new Usuario(dni,nombre,apellido,apellido2,login,pass,email);
        Usuario a= new Usuario();
        
        //ConectarBase lol = new ConectarBase();
        
        //  lol.getallVideos();
        
        // u.getallUsuarios();
        //  a.getallUsuarios();
        
        //a.registrarUsuario(u);
        a.getallVideos("juan");
        /*
        caca= lol.comprobarLogin("juan","juan");
        
        if(caca==false){
        System.out.println("no esta loko.");
        }else if(caca==true){
        System.out.println("si ta loka.");
        }
        
        String caco;
        caco=lol.devuelveDatosDeLogin("juan");
        
        System.out.println(caco);
        */
        
        /*
        
        String prueba=" PROTOCOLCRISTOTUBE1.0#pradosman#GET_ALL";
        String resultado;
        
        Protocolo P= new Protocolo();
        resultado=P.Protocolo(prueba);
        
        System.out.println("ZONA DE PRUEBAS DANGERRRR: ");
        System.out.println(resultado);
        
        String strings[],strings2[];
        String guardado="";
        
        strings= resultado.split("#");
        
        System.out.println("Entramos en el forrrr:");
        System.out.println("________________");
        
        for(int i=0; i<strings.length;i++){
            
           
            System.out.println(strings[i]);
            
            
            guardado=strings[i];
            if(guardado.contains("@")){
                System.out.println("ENTROOO");
                strings2=guardado.split("@");
                 for(int j=0;j<strings2.length;j++){
                
                System.out.println(strings2[j]);
                
            }
            }
          
           
            
        }*/
        /*
        a.getallVideos("juan");
     
      v.encontrarVideo("1");
      */
    // v.mandarVideo("1"); 
    
    Video v= new Video();
    v.getRuta("1");
    }
    
}
