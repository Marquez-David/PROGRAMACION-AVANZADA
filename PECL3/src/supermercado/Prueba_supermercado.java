package supermercado;

/**
 * Clase que se encarga de la creacion y ejecucion de los hilos.
 *
 * @author David Márquez Mínguez.
 */
public class Prueba_supermercado {

    public static void main(String[] args) {

        Supermercado supermercado = new Supermercado();

        for (int i = 0; i < 1000; i++) {
            Comprador comprador = new Comprador("Comprador-" + 0000 + i, supermercado);
            comprador.start();
        }
    }

}
