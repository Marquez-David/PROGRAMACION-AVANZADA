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
public class Arbitro extends Thread{
    
    private String idArbitro;
    private CountDownLatch barreraLlegadaCampo;
    private CountDownLatch barreraPreparacionArbitros;
    private CountDownLatch barreraFinPartido;
    
    public Arbitro(CountDownLatch barreraLlegadaCampo, CountDownLatch barreraPreparacionArbitros,CountDownLatch barreraFinPartido,String idArbitro){
        this.barreraLlegadaCampo = barreraLlegadaCampo;
        this.barreraPreparacionArbitros = barreraPreparacionArbitros;
        this.barreraFinPartido = barreraFinPartido;
        this.idArbitro = idArbitro;
    }
    
    public void run(){
        try {
            barreraLlegadaCampo.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Arbitro.class.getName()).log(Level.SEVERE, null, ex);
        }
        prepararse();
        barreraPreparacionArbitros.countDown();
        
        try {
            barreraFinPartido.await();
            System.out.println("EL PARTGDIO HA TERMINADO");
        } catch (InterruptedException ex) {}

    }
    
    public void prepararse(){
        try {
            sleep((long) (Math.random() * (7000 - 2000)+2000));
            System.out.println("El arbitro " + idArbitro + " se ha preparado");
        } catch (InterruptedException ex) {}
    }
    
}
