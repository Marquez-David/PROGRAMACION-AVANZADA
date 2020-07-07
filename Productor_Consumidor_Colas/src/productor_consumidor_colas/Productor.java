package productor_consumidor_colas;

import java.util.Queue;
import java.util.Random;

public class Productor implements Runnable {

    private static int i;
    private final Queue<String> cola;
    private final Random r;

    public Productor(Queue<String> cola) {
        this.cola = cola;
        r = new Random();
    }

    public void run() {

        while (true) {
            String m = "Mensaje numero: " + (i++);
            cola.offer(m); //Añade el elemento
            synchronized (cola) {
                cola.notifyAll();
            }
            System.out.println("Productor: " + m);
            try {
                Thread.sleep(r.nextInt(5000));
            } catch (InterruptedException ex) {
            }
        }
    }

}
