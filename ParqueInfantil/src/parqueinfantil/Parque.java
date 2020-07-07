/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import interfazgrafica.InterfazServidor;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darks
 */
public class Parque {

    /*//Colas de niños esperando
    private ArrayList<Niño> colaTobogan = new ArrayList<>();
    private ArrayList<Niño> colaColumpios = new ArrayList<>();
    private ArrayList<Niño> colaTiovivo = new ArrayList<>();

    //Cola de niños en atracciones
    private ArrayList<Niño> tobogan = new ArrayList<>();
    private ArrayList<Niño> columpios = new ArrayList<>();
    private ArrayList<Niño> tiovivo = new ArrayList<>();*/

    //Lista de niños que estan decidiendo
    //private ArrayList<String> niñosDecidiendo = new ArrayList<>();

    //Atributos de control de hilos del tobogan
    private final Lock controlColaTobogan = new ReentrantLock();
    private final Lock controlTobogan = new ReentrantLock();
    private final Condition toboganLleno = controlTobogan.newCondition();
    private final Condition toboganVacio = controlTobogan.newCondition();

    //Atributos de control de hilos de columpios
    private final Lock controlColaColumpios = new ReentrantLock();
    private final Lock controlColumpios = new ReentrantLock();
    private final Condition columpiosLlenos = controlColumpios.newCondition();
    private final Condition columpiosVacios = controlColumpios.newCondition();

    //Atributos control de hilos de tiovivo
    private final Lock controlColaTiovivo = new ReentrantLock();
    private final Lock controlTiovivo = new ReentrantLock();
    private final Condition tiovivoLleno = controlTiovivo.newCondition();
    private final Condition tiovivoVacio = controlTiovivo.newCondition();

    //Atributos de control de la aplicacion
    private int numNiñosToboganMax = 1;
    private int numNiñosColumpiosMax = 3;
    private int numNiñosTiovivoMax = 5;
    private boolean expulsionNiño = false;

    //Objetos de otras clases
    private InterfazServidor interfazServidor;
    private CountDownLatch contadorTiovivo;
    private ObjetoRemoto objetoRemoto;

    //Objetos de otras clases
    private Paso paso;

