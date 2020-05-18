/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectchatting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import jdk.nashorn.internal.runtime.regexp.joni.Option;
import org.w3c.dom.css.RGBColor;

/**
 *
 * @author LUUQUAN-PC
 */
public class Mainchat extends javax.swing.JFrame {

    /**
     * Creates new form Mainchat
     */
    public Mainchat() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        txtdate.setBackground(Color.white);
      //  txtdisplay.setBorder(Border.EMPTY);
        btnmenu.setBackground(new Color(0,0,0,0));
       txtdisplay.setEnabled(false);
       txtmessage1.setEnabled(false);
    }
    
    
    
     public Mainchat(String namenhan,String pass,Socket socketlogin){
        this.namenhan=namenhan;
        // Lấy socket login
        this.socketlogin=socketlogin;
        this.pass=pass;
         initComponents();
       txtdisplay.setVisible(false);
       txtmessage1.setVisible(false);
     //  btnout.setVisible(false);
       // txtdisplay.setVisible(false);
      //  txtsend.setVisible(false);
      //  btnsend.setVisible(false);
      //  txtdisplay.setEnabled(false);
    }
    
    
    
    String namenhan="";
    String pass="";
      //Socket mới
    
    Socket socket;
     //Socket lúc login
    Socket socketlogin;
    private ReadThread re;
    private WriteThread rw;
    private PrintWriter writer;
    private String display="";
    private String tennguoinhan="";
    private DefaultListModel<String> modellistonl= new DefaultListModel<>();
    private String namegui="";
    
    public class client {
    private String hostname;
    private int port;
 //   private String username;
    private String userName;
    
    // Lấy thông tin từ thông báo 
  
    public client(String hostname,int port){
        this.hostname=hostname;
        this.port=port;

    }
    public void execute(){
        try {
            socket = new Socket(hostname,port);
            rw = new WriteThread(socket, this);
            rw.start();
            
            re =new  ReadThread(socket, this);
            re.start();
            
            
        } catch (Exception e) {
        }

    }
    
     void setUserName(String userName) {
        this.userName = userName;
    }
 
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
    public String ingetname(){
        return client.getUserName();
    }
   @Override
    public void run() {
          
        client.setUserName(namenhan);
        writer.println(namenhan);
       
       // writer.println(namegui);
    }
    
}
     
 private class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private client client;
    private String nguoinhan;
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
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
  
 
    public void run() {
       
            
            try {
                while (true) {
                String response = reader.readLine();
                
                // Kiểm tra trạng thái chờ bận
                 String[] output = response.split("-");
   
                 if (output[0].equals("Usernhan")){
                     
                          int aa=laythongtintuthongbao(output[1]);

                                    if (aa==JOptionPane.YES_OPTION) 
                                            {
                                           writer.println("Usernhantra " + output[1]);                                
                                           txtnamenhan.setText("Gửi đến: " + output[1]);
                                           listlist.setVisible(false);
                                         //  listlist.set
                                           txtlinechat.setVisible(false);
                                           txtnamechat.setVisible(false);
                                           txtbgleft.setVisible(false);
                                           //listlist.set
                                           
                                         //  listlist.setBorder(new listliss);
                                          //  listlist.setEnabled(false);
                                          jScrollPane1.setVisible(false);
                                          
                                          txtmessage1.setVisible(true);
                                          txtdisplay.setVisible(true);
                                          btnout.setVisible(true);
//                                          txtdisplay.setSize(730, 530);
//                                          jScrollPane3.setSize(730, 530);
//                                          jScrollPane3.setLocation(210, 140);
                                            }
                                  else {

                                   if(aa==JOptionPane.NO_OPTION)
                                    dongconnect();
                                  }  
                        
                     
                 }
                 
                 
                 if(output[0].equals("Ketqua")){
                     if(output[1].equals("true")){
                                
                            //  while(namegui=="" && namenhan2!=""){  
                             //  txtnamenhan.setText("Gửi đến: " + namenhan2);
                              //}
                            //  txtnamenhan.setText("Gửi đến:" + namegui);

                              }else{

                                  baoban();
                              }
                     
                     
                 }
                 
                 // Lấy danh sách online và off
                if(output[0].equals("Online") || output[0].equals("OnlineClose")){
                        Online onllist = new Online(response);
                         onllist.start();
                   
                }
                        else{
                                display = display +"\n"+ response;
                                txtdisplay.setText(display);

                        }
                }  
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
             //   break;
            
        }
    }
} 
 
 
 
