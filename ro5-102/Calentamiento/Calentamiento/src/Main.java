
import java.util.Scanner;
import skt.Servidor;
import skt.ClienteFugaz;
import skt.ClientePersistente;


public class Main {

    static Scanner scan;
    static String entrada;
    static String[] array;
    static String eleccion;
    static String IP;
    static int Puerto;

    public static void main (String[] args) {

        System.out.println("Si desea lanzar un servidor escriba 'servidor numero_Puerto fichero'" +
                "\nSi desea ejecutar un cliente escriba 'cliente -(udp/tcp) direccion_IP  puerto'");
        scan = new Scanner(System.in);
        entrada = scan.nextLine();
        array = entrada.split(" ");
        eleccion = array[0];
        
        
        try {
        	if(eleccion.equals("servidor"))
             Puerto = Integer.parseInt(array[1]);
        	else if(eleccion.equals("cliente"))
        	 Puerto = Integer.parseInt(array[3]);
        } catch (NumberFormatException e2) {
            System.out.println("Se ha introducido un formato de Puerto no valido.");
            System.exit(1);
        } catch (IndexOutOfBoundsException e3) {
            System.out.println("No se ha escogido ningun puerto.");
            System.exit(1);
        }


        if (eleccion.equals("servidor")) Servidor.LanzarServidor(Puerto);
        else if (eleccion.equals("cliente")) {
            try {
                IP = array[2];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("No se ha escogido direccion IP.");
                System.exit(1);
            }
            
            if(array[1].equals("-tcp"))
            ClientePersistente.NuevoCliente(IP, Puerto);
            
            if(array[1].equals("-udp"))
            ClienteFugaz.NuevoCliente(IP, Puerto);
            
        }
            else {
                System.out.println("No se ha introducido 'servidor' o 'cliente'");
                System.exit(1);
            }
    }
}


