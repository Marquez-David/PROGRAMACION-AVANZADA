package suma_nums_concurrente;

import java.util.Date;

/**
 * Suma multiplos de 7 y numeros terminados en 3 o 5 y primos, todo de forma
 * concurrente.
 *
 * @author David
 */
public class Suma_nums_concurrente {

    public static void main(String[] args) {

        long time0 = (new Date()).getTime();
        
        //Creamos los objetos de cada clase;
        Mult_siete m1 = new Mult_siete();
        Terminados_tres_cinco t1 = new Terminados_tres_cinco();
        Num_primos p1 = new Num_primos();

        //Comenzamos los hilos;
        m1.start();
        t1.start();
        p1.start();

        //Esperamos a que terminen todos los hilos;
        try {
            m1.join(); t1.join(); p1.join();
        } catch (InterruptedException e) {

        }
        
        
        long n1 = m1.sumatorio();
        long n2 = t1.sumatorio();
        long n3 = p1.cuantos();

        long time1 = (new Date()).getTime();
        
        System.out.println("La suma de los multiplos de siete es: " + n1);
        System.out.println("La suma de los numeros terminados en tres o cinco es: " + n2);
        System.out.println("La suma de los numeros primos es: " + n3);
        System.out.println("La suma total es: " + (n1+n2+n3) + " ejecutado en " + (time1-time0) + " miliseg");

    }

}
