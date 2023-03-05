package edu.ufp.inf.sd.rmi._04_diglib.server.session;

import java.rmi.RemoteException;

public class RemoteSessionExpiredException extends RemoteException {
    public RemoteSessionExpiredException(){
        super();
    }

    public RemoteSessionExpiredException(String s){
        super(s);
    }

    public RemoteSessionExpiredException(String s, Throwable t){
        super(s, t);
    }
}
