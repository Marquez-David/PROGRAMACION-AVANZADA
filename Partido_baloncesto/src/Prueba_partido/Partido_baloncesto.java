
package Prueba_partido;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class Partido_baloncesto {

    public static void main(String[] args) {
        
        int num_amigos = 12;
        int num_arbitros = 2;
        int num_jugadores = 10;
        CyclicBarrier llegada_campo = new CyclicBarrier(num_amigos+1);
        CyclicBarrier colocar_canastas = new CyclicBarrier(num_arbitros+1);
        CyclicBarrier fin_partido = new CyclicBarrier(num_jugadores+1);
        
        int num_camisetas_amarillas = 5;
        int num_camisetas_azules = 5;
        int num_camisetas_blancas = 2;  
        CountDownLatch camisetas_amarillas_restantes = new CountDownLatch(num_camisetas_amarillas);
        CountDownLatch camisetas_azules_restantes = new CountDownLatch(num_camisetas_azules);
        CountDownLatch camisetas_blancas_restantes = new CountDownLatch(num_camisetas_blancas);
        
        int equipo_ganador = (int) (Math.random() * 1 ); //0-> gana amarillo, 1-> gana azul
        
        for(int i = 0; i<num_amigos; i++){
            Amigo a = new Amigo(equipo_ganador, fin_partido, colocar_canastas, llegada_campo, "Amigo-" + i, camisetas_amarillas_restantes, camisetas_azules_restantes, camisetas_blancas_restantes);
            a.start();
            System.out.println(a.getNombre() + " sale de su casa.");
        }
        
        try{
            llegada_campo.await();
            System.out.println("Todos han llegado al campo. ");
        }catch(Exception e){ }
        
        try{
            colocar_canastas.await();
            System.out.println("Ya se han colocado las canastas. ");
        }catch(Exception e){ }
        
        try{
            fin_partido.await();
            System.out.println("Todos los jugadores han terminado el partido. ");
        }catch(Exception e){ }
        
    }
    
}
