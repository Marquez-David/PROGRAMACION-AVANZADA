/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import interfazgrafica.InterfazServidor;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Darks
 */
public class Niño extends Thread {

    
    //Atributos
    private final String idNiño;
    private final String[] zonasParque = {"Columpios", "Tobogan", "Tiovivo"};
    private String decision;
    private int edadNiño;
    private final int num;
    private final Parque p;
    private final Paso paso;
    private final InterfazServidor serv;
    private CountDownLatch contadorTiovivo;
    private boolean progFuncionando = true;

    public Niño(String idNiño, int num, Parque p, InterfazServidor serv, Paso paso, CountDownLatch contadorTiovivo) {
        this.idNiño = idNiño;
        this.num = num;
        this.p = p;
        this.serv = serv;
        this.paso = paso;
        this.contadorTiovivo = contadorTiovivo;
        asignarEdad();
    }

    @Override
    public void run() {
        try {
            progFuncionando = serv.isProgFuncionando();
            while (progFuncionando) {
                paso.mirar(0);
                elegirZonaParque();
                paso.mirar(0);
                montarAtraccion();
                progFuncionando = serv.isProgFuncionando();
            }
        } catch (InterruptedException ex) {
        }

    }

    public void montarAtraccion() throws InterruptedException {
        if ("Columpios".equals(decision)) {
            paso.mirar(0);
            p.getNiñosDecidiendo().remove(this.idNiño);
            p.actualizarInterfazniñosDecidiendo();
            paso.mirar(0);
            p.entrarColaColumpios(this);
            paso.mirar(0);
            p.montarseColumpios();
            try {
                sleep((long) (Math.random() * (2000 - 200) + 200)); //Tiempo aleatorio entre 0,2 y 2 segundos.
            } catch (InterruptedException ex) {
            }
            paso.mirar(0);
            p.salirColumpios(this);
        } else if ("Tobogan".equals(decision)) {
            paso.mirar(0);
            p.getNiñosDecidiendo().remove(this.idNiño);
            p.actualizarInterfazniñosDecidiendo();
            paso.mirar(0);
            p.entrarColaTobogan(this);
            paso.mirar(0);
            p.montarseTobogan();
            try {
                System.out.println("El niño esta subiendo por el tobogan");
                sleep(1200);
                if (p.isExpulsionNiño()) {
                    System.out.println("Se ha expulsado a " + this.getIdNiño() + " del tobogan porque tiene " + this.getEdadNiño() + " años");
                    p.salirTobogan();
                } else {
                    System.out.println("El niño se esta deslizando por el tobogan");
                    sleep(500);
                    //sleep(5000); //Pruebas para el vigilante
                    paso.mirar(0);
                    p.salirTobogan();
                }
            } catch (InterruptedException ex) {

            }

        } else { //La decision ha sido la del tiiovivo
            paso.mirar(0);
            p.getNiñosDecidiendo().remove(this.idNiño);
            p.actualizarInterfazniñosDecidiendo();
            paso.mirar(0);
            p.entrarColaTiovivo(this);
            paso.mirar(0);
            p.montarseTiovivo();
            contadorTiovivo.await();
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
            }
            paso.mirar(0);
            p.salirTiovivo(this);
        }
    }

    /**
     * Metodo que se encarga de elegir una zona del parque de forma aleatoria
     *
     * @throws java.lang.InterruptedException
     */
    public void elegirZonaParque() throws InterruptedException {
        p.getNiñosDecidiendo().add(idNiño);
        paso.mirar(0);
        p.actualizarInterfazniñosDecidiendo();
        paso.mirar(0);
        sleep((long) (Math.random() * (2000 - 200) + 200)); //Tomando la decision(2seg y 0.2seg)
        paso.mirar(0);
        int rand = (int) (Math.random() * 3); //Numero aleatorio entre 0-2
        decision = zonasParque[rand];
        //decision = "Tobogan"; //Pruebas para el vigilante
        //System.out.println(this.idNiño + " elije " + decision);
    }

    /**
     * Metodo que se encarga de asignar una edad entre 3 y 12 años a cada niño
     */
    public void asignarEdad() {
        edadNiño = 2 + (num % 10);
        //System.out.println(idNiño + " tiene " + edadNiño + " años.");
    }

    public String getIdNiño() {
        return idNiño;
    }

    public int getEdadNiño() {
        return edadNiño;
    }

    public boolean isProgFuncionando() {
        return progFuncionando;
    }

    public void setProgFuncionando(boolean progFuncionando) {
        this.progFuncionando = progFuncionando;
    }


}
