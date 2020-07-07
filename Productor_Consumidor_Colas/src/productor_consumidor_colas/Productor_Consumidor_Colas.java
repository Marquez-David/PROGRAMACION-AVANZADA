
package productor_consumidor_colas;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Productor_Consumidor_Colas {
    
    public static void main(String[] args) {

        Queue<String> cola = new ConcurrentLinkedQueue<String>();
        Productor p = new Productor(cola);
        Consumidor c = new Consumidor(cola);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }

}
