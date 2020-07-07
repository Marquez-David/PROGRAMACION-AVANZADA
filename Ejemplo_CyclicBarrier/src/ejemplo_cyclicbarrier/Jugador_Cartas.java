package ejemplo_cyclicbarrier;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;

import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jugador_Cartas extends Thread {

    private String id_jugador;
    private CyclicBarrier barrera;
    private ArrayList<String> jugadores;
    private int ganador;
    private int i = 0;

    public Jugador_Cartas(CyclicBarrier barrera, String id_jugador, ArrayList<String> jugadores, int ganador) {
        this.barrera = barrera;
        this.id_jugador = id_jugador;
        this.jugadores = jugadores;
        this.ganador = ganador;
    }

    public void run() {
        esperar_jugadores();
        seleccionar_ganador();
    }

    public void esperar_jugadores() {
        try {
            int num = (int) (Math.random() * (3000 - 2000) + 2000);
            sleep(num);
            System.out.println("El jugador " + id_jugador + " ha tardado en llegar " + num + " segundos");
            jugadores.add(id_jugador);
            System.out.println(jugadores);
            barrera.await();
        } catch (Exception ex) {
        }
    }

    public void seleccionar_ganador() {
        try {
            int num = (int) (Math.random() * (2500 - 500) + 500);
            sleep(num);      
            System.out.println("El ganador es: " + jugadores.get(ganador));

        } catch (InterruptedException ex) {
        }
    }

}
