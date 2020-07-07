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
public class Carrera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int num_participantes = 30;
        CyclicBarrier barrera_inicio = new CyclicBarrier(num_participantes+1); //+1 para que el hilo principal tambien espere.

        for(int i = 0; i<num_participantes; i++){
            //Creamos a los participantes
            Participante p = new Participante(barrera_inicio,"dorsal-" + (i + 1)); //+1 para empezar en 1
            System.out.println(p.getDorsal() + " esta listo para empezar");
            p.start();
        }
        
        
        try{
            System.out.println("Se abre la barrera de incio.");
            barrera_inicio.await();    
        }catch(Exception e){ }
        
    }
    
}
