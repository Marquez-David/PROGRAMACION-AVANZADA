/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer_semaforos;

/**
 *
 * @author Darks
 */
public class Prueba_buffer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Buffer buf = new Buffer();
        
        //Creamos productores;
        Productor A = new Productor(buf);
        Productor B = new Productor(buf);
        Productor C = new Productor(buf);
        //Productor D = new Productor(buf);
        //Productor E = new Productor(buf);
        
        //Creamos los consumidores;
        Consumidor Jose = new Consumidor(buf, "Jose");
        Consumidor Ana = new Consumidor(buf, "Ana");
        Consumidor Maria = new Consumidor(buf, "Maria");
        
        //Inciamos los hilos
        A.start();
        B.start();
        C.start();
        Jose.start();
        Ana.start();
        Maria.start();
    }
    
}
