package edu.ufp.inf.sd.rmi._04_diglib.server.diglibfactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ufp.inf.sd.rmi._04_diglib.server.db.*;
import edu.ufp.inf.sd.rmi._04_diglib.server.session.*;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.User;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.RemoteUserNotFoundException;

public class DigLibFactoryImpl extends UnicastRemoteObject implements DigLibFactoryRI {

    private DBMockupI db;
    
    public DigLibFactoryImpl() throws RemoteException {
        super();
        this.db = new DBMockup();
    }

    @Override
    public DigLibSessionRI login(User user) throws RemoteException {
        if (!this.db.exists(user.getUname(), user.getPword()))
            throw new RemoteUserNotFoundException("User not Found!");

        return this.db.session(user.getUname())
            .orElse(new DigLibSessionImpl(db, user));
    }

    
}
