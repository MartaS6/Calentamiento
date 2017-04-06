package skt;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Servidor{
    public static void LanzarServidor(int Puerto) {
    	
    	ServerSocket serverSocket = null;
        InetSocketAddress addr = null;
        DatagramSocket ss = null;
                
         System.out.print("Inicializando servidor... ");
         
       
       
         try {
            serverSocket = new ServerSocket();
             addr = new InetSocketAddress(Puerto);
             serverSocket.bind(addr);
         } catch (IOException e) {
             e.printStackTrace();
         }
         
         
    	 try{
	         ss = new DatagramSocket(Puerto);
	       	 }catch(IOException ex){
	          	ex.printStackTrace();
	         }
         
    	    System.out.println("OK");
  
    	    Loop_Server_dns g = new Loop_Server_dns(ss);
    	    g.start();
        
            Loop_server_tcp l = new Loop_server_tcp(serverSocket);
            l.start();
            
            
            try {
                l.join();
                l.close_sockets();
                ss.close();
                serverSocket.close();
            } catch (InterruptedException ex) {
               
            }  catch (IOException ex) {
                System.out.println("I/O error: " + ex);
            }
    }
    }
            
  
