package edu.ufp.inf.sd.rmi._04_diglib.server.db;

import java.util.List;

import edu.ufp.inf.sd.rmi._04_diglib.server.book.Book;

public interface DBMockupI {
    public void register(String u, String p);
    public boolean exists(String u, String p);
    public void insert(String t, String a);
    public List<Book> select(String t, String a);
}
