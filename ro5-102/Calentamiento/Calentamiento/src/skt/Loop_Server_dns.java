package skt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Loop_Server_dns extends Thread {
	
	DatagramSocket serverDNS;
	
	 public Loop_Server_dns(DatagramSocket serverDNS) {
         this.serverDNS = serverDNS;
      }
	 
    public void run(){
	
	       	 byte [] recibirDatos = new byte[1024];
	       	 byte [] enviarDatos = new byte[1024];
	         Scanner fichero = LeerFichero("proverbios.txt");
	       	 
	       	 while(true){
	       		
                     DatagramPacket recibido = null;
	       	     recibido = new DatagramPacket(recibirDatos,recibirDatos.length);
	       	    try{
	       		serverDNS.receive(recibido);
	       	    }catch(IOException e){
	       	    	System.out.println("Error al recibir");
	       			 System.exit ( 0 );
	       	    }
	       	  
            	String linea = null;
            	
            		linea = fichero.nextLine().trim(); 
            		
            		try{
            		enviarDatos = linea.getBytes("US-ASCII");
            		}catch(IOException e){
	       				 
	       			 System.out.println("Error en el paquete");
	       			 System.exit ( 0 );
	       			 
	       			 }
            		DatagramPacket paquete = new DatagramPacket(enviarDatos,enviarDatos.length,recibido.getAddress(),recibido.getPort());
            		
         	
	       	   try {      
	       			
	       			serverDNS.send(paquete);
	       			
	       			 } catch (IOException e){
	       				 
	       			 System.out.println("Error al enviar");
	       			 System.exit ( 0 );
	       			 
	       			 }
                      
	       	 	
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
