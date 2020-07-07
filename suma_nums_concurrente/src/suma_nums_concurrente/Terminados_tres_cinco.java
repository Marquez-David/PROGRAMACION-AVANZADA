package suma_nums_concurrente;

public class Terminados_tres_cinco extends Thread {

    private int x = 10000;
    private long sumador = 0;

    public Terminados_tres_cinco() {

    }

    public boolean terminado_correcto(int num) {

        boolean termina = false;

        //Comprobamos que el numero termina en 3
        if (num % 10 == 3 || num % 10 == 5) {
            termina = true;
        }

        return termina;

    }

    public void run() {

        for (int i = 1; i < x; i++) {
            if (terminado_correcto(i)) {
                sumador += i;
            }
        }
    }

    public long sumatorio() {
        return sumador;
    }

}
