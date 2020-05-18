/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectchatting;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author LUUQUAN-PC
 */
public class mainchatclass extends javax.swing.JFrame {

    /**
     * Creates new form mainchatclass
     */
    public mainchatclass() {
        initComponents();
        
    }
    private String name="";
    private String pass="";
    private Socket socket;
    private String display="";
    public mainchatclass(String name, String pass, Socket socket) {
        initComponents();
        this.name=name;
        this.pass=pass;
     //   this.socket=socket;
    }
    ReadThread re;
    WriteThread rw;
      private DefaultListModel<String> modellistonl= new DefaultListModel<>();
    private PrintWriter writer;
    private List<String> listonl = new ArrayList<String>();
    
    
     public class client{
    private String hostname;
    private int port;
    private String userName;
     
    public client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
      
    }
   
    
    public void execute() {
        try {
           socket = new Socket(hostname, port);
            System.out.println("Connected to the chat server");
      
            rw = new WriteThread(socket, this);
            rw.start();
            re =new  ReadThread(socket, this);
            re.start();
      
           
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            
            khthayserver();
            System.exit(0);
            System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
    //Thiết lập tên của client này
    void setUserName(String userName) {
        this.userName = userName;
    }
    //Lấy tên của client này 
    String getUserName() {
        return this.userName;
    }
  
    
    
    }
     
     private class WriteThread extends Thread {
   // private PrintWriter writer;
    private Socket socket;
    private client client;
 
    public WriteThread(Socket socket, client client) {
        this.socket = socket;
        this.client = client;
 
        try {
            OutputStream output = socket.getOutputStream();
            //Dùng writer để gửi thông điệp
            //Sử dụng ở Btn
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void close(){
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
    
    // Hàm lấy tên của client này
    public String ingetname(){
        return client.getUserName();
    }
   @Override
    public void run() {
        // Gọi hàm và thiết lập tên cho client này bên trên
        client.setUserName(name);
        // Gửi cho server biết tên của mình
        writer.println(name);
 
    }
    
}
     
     
    private class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private client client;
 
    public ReadThread(Socket socket,client client) {
        this.socket = socket;
        this.client = client;
 
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void close(){
        try {
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(mainchatclass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void run() {
       // txtonl.setText("Nobody !!!");
        while (true) {
            try {
               
                String response = reader.readLine();
             
                // Phân tích dữ liệu đầu vào 
                 
                 String[] output = response.split("-");

                 //New single
                 if(output[0].equals("Online") || output[0].equals("OnlineClose")){
                    Online onllist = new Online(response);
                    onllist.start();
                   
                }
                else{
                    
                    
        
                    
                System.out.println("\n" + response);
                display = display +"\n"+ response;
                txtdisplay.setText(display);
      
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
     
    private class Online extends Thread {

      private String clientonl;
       
      public Online(String clientonl){
                this.clientonl=clientonl;
            }
            @Override
            public void run(){   
                ktinput();
            }

          public void ktinput(){
             String[] output = clientonl.split("-");
               if(output[0].equals("Online")){
                   String[] output2 = clientonl.split(" ");
                   addlistonl(output2[3]);

               }
                if(output[0].equals("OnlineClose")){
                       String[] output3 = clientonl.split(" ");
                        removelistonl(output3[1]);
               }

        }
    
}  

    public void addlistonl(String listdetail){
           modellistonl.addElement(listdetail);
           listlist.setModel(modellistonl);

        }
    public void removelistonl(String listdetail){
        modellistonl.removeElement(listdetail);
        listlist.setModel(modellistonl);
    }
    
    
      
       void anform(){
           this.dispose();
       }

        void dongconnect(){   
           this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        void khthayserver(){
            JOptionPane.showMessageDialog(this, "Không tìm thấy server");
        }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        txtmessage = new javax.swing.JTextField();
        txtmyname = new javax.swing.JLabel();
        txtnamechat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listlist = new javax.swing.JList<>();
        btnghim = new javax.swing.JLabel();
        btnsned = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnout = new javax.swing.JLabel();
        txtnamenhan1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtdisplay = new javax.swing.JTextArea();
        txtlinechat = new javax.swing.JLabel();
        txtbgleft = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdate = new cambodia.raven.DateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.0F);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("SVN-Franko", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(47, 138, 199));
        jLabel12.setText("Avaliable");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 40, 90, -1));

        txtmessage.setFont(new java.awt.Font("SVN-Arial", 0, 16)); // NOI18N
        txtmessage.setText("Nhập vào để chat...");
        txtmessage.setBorder(null);
        txtmessage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmessageFocusGained(evt);
            }
        });
        txtmessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtmessageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtmessageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtmessageMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtmessageMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtmessageMouseReleased(evt);
            }
        });
        txtmessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmessageActionPerformed(evt);
            }
        });
        txtmessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmessageKeyPressed(evt);
            }
        });
        getContentPane().add(txtmessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 700, 460, 30));

        txtmyname.setFont(new java.awt.Font("SVN-Franko", 0, 20)); // NOI18N
        txtmyname.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtmyname, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 120, -1));

        txtnamechat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Chat .png"))); // NOI18N
        getContentPane().add(txtnamechat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        listlist.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        listlist.setForeground(new java.awt.Color(52, 152, 219));
        listlist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listlist.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        listlist.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                listlistMouseMoved(evt);
            }
        });
        listlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listlistMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listlistMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listlistMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(listlist);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 250, 550));

        btnghim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btnghim.png"))); // NOI18N
        getContentPane().add(btnghim, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 695, 20, 40));

        btnsned.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btnsend.png"))); // NOI18N
        btnsned.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsnedMouseClicked(evt);
            }
        });
        getContentPane().add(btnsned, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 690, -1, 50));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgchat.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 690, 570, 50));

        btnout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_exit_26px_1.png"))); // NOI18N
        btnout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnoutMouseClicked(evt);
            }
        });
        getContentPane().add(btnout, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 100, -1, 30));

        txtnamenhan1.setFont(new java.awt.Font("SVN-Franko", 0, 20)); // NOI18N
        txtnamenhan1.setForeground(new java.awt.Color(52, 152, 219));
        txtnamenhan1.setText("Group chat");
        getContentPane().add(txtnamenhan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 93, 120, -1));

        txtdisplay.setColumns(20);
        txtdisplay.setFont(new java.awt.Font("SVN-Arial", 0, 20)); // NOI18N
        txtdisplay.setRows(5);
        txtdisplay.setBorder(null);
        txtdisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtdisplay.setMargin(new java.awt.Insets(10, 30, 10, 30));
        jScrollPane4.setViewportView(txtdisplay);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 540, 530));

        txtlinechat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/linechat.png"))); // NOI18N
        getContentPane().add(txtlinechat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 240, 30));

        txtbgleft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgmenber.png"))); // NOI18N
        txtbgleft.setText("jLabel4");
        getContentPane().add(txtbgleft, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 316, 698));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bg.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -8, 1365, 110));
        getContentPane().add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 170, 270, 230));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgleft.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 94, 768));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgright.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 140, -1, 530));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Background.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listlistMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listlistMouseMoved

        //    jlisst.set

    }//GEN-LAST:event_listlistMouseMoved

    private void listlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listlistMouseClicked
       // Click listt

    }//GEN-LAST:event_listlistMouseClicked

    private void listlistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listlistMouseEntered

        //  jList1.setBackground(Color.yellow);
        //     jList1.

    }//GEN-LAST:event_listlistMouseEntered

    private void listlistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listlistMouseExited

        //   jList1.setBackground(Color.white);

    }//GEN-LAST:event_listlistMouseExited

    private void txtmessageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmessageFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessageFocusGained

    private void txtmessageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessageMouseClicked
        txtmessage.setText("");
    }//GEN-LAST:event_txtmessageMouseClicked

    private void txtmessageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessageMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessageMouseEntered

    private void txtmessageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessageMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessageMouseExited

    private void txtmessageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessageMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessageMousePressed

    private void txtmessageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessageMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessageMouseReleased

    private void txtmessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessageActionPerformed

    private void txtmessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmessageKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER){

            String text=txtmessage.getText();
            writer.println(text);
            txtmessage.setText("");
            if(text.equals("bye")){
                try {
                    rw.close();
                    this.dispose();
                } catch (Exception e) {
                }

            }
            display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
            if(rw.ingetname()!=null){
                txtdisplay.setText(display);
            }

        }

    }//GEN-LAST:event_txtmessageKeyPressed

    private void btnsnedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsnedMouseClicked
        // TODO add your handling code here:
        String text=txtmessage.getText();
        writer.println(text);
        txtmessage.setText("");
        if(text.equals("bye")){
            rw.close();
            this.dispose();

        }
        display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
        if(rw.ingetname()!=null){
            txtdisplay.setText(display);
        }

    }//GEN-LAST:event_btnsnedMouseClicked

    private void btnoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnoutMouseClicked
        
        int logout= JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thoát cuộc trò chuyện");
        if(logout==JOptionPane.YES_OPTION){
        
            try {
                writer.println("bye");
                re.close();
            } catch (Exception e) {
            }
               new Menuafter(this.name,this.pass,this.socket).setVisible(true);
               this.dispose();
            
        }else 
            if(logout==JOptionPane.NO_OPTION){
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
        
        
        
        
    }//GEN-LAST:event_btnoutMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       
          for (double i=0.0;i<=1.0;i=i+1.0){
            String val = i+ "";
            float f= Float.parseFloat(val);
            this.setOpacity(f);
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
            
        }
            txtmyname.setText(name);
        try {
         client a = new client("localhost",2314);
            a.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server chua duoc khoi tao !!!");
        }
       
       
        
        
        
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
            java.util.logging.Logger.getLogger(mainchatclass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainchatclass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainchatclass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainchatclass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainchatclass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnghim;
    private javax.swing.JLabel btnout;
    private javax.swing.JLabel btnsned;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listlist;
    private javax.swing.JLabel txtbgleft;
    private cambodia.raven.DateChooser txtdate;
    private javax.swing.JTextArea txtdisplay;
    private javax.swing.JLabel txtlinechat;
    private javax.swing.JTextField txtmessage;
    private javax.swing.JLabel txtmyname;
    private javax.swing.JLabel txtnamechat;
    private javax.swing.JLabel txtnamenhan1;
    // End of variables declaration//GEN-END:variables
}
