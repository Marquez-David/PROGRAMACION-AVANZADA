/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer_ColasConcurrentes;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Darks
 */
public class Prueba_Buffer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BlockingQueue buf = new LinkedBlockingQueue(10);
        Productor p = new Productor(buf);
        Consumidor c = new Consumidor(buf);
        
        p.start();
        c.start();
    }
    
}
