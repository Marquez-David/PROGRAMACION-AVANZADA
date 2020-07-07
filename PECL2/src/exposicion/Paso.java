/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exposicion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Darks
 */
public class Paso {
    
    private int num_hilos;
    private boolean cerrado[];
    private Lock control[];
    private Condition parar[];
    
    public Paso(int num_hilos){
        this.num_hilos = num_hilos;
        
        //Inicilizo el array de booleanos,locks y conditions para cada hilo para cada hilo
        this.cerrado = new boolean[num_hilos];
        this.control = new ReentrantLock[num_hilos];
        this.parar = new Condition[num_hilos];
        for(int i = 0; i<num_hilos; i++){
            control[i] = new ReentrantLock();
            parar[i] = control[i].newCondition();
            this.cerrado[i] = false;
        }
    }
    
    public void mirar(int hilo) throws InterruptedException{
        control[hilo].lock();
        try{
            while(cerrado[hilo]){ //Mientras que el hilo este cerrado
                parar[hilo].await();
            }
        }finally{
            control[hilo].unlock();
        }
    }
    
    public void abrir(int hilo){
        control[hilo].lock();
        try{
            cerrado[hilo] = false;
            parar[hilo].signalAll();
        }finally{
            control[hilo].unlock();
        }
                
    }
    
    public void cerrar(int hilo){
        control[hilo].lock();
        try{
            cerrado[hilo] = true;
            parar[hilo].signalAll();
        }finally{
            control[hilo].unlock();
        }
    }
}
