package Buffer_locks;

/**
 * Clase que simula un hilo productor.
 *
 * @author David Márquez y Victor Pablo.
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