    /**
     * Constructor
     *
     * @param interfazServidor
     * @param paso
     * @param contadorTiovivo
     */
    public Parque(InterfazServidor interfazServidor, Paso paso, CountDownLatch contadorTiovivo) {
        this.interfazServidor = interfazServidor;
        this.paso = paso;
        this.contadorTiovivo = contadorTiovivo;

        try {
            objetoRemoto = new ObjetoRemoto(); //Creamos el objeto remoto
            LocateRegistry.createRegistry(1099); //Arranca rmiregistry local en el puerto 1099
            Naming.rebind("//localhost/ObjetoRemoto", objetoRemoto);   //rebind sólo funciona sobre una url del equipo local
            System.out.println("El Objeto Remoto ha quedado registrado");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Funcion que se encarga de gestionar la entrada de los niños a la cola del
     * tobogan
     *
     * @param n
     */
    public void entrarColaTobogan(Niño n) {
        paso.mirar(0);
        controlColaTobogan.lock();
        try {
            colaTobogan.add(n); //Añadimos al objeto niño al arraylist que representa la cola
            actualizarColas(); //Actualizamos la interfaz de las colas
            System.out.println(n.getIdNiño() + " ha entrado en la cola del tobogan");
        } finally {
            controlColaTobogan.unlock();
        }
    }

    /**
     * Funcion que se encarga de gestionar el momento en el que los niños se
     * montan al tobogan
     */
    public void montarseTobogan() {
        paso.mirar(0);
        controlTobogan.lock();
        try {
            while (tobogan.size() == numNiñosToboganMax) { //Mientra el tobogan esta ocupado, no pueden subir niños.
                toboganLleno.await(); //Espero a que el tobogan no este ocupado
                System.out.println("El tobogan esta ocupado!");
            }
            Niño n = colaTobogan.get(0);
            paso.mirar(0);
            colaTobogan.remove(0);
            actualizarColas(); //Actualizo la interfaz de las colas
            paso.mirar(0);
            tobogan.add(n); //Añado al porimer niño de la cola del tobogan a la atraccion
            System.out.println("Se ha añadido el niño " + n.getIdNiño() + " al tobogan");
            toboganVacio.signal(); //Aviso de que el tobogan no esta vacio
            actualizarInterfazTobogan(); //Actualizo la interfaz del tobogan
        } catch (InterruptedException ex) {
        } catch (RemoteException ex) {
            Logger.getLogger(Parque.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            controlTobogan.unlock();
        }
    }

    /**
     * Funcion que representa la accion que realizan los niños de salir del
     * tobogan
     */
    public void salirTobogan() {
        paso.mirar(0);
        controlTobogan.lock();
        try {
            while (tobogan.size() == 0) { //Mientra el tobogan este vacio, no pueden salir niños
                toboganVacio.await(); //Espero a que el tobogan no este vacio
                System.out.println("El tobogan esta vacio ");
            }

            System.out.println(tobogan.get(0).getIdNiño() + " ha salido del tobogan");
            tobogan.remove(0);
            toboganLleno.signal(); //Aviso de que el tobogan ya no esta lleno
            actualizarInterfazTobogan();
        } catch (InterruptedException ex) {
        } catch (RemoteException ex) {
            Logger.getLogger(Parque.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            controlTobogan.unlock();
        }
    }

    /**
     * Funcion que gestiona la entrada de niños a los columpios
     *
     * @param n
     */
    public void entrarColaColumpios(Niño n) {
        paso.mirar(0);
        controlColaColumpios.lock();
        try {
            colaColumpios.add(n);
            actualizarColas(); //Actualizo la interfaz
            System.out.println(n.getIdNiño() + " ha entrado en la cola de columpios.");
        } finally {
            controlColaColumpios.unlock();
        }
    }

    /**
     * Fucnion que se encarga de gestionar el momento ene l que los niños se
     * suben a los columpios
     */
    public void montarseColumpios() {
        paso.mirar(0);
        controlColumpios.lock();
        try {
            while (numNiñosColumpiosMax == columpios.size()) { //Mientras que los columpios esten llenos
                columpiosLlenos.await(); //Los niños de la cola esperan a que haya sitio libres
                System.out.println("Los columpios estan llenos!");
            }
            Niño n = colaColumpios.get(0);
            paso.mirar(0);
            colaColumpios.remove(0);
            actualizarColas();//Actualizo la interfaz
            paso.mirar(0);
            columpios.add(n); //Añado el primer niño que estaba en la cola a los columpios
            System.out.println("Se ha añadido el niño " + n.getIdNiño() + " a los columpios");
            columpiosVacios.signal(); //Aviso de que los columpios ya no estan vacios
            actualizarInterfazColumpios(); //Actualizo la interfaz de los columpios
        } catch (InterruptedException ex) {

        } finally {
            controlColumpios.unlock();
        }
    }

    /**
     * Gestiona la salida de los niños de los columpios
     *
     * @param n
     */
    public void salirColumpios(Niño n) {
        paso.mirar(0);
        controlColumpios.lock();
        try {
            while (columpios.size() == 0) { //Mientras los columpios esten vacios
                columpiosVacios.await();
                System.out.println("Los columpios estan vacios!");
            }
            columpios.remove(n); //Eliminamos al niño que quiere salir de los columpios
            System.out.println(n.getIdNiño() + " ha salido de los columpios.");
            columpiosLlenos.signal();
            actualizarInterfazColumpios(); //Actualizo la interfaz de los columpios
        } catch (InterruptedException ex) {

        } finally {
            controlColumpios.unlock();
        }
    }

    /**
     * Gestiona la entrada de niños a la cola del tiovivo
     *
     * @param n
     */
    public void entrarColaTiovivo(Niño n) {
        paso.mirar(0);
        controlColaTiovivo.lock();
        try {
            colaTiovivo.add(n); //Añado al niño a la cola
            actualizarColas(); //Actualizo la interfaz
            System.out.println(n.getIdNiño() + " ha entrado en la cola del tiovivo");
        } finally {
            controlColaTiovivo.unlock();
        }
    }

    /**
     * Gestiona el momento en el que los niños se montan el el tiovivo
     */
    public void montarseTiovivo() {
        paso.mirar(0);
        controlTiovivo.lock();
        try {
            while (tiovivo.size() == numNiñosTiovivoMax) { //Mientras erl tiovivo este lleno
                tiovivoLleno.await();//Los hilos espetan para entrar
                System.out.println("El tiovivo est lleno!");
            }
            Niño n = colaTiovivo.get(0);
            paso.mirar(0);
            colaTiovivo.remove(0);
            actualizarColas();//Actualizo la interfaz
            paso.mirar(0);
            tiovivo.add(n); //Añado al prtimer niño que estuviese esperando en la cola
            System.out.println("Se ha añadido el niño " + n.getIdNiño() + " al tiovivo");
            tiovivoVacio.signal(); //Aviso de que el tiovivo ya no esta vacio
            actualizarInterfazTiovivo(); //Actualizo la interfaz
            contadorTiovivo.countDown(); //Decremento el contador
            System.out.println("Contador del latch: " + contadorTiovivo.getCount());
        } catch (InterruptedException ex) {

        } finally {
            controlTiovivo.unlock();
        }
    }

    /**
     * Gestiona la salida de los niños de la atraccion
     *
     * @param n
     */
    public void salirTiovivo(Niño n) {
        paso.mirar(0);
        controlTiovivo.lock();
        try {
            while (tiovivo.size() == 0) { //Mientras la atraccion este vacia
                tiovivoVacio.await();
                System.out.println("El tiovivo esta vacio!");
            }
            tiovivo.remove(n);
            System.out.println(n.getIdNiño() + " ha salido del tiovivo.");
            tiovivoLleno.signal();//Aviso de que la atraccion ya no esta llena
            actualizarInterfazTiovivo();
        } catch (InterruptedException ex) {

        } finally {
            controlTiovivo.unlock();
        }
    }

    /**
     * Actualizo la interfaz del servidor
     */
    public void actualizarColas() {
        interfazServidor.getColaEsperaToboganTextField().setText(colaToboganToString());
        interfazServidor.getColaEsperaColumpiosTextField().setText(colaColumpiosToString());
        interfazServidor.getColaEsperaTiovivoTextField().setText(colaTiovivoToString());
        interfazServidor.setTamañoColaTobogan(colaTobogan.size());
        interfazServidor.setTamañoColaColumpios(colaColumpios.size());
        interfazServidor.setTamañoColaTiovivo(colaTiovivo.size());
    }

    //Metodos que se encargan de gestionar la actualizacion de la interfaz
    
    /**
     * Actualizo la interfaz del tobogan
     *
     * @throws RemoteException
     */
    public void actualizarInterfazTobogan() throws RemoteException {
        expulsionNiño = false;
        if (tobogan.size() == 0) {
            interfazServidor.getToboganTextField().setText("");
            interfazServidor.getAñosNiñoToboganTextField().setText("");
        } else {
            interfazServidor.getToboganTextField().setText(niñoToboganToString());
            interfazServidor.getAñosNiñoToboganTextField().setText(edadNiñoToboganToString());
            objetoRemoto.setEdadNiño(tobogan.get(0).getEdadNiño() + "");
            //Compara la edad del niño, como es un string es mas engorroso, pero lo que estoy haciendo es comparar que tenga mas de 7 años.
            //No introduzco la opcion de 1 año porque todo niño suma la unidad de su id + 2.
            if (tobogan.get(0).getEdadNiño() > 7) {
                expulsionNiño = true;
                //expulsarNiñoTobogan();
            }
        }

    }

    public void actualizarInterfazniñosDecidiendo() {
        interfazServidor.getNiñosDecidiendoTextField().setText(niñosDecidiendoToString());
    }

    public void actualizarInterfazColumpios() {
        interfazServidor.getColumpiosTextField().setText(niñosColumpiosToString());
    }

    public void actualizarInterfazTiovivo() {
        interfazServidor.getTiovivoTextField().setText(niñosTiovivoToString());
    }

    public String colaToboganToString() {
        String texto = "";
        for (int i = 0; i < colaTobogan.size(); i++) {
            texto += colaTobogan.get(i).getIdNiño() + "  ";
        }
        return texto;
    }

    public String niñosTiovivoToString() {
        String texto = "";
        for (int i = 0; i < tiovivo.size(); i++) {
            texto += tiovivo.get(i).getIdNiño() + "  ";
        }
        return texto;
    }

    public String niñosColumpiosToString() {
        String texto = "";
        for (int i = 0; i < columpios.size(); i++) {
            texto += columpios.get(i).getIdNiño() + "  ";
        }
        return texto;
    }

    public String colaColumpiosToString() {
        String texto = "";
        for (int i = 0; i < colaColumpios.size(); i++) {
            texto += colaColumpios.get(i).getIdNiño() + "  ";
        }
        return texto;
    }

    public String colaTiovivoToString() {
        String texto = "";
        for (int i = 0; i < colaTiovivo.size(); i++) {
            texto += colaTiovivo.get(i).getIdNiño() + "  ";
        }
        return texto;
    }

    public String niñoToboganToString() {
        return tobogan.get(0).getIdNiño();
    }

    public String niñosDecidiendoToString() {
        String texto = "";
        for (int i = 0; i < niñosDecidiendo.size(); i++) {
            texto += niñosDecidiendo.get(i) + "  ";
        }
        return texto;
    }

    public String edadNiñoToboganToString() {
        return tobogan.get(0).getEdadNiño() + "";
    }

    public ArrayList<String> getNiñosDecidiendo() {
        return niñosDecidiendo;
    }

    public boolean isExpulsionNiño() {
        return expulsionNiño;
    }

    public void setExpulsionNiño(boolean expulsionNiño) {
        this.expulsionNiño = expulsionNiño;
    }

}
