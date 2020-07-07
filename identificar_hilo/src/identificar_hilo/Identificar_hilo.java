
package identificar_hilo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Programa que se encarga de crear 1000 hilos donde cada uno imprimira su identificador
 * @author David
 */
public class Identificar_hilo {

    public static void main(String[] args){
        
        for(int i = 0; i<1000; i++){
            Hilo h = new Hilo(i);
            
            h.start();
            
        }
    }
    
}
