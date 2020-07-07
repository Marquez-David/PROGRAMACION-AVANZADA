/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museo;

/**
 *
 * @author Darks
 */
public class PruebaMuseo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int numPersonas = 50;
        
        Museo museo = new Museo();
        
        for(int i = 0; i<numPersonas; i++){
            Persona p = new Persona(museo, "Persona " + i);
            p.start();
        }
    }
    
}
