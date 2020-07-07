/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museo;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darks
 */
public class Museo {
    
    
    private ArrayList<Persona> listaMuseo = new ArrayList<>();
    private ArrayList<Persona> colaEste = new ArrayList<>();
    private ArrayList<Persona> colaOeste = new ArrayList<>();
    
    private Lock controlMuseo = new ReentrantLock();
    private Condition museoLleno = controlMuseo.newCondition();
    private Condition museoVacio = controlMuseo.newCondition();
    
    private Lock controlColaEste = new ReentrantLock();
    private Condition colaEsteVacia = controlColaEste.newCondition();
    
    private Lock controlColaOeste = new ReentrantLock();
    private Condition colaOesteVacia = controlColaOeste.newCondition();
    
    private int aforoMuseo = 20;

    public void entrarColaEste(Persona p){
        controlColaEste.lock();
        try{
            colaEste.add(p);
        }finally{
            controlColaEste.unlock();
        }
    }

    public void entrarColaOeste(Persona p){
        controlColaOeste.lock();
        try{
            colaOeste.add(p);
        }finally{
            controlColaOeste.unlock();
        }
    }
   
    public void entrarMuseoEste(Persona p){
        controlMuseo.lock();
        entrarColaEste(p);
        try{
            while(aforoMuseo == listaMuseo.size()){
                museoLleno.await();
            }
            Persona persona = colaEste.get(0);
            colaEste.remove(0);
            listaMuseo.add(persona);
            System.out.println(persona.getIdPersona() + "ha entrado al museo por la entrada este");
            museoVacio.signal();
        } catch (InterruptedException ex) {
            
        }finally{
            controlMuseo.unlock();
        }
    }
    
    public void entrarMuseoOeste(Persona p){
        controlMuseo.lock();
        entrarColaOeste(p);
        try{
            while(aforoMuseo == listaMuseo.size()){
                museoLleno.await();
            }
            Persona persona = colaOeste.get(0);
            colaOeste.remove(0);
            listaMuseo.add(persona);
            System.out.println(persona.getIdPersona() + "ha entrado al museo por la entrada oeste");
            museoVacio.signal();
        } catch (InterruptedException ex) {
            
        }finally{
            controlMuseo.unlock();
        }
    }
    
    public void salirMuseo(Persona p){
        controlMuseo.lock();
        try{
            while(listaMuseo.size() == 0){
                museoVacio.await();
            }
            listaMuseo.remove(p);
            System.out.println(p.getIdPersona() + "ha salido del museo");
            museoLleno.signal();
        } catch (InterruptedException ex) {
            
        }finally{
            controlMuseo.unlock();
        }
    }
}
