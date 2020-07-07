
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

public class Pintor extends Thread {

    JButton b;

    Color paleta[] = new Color[10];

    public Pintor(JButton b) {

        this.b = b;

        paleta[0] = Color.BLACK;

        paleta[1] = Color.RED;

        paleta[2] = Color.GREEN;

        paleta[3] = Color.BLUE;

        paleta[4] = Color.YELLOW;

        paleta[5] = Color.DARK_GRAY;

        paleta[6] = Color.PINK;

        paleta[7] = Color.ORANGE;

        paleta[8] = Color.WHITE;

        paleta[9] = Color.MAGENTA;

    }

    public void run() {

        while (true) {

            //Lo pintamos de negro durante 2-4 seg
            b.setBackground(paleta[0]);
            long rand1 = (long) (Math.random() * (3 - 1)) + 2;
            System.out.println("Negro: " + rand1 + " seg");

            try {
                sleep(rand1 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pintor.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Lo pintamos de un color aleatorio entre rojo-verde-azul-amarillo durante 3-5 seg
            int rand2 = (int) ((long) (Math.random() * 4) + 1);
            b.setBackground(paleta[rand2]);

            long rand3 = (long) (Math.random() * (5 - 2)) + 3;
            System.out.println("Rojo-verde-azul-amarillo: " + rand3 + " seg");

            try {
                sleep(rand3 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pintor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
