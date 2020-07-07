package exposicion;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Visitante extends Thread {

    Exposicion expo;
    Paso p;

    public Visitante(int id, Exposicion expo, Paso p) {
        this.p = p;
        super.setName(String.valueOf(id));
        this.expo = expo;
    }

    public void run() {
        try {
            try {
                sleep((int) (3000 * Math.random()));
            } catch (InterruptedException e) {
            }
            p.mirar(0);
            expo.entrar(this); //Entra en la exposición, si hay hueco; y sino espera en la cola
            p.mirar(0);
            expo.mirar(this); //Está un tiempo dentro de la exposición
            p.mirar(0);
            expo.salir(this); //Sale de la exposición
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
