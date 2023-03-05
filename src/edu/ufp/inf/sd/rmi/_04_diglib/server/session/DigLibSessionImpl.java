package edu.ufp.inf.sd.rmi._04_diglib.server.session;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.ufp.inf.sd.rmi._04_diglib.server.book.Book;
import edu.ufp.inf.sd.rmi._04_diglib.server.db.DBMockupI;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.User;

public class DigLibSessionImpl extends UnicastRemoteObject implements DigLibSessionRI {

    private DBMockupI db;
    private User user;
    
    public DigLibSessionImpl(DBMockupI db, User user) throws RemoteException {
        super();
        this.db = db;
        this.user = user;
        this.db.addSession(user.getUname(), this);
    }

    @Override
    public List<Book> search(String title, String author) throws RemoteException {
        return this.db.select(title, author);
    }

    @Override
    public void logout() throws RemoteException {
        this.db.removeSession(this.user.getUname());
    }
    
}
