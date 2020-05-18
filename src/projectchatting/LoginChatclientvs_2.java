/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectchatting;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.soap.Detail;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import jdk.nashorn.internal.ir.BreakNode;
 
/**
 *
 * @author LUUQUAN-PC
 */
public class LoginChatclientvs_2 extends javax.swing.JFrame {

    /** Creates new form Login */
    public LoginChatclientvs_2() {
        initComponents();
    }

    
    public static Socket socket;
    private InputStream input;
    private BufferedReader reader ;
    private OutputStream output;
    private PrintWriter writer;
    
    
    
    
    
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

                              kq = reader.readLine();
                               System.out.println(kq);
             }catch(Exception ex){
                 
             }
               
             if(txtid.getText().equals("")|| txtpass.getPassword().equals("")){
                cbchuanhap();
                }
             else{
                        try {
                            if(kq.equals(nameString))
                                  {
                                     
                                        new Menu(nameString,pass,socket).setVisible(true);
                                        
                            
                                    }
                                        if(kq.equals("Fasle"))
                                        {
                                             cbdangnhapsai();
                                             txtid.setText("");
                                             txtpass.setText("");
                                        }
                             } catch (Exception e) {
               
                                                    }
 
                     } 
            
            
            
                
        }
        
        
        
    }
    
    class dangky extends Thread{
        
        
        
        
        
        
        
}
    
    
    public void cbdangnhapsai(){
        
        JOptionPane.showMessageDialog(this,"Đăng nhập sai !!!");
        
    }
    public void  cbchuanhap(){
        
        JOptionPane.showMessageDialog(this,"M chưa nhập 1 ô nào đó !!!");
        
    }
    void anform(){
        
        this.dispose();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtid = new javax.swing.JTextField();
        btnok = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        btndangky = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnok.setText("Ok");
        btnok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnokMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Login");

        txtpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpassKeyPressed(evt);
            }
        });

        btndangky.setText("Đăng ký");
        btndangky.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndangkyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtid)
                        .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnok, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btndangky)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnok)
                    .addComponent(btndangky))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnokMouseClicked
        String pas="";
            char[] s = txtpass.getPassword();
        for(int i=0;i<s.length;i++){
        pas=pas + Character.toString(s[i]);
        }
    
        System.out.println(pas);
        goiform gf=new goiform(txtid.getText(),pas);
        gf.start();
  
              
    }//GEN-LAST:event_btnokMouseClicked

    private void txtpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpassKeyPressed
       
        
        
            if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                  
            String pas="";
            char[] s = txtpass.getPassword();
                for(int i=0;i<s.length;i++){
                     pas=pas + Character.toString(s[i]);
                }
    
            
             goiform gf=new goiform(txtid.getText(),pas);
             gf.start();
        }     
    }//GEN-LAST:event_txtpassKeyPressed

    private void btndangkyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndangkyMouseClicked
    
              
        
        
        
        
        
    }//GEN-LAST:event_btndangkyMouseClicked


    
    
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
            java.util.logging.Logger.getLogger(LoginChatclientvs_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginChatclientvs_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginChatclientvs_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginChatclientvs_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginChatclientvs_2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndangky;
    private javax.swing.JButton btnok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtid;
    private javax.swing.JPasswordField txtpass;
    // End of variables declaration//GEN-END:variables

}
