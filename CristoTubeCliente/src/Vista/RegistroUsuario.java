
package Vista;
import Controlador.ConexionCliente;
import static Vista.InicioSesion.hostName;
import static Vista.InicioSesion.portNumber;
import javax.swing.SwingWorker;
/**
 *
 * @author julio
 */
public class RegistroUsuario extends javax.swing.JFrame {
    
    
    public RegistroUsuario() {
        initComponents();
        
        
    }
    
    String nombre,apellido,apellido2,pass="vacio",login="vacio",dni,email;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Pass = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        Login = new javax.swing.JTextField();
        Registro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Notificacion = new javax.swing.JTextArea();
        Volver = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        DNI = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        seguApellido = new javax.swing.JTextField();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        jLabel1.setText("Registro Usuario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, -10, 390, 60));

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        getContentPane().add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 110, 30));

        Pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassActionPerformed(evt);
            }
        });
        getContentPane().add(Pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 110, 30));

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, 20));

        jLabel3.setText("Pass");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, 20));

        jLabel4.setText("Login");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, 20));

        jLabel5.setText("Apellido");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));

        Apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoActionPerformed(evt);
            }
        });
        getContentPane().add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 110, 30));

        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 110, 30));

        Registro.setText("Registro");
        Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroActionPerformed(evt);
            }
        });
        getContentPane().add(Registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, -1, -1));

        Notificacion.setColumns(20);
        Notificacion.setRows(5);
        jScrollPane2.setViewportView(Notificacion);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 310, 140));

        Volver.setText("Volver");
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });
        getContentPane().add(Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 80, -1));

        jLabel6.setText("DNI");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 30, 30));

        DNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DNIActionPerformed(evt);
            }
        });
        getContentPane().add(DNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 110, 30));

        jLabel7.setText("E-mail");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, -1, -1));

        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });
        getContentPane().add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 110, 40));

        jLabel8.setText("Segundo Apellido");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        seguApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seguApellidoActionPerformed(evt);
            }
        });
        getContentPane().add(seguApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 110, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        nombre=this.Nombre.getText();
        System.out.println(nombre);
    }//GEN-LAST:event_NombreActionPerformed
    
    private void ApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoActionPerformed
        apellido=this.Apellido.getText();
    }//GEN-LAST:event_ApellidoActionPerformed
    
    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        login=this.Login.getText();
    }//GEN-LAST:event_LoginActionPerformed
    
    private void RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroActionPerformed
        
        if(login=="vacio" || pass=="vacio" ){
            this.Notificacion.append("Campos incorrectos \n(Mejora en futuro.)");
            
        }else{
            this.Notificacion.append("Campos rellenos");
            this.Notificacion.append("\nLogin: "+login);
            this.Notificacion.append("\nContraseña: "+pass);
            this.Notificacion.append("\nNombre: "+nombre);
            this.Notificacion.append("\nApellido: "+apellido);
            this.Notificacion.append("\nApellido2: "+apellido2);
            this.Notificacion.append("\nDNI: "+dni);
            this.Notificacion.append("\nE-Mail: "+email);
            
            //Agrupamos todo en cadena, y lo dividimos con #
            //PROTOCOLCRISTOTUBE1.0#REGISTER#DNI#NOMBRE#APELLIDO1#APELLIDO2#LOGIN#PASS#EMAIL
            
            String cadenaDatos=  "PROTOCOLOCRISTOTUBE1.0" + "#" + "REGISTER" + "#" + dni+ "#"+ nombre +"#"+ apellido+"#" +apellido2+"#"+login+"#"+pass+"#"+email;
            final SwingWorker worker = new SwingWorker(){ //Evita que se congele la vista, falta el trycatch
                
                @Override
                protected Object doInBackground() throws Exception {
                    
                    ConexionCliente C1= new ConexionCliente(hostName,portNumber);
                    
                    C1.sendKey(cadenaDatos);//Enviamos la cadena al servidor.
                    Notificacion.append("\n"+C1.reciveKey());
                    
                    
                    return null;
                }
            };
            
            worker.execute();
            
            
            
        }
        
        
    }//GEN-LAST:event_RegistroActionPerformed
    
    private void PassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassActionPerformed
        pass=this.Pass.getText();
    }//GEN-LAST:event_PassActionPerformed
    
    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        InicioSesion Ca= new InicioSesion();
        Ca.setVisible(true);//Hace que salte la nueva pantalla
        dispose(); //Oculta la pantalla anterior
    }//GEN-LAST:event_VolverActionPerformed
    
    private void DNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DNIActionPerformed
        dni=this.DNI.getText();
    }//GEN-LAST:event_DNIActionPerformed
    
    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        email=this.Email.getText();
    }//GEN-LAST:event_EmailActionPerformed
    
    private void seguApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seguApellidoActionPerformed
        apellido2=this.seguApellido.getText();
    }//GEN-LAST:event_seguApellidoActionPerformed
    
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
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroUsuario().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JTextField DNI;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Login;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextArea Notificacion;
    private javax.swing.JPasswordField Pass;
    private javax.swing.JButton Registro;
    private javax.swing.JButton Volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField seguApellido;
    // End of variables declaration//GEN-END:variables
}
