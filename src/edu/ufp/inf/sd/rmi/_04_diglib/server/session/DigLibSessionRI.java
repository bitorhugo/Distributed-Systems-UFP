package edu.ufp.inf.sd.rmi._04_diglib.server.session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.ufp.inf.sd.rmi._04_diglib.server.book.Book;

public interface DigLibSessionRI extends Remote {
    List<Book> search(String title, String author) throws RemoteException;
}
