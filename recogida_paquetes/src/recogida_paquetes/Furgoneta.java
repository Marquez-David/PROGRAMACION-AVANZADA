package recogida_paquetes;

public class Furgoneta extends Thread {

    private int id_paquete;
    Muelle muelle;

    //Constructor
    public Furgoneta(Muelle muelle) {

        this.muelle = muelle;
    }

    public void run() {

        //La furgonetas llevaran paquetes hasta su capacidad maxima.
        for (int i = 0; i < 20; i++) {

            id_paquete = i;

            //Las furgonetas estan lleando el paquete.
            try {
                sleep((long) (Math.random() * (6 - 4) + 4));
                muelle.insertar_paquete();
            } catch (InterruptedException ex) {
              }

        }
    }
}
