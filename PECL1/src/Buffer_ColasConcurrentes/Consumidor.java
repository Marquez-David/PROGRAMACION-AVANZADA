/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer_ColasConcurrentes;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darks
 */
public class Consumidor extends Thread {

    private BlockingQueue<Integer> buf;

    public Consumidor(BlockingQueue buf) {
        this.buf = buf;
    }

    public void run() {

        boolean hay_elems = true;
        while (hay_elems) {
            
            try {
                System.out.println("Se extrae: " + buf.take());
                System.out.println(buf);
                long rand = (long) (Math.random() * (700 - 300) + 300); //Numero aleatorio entre 300-700 mseg;
                sleep(rand);
            } catch (InterruptedException ex) { }

        }
    }
}
