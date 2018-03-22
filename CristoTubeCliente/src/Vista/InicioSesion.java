
package Vista;

import Controlador.ConexionCliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 * @author julio
 */
public class InicioSesion extends javax.swing.JFrame {
    
    public static String nombre;
    String pass;                //192.168.1.160
    public static String hostName="192.168.1.128"; //static para todo el proyecto
    public static int portNumber=4444;
    
    
    public InicioSesion() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Usuariologg = new javax.swing.JTextField();
        Contralogg = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Conectar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TerminalCliente = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        Regis = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Usuariologg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariologgActionPerformed(evt);
            }
        });
        getContentPane().add(Usuariologg, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 116, 31));

        Contralogg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContraloggActionPerformed(evt);
            }
        });
        getContentPane().add(Contralogg, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 116, 27));

        jLabel1.setText("Usuario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jLabel2.setText("Contraseña");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        Conectar.setText("Conectar");
        Conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConectarActionPerformed(evt);
            }
        });
        getContentPane().add(Conectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, -1));

        TerminalCliente.setColumns(20);
        TerminalCliente.setRows(5);
        TerminalCliente.setText("Estado:\n");
        jScrollPane1.setViewportView(TerminalCliente);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 48)); // NOI18N
        jLabel3.setText("Cliente");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 200, 70));

        Regis.setText("Registrar");
        Regis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisActionPerformed(evt);
            }
        });
        getContentPane().add(Regis, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void UsuariologgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariologgActionPerformed
        
        nombre= this.Usuariologg.getText();
        this.TerminalCliente.append("\nUsuario: "+nombre);
    }//GEN-LAST:event_UsuariologgActionPerformed
    
    private void ContraloggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContraloggActionPerformed
        pass= this.Contralogg.getText();
        this.TerminalCliente.append("\nContraseña (Debugg): "+pass);
    }//GEN-LAST:event_ContraloggActionPerformed
    
    private void ConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConectarActionPerformed
        
        String cadenaDatos="PROTOCOLCRISTOTUBE1.0" + "#" + "LOGIN" + "#" + nombre+ "#"+ pass;
        
        final SwingWorker worker = new SwingWorker(){ //Evita que se congele la vista, falta el trycatch
            
            @Override
            protected Object doInBackground() throws Exception {
                
                ConexionCliente C=new ConexionCliente(hostName,portNumber);
                String devolucion;
                
                System.out.println(cadenaDatos);
                C.sendKey(cadenaDatos);//Enviamos la cadena al servidor.
                devolucion=C.reciveKey();
                TerminalCliente.append("\n"+devolucion);
                TerminalCliente.append("\n¡Conectado!");
                
                String[] prueba = devolucion.split("#");
                
                System.out.println(prueba[2]);
               
                if (prueba[2].equals("USER_LOGGED")){
                    
                    String getall="PROTOCOLCRISTOTUBE1.0#"+nombre+"#GET_ALL";
                       
                    System.out.println("el get all antes de saltar a la pag.principal: "+getall);
                    PaginaPrincipal Coco= new PaginaPrincipal(getall);
                   
                    
                    Coco.setVisible(true);//Hace que salte la nueva pantalla
                    dispose(); //Oculta la pantalla anterior
                }
                /*
                if(C.reciveKey().contains("PROTOCOLOCRISTOTUBE1.0" + "#" + "OK" + "#" + "USER_LOGGED")){
                VistaSesion Coco= new VistaSesion();
                Coco.setVisible(true);//Hace que salte la nueva pantalla
                dispose(); //Oculta la pantalla anterior
                }*/
                return null;
            }
        };
        
        
        
        worker.execute();
    }//GEN-LAST:event_ConectarActionPerformed
    
    private void RegisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisActionPerformed
        RegistroUsuario Caca= new RegistroUsuario();
        Caca.setVisible(true);//Hace que salte la nueva pantalla
        dispose(); //Oculta la pantalla anterior
    }//GEN-LAST:event_RegisActionPerformed
    
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
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Conectar;
    private javax.swing.JTextField Contralogg;
    private javax.swing.JButton Regis;
    private javax.swing.JTextArea TerminalCliente;
    private javax.swing.JTextField Usuariologg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
