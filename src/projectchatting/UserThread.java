/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectchatting;
import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 *
 * @author www.codejava.net
 */
public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
 
    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }
 
    public void run() {
        try {
            //Thiet lap bien Buffee de nhan data
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            //Thiet lap bien writer de truyen du lieu di    
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
 
            printUsers(this);
 
            // Ghi ten tu nguoi dung va them name Client vao danh sach Ten cua server 
            String userName = reader.readLine();
            server.addUserName(userName);
     
            String serverMessage = "Online-New user connected: " + userName;
            
            server.broadcast(serverMessage, this);               
            
            
            String clientMessage;
            
            do {
                //Send message do nguoi dung truyen toi cho cac nguoi dung con lại
                clientMessage = reader.readLine();

                
                serverMessage = "[" + userName + "]: " + clientMessage;            
                server.broadcast(serverMessage, this);
                //Chay cho toi khi nao client noi "bye"
            } while (!clientMessage.equals("bye"));
            // Khi người dùng say "Bye" sẽ xoá tên người dùng ra khỏi list
            
            server.removeUser(userName, this);
            socket.close();
            // Gui thong diep cho cac nguoi dung client đó đã out
            serverMessage ="OnlineClose- "+ userName +" has quitted.";
            server.broadcast(serverMessage, this);
            
        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers(UserThread user) {
        if (server.hasUsers()) {
            server.getUserNames();
            for(String element:server.getUserNames()){
            writer.println("Online-Connected users connection: " + element);    
            }
            
        } else {
           // writer.println("No other users connected");
        }
    }
 
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}

