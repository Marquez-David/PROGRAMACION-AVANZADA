/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_examen;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Darks
 */
public class Productor extends Thread {
    
    private BlockingQueue cola_concurrente;
    private ArrayList<Integer> coleccion;
    private CopyOnWriteArrayList coleccion_concurrente;
    private int cap_max_elems = 20;
    private int num_elems = 0;
    
    public Productor(BlockingQueue cola_concurrente, ArrayList coleccion, CopyOnWriteArrayList coleccion_concurrente) {
        this.cola_concurrente = cola_concurrente;
        this.coleccion = coleccion;
        this.coleccion_concurrente = coleccion_concurrente;
    }
    
    public void run() {
        
        try {
            while(true){
                int num = (int)(Math.random() * 10);
                System.out.println("Produzco: " + num);
                cola_concurrente.put(num);
                System.out.println("Cola concurrente: " + cola_concurrente);
                coleccion.add(num);
                //num_elems++;
                System.out.println("Coleccion: " + coleccion);
                coleccion_concurrente.add(num);
                System.out.println("Coleccion concurrente: " + coleccion_concurrente);
            }
        }catch(InterruptedException e){
            
        }
    }
}
