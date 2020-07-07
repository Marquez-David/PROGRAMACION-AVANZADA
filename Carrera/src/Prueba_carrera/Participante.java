/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba_carrera;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author Darks
 */
public class Participante extends Thread {

    private String dorsal;
    private CyclicBarrier barrera_inicio;

    public Participante(CyclicBarrier barrera_inicio, String dorsal) {
        this.barrera_inicio = barrera_inicio;
        this.dorsal = dorsal;
    }

    public void run() {

        
        try {
            barrera_inicio.await();
            long num = (long) (Math.random() * (5000 - 3000) + 3000);
            sleep(num);
            System.out.println(dorsal + " ha acabado la carrera" + " ha durado: " + num);
        } catch (Exception ex) {
        }

    }

    public String getDorsal() {
        return dorsal;
    }

}
