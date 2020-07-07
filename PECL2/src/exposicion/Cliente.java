/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exposicion;

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
public class Cliente {

    //Atributos
    Socket sock;
    DataInputStream entrada;
    DataOutputStream salida;
    String msg, respuesta;
    Modulo_control modulo_control;

    /**
     * Constructor donde establezco la conexion.
     *
     * @param modulo_control
     */
    public Cliente(Modulo_control modulo_control) throws IOException {
        try {
            //Creo el socket
            sock = new Socket(InetAddress.getLocalHost(), 5000);
            //Creo los canales
            entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());

            //Si el cliente recibe el msg de bienvenida se muestra loa interfaz del modulo de control
            if ("Bienvenido".equals(entrada.readUTF())) {
                modulo_control.setVisible(true);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void orden_detener() throws IOException {
        msg = "Detener";
        salida.writeUTF(msg);
        System.out.println("El msg es: " + msg + " y el socket es: " + sock);
    }

    public void orden_reanudar() throws IOException {
        msg = "Reanudar";
        salida.writeUTF(msg);
        System.out.println("El msg es: " + msg + " y el socket es: " + sock);
    }

    public void cerrar_conexion() throws IOException {
        salida.close();
        entrada.close();
        sock.close();
    }
}
