package skt;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientePersistente {
	
	public static void NuevoCliente(String IP, int Puerto){

    InputStream is;
    Socket clientSocket = null;
    InetSocketAddress addr = null;
    byte[] respuesta;
    
    
        try {
            clientSocket = new Socket();
            addr = new InetSocketAddress(IP, Puerto);
            clientSocket.connect(addr);
          
            boolean conectado = true;
            while(conectado){
            try{
                is = clientSocket.getInputStream();
                respuesta = new byte[1024];
                is.read(respuesta);
                System.out.println(new String(respuesta).trim());
                
            }catch(IOException ex){
            	ex.printStackTrace();
            	conectado = false;
              }
            }
            
            clientSocket.close();

        } catch (IOException e) {
        	
            e.printStackTrace();
        }
     }
	
		
	}
