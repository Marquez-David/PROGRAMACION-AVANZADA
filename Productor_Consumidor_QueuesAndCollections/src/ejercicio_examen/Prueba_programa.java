/*
 * El programa consiste en implementar un preoductor-consumidor utilizando:
 * 1- Una coleccion concurrente no segura(ArrayList)
 * 2- Una cola concurrente
 * 3- Una coleccion concurrente
 */
package ejercicio_examen;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Prueba_programa {

    public static void main(String[] args) {
        
        BlockingQueue cola_concurrente = new LinkedBlockingQueue();
        ArrayList<Integer> coleccion = new ArrayList<>();
        CopyOnWriteArrayList coleccion_concurrente = new CopyOnWriteArrayList();
        
        Productor p = new Productor(cola_concurrente, coleccion, coleccion_concurrente);
        Consumidor c1 = new Consumidor(cola_concurrente, coleccion, coleccion_concurrente);
        Consumidor c2 = new Consumidor(cola_concurrente, coleccion, coleccion_concurrente);
        Consumidor c3 = new Consumidor(cola_concurrente, coleccion, coleccion_concurrente);
        
        p.start();
        c1.start();
        c2.start();
        c3.start();
        
    }
    
}
