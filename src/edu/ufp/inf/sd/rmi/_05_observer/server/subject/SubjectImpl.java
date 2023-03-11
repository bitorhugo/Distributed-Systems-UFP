package edu.ufp.inf.sd.rmi._05_observer.server.subject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import edu.ufp.inf.sd.rmi._05_observer.client.ObserverRI;
import edu.ufp.inf.sd.rmi._05_observer.server.state.State;

public class SubjectImpl extends UnicastRemoteObject implements SubjectRI {

    private State state;
    private List<ObserverRI> observerList = new ArrayList<>();
    
    public SubjectImpl() throws RemoteException {
        super();
    }

    public SubjectImpl(State state) throws RemoteException {
        super();
        this.state = state;
    }

    @Override
    public void attach(ObserverRI obs) throws RemoteException {
        this.observerList.add(obs);
    }

    @Override
    public void detach(ObserverRI obs) throws RemoteException {
        this.observerList.remove(obs);
    }

    @Override
    public State getState() throws RemoteException {
        return this.state;
    }

    @Override
    public void setState(State state) throws RemoteException {
        this.state = state;
    }

    public void notifyObservers() {
        this.observerList.forEach(o -> {
            try {
                o.getSubject().setState(this.state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

}
