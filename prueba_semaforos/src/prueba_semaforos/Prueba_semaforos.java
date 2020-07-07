/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba_semaforos;

import static java.lang.Thread.sleep;
import java.util.ArrayList;

/**
 *
 * @author Darks
 */
public class Prueba_semaforos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Cliente> clientes_comprando = new ArrayList<>();

        Supermercado_prueba superm = new Supermercado_prueba(clientes_comprando);
        
        Cajero caj = new Cajero("Cajero", superm);
        caj.start();
        
        Cajero caj2 = new Cajero("", superm);
        caj2.start();

        for (int i = 0; i < 20; i++) {
            //sleep(500);
            Cliente c = new Cliente("Cliente" + i, superm);
            c.start();
        }
    }

}
