/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectchatting;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import static projectchatting.Menu.socket;

/**
 *
 * @author LUUQUAN-PC
 */
public class Menuafter extends javax.swing.JFrame {

    /**
     * Creates new form Menuafter
     */
    public Menuafter() {
        initComponents();
    }
     
    public static Socket socket;
    private OutputStream output;
    private PrintWriter writer;
    private InputStream input;
    private BufferedReader reader ;
    
    private String name="";
    private String pass="";
    
    public Menuafter(String name,String pass,Socket socket) {
        this.name=name;
     //   this.socket=socket;
        this.pass=pass;
        initComponents();
    }
    
    
     public class guiyeucau extends  Thread{
            Socket socket;
        
        public guiyeucau(Socket socket){
            this.socket=socket;
            
        }
        @Override
        public void run(){
            String kq="";
             try {
               //  System.out.println(socket);
                 
                 output = socket.getOutputStream();
                 writer = new PrintWriter(output, true);        
                        writer.println("Chatclass");

             }catch(Exception ex){
                 
             }
        }
    }

    
    public class guiyeucausingle extends  Thread{
            Socket socket;
        
        public guiyeucausingle(Socket socket){
            this.socket=socket;
            
        }
        @Override
        public void run(){
            String kq="";
             try {
             //    thongbaosocket(this.socket);
                 
                 output = socket.getOutputStream();
                 writer = new PrintWriter(output, true);        
                 // Để mở luồng
                 writer.println("Chatsingle");

             }catch(Exception ex){
                 
             }
        }
    }
    
    
    
    
    
      public class goiform extends Thread{
        String nameString="";
        String pass="";

        public goiform(String id,String pass){
            this.nameString=id;
            this.pass=pass;
            
        }
        @Override
        public void run(){
            String kq="";
             try {
                 socket = new Socket("localhost", 8778);
                 input = socket.getInputStream();
                 reader = new BufferedReader(new InputStreamReader(input));
                 output = socket.getOutputStream();
                 writer = new PrintWriter(output, true);        
                        writer.println(nameString);
                        writer.println(pass);
                            System.out.println(nameString);
                            System.out.println(pass);

                              
             }catch(Exception ex){
                 
             }
               
                    
             
 
                     } 
            
            
            
                
     //   }
        
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnclass = new javax.swing.JButton();
        btnsingle1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnclass.setText("Class");
        btnclass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnclassMouseClicked(evt);
            }
        });

        btnsingle1.setText("Single");
        btnsingle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsingle1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnclass, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnsingle1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnclass, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsingle1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        setSize(new java.awt.Dimension(416, 255));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
         goiform gf = new goiform(name, pass);
         gf.start();
    }//GEN-LAST:event_formWindowOpened

    private void btnclassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclassMouseClicked

        guiyeucau chude = new guiyeucau(socket);
        chude.start();
        //    new Chatclientvr2(name).setVisible(true);
        //   this.dispose();
        new mainchatclass(name, pass, socket).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnclassMouseClicked

    private void btnsingle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsingle1MouseClicked

            
     //   JOptionPane.showMessageDialog(this, socket);

        guiyeucausingle chude = new guiyeucausingle(socket);
        chude.start();        
        new Mainchat(name,pass,this.socket).setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnsingle1MouseClicked

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
            java.util.logging.Logger.getLogger(Menuafter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menuafter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menuafter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menuafter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menuafter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclass;
    private javax.swing.JButton btnsingle1;
    // End of variables declaration//GEN-END:variables
}

