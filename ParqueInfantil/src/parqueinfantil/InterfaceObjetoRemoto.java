/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueinfantil;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Darks
 */
public interface InterfaceObjetoRemoto extends Remote{
    String getEdadNiño() throws RemoteException;
}
