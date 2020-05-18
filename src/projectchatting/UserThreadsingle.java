/*ss
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectchatting;

import java.io.BufferedReader;
import java.io.IOException;
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
public class UserThreadsingle extends Thread {
    private Socket sockett;
    private ChatServer serverr;
    private PrintWriter writerr;
  //  private String tennguoinhan ="";

    public UserThreadsingle(Socket socket, ChatServer server) {
        this.sockett = socket;
        this.serverr = server;
    }
 
    public void run() {
        try {
            //Thiet lap bien Buffee de nhan data
            InputStream input = sockett.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            //Thiet lap bien writer de truyen du lieu di    
            OutputStream output = sockett.getOutputStream();
            writerr = new PrintWriter(output, true);
 
            printUsersingle(this);
 
            // Ghi ten tu nguoi dung va them name Client vao danh sach Ten cua server 
            String userName = reader.readLine();       
            
            
            serverr.addUserNamesinggle(userName);
            
            //Thêm để test tra cứu
            serverr.addtracuu(this, userName);
            
            
            String serverMessage = "Online-New user connected: " + userName;
            
            
            
        //  serverr.broadcastsigle(serverMessage, usernhan);               
            serverr.broadcastsinglee(serverMessage, this);           
            
          
            String clientMessage="";
             String tennguoinhan ="";
             do {
                 
                
                 
                clientMessage = reader.readLine();
              
                    
               String[] tennhan=clientMessage.split(" ");
               if(tennhan[0].equals("Usernhan") || tennhan[0].equals("Usernhantra")){
                   
                        if(tennguoinhan.equals("")){
                                    if(serverr.ktchoban(tennhan[1])==false){
                                                serverr.broadcastsigle("Ketqua-false", userName);
                                            }
                                                else{
                                                            if(serverr.ktchoban(tennhan[1])==true){

                                                                    tennguoinhan=tennhan[1];
                                                                   //serverr.settennhan(tennhan[1]);
                                                                    //Gửi về máy yêu cầu đã gửi yêu cầu
                                                                    serverr.broadcastsigle("Ketqua-true", userName);
                                                                    //Thêm vào danh sách bận
                                                                    serverr.addchoban(tennhan[1]);

                                                                //    tennguoinhan=tennhan[1];    
                                                                     serverr.broadcastsigle("Usernhan-"+userName,tennguoinhan);
                                                                   }
                                                }
                                    }
               }
               else{  
               
             
                  
                   if( tennguoinhan!=""){
                                            try {
                                            String mess="";

                                            mess = "[" + userName + "]: " + clientMessage;      

                                            serverr.broadcastsigle(mess,tennguoinhan);  
                                           } catch (Exception e) {
                                           }
                   
                                        }
  
               }
            } while (!clientMessage.equals("bye"));
               
             //Gửi thông điệp kết thúc
             serverr.broadcastsigle(userName+" đã thoát",  tennguoinhan);
               
            // Khi người dùng say "Bye" sẽ xoá tên người dùng ra khỏi list
            
            serverr.removeUsersinggle(userName, this);
      
            //Xoá khỏi tra cứu
            serverr.removetracuu(this, userName);
         
            //Xoá khỏi chờ bận
           serverr.removechoban(tennguoinhan);
            try {
                sockett.close();
            } catch (Exception e) {
            }
            
            // Gui thong diep cho cac nguoi dung khác client đó đã out
            serverMessage ="OnlineClose- "+ userName +" has quitted.";
            serverr.broadcastsinglee(serverMessage, this);
                
        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsersingle(UserThreadsingle user) {
        if (serverr.hasUsersingle()) {
            serverr.getUserNamesinggle();
            for(String element:serverr.getUserNamesinggle()){
            writerr.println("Online-Connected users connection: " + element);    
            }
            
        } else {
           // writer.println("No other users connected");
        }
    }
 
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writerr.println(message);
    }
}
