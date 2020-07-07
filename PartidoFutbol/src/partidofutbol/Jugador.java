/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partidofutbol;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darks
 */
public class Jugador extends Thread{
    
    private CountDownLatch barreraLlegadaCampo;
    private CountDownLatch barreraPreparacionArbitros;
    private CountDownLatch barreraFinPartido;
    private int i = 0;
    private String idJugador;
    
    public Jugador(CountDownLatch barreraLlegadaCampo, CountDownLatch barreraPreparacionArbitros,CountDownLatch barreraFinPartido,String idJugador){
        this.barreraLlegadaCampo = barreraLlegadaCampo;
        this.barreraPreparacionArbitros = barreraPreparacionArbitros;
        this.barreraFinPartido = barreraFinPartido;
        this.idJugador = idJugador;
    }
    
    public void run(){
        System.out.println("El jugador " + idJugador + " ha llegado al campo");
        barreraLlegadaCampo.countDown();

        try {
            System.out.println("El jugador " + idJugador + " esta esperando a que los arbitros esten preparados");
            barreraPreparacionArbitros.await();
        } catch (InterruptedException ex) {}
        
        jugarPartido();
        barreraFinPartido.countDown();
        
    }
    
    public void jugarPartido(){
        try {
            sleep(10000);
            i++;
            if(i==1){
               System.out.println("El partido ha terminado");
            }
        } catch (InterruptedException ex) {}
    }
}
