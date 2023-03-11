package edu.ufp.inf.sd.rmi._05_observer.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ufp.inf.sd.rmi._05_observer.server.state.State;
import edu.ufp.inf.sd.rmi._05_observer.server.subject.SubjectRI;

public class ObserverImpl extends UnicastRemoteObject implements ObserverRI {

    private String username;
    private SubjectRI subject;
    private ObserverGUIClient obsGUI;
    
    public ObserverImpl() throws RemoteException {
        super();
    }
    
    public ObserverImpl(String username, ObserverGUIClient obsGUI, SubjectRI subject) throws RemoteException {
        super();
        this.username = username;
        this.obsGUI = obsGUI;
        this.subject = subject;
        
        this.subject.attach(this); // attach observer
    }

    @Override
    public SubjectRI getSubject() throws RemoteException {
        return this.subject;
    }

    @Override
    public State getLastObserverState() throws RemoteException {
        return this.subject.getState();
    }

}
