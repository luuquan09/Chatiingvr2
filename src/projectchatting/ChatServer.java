/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectchatting;
//import com.mysql.jdbc.Connection;
import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.soap.Detail;
 
/**
 * This is the chat server program.
 * Press Ctrl + C to terminate the program.
 *
 * @author www.codejava.net
 */
public class ChatServer {
    public static String name;
    boolean right = false;
    private int port;
    
    private Socket socket;
    
  //  private ServerSocket serverSocket=null;
    
    private Set<String> userNames = new HashSet<>();
   
    private Set<String> userNamesingle = new HashSet<>();
    
    private Set<UserThread> userThreads = new HashSet<>();
 
    private Set<UserThreadsingle> userThreadssigle = new HashSet<>();
    // chuỗi để tra cứu UserThread từ tên
    private HashMap<UserThreadsingle, String> tracuu = new HashMap<UserThreadsingle,String>();
    
    // Chuỗi chờ bận:
    
    private Set<String> choban = new HashSet<>();
    
    // Danh sach login
    
     private Set<String> listlogin = new HashSet<>();
    
    private PrintWriter writer ;
    
    BufferedReader reader;
    
    public ChatServer(int port) {
        this.port = port;
    }
   

    public class mysql extends Thread{
        String id="";
        String pass="";
        public mysql(String id, String pass){
            this.id=id;
            this.pass=pass;
            
            
        }
        @Override
        public void run(){
              try {
                    //connection to database
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datachating","root","");

                //   Connection myConn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/databaseluuquan","luuquan","luuquantn");
                  
                    //create statement 
                     Statement myStmt = myConn.createStatement();

                  //    String truyvan = "SELECT usename,password FROM chatting WHERE usename='"+this.id+"' and password='"+this.pass+"'";
                  
                  String truyvan = "SELECT id,name FROM userchat WHERE id='"+this.id+"' and name='"+this.pass+"'";
                  
                      System.out.println("___"+this.id+this.pass);
                     ResultSet myRs = myStmt.executeQuery(truyvan);
                     
                        while (myRs.next()) {
                               
                          
                            
                                    writer.println(this.id);
                                    
                                    listlogin.add(this.id);
                                
                                                           
                             taouserThreadsingle tuts = new taouserThreadsingle(2313);
                            // tuts.execute();
                               tuts.start();
                               // }
                             
                        }
       
                           writer.println("Fasle");
                      
                 }
            catch (Exception exc) {
            exc.printStackTrace();
            }
}
        
        
        
    }
    
     // Luồng để nhận từng id và tài khoản để kiểm tra
    public class trakq extends Thread {
        Socket socket;
        public trakq(Socket sc ){
            this.socket=sc;
        }
        @Override
        public void run(){
            try {
              // Hàm nhận và xuất thông điệp  
             InputStream input = socket.getInputStream();
             reader = new BufferedReader(new InputStreamReader(input));   
             OutputStream output = socket.getOutputStream();
              writer = new PrintWriter(output, true);
            // Nhận thông điệp Tên đăng nhập và mật khẩu gửi đến 
            // Do Tên đăng nhập gửi cùng lúc nên phải tạo 2 biến để nhận 
               String username= reader.readLine();
               String pass = reader.readLine();
             //   System.out.println(username + pass);
            // Gọi luồng để kiểm tra từng Tên đăng nhập và pass
             
            //Here
            
            if(listlogin.size()!=0){
                    for(String element:listlogin){
                        if(!element.equals(username)){
                                   mysql my  = new mysql(username, pass);
                                    my.start();
                        }
                        else{
                            writer.println("Fasle");
                        }
                    }
                }
//                mysql my  = new mysql(username, pass);
//                my.start();
//        
                     
            } catch (Exception e) {
            }
            
            
            
        }
        
        
    }
    // Tạo một userThread để giao tiếp
    // Khi da dang nhap thanh cổng tạo socket riêng để các client kết nối với nhau
   public void taouserThread(int portsingle){
                //ServerSocket a= new ServerSocket("localhost",2232);
          
        
                System.out.println("New user connected");
            
               String chude="";
               
                  try {
                    chude= reader.readLine();
                 } catch (Exception e) {
                }
               
               
               System.out.println(chude);
               
               if(chude.equals("Chatclass")){

                            try(ServerSocket serverSocket = new ServerSocket(portsingle+1)) {
                            System.out.println("Cong Chat class"+portsingle+1+" đã được mở !!!....");                  
                            socket = serverSocket.accept();    

                               } catch (Exception e) {
                               }      

                                    UserThread newUser = new UserThread(socket, this);
                                    userThreads.add(newUser);
                                    newUser.start();   
                                    System.out.println("Đã tạo được class: "+ newUser);
               }
               if(chude.equals("Chatsingle")){
                   
                            try(ServerSocket serverSocket = new ServerSocket(portsingle)) {
                            System.out.println("Cong chat single"+portsingle+" đã được mở !!!....");                  
                             socket = serverSocket.accept();    



                                } catch (Exception e) {
                                }   



                           UserThreadsingle newUser = new UserThreadsingle(socket, this);
                            userThreadssigle.add(newUser);
                               System.out.println("UserThreadsin:" + newUser);
                            newUser.start();
                   
               }
        
    }
    
