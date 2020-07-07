package num_primos;

import java.util.Date;

/**
 * Programa que calcula concurrentemente el numero de primos y el tiempo de cada
 * hilo.
 *
 * @author David
 */
public class Num_primos {

    public static void main(String[] args) {

        long t0 = (new Date()).getTime(); //t0=instante de inicio de los cálculos

        Primos p1 = new Primos(1, 2000000);
        Primos p2 = new Primos(2000001, 4000000);
        Primos p3 = new Primos(4000001, 6000000);
        Primos p4 = new Primos(6000001, 8000000);
        Primos p5 = new Primos(8000001, 10000000);
        
        // Hacemos los calculos del hilo1 y guardamos los tiempos.
        long th1_ini = (new Date()).getTime();
        p1.calcular();
        long th1_fin = (new Date()).getTime();
        
        //Hacemos los calculos  del hilo2 y guardamos los tiempos.
        long th2_ini = (new Date()).getTime();
        p2.calcular();
        long th2_fin = (new Date()).getTime();
        
        //Hacemos los calculos  del hilo3 y guardamos los tiempos.
        long th3_ini = (new Date()).getTime();
        p3.calcular();
        long th3_fin = (new Date()).getTime();
        
        //Hacemos los calculos  del hilo4 y guardamos los tiempos.
        long th4_ini = (new Date()).getTime();
        p4.calcular();
        long th4_fin = (new Date()).getTime();
        
        //Hacemos los calculos  del hilo5 y guardamos los tiempos.
        long th5_ini = (new Date()).getTime();
        p5.calcular();
        long th5_fin = (new Date()).getTime();

        int n = p1.cuantos() + p2.cuantos() + p3.cuantos() + p4.cuantos() + p5.cuantos();
        long t_total_hilos = (th1_fin - th1_ini) + (th2_fin - th2_ini) + (th3_fin - th3_ini) + (th4_fin - th4_ini) + (th5_fin - th5_ini);
        
        long t1 = (new Date()).getTime(); //t1=instante de final de los cálculos

        System.out.println("Número de primos menores que 10.000.000: " + n + " calculado en " + (t1 - t0) + " miliseg.");
        System.out.println("El tiempo que ha tardado el primer hilo es: " + (th1_fin - th1_ini) + " miliseg.");
        System.out.println("El tiempo que ha tardado el segundo hilo es: " + (th2_fin - th2_ini) + " miliseg.");
        System.out.println("El tiempo que ha tardado el tercer hilo es: " + (th3_fin - th3_ini) + " miliseg.");
        System.out.println("El tiempo que ha tardado el cuarto hilo es: " + (th4_fin - th4_ini) + " miliseg.");
        System.out.println("El tiempo que ha tardado el quinto hilo es: " + (th5_fin - th5_ini) + " miliseg.");
        System.out.println("El tiempo en tramos del los hilos es: " + t_total_hilos + " miliseg.");
        
    }
}
