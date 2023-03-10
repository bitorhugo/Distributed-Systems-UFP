package edu.ufp.inf.sd.rmi._05_observer.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ufp.inf.sd.rmi._05_observer.server.subject.SubjectRI;

public class ObserverImpl extends UnicastRemoteObject implements ObserverRI {

    public ObserverImpl() throws RemoteException {
        super();
    }
    
    public ObserverImpl(String username, ObserverGuiClient obsGUI, SubjectRI subject) throws RemoteException {
        super();
    }
}
