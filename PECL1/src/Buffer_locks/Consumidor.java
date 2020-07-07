package Buffer_locks;

/**
 * Clase que simula un hilo consumidor.
 *
 * @author David Márquez y Victor Pablo.
 */
public class Consumidor extends Thread {

    private String nombre_consumidor;
    private Buffer buf;

    /**
     * Constructor del hilo consumidor;
     * @param buf
     * @param nombre_consumidor 
     */
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
