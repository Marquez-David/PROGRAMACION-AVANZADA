package suma_nums_concurrente;

/**
 * Calcula la suma de los multiplos de siete entre 1 y 10.000
 *
 * @author David
 */
public class Mult_siete extends Thread{

    private int x = 10000;
   private long sumatorio = 0;
    private boolean multiplo = false;
    
    //Constructor
    public Mult_siete(){
        
        //calcular_suma();
        
    }

    public void run() {

        for (int i = 1; i < x; i++) {
           
            if (es_multiplo(i)) {
                sumatorio += i;
            }
        }
    }

    public boolean es_multiplo(long num) {

        if (num % 7 == 0) { //Si el resto de la division de cualquier numero entre 7 = 0, es multiplo de siete.
            multiplo = true;
        }

        return multiplo;
    }
    
    public long sumatorio(){
        return sumatorio;
    }

}
