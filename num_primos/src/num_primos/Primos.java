package num_primos;

/**
 * Clase que simula los datos de un numero primo.
 *
 * @author David
 */
public class Primos {

    private int x, y, n = 0;

    public Primos(int x, int y) {

        this.x = x;

        this.y = y;
    }

    private boolean esPrimo(int n) {

        int raiz = (int) Math.sqrt((double) n);

        boolean primo = true;

        int i = 2;

        while (primo && i <= raiz) {

            if (n % i == 0) {
                primo = false;
            }

            i++;

        }

        return primo;
    }

    public void calcular() {

        for (int i = x; i <= y; i++) {

            if (esPrimo(i)) {

                n++;

            }
        }
    }

    public int cuantos() {

        return n;

    }
}
