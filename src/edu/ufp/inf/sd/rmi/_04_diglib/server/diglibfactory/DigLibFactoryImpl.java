package edu.ufp.inf.sd.rmi._04_diglib.server.diglibfactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import edu.ufp.inf.sd.rmi._04_diglib.server.db.*;
import edu.ufp.inf.sd.rmi._04_diglib.server.session.*;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.User;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.RemoteUserNotFoundException;

public class DigLibFactoryImpl extends UnicastRemoteObject implements DigLibFactoryRI {

    private DBMockupI db;
    HashMap<User, DigLibSessionImpl> sessions;
    
    public DigLibFactoryImpl() throws RemoteException {
        super();
        this.db = new DBMockup();
    }

    @Override
    public DigLibSessionRI login(User user) throws RemoteException {
        if (this.db.exists(user.getUname(), user.getPword())) {
            return this.sessions.containsKey(user)
                ? this.sessions.get(user)
                : this.sessions.put(user, new DigLibSessionImpl(this.db));
        }
        else {
            throw new RemoteUserNotFoundException("Wrong Credentials");
        }
    }
    
}
