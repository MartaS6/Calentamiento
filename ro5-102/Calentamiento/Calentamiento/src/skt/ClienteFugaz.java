package skt;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;


public class ClienteFugaz {

	public static void NuevoCliente(String IP, int Puerto) {
			 
		DatagramSocket socketCliente = null;
		 InetAddress DireccionIP = null;
		 
		try {
		 socketCliente = new DatagramSocket();
		    } catch (IOException e){
			 System.out.println("Error al crear el objeto socket cliente");
			 System.exit ( 0 );
			 }
		 
			 
			 try {
			   DireccionIP = InetAddress.getByName(IP);
		     } catch (IOException e) {
			 System.out.println("Error al recuperar la IP del proceso");
			 System.exit ( 0 );
			 }
			 
			 byte [] recibirDatos = new byte[1024];
	         byte [] enviarDatos  = new byte[1024];
	         
//	         enviarDatos = frase.getBytes();
	         DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, DireccionIP, Puerto);
	         
	         try{
	         socketCliente.send(enviarPaquete);
	         }catch(IOException e){
	        	 System.out.println("Error");
				 System.exit ( 0 );
	         }
	         
		   
	         DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
			
		     try{
		     socketCliente.receive(recibirPaquete);
			}catch(IOException er){
				System.out.println("Error");
				 System.exit ( 0 );
			}
			
			 String proverbio = new String(recibirPaquete.getData());
			 
		     System.out.println(proverbio);
			
			 socketCliente.close();
		

	}

}
