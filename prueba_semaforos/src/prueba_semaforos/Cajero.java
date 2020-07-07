/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba_semaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darks
 */
public class Cajero extends Thread {

    private String id;
    private Supermercado_prueba supermercado;

    public Cajero(String id, Supermercado_prueba supermercado) {
        this.id = id;
        this.supermercado = supermercado;
    }

    public void run() {
        boolean hay_clientes = true;
        while (hay_clientes) {
            try {
                sleep(500);
                supermercado.salir_supermercado();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
