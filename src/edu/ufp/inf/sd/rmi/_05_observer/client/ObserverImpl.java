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
        this();
        this.username = username;
        this.obsGUI = obsGUI;
        this.subject = subject;
        this.subject.attach(this); // attach observer
    }

    public SubjectRI getSubject() {
        return this.subject;
    }

    public State getLastObserverState() throws RemoteException{
        return this.subject.getState();
    }

    @Override
    public void update() throws RemoteException {
        try {
            if (this.subject.getState().getId().compareTo(this.username) == 0) { // current user msg goes to the right
                this.obsGUI.doc.insertString(this.obsGUI.doc.getLength(), this.subject.getState().toString() + "\n", this.obsGUI.rightAlign);
            }
            else {
                this.obsGUI.doc.insertString(this.obsGUI.doc.getLength(), this.subject.getState().toString() + "\n", this.obsGUI.leftAlign);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    
}

