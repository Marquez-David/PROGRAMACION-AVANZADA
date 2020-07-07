/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partido_futbol;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona extends Thread {

    private String id_jugador;
    private String tipo;
    private CyclicBarrier llegada;
    private CyclicBarrier preparacion_arbitros;
    private CyclicBarrier fin_partido;

    public Persona(String tipo, String id_jugador, CyclicBarrier llegada, CyclicBarrier preparacion_arbitros, CyclicBarrier fin_partido) {

        this.id_jugador = id_jugador;
        this.tipo = tipo;
        this.llegada = llegada;
        this.preparacion_arbitros = preparacion_arbitros;
        this.fin_partido = fin_partido;

    }

    public void run() {
        try {
            if ("Jugador".equals(this.tipo)) {
                llegar_campo();
            }

            llegada.await();

            if ("Arbitro".equals(this.tipo)) {
                preparar_arbitros();
            }
            preparacion_arbitros.await();
            
            if("Jugador".equals(this.tipo)){
                transcurrir_partido();
            }
            
            fin_partido.await();
            
            System.out.println("El ganado es el equipo1");
        } catch (Exception ex) {
        }

    }

    public void llegar_campo() {

        try {

            int num = (int) (Math.random() * (20000 - 5000) + 5000);

            sleep(num);

            System.out.println("El jugador " + id_jugador + " ha llegado al campo ha tardado " + num + " segundos");

        } catch (Exception e) {
        }

    }

    public void preparar_arbitros() {
        try {
            //preparacion_arbitros.await();
            int num = (int) (Math.random() * (7000 - 2000) + 2000);//Numero aleatorio entre 2-7
            sleep(num);
            System.out.println("El  " + tipo + id_jugador + " ha terminado de prepararse y ha tardado " + num + " segundos");

            
        } catch (Exception ex) {
        }
    }

    public void transcurrir_partido() {
        try {
            int num = (int) (Math.random() * (9 - 6) + 6);
            sleep(num);
            System.out.println(id_jugador + " ha terminado el partido ha durado" + num);
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
