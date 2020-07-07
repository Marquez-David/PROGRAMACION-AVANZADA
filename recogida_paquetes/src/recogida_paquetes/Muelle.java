package recogida_paquetes;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Darks
 */
public class Muelle {

    private int capacidad_maxima_muelle = 20;
    private int paquetes_actuales = 0;

    private Lock control = new ReentrantLock();
    private Condition lleno = control.newCondition();
    private Condition vacio = control.newCondition();

    public Muelle(int capacidad_maxima_muelle) {

        this.capacidad_maxima_muelle = capacidad_maxima_muelle;
        this.paquetes_actuales = paquetes_actuales;
    }

    //Producotor-Furgonetas
    public void insertar_paquete() throws InterruptedException {

        control.lock();

        //Si el muelle esta lleno.
        while (paquetes_actuales == capacidad_maxima_muelle) {
            lleno.await();
        }

        try {
            paquetes_actuales++;
            System.out.println(paquetes_actuales);
            vacio.signal(); //Muelle ya no esta vacio
        } finally {
            control.unlock();
        }
    }

    //Consumidor-Operarios
    public void extraer_paquete() throws InterruptedException {

        control.lock();

        while (paquetes_actuales == 0) {
            vacio.await();
        }

        try {
            paquetes_actuales--;
            System.out.println(paquetes_actuales);
            lleno.signal();
        } finally {
            control.unlock();
        }

    }

}
