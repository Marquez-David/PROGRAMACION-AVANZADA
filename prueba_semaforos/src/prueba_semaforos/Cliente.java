/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba_semaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darks
 */
public class Cliente extends Thread{
    
    private String id;
    private Supermercado_prueba supermercado;
    
    public Cliente(String id, Supermercado_prueba supermercado){
        this.id = id;
        this.supermercado = supermercado;
    }
    
    public void run(){
        try {
            supermercado.entrar_supermercado(this);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
