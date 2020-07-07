/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suma_nums_concurrente;

/**
 * Calcula numero primos entre 1 y 10000
 * @author David
 */
public class Num_primos extends Thread{

    private int n = 0;
    
    public Num_primos(){
        
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

    public void run() {

        for (int i = 1; i <= 10000; i++) {

            if (esPrimo(i)) {

                n+= i;

            }
        }
    }

    public int cuantos() {

        return n;

    }

}
