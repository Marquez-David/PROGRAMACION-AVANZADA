/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Darks
 */
public class ObjetoRemoto extends UnicastRemoteObject implements InterfaceObjetoRemoto{
    
    private String edadNiño;
    
    public ObjetoRemoto() throws RemoteException {
        this.edadNiño = "";
    }

    @Override
    public String getEdadNiño() throws RemoteException{
        return edadNiño;
    }

    public void setEdadNiño(String edadNiño) {
        this.edadNiño = edadNiño;
    }
    
    
}
