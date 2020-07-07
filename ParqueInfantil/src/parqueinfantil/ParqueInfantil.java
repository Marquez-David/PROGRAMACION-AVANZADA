/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import interfazgrafica.InterfazServidor;
import static java.lang.Thread.sleep;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Darks
 */
public class ParqueInfantil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

       CountDownLatch contadorTiovivo = new CountDownLatch(5);
        
        int numNiñosTotales = 50;
        Paso paso = new Paso(1);

        InterfazServidor serv = new InterfazServidor(paso);
        serv.setVisible(true);
        
        Parque p = new Parque(serv, paso, contadorTiovivo);
        
        //Creamos los 50 hilos(niños)
        for (int i = 0; i < numNiñosTotales; i++) {
            paso.mirar(0);
            sleep(200);
            Niño n = new Niño("Niño-" + i, i, p, serv, paso, contadorTiovivo);
            paso.mirar(0);
            n.start();
        }
    }

}
