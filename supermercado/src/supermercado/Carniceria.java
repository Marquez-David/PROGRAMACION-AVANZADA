
package supermercado;

import java.util.ArrayList;

/**
 * Clase que simula un carnicero.
 * @author David Márquez Mínguez.
 */
public class Carniceria extends Thread {
    
    private int cap_max_carnicero = 1;
    //La cola seguira una politica de reemplazo FIFO.
    private ArrayList<Comprador> cola_carniceria = new ArrayList<>();
    
}
