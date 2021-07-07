
package Server;

import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    
    Server(int port){
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept(); // Connect to a client
                System.out.println("Accepted");
                Thread thread = new ConnectionHandler(socket);
                thread.start();
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String args[]){
        Server server=new Server(8050);
    }
}
