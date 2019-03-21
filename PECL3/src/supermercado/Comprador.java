
package supermercado;

/**
 * Clase que simula un comprador del supermercado.
 * @author David Márquez Mínguez.
 */
public class Comprador extends Thread {
    
    private String id_comprador;
    private Supermercado supermercado;
    
    public Comprador(String id_comprador, Supermercado supermercado) {
        this.id_comprador = id_comprador;
        this.supermercado = supermercado;
    }
    
    public void run(){
        hola();
    }
    
    public void hola(){
        System.out.println(id_comprador);
    }
    
}
