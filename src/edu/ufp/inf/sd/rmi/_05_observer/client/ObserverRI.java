package edu.ufp.inf.sd.rmi._05_observer.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.ufp.inf.sd.rmi._05_observer.server.state.State;
import edu.ufp.inf.sd.rmi._05_observer.server.subject.SubjectRI;

public interface ObserverRI extends Remote {
    public SubjectRI getSubject() throws RemoteException;
    public State getLastObserverState() throws RemoteException;    
}
