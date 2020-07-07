
package ejercicio_examen;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;


public class Consumidor extends Thread {
    
    private BlockingQueue cola_concurrente;
    private ArrayList<Integer> coleccion;
    private CopyOnWriteArrayList coleccion_concurrente;
    
    public Consumidor(BlockingQueue cola_concurrente, ArrayList<Integer> coleccion, CopyOnWriteArrayList coleccion_concurrente) {
        this.cola_concurrente = cola_concurrente;
        this.coleccion = coleccion;
        this.coleccion_concurrente = coleccion_concurrente;
    }
    
    public void run(){
        
        try {
            while(true){
                System.out.println("Consumo: " + cola_concurrente.take());
                System.out.println("Consumo: " + coleccion.remove(0));
                System.out.println("Consumo: " + coleccion_concurrente.remove(0));
            }
        }catch(InterruptedException e){
            
        }
    }
    
}
