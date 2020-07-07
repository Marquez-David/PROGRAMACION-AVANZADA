/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer_ColasConcurrentes;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darks
 */
public class Productor extends Thread {

    private BlockingQueue<Integer> buf;

    public Productor(BlockingQueue buf) {
        this.buf = buf;
    }

    public void run() {

        boolean hay_elems = true;
        while (hay_elems) {
            int num = (int) (Math.random() * 21); //Numero leatorio entre 0-20;
            System.out.println("Se produce" + num);
            try {
                buf.put(num);
                System.out.println(buf);
                long rand = (long) (Math.random() * (700 - 300) + 300); //Numero aleatorio entre 300-700 mseg;
                sleep(rand);
            } catch (InterruptedException ex) { }
        }
    }

}
