/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectchatting;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author LUUQUAN-PC
 */
public class ThreadsingleServer extends Thread{
    int port;
    Socket socket;
    BufferedReader reader;

    public ThreadsingleServer(int port){
        this.port=port;

    }
    public  void run(){
      //  while (true) {            
             taouserThread(2313);
     //   }
       
    }
     // Tạo một userThread để giao tiếp
    // Khi da dang nhap thanh cổng tạo socket riêng để các client kết nối với nhau
   public void taouserThread(int portsingle){
                //ServerSocket a= new ServerSocket("localhost",2232);
                try(ServerSocket serverSocket = new ServerSocket(portsingle)) {
                    System.out.println("Cong "+portsingle+" đã được mở !!!....");
                    socket = serverSocket.accept();
                    
                    
                          System.out.println("New user connected");
            
               String chude="";
               
                  try {
                    chude = reader.readLine();
                 } catch (Exception e) {
                }
               
               
               System.out.println(chude);
               
               if(chude.equals("Chatclass")){
                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();   
               }
               if(chude.equals("Chatsingle")){
                   
               UserThreadsingle newUser = new UserThreadsingle(socket, this);
                userThreadssigle.add(newUser);
                   System.out.println("UserThreadsin:" + newUser);
                newUser.start();
                   
               }
                
        } catch (Exception e) {
        }
        
    }
   
//      public void taouserThread(Socket socket){
//                //ServerSocket a= new ServerSocket("localhost",2232);
//        
//        
//                System.out.println("New user connected");
//            
//               String chude="";
//               
//                  try {
//                    chude = reader.readLine();
//                 } catch (Exception e) {
//                }
//               
//               
//               System.out.println(chude);
//               
//               if(chude.equals("Chatclass")){
//                UserThread newUser = new UserThread(socket, this);
//                userThreads.add(newUser);
//                newUser.start();   
//               }
//               if(chude.equals("Chatsingle")){
//                   
//               UserThreadsingle newUser = new UserThreadsingle(socket, this);
//                userThreadssigle.add(newUser);
//                   System.out.println("UserThreadsin:" + newUser);
//                newUser.start();
//                   
//               }

     
 
}
