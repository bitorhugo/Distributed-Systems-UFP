package edu.ufp.inf.sd.rmi._04_diglib.server.diglibfactory;

import java.rmi.Remote;
import java.rmi.RemoteException;


import edu.ufp.inf.sd.rmi._04_diglib.server.session.DigLibSessionRI;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.User;

public interface DigLibFactoryRI extends Remote {
    public DigLibSessionRI login(User user) throws RemoteException;
    public DigLibSessionRI register(User user) throws RemoteException;
}
