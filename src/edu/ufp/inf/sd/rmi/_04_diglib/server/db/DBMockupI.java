package edu.ufp.inf.sd.rmi._04_diglib.server.db;

import edu.ufp.inf.sd.rmi._04_diglib.server.book.Book;

public interface DBMockupI {
    public void register(String u, String p);
    public boolean exists(String u, String p);
    public void insert(String t, String a);
    public Book[] select(String t, String a);
}
