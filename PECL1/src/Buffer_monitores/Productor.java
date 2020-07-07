/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer_monitores;

/**
 *
 * @author Darks
 */
public class Productor extends Thread {
    
    private Buffer buf;
    private int num_max_produccion = 70;

    /**
     * Constructor del hilo productor;
     *
     * @param buf
     */
    public Productor(Buffer buf) {
        this.buf = buf;
    }

    public void run() {

        for (int i = 0; i < num_max_produccion; i++) {

            long rand = (long) (Math.random() * (700 - 300) + 300); //Numero aleatorio entre 300-700 mseg;
            try {
                sleep(rand);

                int num = (int) (Math.random() * 21); //Numero leatorio entre 0-20;
                buf.insertar_numero(num);
            } catch (InterruptedException ex) {
            }

        }
    }
    
}
