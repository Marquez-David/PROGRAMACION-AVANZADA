
package ejemplo_cyclicbarrier;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Ejemplo_CyclicBarrier {

 
    public static void main(String[] args) {
       
        int num_jugadores = 4;
        CyclicBarrier barrera = new CyclicBarrier(num_jugadores+1);
        ArrayList<String> jugadores = new ArrayList<>();
        int ganador = (int) (Math.random() * 3);
        
        for(int i = 0; i<num_jugadores; i++){
            Jugador_Cartas j = new Jugador_Cartas(barrera, "Jugador-" + i, jugadores, ganador);
            j.start();
        }
        
        try{
            barrera.await();
            System.out.println("Ya han llegado todos los jugadores");
        }catch(Exception e) {
            
        }
      
    }
    
}
