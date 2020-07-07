/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partidofutbol;

import static java.lang.Thread.sleep;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darks
 */
public class PartidoFutbol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int numJugadores = 22;
        int numArbitros = 5;

        final CountDownLatch barreraLlegadaCampo = new CountDownLatch(numJugadores);
        final CountDownLatch barreraPreparacionArbitros = new CountDownLatch(numArbitros);
        final CountDownLatch barreraFinPartido = new CountDownLatch(numJugadores);

        for (int i = 0; i < numJugadores; i++) {
            Jugador j = new Jugador(barreraLlegadaCampo, barreraPreparacionArbitros, barreraFinPartido,"Jugador-" + i);
            try {
                sleep((long) (Math.random() * (700 - 300) + 300));
            } catch (InterruptedException ex) {
            }
            j.start();
        }

        for (int j = 0; j < numArbitros; j++) {
            Arbitro a = new Arbitro(barreraLlegadaCampo, barreraPreparacionArbitros, barreraFinPartido,"Arbitro-" + j);
            a.start();
        }

        /*try {
            barreraLlegadaCampo.await();
            System.out.println("Todos los jugadores han llegado");
            
            barreraPreparacionArbitros.await();
            System.out.println("Tanto arbitros como jugadores estan listos para empezar el partido");
        } catch (Exception e) {

        }*/
    }

}
