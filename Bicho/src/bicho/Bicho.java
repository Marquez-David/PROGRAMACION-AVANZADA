package bicho;

import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se encarga de representar un organismo vivo;
 *
 * @author David;
 */
public class Bicho extends Thread {

    /**
     * Atributos de la clase Bicho;
     */
    private String nombre_bicho = "ADAN";
    private int generacion_bicho = 0;
    private long tiempo_vida;
    private char[] abecedario = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private Bicho b2;

    /**
     * Constructor de la clase Bicho;
     *
     * @param nombre_bicho
     * @param generacion_bicho
     */
    public Bicho(String nombre_bicho, int generacion_bicho) {
        this.generacion_bicho = generacion_bicho;
        this.nombre_bicho = nombre_bicho;

    }

    /**
     * Funcion que se encarga de determinar un nombre aleatorio al bicho;
     *
     * @return nombre_bicho;
     */
    public String crear_nombre_bicho() {

        if (generacion_bicho >= 1) {

            nombre_bicho = "";
            for (int i = 0; i < 4; i++) {

                Random rand = new Random();

                //Genero una letra aleatoria del abecedario;
                int pos_letra = rand.nextInt(26);
                nombre_bicho += abecedario[pos_letra];

            }

        } else {
            nombre_bicho = "ADAN";
        }
        return nombre_bicho;
    }

    public void run() {

        long time0 = (new Date()).getTime();

        crear_nombre_bicho();
        escribir_generacion(nombre_bicho, generacion_bicho);
        esperar_tiempo();

        //A partir de la 5ª geenracion los hijos son esteriles;
        if (generacion_bicho < 5) {
            crear_bicho_nuevo();
        }

        long time1 = (new Date()).getTime();

        tiempo_vida = (time1 - time0);
        
        

        muerte_bicho(nombre_bicho, generacion_bicho, tiempo_vida);
    }

    /**
     * Función que se encarga de imprimir por pantalla el nombre y generacion
     * del bicho;
     *
     * @param nombre_bicho
     * @param generacion_bicho
     */
    public void escribir_generacion(String nombre_bicho, int generacion_bicho) {

        System.out.println("NACE " + "nombre: " + nombre_bicho + " , generacion: " + generacion_bicho);
    }

    /**
     * Funcion que se encarga de que un hilo espere un tiempo aleatorio entre
     * 0.5 y 1;
     */
    public void esperar_tiempo() {

        Random rand = new Random();

        //Numero aleatorio entre 0.5-1;
        double num = rand.nextDouble() * (1000 - 500) + 500;
        
        try {
            sleep((long) num);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bicho.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("He esperado " + num + " msegundos");
    }

    /**
     * Funcion que se encarga de simular la creacion de un bicho hijo nuevo;
     *
     * @return b2
     */
    public Bicho crear_bicho_nuevo() {

        //generacion_bicho += 1;

        //Bicho b2 = new Bicho(crear_nombre_bicho(), generacion_bicho);
        b2 = new Bicho(nombre_bicho, generacion_bicho+1);
        b2.start();
        
        //Esperamos a que el hijo muera;
        try {
            b2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Bicho.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b2;
    }

    /**
     * Funcion que se encarga de imprimir los datos del bicho que ha muerto;
     *
     * @param nombre_bicho
     * @param generacion_bicho
     * @param tiempo_vida
     */
    public void muerte_bicho(String nombre_bicho, int generacion_bicho, long tiempo_vida) {

        System.out.println("MUERE: " + nombre_bicho + " ,generacion: " + generacion_bicho + " ,tiempo de vida: " + tiempo_vida + " mseg");
    }
}
