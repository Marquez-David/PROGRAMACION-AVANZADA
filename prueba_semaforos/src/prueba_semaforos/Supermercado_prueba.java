/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba_semaforos;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Darks
 */
public class Supermercado_prueba {

    private ArrayList<Cliente> clientes_dentro;
    private int clientes = 0;
    private int clientes_max = 10;

    private Lock control = new ReentrantLock();
    //private Semaphore sem = new Semaphore(2,true);
    private Condition vacio = control.newCondition();
    
    private Semaphore semaforo_vacio = new Semaphore(0,true);

    public Supermercado_prueba(ArrayList<Cliente> clientes_dentro) {
        this.clientes_dentro = clientes_dentro;
    }

    public void entrar_supermercado(Cliente c) throws InterruptedException {
        control.lock();
        try {
            clientes++;
            clientes_dentro.add(c);
            semaforo_vacio.release();
            vacio.signal();
            System.out.println("Añadimos un cliente"+ semaforo_vacio);
            System.out.println(clientes_dentro);
        } finally {
            control.unlock();
        }
    }

    public void salir_supermercado() throws InterruptedException {
        
        semaforo_vacio.acquire();
        control.lock();
        try {
            //sem.acquire();
            //while(clientes == 0){
               // vacio.await();
            //}
            clientes--;
            clientes_dentro.remove(0);
            //lleno.signal();
            System.out.println("Se esta sacando un cliente" + semaforo_vacio);
            System.out.println(clientes_dentro);
        }finally{
            System.out.println("Cliente sacado");;
            control.unlock();
        }

    }
}
