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
import java.io.File;
import javax.swing.SwingWorker;

/**
 *
 * @author julio
 */
public class subirVideo extends javax.swing.JFrame {

    String titu,rut,descrip;
    public subirVideo() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subir = new javax.swing.JButton();
        titulo = new javax.swing.JTextField();
        descripcion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ruta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TerminalCliente = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subir.setText("Subir");
        subir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirActionPerformed(evt);
            }
        });
        getContentPane().add(subir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, -1, -1));

        titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloActionPerformed(evt);
            }
        });
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 98, -1));

        descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripcionActionPerformed(evt);
            }
        });
        getContentPane().add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 120, 57));

        jLabel1.setText("Titulo del video");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setText("Subida de video");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 0, -1, -1));

        jLabel3.setText("Descripción del video");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        ruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rutaActionPerformed(evt);
            }
        });
        getContentPane().add(ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 270, -1));

        jLabel4.setText("Ruta del video");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        TerminalCliente.setColumns(20);
        TerminalCliente.setRows(5);
        TerminalCliente.setText("Estado:\n");
        jScrollPane1.setViewportView(TerminalCliente);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirActionPerformed
         final SwingWorker worker = new SwingWorker(){ //Evita que se congele la vista, falta el trycatch
            String devolucioncita;
            String [] dividido=null;
            @Override
            protected Object doInBackground() throws Exception {
                
                ConexionCliente C=new ConexionCliente(hostName,portNumber);
                String ruta1="/Users/julio/Desktop/CristoTubeMultiServer/"+rut;
                File video = new File(ruta1);
                String cadena="PROTOCOLCRISTOTUBE1.0#VIDEO_UP#"+video.length()+"#1024#METADATOS_VIDEO#"+nombre+"#"+titu+"#"+descrip;
                
                System.out.println(cadena);
                C.sendKey(cadena);
                
                devolucioncita=C.reciveKey();
                 System.out.println("La devolucuion del server es de : "+devolucioncita);
                 dividido=devolucioncita.split("#");
                 
                 System.out.println("Dividido [3]"+ dividido[3]);
                  if(dividido[3].equals("PREPARED_TO_RECEIVE")){
                      C.subirVideo(ruta1, video,dividido);
                  }
                return null;
            }
        };
        
        
        
        worker.execute();
    }//GEN-LAST:event_subirActionPerformed

    private void descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descripcionActionPerformed
          descrip= this.descripcion.getText();
        this.TerminalCliente.append("\nDescripción: "+descrip);
    }//GEN-LAST:event_descripcionActionPerformed

    private void tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloActionPerformed
         titu= this.titulo.getText();
        this.TerminalCliente.append("\nTítulo: "+titu);
    }//GEN-LAST:event_tituloActionPerformed

    private void rutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rutaActionPerformed
          rut= this.ruta.getText();
        this.TerminalCliente.append("\nruta: "+rut);
    }//GEN-LAST:event_rutaActionPerformed

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
            java.util.logging.Logger.getLogger(subirVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(subirVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(subirVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(subirVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new subirVideo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TerminalCliente;
    private javax.swing.JTextField descripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField ruta;
    private javax.swing.JButton subir;
    private javax.swing.JTextField titulo;
    // End of variables declaration//GEN-END:variables
}
