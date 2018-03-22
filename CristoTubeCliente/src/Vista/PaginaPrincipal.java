
package Vista;

import Controlador.ConexionCliente;
import static Vista.InicioSesion.hostName;
import static Vista.InicioSesion.nombre;
import static Vista.InicioSesion.portNumber;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;


public class PaginaPrincipal extends javax.swing.JFrame {
    
    private static String cadenichi;
    private static DefaultTableModel modeloTabla;
    private static String videoSeleccionado=null;
    
    public PaginaPrincipal(String cadena) throws SQLException, IOException {
        initComponents();
        cadenichi=cadena;
        insertaDatosCadena();
        
    }
    
    private PaginaPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public void insertaDatosCadena() throws IOException{
        final SwingWorker worker = new SwingWorker(){
            
            @Override
            protected Object doInBackground() throws Exception {
                
                int c = 0;
                String nombre;
                String idvideo;
                String usuario;
                String desc;
                String respuesta_server;
                System.out.println("kiaa :"+cadenichi);
                ConexionCliente nuevoCliente = new ConexionCliente(hostName, portNumber);
                nuevoCliente.sendKey(cadenichi);
                respuesta_server = nuevoCliente.reciveKey();
                System.out.println("Respuesta del servidor en la nueva ventana: " + respuesta_server);
                
                String[] res1;
                res1 = respuesta_server.split("#");
                
                System.out.println("Imprimiendo resultado de mi alopecia 1: " + res1[1]);
                
                String[] res2 = new String[res1.length];
                
                //Comienza a iterar en la segunda posición, para quitar PROTOCOLOCRISTOTUBE1.O y GET_ALL_RESPONSE
                for(int i = 2;i < res1.length; i++){
                    res2[c] = res1[i];
                    c++;
                    System.out.println("Imprimiendo resultado 1: "+ res1[i]);
                }
                //System.out.println(res1.length);
                String cadena_nueva[] = new String[res2.length];
                for(int b = 0; b < res2.length; b++){
                    cadena_nueva = res2[b].split("@");
                    usuario = cadena_nueva[0];
                    
                    for(int j = 1; j<cadena_nueva.length;j++){
                        String[] cadena2 = cadena_nueva[j].split("&");
                        idvideo = cadena2[0];
                        nombre = cadena2[1];
                        desc=cadena2[2];
                        String[] datosBD = {usuario,idvideo,nombre,desc};
                        modeloTabla =  (DefaultTableModel)jTable.getModel();
                        modeloTabla.addRow(datosBD);
                    }
                }
                
                return null;
            }
            
        };
        worker.execute();
        
        
        
        jTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table =(JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {
                    videoSeleccionado= (String) jTable.getValueAt(jTable.getSelectedRow(), 1);
                    System.out.println(""+videoSeleccionado);
                    
                }
            }
        });//Fin del mouse
        
        
    }//Fin de la zona de la table
    
    private void caca(){
        
        final SwingWorker workerr = new SwingWorker(){
            @Override
            @SuppressWarnings("empty-statement")
            protected Object doInBackground() throws Exception {
                int  tamaniovideo=0;
                String devolu[]=null;
                
                if(videoSeleccionado!=null){
                    String cadenaDatos="PROTOCOLCRISTOTUBE1.0#"+nombre+"#GETVIDEO#"+videoSeleccionado;
                    String devolucion;
                    System.out.println(cadenaDatos);
                    ConexionCliente C=new ConexionCliente(hostName,portNumber);
                    
                    C.sendKey(cadenaDatos);//Enviamos la cadena al servidor.
                    devolucion=C.reciveKey();
                    System.out.println("La devolucuion del server es de : "+devolucion);
                    devolu=devolucion.split("#");
                    System.out.println("Devolucion tamaño:"+devolu[4]+" a "+devolu[5]);
                    
                    if(devolu[2].equals("VIDEO_FOUND")){
                        
                        String preparado="PROTOCOLCRISTOTUBE1.0#OK#"+nombre+"#PREPARED_TO_RECEIVE#"+devolu[5];
                        C.sendKey(preparado);
                        
                        tamaniovideo = Integer.parseInt(devolu[4]);
                        System.out.println("El tamaño es de: " + devolu[4]);
                        
                        System.out.println("Entro en DescargarVideo");
                        
                        byte [] video=null;
                        int contador=0;//FIJARSE BIEN
                        boolean videoRecibido=false;
                        String recibeUser=null;
                        String[] llegada;
                        
                        video= new byte[tamaniovideo];
                        
                        System.out.println("Me quedo en la puerta");
                        //System.out.println(in.readLine());
                        // while(!C.reciveKey()){};
                        
                        System.out.println("Paso el while de barrera");
                        
                        //userInput = C.reciveKey();
                        System.out.println("User input del cliente: " + recibeUser);
                        
                        
                        while (((recibeUser=C.reciveKey()))!= null && videoRecibido==false) {
                            
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
                        verVideo vista= new verVideo(videof.getPath());
                        
                        vista.setVisible(true);
                    }
                    
                }
                
                
                return null;
                
            }
            
        };
        workerr.execute();
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subirVideo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TerminalVideo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Generar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subirVideo.setText("SubirVideo");
        subirVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirVideoActionPerformed(evt);
            }
        });
        getContentPane().add(subirVideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        TerminalVideo.setColumns(20);
        TerminalVideo.setRows(5);
        jScrollPane1.setViewportView(TerminalVideo);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 720, 90));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("   GetALL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 180, 80));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setText("Prototipo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 180, 110));

        Generar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Generar.setText("Generar");
        Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarActionPerformed(evt);
            }
        });
        getContentPane().add(Generar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 140, 80));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LOGIN", "IDVIDEO", "TITULO", "DESCRIPCION"
            }
        ));
        jScrollPane2.setViewportView(jTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 530, 310));

        jButton1.setText("Ver video");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, -1, -1));
        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, -40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void subirVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirVideoActionPerformed
        subirVideo subi= new subirVideo();
        
        subi.setVisible(true);
    }//GEN-LAST:event_subirVideoActionPerformed
    
    private void GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarActionPerformed
        
        try {
            ConexionCliente C2=new ConexionCliente(hostName,portNumber);
            
            String devolucion;
            
            System.out.println(cadenichi);
            
            C2.sendKey(cadenichi);//Enviamos la cadena al servidor.
            devolucion=C2.reciveKey();
            TerminalVideo.append("\nEntrando...");
            TerminalVideo.append("\n"+devolucion);
            
        } catch (IOException ex) {
            Logger.getLogger(PaginaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_GenerarActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        caca();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    
    
    public static void main(String args[]) {
        
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaginaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaginaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaginaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaginaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaginaPrincipal().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Generar;
    private javax.swing.JTextArea TerminalVideo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JButton subirVideo;
    // End of variables declaration//GEN-END:variables
}
