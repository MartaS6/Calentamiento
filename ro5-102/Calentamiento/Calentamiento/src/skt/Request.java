package skt;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Request extends Thread {

    protected Socket socket;

    public Request(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {

        InputStream is;
        OutputStream os;
          boolean loop = true;
          
        try {
            is = socket.getInputStream();
            os =  socket.getOutputStream();
            
            
            while(loop){
            	Scanner fichero = LeerFichero("proverbios.txt");
            	Thread.sleep(1000);
            	
            	while(fichero.hasNextLine()){
                Thread.sleep(1000);
            	String linea = null;
            	
            	linea = fichero.nextLine().trim(); 
            	
            try{
                os.write(linea.getBytes("US-ASCII"));
            	}catch(SocketException e){
            		loop = false;
            	}	
            	}
            }
            
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }
    
    public static Scanner LeerFichero(String fichero){
    	
    	Scanner lectura = null;
    	try{
    		
    		lectura = new Scanner(new FileInputStream(fichero));
    		
    	}catch(FileNotFoundException e){
    		System.err.println("Fichero inexistente <"+ fichero +">");
    		System.exit(-1);
    	}
    	 return lectura;
    }
   
    
}
