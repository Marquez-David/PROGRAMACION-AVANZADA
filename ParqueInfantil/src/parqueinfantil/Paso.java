/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Darks
 */
public class Paso {

    private Condition pararParque[];
    private Lock cerrojo[];
    private boolean cerrado[];

    public Paso(int numHilos) {
        this.pararParque = new Condition[numHilos];
        this.cerrojo = new ReentrantLock[numHilos];
        this.cerrado = new boolean[numHilos];

        for (int i = 0; i < numHilos; i++) {
            cerrojo[i] = new ReentrantLock();
            pararParque[i] = cerrojo[i].newCondition();
            this.cerrado[i] = false;
        }
    }

    public void mirar(int idHilo) {
        try {
            cerrojo[idHilo].lock();
            while (cerrado[idHilo]) {
                try {
                    pararParque[idHilo].await();
                } catch (InterruptedException ie) {
                }
            }
        } finally {
            cerrojo[idHilo].unlock();
        }
    }

    public void abrir(int idHilo) {
        try {
            cerrojo[idHilo].lock();
            cerrado[idHilo] = false;
            pararParque[idHilo].signalAll();
        } finally {
            cerrojo[idHilo].unlock();
        }
    }

    public void cerrar(int idHilo) {
        try {
            cerrojo[idHilo].lock();
            cerrado[idHilo] = true;
            pararParque[idHilo].signalAll();
        } finally {
            cerrojo[idHilo].unlock();
        }
    }

}
