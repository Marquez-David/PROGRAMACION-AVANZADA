/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba_partido;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Darks
 */
public class Amigo extends Thread {

    private String equipo;
    private CyclicBarrier llegada_campo;
    private String nombre;
    private int equipo_ganador;
    private CountDownLatch camisetas_amarillas_restantes;
    private CountDownLatch camisetas_azules_restantes;
    private CountDownLatch camisetas_blancas_restantes;
    private CyclicBarrier colocar_canastas;
    private CyclicBarrier fin_partido;
    private Lock lock = new ReentrantLock();

    public Amigo(int equipo_ganador, CyclicBarrier fin_partido, CyclicBarrier colocar_canastas, CyclicBarrier llegada_campo, String nombre, CountDownLatch camisetas_amarillas_restantes, CountDownLatch camisetas_azules_restantes, CountDownLatch camisetas_blancas_restantes) {
        this.llegada_campo = llegada_campo;
        this.nombre = nombre;
        this.camisetas_amarillas_restantes = camisetas_amarillas_restantes;
        this.camisetas_azules_restantes = camisetas_azules_restantes;
        this.camisetas_blancas_restantes = camisetas_blancas_restantes;
        this.colocar_canastas = colocar_canastas;
        this.fin_partido = fin_partido;
        this.equipo_ganador = equipo_ganador;
    }

    public void run() {
        try {
            llegar_campo();
            seleccionar_equipo();
            if("blanco".equals(this.equipo)){ //Son arbitros
               colocar_canastas(); 
            }
            if("azul".equals(this.equipo) || "amarillo".equals(this.equipo)) { //Si los amigos son jugadores
                finalizar_partido();
            }
            if("blanco".equals(this.equipo)){ //Son arbitros
                indicar_equipo_ganador();
            }
            if("amarillo".equals(this.equipo)){ //Si los amigos son jugadores
                System.out.println("Nombre: " + nombre + " color: " + equipo + " :)");
            }
            if("azul".equals(this.equipo)){
                System.out.println("Nombre: " + nombre + " color: " + equipo + " :(");
            }
        } catch (Exception ex) {
        }

    }
    
    public void indicar_equipo_ganador(){
        System.out.println(nombre + " indica que el equipo ganador es: " + this.equipo);
    }

    public void colocar_canastas() {
        try {
            long num = (long) (Math.random() * (6000 - 3000) + 3000); //Tarda en llegar entre 3-6 segundos
            sleep(num);
            System.out.println(this.nombre + " ha colocado la canasta en " + num + " segundos");
            colocar_canastas.await(); 
        } catch (InterruptedException | BrokenBarrierException e) {

        }
    }

    public void seleccionar_equipo() {

        lock.lock();
        try {
            while (this.equipo == null) {
                int rand = (int) (Math.random() * (3)); //Numero aleatorio entre 0-2
                //System.out.println(nombre + " selecciona " + rand);
                if ((rand == 0) && (camisetas_amarillas_restantes.getCount() > 0)) { //Si ha escogido esa camisetas y hay.
                    this.equipo = "amarillo";
                    camisetas_amarillas_restantes.countDown();
                    //System.out.println(nombre + " ha seleccionado amarillo, camisetas amarillas restante= " + camisetas_amarillas_restantes.getCount());
                } else if ((rand == 1) && (camisetas_azules_restantes.getCount() > 0)) {  //Si ha escogido esa camisetas y hay.
                    this.equipo = "azul";
                    camisetas_azules_restantes.countDown();
                    //System.out.println(nombre + " ha seleccionado azul, camisetas azules restante= " + camisetas_azules_restantes.getCount());
                } else if ((rand == 2) && (camisetas_blancas_restantes.getCount() > 0)) {
                    this.equipo = "blanco";
                    camisetas_blancas_restantes.countDown();
                    //System.out.println(nombre + " ha seleccionado blanco, camisetas blancas restante= " + camisetas_blancas_restantes.getCount());
                }
            }
        } finally {
            lock.unlock();
        }
        
        System.out.println(nombre + " ha seleccionado el equipo " + this.equipo);
    }

    public void llegar_campo() {
        try {
            long num = (long) (Math.random() * (3000 - 1000) + 1000); //Tarda en llegar entre 1-3 segundos
            sleep(num);
            System.out.println(nombre + " ha llegado al campo y ha tardado " + num + " segundos");
            llegada_campo.await();
        } catch (InterruptedException | BrokenBarrierException e) {

        }
    }
    
    public void finalizar_partido() {
        try {
            long num = (long) (Math.random() * (10000 - 5000) + 5000); //Tarda en llegar entre 5-10 segundos
            sleep(num);
            System.out.println(nombre + " ha finalizado el partido en " + num + " segundos");
            fin_partido.await();
        } catch (InterruptedException | BrokenBarrierException e) {

        }
    }

    public String getNombre() {
        return nombre;
    }

}
