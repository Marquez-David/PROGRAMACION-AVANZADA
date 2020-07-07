/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partido_futbol;

import java.util.concurrent.CyclicBarrier;

public class Partido_futbol {

    public static void main(String[] args) throws Exception {

        int num_jugadores = 22;
        int num_arbitros = 5;
        CyclicBarrier llegada = new CyclicBarrier(num_jugadores+num_arbitros + 1); //+1 porque tambien hay que esperar al hiloprincipal
        CyclicBarrier preparacion_arbitros = new CyclicBarrier(num_arbitros+num_jugadores + 1);
        CyclicBarrier fin_partido = new CyclicBarrier(num_jugadores+num_arbitros+1);

        //Se crean los jugadores
        for (int i = 0; i < num_jugadores; i++) {

            Persona j = new Persona("Jugador", "Jugador-" + i, llegada, preparacion_arbitros, fin_partido);

            j.start();

        }

        //Se crean los arbitro
        for (int j = 0; j < num_arbitros; j++) {

            Persona a = new Persona("Arbitro", "Arbitro-" + j, llegada, preparacion_arbitros, fin_partido);

            a.start();

        }

        try {

            llegada.await();

            System.out.println("Ya han llegado todos los jugadores");

        } catch (InterruptedException e) {
        }

        try {
            preparacion_arbitros.await();
            System.out.println("Los arbitros ya se han preparado");
        } catch (InterruptedException e) {
        }
        
        try {
            fin_partido.await();
            System.out.println("Todos los jugadores han terminado el partido");
        } catch (InterruptedException e) {
        }
    }
}
