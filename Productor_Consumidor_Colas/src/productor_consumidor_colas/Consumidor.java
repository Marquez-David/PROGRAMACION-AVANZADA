package productor_consumidor_colas;

import java.util.Queue;

public class Consumidor implements Runnable {

    private final Queue<String> cola;

    public Consumidor(Queue<String> cola) {
        this.cola = cola;
    }

    public void run() {

        while (true) {
            while (!cola.isEmpty()) { //Mientras haya elementos
                String m = cola.poll(); //Saca el 1º elemento y lo borra
                if (m != null) {
                    System.out.println("Consumidor: " + m);
                }
            }
            try { //Aquí la cola ya está vacía
                synchronized (cola) {
                    cola.wait(); //Espera hasta que llegue un elemento
                }
            } catch (InterruptedException ex) {
            }
        }
    }
}