public int laythongtintuthongbao(String nameden){
  // int  thongbaoa=
   return  JOptionPane.showConfirmDialog(this, "Chat with: " + nameden);
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
//public void mointerface(){
//        txtdisplay.setVisible(true);
//        txtsend.setVisible(true);
//        btnsend.setVisible(true);
//        txtdisplay.setEnabled(true);
//        txtlistonl.setVisible(false);
//       
//}

//Báo người dùng bận
void baoban(){
    JOptionPane.showMessageDialog(this, "Người dùng bận !!!");
}
void anform(){
    this.dispose();
}

 void dongconnect(){   
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnmenu = new javax.swing.JLabel();
        btnout = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtminj = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listlist = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtnamenhan = new javax.swing.JLabel();
        txtmessage1 = new javax.swing.JTextField();
        txtdate = new cambodia.raven.DateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnamechat = new javax.swing.JLabel();
        txtlinechat = new javax.swing.JLabel();
        txtbgleft = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdisplay = new javax.swing.JTextArea();
        btnlogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setUndecorated(true);
        setOpacity(0.0F);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnmenu.setBackground(new java.awt.Color(255, 255, 255));
        btnmenu.setForeground(new java.awt.Color(255, 255, 255));
        btnmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_menu_32px_1.png"))); // NOI18N
        btnmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmenuMouseExited(evt);
            }
        });
        getContentPane().add(btnmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 40, 40));

        btnout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_exit_26px_1.png"))); // NOI18N
        btnout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnoutMouseClicked(evt);
            }
        });
        getContentPane().add(btnout, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 100, -1, 30));

        jLabel10.setFont(new java.awt.Font("SVN-Franko", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(47, 138, 199));
        jLabel10.setText("Avaliable");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 40, 90, -1));

        txtminj.setFont(new java.awt.Font("SVN-Franko", 0, 20)); // NOI18N
        txtminj.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtminj, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 120, -1));

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
        listlist.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 250, 550));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bg.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -8, 1365, 110));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btnghim.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 695, 20, 40));

        txtnamenhan.setFont(new java.awt.Font("SVN-Franko", 0, 20)); // NOI18N
        txtnamenhan.setForeground(new java.awt.Color(52, 152, 219));
        txtnamenhan.setText("Gửi đến:");
        getContentPane().add(txtnamenhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 93, 200, -1));

        txtmessage1.setFont(new java.awt.Font("SVN-Arial", 0, 16)); // NOI18N
        txtmessage1.setText("Nhập vào để chat...");
        txtmessage1.setBorder(null);
        txtmessage1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmessage1FocusGained(evt);
            }
        });
        txtmessage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtmessage1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtmessage1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtmessage1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtmessage1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtmessage1MouseReleased(evt);
            }
        });
        txtmessage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmessage1ActionPerformed(evt);
            }
        });
        txtmessage1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmessage1KeyPressed(evt);
            }
        });
        getContentPane().add(txtmessage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 700, 460, 30));
        getContentPane().add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 170, 270, 230));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgright.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 140, -1, 530));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btnsend.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 690, -1, 50));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgchat.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 690, 570, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgleft.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 94, 768));

        txtnamechat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Chat .png"))); // NOI18N
        getContentPane().add(txtnamechat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        txtlinechat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/linechat.png"))); // NOI18N
        getContentPane().add(txtlinechat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 240, 30));

        txtbgleft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgmenber.png"))); // NOI18N
        txtbgleft.setText("jLabel4");
        getContentPane().add(txtbgleft, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 316, 698));

        txtdisplay.setColumns(20);
        txtdisplay.setFont(new java.awt.Font("SVN-Arial", 0, 20)); // NOI18N
        txtdisplay.setRows(5);
        txtdisplay.setBorder(null);
        txtdisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtdisplay.setMargin(new java.awt.Insets(10, 30, 10, 30));
        jScrollPane3.setViewportView(txtdisplay);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 540, 530));

        btnlogout.setBackground(new java.awt.Color(204, 102, 0));
        btnlogout.setForeground(new java.awt.Color(153, 0, 204));
        btnlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Background.png"))); // NOI18N
        btnlogout.setText("jLabel1");
        getContentPane().add(btnlogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 768));

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

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
        
        
        
         txtminj.setText(namenhan);
         try {
        client a = new client("localhost",2313);
            a.execute();
             
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server chua duoc khoi tao !!!");
        }
        
        
        
        
    }//GEN-LAST:event_formWindowOpened

    private void listlistMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listlistMouseMoved
        
        
        
    //    jlisst.set
        
        
    }//GEN-LAST:event_listlistMouseMoved

    private void listlistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listlistMouseEntered
       
     //  jList1.setBackground(Color.yellow);
  //     jList1.
        
    }//GEN-LAST:event_listlistMouseEntered

    private void listlistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listlistMouseExited
       
        
     //   jList1.setBackground(Color.white);
        
    }//GEN-LAST:event_listlistMouseExited

    private void btnmenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmenuMouseEntered
        
     //   btnmenu.setBackground(Color.yellow);
        
    }//GEN-LAST:event_btnmenuMouseEntered

    private void btnmenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmenuMouseExited
    //   btnmenu.setBackground(Color.white);
    }//GEN-LAST:event_btnmenuMouseExited

    private void listlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listlistMouseClicked
        // TODO add your handling code here:
       // Dimension value = listlist.getSize();
       
       
   //    while( modellistonl.getSize()!=0){
        
       
       
         namegui=listlist.getSelectedValue();
        int a = JOptionPane.showConfirmDialog(this, "Gửi yêu cầu đến: "+ namegui);
      
        
        if (a==JOptionPane.YES_OPTION) 
        {
        writer.println("Usernhan " + namegui);
       //JOptionPane.showMessageDialog(this, "Đã gửi yêu cầu kết nối");
       // new Singlechatform(namegui, socket).setVisible(true);
        }
        else {
                
           if(a==JOptionPane.NO_OPTION)
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }  
       //}
       
     //  JOptionPane.showMessageDialog(this,"Không có ai đang truy cập ở đây !!!");
       
        
        
    }//GEN-LAST:event_listlistMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
         String text=txtmessage1.getText();
            writer.println(text);
            txtmessage1.setText("");
            if(text.equals("bye")){
               rw.close();
               this.dispose();
                        
            }
              display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
           if(rw.ingetname()!=null){
          txtdisplay.setText(display);
           }
        
        
    }//GEN-LAST:event_jLabel8MouseClicked

    private void txtmessage1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmessage1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessage1FocusGained

    private void txtmessage1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessage1MouseClicked
       txtmessage1.setText("");
    }//GEN-LAST:event_txtmessage1MouseClicked

    private void txtmessage1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessage1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessage1MouseEntered

    private void txtmessage1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessage1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessage1MouseExited

    private void txtmessage1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessage1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessage1MousePressed

    private void txtmessage1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmessage1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessage1MouseReleased

    private void txtmessage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmessage1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmessage1ActionPerformed

    private void txtmessage1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmessage1KeyPressed
     
             if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                 
                 
            String text=txtmessage1.getText();
            writer.println(text);
            txtmessage1.setText("");
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
          
        
    }//GEN-LAST:event_txtmessage1KeyPressed

    private void btnoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnoutMouseClicked
     
        
        int logout= JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thoát cuộc trò chuyện");
        if(logout==JOptionPane.YES_OPTION){
        
            try {
                writer.println("bye");
                re.close();
              //  socket.close();
            } catch (Exception e) {
            }

               //txtnamenhan.setText("");
           //    JOptionPane.showMessageDialog(this, this.socketlogin);
               new Menuafter(this.namenhan,this.pass,this.socketlogin).setVisible(true);
               this.dispose();
            
        }else 
            if(logout==JOptionPane.NO_OPTION){
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
        
        
        
       
    }//GEN-LAST:event_btnoutMouseClicked
