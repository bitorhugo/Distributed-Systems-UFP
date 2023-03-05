package edu.ufp.inf.sd.rmi._04_diglib.server.db;

import java.util.List;
import java.util.Optional;

import edu.ufp.inf.sd.rmi._04_diglib.server.book.Book;
import edu.ufp.inf.sd.rmi._04_diglib.server.session.DigLibSessionRI;

public interface DBMockupI {
    public void register(String u, String p);
    public boolean exists(String u, String p);
    public void insert(String t, String a);
    public List<Book> select(String t, String a);
    public void addSession(String username, DigLibSessionRI session);
    public void removeSession(String username);
    public Optional<DigLibSessionRI> session(String username);
}
