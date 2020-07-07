package num_primos2;

import java.util.Date;

/**
 * Calcula cuantos primos hay pero implementando runnable
 *
 * @author David
 */
public class Num_primos2 {

    public static void main(String[] args) {

        long t0 = (new Date()).getTime(); //t0=instante de inicio de los cálculos

        Primos p1 = new Primos(1, 2000000);
        Primos p2 = new Primos(2000001, 4000000);
        Primos p3 = new Primos(4000001, 6000000);
        Primos p4 = new Primos(6000001, 8000000);
        Primos p5 = new Primos(8000001, 10000000);

        /*Thread thread1 = new Thread(p1);
        Thread thread2 = new Thread(p2);
        Thread thread3 = new Thread(p3);
        Thread thread4 = new Thread(p4);
        Thread thread5 = new Thread(p5);
        
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();*/
        
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        try {

            p1.join();
            p2.join();
            p3.join();
            p4.join();
            p5.join(); //esperamos a que terminen todos

        } catch (InterruptedException e) {
        }

        int n = p1.cuantos() + p2.cuantos() + p3.cuantos() + p4.cuantos() + p5.cuantos();

        long t1 = (new Date()).getTime(); //t1=instante de final de los cálculos

        System.out.println("Número de primos menores que 10.000.000: " + n + " calculado en " + (t1 - t0) + " miliseg.");

    }

}

