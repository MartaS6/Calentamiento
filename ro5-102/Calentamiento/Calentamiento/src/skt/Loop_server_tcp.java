package skt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loop_server_tcp extends Thread {

      ServerSocket serverSocket;
      Socket socket = null;
      Request r = null;
      ArrayList <Socket> sockets = new ArrayList<>();
      boolean loop = true;
    
       public Loop_server_tcp(ServerSocket serverSocket) {
          this.serverSocket = serverSocket;
       }
    
     @Override
     public void run() {
	
       while (loop) {
          socket = null;
          r = null;
         try {
            socket = serverSocket.accept();
            r = new Request(socket);
            r.start();
            sockets.add(socket);
         } catch (IOException e) {
            System.out.println("I/O error: " + e);
         }
       }
     }

      public void close_sockets() {
         loop = false;
    
      for (Socket socketA : sockets) {
         if ( !socketA.isClosed() ) {
            try {
                socketA.close();
             } catch (IOException ex) {
                Logger.getLogger(Loop_server_tcp.class.getName())
                        .log(Level.SEVERE, null, ex);
             }
         }
      }
    
      }
}


