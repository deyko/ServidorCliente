/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Vista;

import Controlador.ConexionCliente;
import static Vista.InicioSesion.hostName;
import static Vista.InicioSesion.nombre;
import static Vista.InicioSesion.portNumber;
import javax.swing.SwingWorker;

/**
 *
 * @author julio
 */
public class BORRAR extends javax.swing.JFrame {
    
    /**
     * Creates new form BORRAR
     */
    public BORRAR() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jButton1)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final SwingWorker worker = new SwingWorker(){ //Evita que se congele la vista, falta el trycatch
            
            @Override
            protected Object doInBackground() throws Exception {
                
                ConexionCliente C=new ConexionCliente(hostName,portNumber);
                String devolucion;
                String cadenaDatos="PROTOCOLCRISTOTUBE1.0#pradosman#GETVIDEO#1";
                
                System.out.println(cadenaDatos);
                C.sendKey(cadenaDatos);//Enviamos la cadena al servidor.
                devolucion=C.reciveKey();
                
                String preparado="PROTOCOLCRISTOTUBE1.0#OK#pradosman#PREPARED_TO_RECEIVE#1024";
                C.sendKey(preparado);
                
                
                int cont=0;
                while (C.reciveKey()!= null) {
                    
                    devolucion=C.reciveKey();
                    System.out.println(devolucion);
                    
                    
                    /*
                    }
                    // System.out.println("leyendo paquete::"+userInput);
                    salida=userInput.split("#");
                    byte[] paquete=Base64.decode(salida[3]);
                    
                    for(int i=0;i< paquete.length && cont<video.length;i++){
                    video[cont]=paquete[i];
                    cont++;*/
                }
                
                
                
                return null;
            }
        };
        
        
        
        worker.execute();
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BORRAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BORRAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BORRAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BORRAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BORRAR().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}