    // Neww 17/05
    public class taouserThreadsingle extends Thread{
        int port=0;
        public taouserThreadsingle(int port){
            this.port=port;
        }
        public void run(){
          //   try (taouserThreadsingle tt = new  taouserThreadsingle(port)){
            taouserThread(port);
             }
        }
    

    
 
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            
            System.out.println("Chat Server is listening on port " + port);
                                
           while (true) {
                socket = serverSocket.accept();
                trakq kkq = new trakq(socket);
                kkq.start();

                        
                  }
    
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        int port = 8778;
        ChatServer server = new ChatServer(port);
        server.execute();
    }
 
    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
  

    /**
     * Stores username of the newly connected client.
     */
    void addUserName(String userName) {
        userNames.add(userName);
    }

 
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    // Khi client da ngung ket noi kiem tra user name dung thi removed tra ve true
    // Neu true thi xoa cai usetthreads ra khoi list threads
    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
 
    Set<String> getUserNames() {
        return this.userNames;
    
    }

    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
    
    
    
    
    
    
    
    
        // SINGLE UserThreadsingle 
    
    public int tracuuUserThread(String name){
        
        int numberindex=0;
        int lay=0;
        
       for(String element:userNamesingle){
              
           if(element.equals(name)){
               //return numberindex;
              lay=numberindex;
                }
           numberindex++;  
            
       }
     //   System.out.println(numberindex);
        return lay;
    }
    public UserThreadsingle getuserThread(String name){
        int dem=0;
        UserThreadsingle laytum=null;
     
        for(UserThreadsingle element: userThreadssigle){
            if (dem==tracuuUserThread(name)){
               laytum=element;
            }            
            dem++;
            
        }
        System.out.println("Index là: "+tracuuUserThread(name));
        return laytum;
        
         
    }
      // Send single 
    void broadcastsigle(String message, String tennhan){
        System.out.println("\n"+" Ten nhan la: "+ tennhan);
            System.out.println("UserThreadsingle là: " + tracuusingleThread(tennhan));
           
            // Nếu tracuusingle Thread trả về null thì thông điệp sẻ không được gửi 
            if(tracuusingleThread(tennhan)!=null){
           tracuusingleThread(tennhan).sendMessage(message);
           System.out.println("Thong diep tới:" + message);
            }
    }

    
     void broadcastsinglee(String message, UserThreadsingle excludeUser) {
        for (UserThreadsingle aUser : userThreadssigle) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
    
       void addUserNamesinggle(String userName) {
           System.out.println(userName);   
        userNamesingle.add(userName);
    }
        
        
     void removeUsersinggle(String userName, UserThreadsingle aUser) {
        boolean removed = userNamesingle.remove(userName);
        if (removed) {
            userThreadssigle.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
    
 
    Set<String> getUserNamesinggle() {
        return this.userNamesingle;
    
    }

    boolean hasUsersingle() {
        return !this.userNamesingle.isEmpty();
    }
    
    
    //Thêm vào để tra cứu UserThread
    
    void addtracuu(UserThreadsingle user,String name){
        tracuu.put(user, name);
        System.out.println("----------------"+tracuu);
    }
    
    HashMap<UserThreadsingle, String> gettracuu(){
        return this.tracuu;
    }
    UserThreadsingle tracuusingleThread(String name){
      //  UserThreadsingle uts=null;
      // Tra cứu trong mảng 2 chiều của tên người nhận và userThreadsingle 
      // Nếu tên người nhận có userThreadsinggle thì trả về userThreadsingle
      // Ngược lại trả về null
       UserThreadsingle uts=null; 
        for(UserThreadsingle i: tracuu.keySet()){
            if (tracuu.get(i).equals(name)){
               uts = i;
          
            }
          
        }
     return uts;
      
    }
    
    void removetracuu(UserThreadsingle uts, String name){
        tracuu.remove(uts, name);
    }
    
    //Thêm vào chuỗi chờ bận
    void addchoban(String namenhan){
        choban.add(namenhan);        
    }
    // Xoá khỏi chuỗi chờ bận
    void removechoban(String namenhan){
        choban.remove(namenhan);
    }
    
    //Kiểm tra chuỗi chờ bận 
    boolean ktchoban(String namekt){
        boolean kq=true;
        for(String element: choban){
            if(element.equals(namekt)){
                kq=false;
            }
        }
        return kq;
    }
    String tennhan="";
    void settennhan(String ten){
        tennhan=ten;
    }
    String tennhan(){
        System.out.println("Tên nhận là: "+ tennhan);
        return tennhan;
    }
}