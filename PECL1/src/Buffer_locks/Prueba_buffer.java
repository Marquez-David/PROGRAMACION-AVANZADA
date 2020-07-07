
package Buffer_locks;

/**
 * Clase de prueba, creacion y lanzamineto de hilos;
 * @author David Márquez y Victor Pablo.
 */
public class Prueba_buffer {

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
