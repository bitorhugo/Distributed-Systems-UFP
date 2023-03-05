package edu.ufp.inf.sd.rmi._04_diglib.server.session;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

import edu.ufp.inf.sd.rmi._04_diglib.server.book.Book;
import edu.ufp.inf.sd.rmi._04_diglib.server.db.DBMockupI;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.User;

public class DigLibSessionImpl extends UnicastRemoteObject implements DigLibSessionRI, Runnable {

    private LocalDateTime sessionTimer;
    private DBMockupI db;
    private User user;
    
    public DigLibSessionImpl(DBMockupI db, User user) throws RemoteException {
        super();
        this.db = db;
        this.user = user;
        this.db.addSession(user.getUname(), this);
    }

    public DigLibSessionImpl(DBMockupI db, User user, LocalDateTime sessionTimer) throws RemoteException {
        this(db, user);
        this.sessionTimer = sessionTimer;
    }

    @Override
    public List<Book> search(String title, String author) throws RemoteSessionExpiredException {
        if (this.db.hasSession(this.user.getUname())) {
            return this.db.select(title, author);
        }
        else throw new RemoteSessionExpiredException("Error 419: Session Expired");
    }

    @Override
    public void logout() throws RemoteException {
        this.db.removeSession(this.user.getUname());
    }

    @Override
    public void run() {
        System.out.println("Session ticking...");
        while (this.sessionTimer.isAfter(LocalDateTime.now())) {
        }
        System.out.println("Session Expired!");
        this.db.removeSession(this.user.getUname());
    }
    
}
