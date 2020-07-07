/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buffer_monitores;

import java.util.ArrayList;

/**
 *
 * @author Darks
 */
public class Buffer {
    
    private ArrayList<Integer> buf = new ArrayList<>();
    private int cap_max_buf = 20;
    private int num_elem = 0;
    private int resultado = 0;
    
    public synchronized void insertar_numero(int num) throws InterruptedException {
        
        while(num_elem == cap_max_buf) {
            wait();
        }
        
        buf.add(num);
        System.out.println("Añadimos el numero: " + num);
        System.out.println(buf);
        num_elem ++;
        notifyAll();
    }
    
    public synchronized void consumir_numero(String hola) throws InterruptedException {
        
        while(num_elem == 0){
            wait();
        }
        
        int num = buf.get(0);
        buf.remove(0);
        System.out.println("Eliminamos el numero: " + num);
        System.out.println(buf);
        resultado += num;
        System.out.println(resultado);
        num_elem --;     
        notifyAll();
    }
    
}
