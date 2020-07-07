/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import interfazgrafica.InterfazVigilante;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 *
 * @author Darks
 */
public class Vigilante {
    
    private InterfazVigilante interfazVigilante;
    private InterfaceObjetoRemoto objetoRemoto;
    
    
    public Vigilante(InterfazVigilante interfazVigilante){
        this.interfazVigilante = interfazVigilante;
        try{
            objetoRemoto = (InterfaceObjetoRemoto) Naming.lookup("//127.0.0.1/ObjetoRemoto");
        }catch(Exception e){
            System.out.println("No se ha encontrado el objeto remoto");
            System.exit(1);//Cerramos la ejecución forzadamente.
        }
    }
    
    public void tareaVigilante(){
        try {
            interfazVigilante.getPruebaTextField().setText(objetoRemoto.getEdadNiño() + " años");
        } catch (RemoteException ex) {}
    }
}
