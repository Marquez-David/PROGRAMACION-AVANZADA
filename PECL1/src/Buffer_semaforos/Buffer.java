/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer_semaforos;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Darks
 */
public class Buffer {

    private ArrayList<Integer> buf = new ArrayList<>();
    private int cap_max_buf = 20;
    private Lock lock = new ReentrantLock();
    private Semaphore vacio = new Semaphore(0);
    private Semaphore lleno = new Semaphore(cap_max_buf, true);
    private int resultado = 0;

    public void insertar_numero(int num) throws InterruptedException{

        lleno.acquire();//-1
        try {
            buf.add(num);
            System.out.println("Se ha introducido: " + num);
            System.out.println(buf);

            vacio.release();//+1
        } finally {
        }
    }

    public void consumir_numero(String hola) throws InterruptedException {

        vacio.acquire();//-1

        try {
            
            int num = buf.get(0);
            System.out.println("Se ha eliminado: " + num);
            buf.remove(0);
            System.out.println(buf);
            resultado += num;
            System.out.println(resultado);

            lleno.release();//+1
        } finally {

        }
    }

}
