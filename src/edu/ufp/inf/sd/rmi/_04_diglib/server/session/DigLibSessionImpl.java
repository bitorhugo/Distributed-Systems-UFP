package edu.ufp.inf.sd.rmi._04_diglib.server.session;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.ufp.inf.sd.rmi._04_diglib.server.book.Book;
import edu.ufp.inf.sd.rmi._04_diglib.server.db.DBMockupI;

public class DigLibSessionImpl extends UnicastRemoteObject implements DigLibSessionRI {

    private DBMockupI db;
    
    public DigLibSessionImpl(DBMockupI db) throws RemoteException {
        super();
        this.db = db;
    }

    @Override
    public List<Book> search(String title, String author) throws RemoteException {
        return this.db.select(title, author);
    }
    
}
