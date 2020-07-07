
package supermercado;

import java.util.ArrayList;

/**
 * Clase que simula un pescadero.
 * @author David Márquez Mínguez.
 */
public class Pescaderia extends Thread {
    
    private int cap_max_pescadero = 1;
    //La cola seguira una politica de reemplazo FIFO.
    private ArrayList<Comprador> cola_pescaderia = new ArrayList<>();

    
}