// Luồng đê tạo text area chat
        
 class threadarea extends JFrame implements Runnable{
  //   static JTextArea jt;
     String namethreadarea;
     JTextArea namethreadare;
     public threadarea(String tenthread)
     { 
         
         this.namethreadarea=tenthread;
         
         
     }
     @Override
     public void run(){
            namethreadare = new JTextArea();
          JScrollPane sp = new JScrollPane( namethreadare);
      //   ta.size(300, 0x91);
        // txtdisplaythread.setBounds(340, 145, WIDTH, WIDTH);
         getContentPane().add(sp, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 540, 530));   
         namethreadare.setColumns(20);
         namethreadare.setFont(new java.awt.Font("SVN-Arial", 0, 20)); // NOI18N
         namethreadare.setRows(5);
         namethreadare.setBorder(null);
         namethreadare.setMargin(new java.awt.Insets(10, 10, 10, 10));
         namethreadare.setBackground(Color.yellow);
         sp.setViewportView( namethreadare);
         
     }
     void displayarea(){
         
          namethreadare.setVisible(true);
         
     }
     
     
     void notdisplayarea(){
         
          namethreadare.setVisible(false);
         
         
     }
     
     
     
     
 }   
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(Mainchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainchat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnlogout;
    private javax.swing.JLabel btnmenu;
    private javax.swing.JLabel btnout;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listlist;
    private javax.swing.JLabel txtbgleft;
    private cambodia.raven.DateChooser txtdate;
    private javax.swing.JTextArea txtdisplay;
    private javax.swing.JLabel txtlinechat;
    private javax.swing.JTextField txtmessage1;
    private javax.swing.JLabel txtminj;
    private javax.swing.JLabel txtnamechat;
    private javax.swing.JLabel txtnamenhan;
    // End of variables declaration//GEN-END:variables
}
