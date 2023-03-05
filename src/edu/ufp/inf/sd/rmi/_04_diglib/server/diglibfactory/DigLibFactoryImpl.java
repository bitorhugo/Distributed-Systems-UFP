package edu.ufp.inf.sd.rmi._04_diglib.server.diglibfactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

import edu.ufp.inf.sd.rmi._04_diglib.server.db.*;
import edu.ufp.inf.sd.rmi._04_diglib.server.session.*;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.User;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.RemoteUserNotFoundException;

public class DigLibFactoryImpl extends UnicastRemoteObject implements DigLibFactoryRI {

    private DBMockupI db;
    static int SessionTimeInSeconds = 10;
    
    public DigLibFactoryImpl(DBMockupI db) throws RemoteException {
        super();
        this.db = db;
    }

    @Override
    // login creates a session (factory method)
    public DigLibSessionRI login(User user) throws RemoteException {
        if (!this.db.exists(user.getUname(), user.getPword()))
            throw new RemoteUserNotFoundException("User not Found!");

        DigLibSessionRI session = this.db.session(user.getUname())
            .orElse(new DigLibSessionImpl(db,
                                          user,
                                          LocalDateTime.now().plusSeconds(SessionTimeInSeconds)));

        Thread t = new Thread((Runnable)session);
        t.start(); // launch session in new thread
        
        return session;
    }

}
