package Buffer_locks;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que simula un Buffer.
 *
 * @author David Márquez y Victor Pablo.
 */
public class Buffer {

    private ArrayList<Integer> buf = new ArrayList<>();
    private int cap_max_buf = 20;
    private int num_elems_buf = 0;
    private int resultado = 0;

    private Lock control = new ReentrantLock();
    private Condition vacio = control.newCondition();
    private Condition lleno = control.newCondition();

    /**
     * Metodo que se encarga de insertar un numero en un buffer, si ha hueco en
     * el;
     *
     * @param num
     */
    public void insertar_numero(int num) {

        control.lock();
        //Si no hay sitio en el buffer hacemos que los productores esperen;
        while (num_elems_buf == cap_max_buf) {
            try {
                lleno.await();
                //System.out.println("¡Buffer lleno!");
            } catch (InterruptedException ex) {
            }
        }

        try {
            buf.add(num);
            num_elems_buf += 1;
            System.out.println("Se ha introducido el numero: " + num);
            System.out.println(buf);
            vacio.signal(); //Avisamos a los consumidores  que el buffer ya no esta vacio;
        } finally {
            control.unlock();
        }
    }

    /**
     * Metodo que se encarga de sacar numeros del buffer, si hay numeros en el;
     *
     * @param nombre_consumidor
     */
    public void consumir_numero(String nombre_consumidor) {

        control.lock();
        //Si el buffer esta vacion hacemos que los consumidores esperen;
        while (num_elems_buf == 0) {
            try {
                vacio.await();
                //System.out.println("¡Buffer vacio!");
            } catch (InterruptedException ex) {
            }
        }

        try {
            int extraido = buf.get(0);
            buf.remove(0);
            num_elems_buf -= 1;
            resultado += extraido;
            System.out.println(nombre_consumidor + " ha extraido: " + extraido + ", Resultado = " + resultado);
            System.out.println(buf);
            lleno.signal();//Avisamos a los productores de que el buffer ya no esta lleno;
        } finally {
            control.unlock();
        }
    }

}
