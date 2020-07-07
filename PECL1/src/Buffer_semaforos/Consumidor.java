/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer_semaforos;

/**
 *
 * @author Darks
 */
public class Consumidor extends Thread {
    
    private String nombre_consumidor;
    private Buffer buf;
    
    public Consumidor(Buffer buf, String nombre_consumidor) {
        this.buf = buf;
        this.nombre_consumidor = nombre_consumidor;
    }
    
    public void run() {
        
        boolean consumiendo = true;
        //Los consumidores no terminan nunca;
        while (consumiendo) {

            //Numero aleatorio entre 200-800 mseg;
            long rand = (long) (Math.random() * (800 - 200) + 200);

            try {
                sleep(rand);
                buf.consumir_numero(nombre_consumidor);
            } catch (InterruptedException e) {
            }

        }
    }
}
