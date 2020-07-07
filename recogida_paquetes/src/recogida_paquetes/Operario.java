package recogida_paquetes;

public class Operario extends Thread {

    private int id_paquete;
    Muelle muelle;

    public Operario(Muelle muelle) {

        this.muelle = muelle;
    }

    public void run() {

        for (int i = 0; i < 20; i++) {
            
            id_paquete = i;
            
            try {
                sleep((long) (Math.random()* ( 8 - 6 ) + 6) );
                muelle.extraer_paquete();
            } catch (InterruptedException ex) { }
        }

    }

}
