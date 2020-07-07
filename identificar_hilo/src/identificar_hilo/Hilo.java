package identificar_hilo;

/**
 * Clase que se encarga de simular un hilo
 *
 * @author David
 */
public class Hilo extends Thread{

    private int id;

    public Hilo(int id) {
        this.id = id;
    }
    
    public void run() {
        id = id;
        System.out.println("Id: " + id);
    }
    
    

}
