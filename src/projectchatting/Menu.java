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

/**
 *
 * @author LUUQUAN-PC
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
       
    }
    
    public static Socket socket;
    private OutputStream output;
    private PrintWriter writer;
    
    
    
    public Menu(String name,String pass,Socket socket) {
        this.name=name;
        this.socket=socket;
        this.pass=pass;
        initComponents();
    }
    private String name="";
    private String pass="";
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
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        btnclass = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        btnsingle = new javax.swing.JButton();
        txtten = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
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
        jScrollPane1.setViewportView(btnclass);

        btnsingle.setText("Single");
        btnsingle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsingleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(btnsingle);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addGap(85, 85, 85))
        );

        setSize(new java.awt.Dimension(368, 288));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnclassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclassMouseClicked

        guiyeucau chude = new guiyeucau(socket);
        chude.start();
    //    new Chatclientvr2(name).setVisible(true);
      //   this.dispose();
        new mainchatclass(name, pass, socket).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnclassMouseClicked
    //btn single
    
    
    
    private void btnsingleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsingleMouseClicked

        JOptionPane.showMessageDialog(this, socket);
        guiyeucausingle chude = new guiyeucausingle(socket);
        chude.start();        
        new Mainchat(name,pass,this.socket).setVisible(true);
        this.dispose();
                
    }//GEN-LAST:event_btnsingleMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       
        txtten.setText(name);
        
        
        
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclass;
    private javax.swing.JButton btnsingle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel txtten;
    // End of variables declaration//GEN-END:variables
}