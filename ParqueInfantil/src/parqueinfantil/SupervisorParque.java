/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import interfazgrafica.InterfazSupervisor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Darks
 */
public class SupervisorParque {
    
    Socket conexion;
    DataInputStream entrada;
    DataOutputStream salida;
    String mensaje, respuesta1, respuesta2, respuesta3;
    InterfazSupervisor interfazSupervisor;
    
    public SupervisorParque(InterfazSupervisor interfazSupervisor){
        try {
            conexion = new Socket(InetAddress.getLocalHost(),2222);
            entrada = new DataInputStream(conexion.getInputStream());
            salida = new DataOutputStream(conexion.getOutputStream());

            if(entrada.readUTF().equals("Bienvenido")){
                interfazSupervisor.setVisible(true);
                System.out.println("¡El servidor me ha dado la bienvenida!");
            }
        } catch (UnknownHostException ex) {}
        
          catch (IOException ex) {}
    }
    
    public void enviarOrdenRefrescar(){
        try{
            mensaje="Refrescar";
            salida.writeUTF(mensaje);
            System.out.println("Mi mensaje es: " + mensaje + " y lo envío desde el puerto " + conexion.getLocalPort() + " hacia el puerto "+conexion.getPort());
            respuesta1 = entrada.readUTF();
            System.out.println("La respuesta del servidor es: " + respuesta1);
            
            respuesta2 = entrada.readUTF();
            System.out.println("La respuesta del servidor es: " + respuesta2);
            
            respuesta3 = entrada.readUTF();
            System.out.println("La respuesta del servidor es: " + respuesta3);
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void enviarOrdenCerrarParque(){
        try {
            mensaje = "Cerrar";
            salida.writeUTF(mensaje);
            System.out.println("Mi mensaje es: " + mensaje + " y lo envío desde el puerto " + conexion.getLocalPort() + " hacia el puerto "+conexion.getPort());
        } catch (IOException ex) {
            
        }
    }
    
    public void cerrarConexion() throws IOException{
        salida.close();
        entrada.close();
        conexion.close();
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }
    
    
}
