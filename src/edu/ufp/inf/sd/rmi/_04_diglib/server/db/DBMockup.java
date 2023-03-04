package edu.ufp.inf.sd.rmi._04_diglib.server.db;

import java.util.ArrayList;

import edu.ufp.inf.sd.rmi._04_diglib.server.book.Book;
import edu.ufp.inf.sd.rmi._04_diglib.server.user.User;

/**
 * This class simulates a DBMockup for managing users and books.
 *
 * @author rmoreira
 *
 */
public class DBMockup implements DBMockupI {

    private final ArrayList<Book> books;// = new ArrayList();
    private final ArrayList<User> users;// = new ArrayList();


    /**
     * This constructor creates and inits the database with some books and users.
     */
    public DBMockup() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        //Add 3 books
        books.add(new Book("Distributed Systems: principles and paradigms", "Tanenbaum"));
        books.add(new Book("Distributed Systems: concepts and design", "Colouris"));
        books.add(new Book("Distributed Computing Networks", "Tanenbaum"));
        //Add one user
        users.add(new User("guest", "ufp"));
    }

    /**
     * Registers a new user.
     * 
     * @param u username
     * @param p passwd
     */
    @Override
    public void register(String u, String p) {
        if (!exists(u, p)) {
            users.add(new User(u, p));
        }
    }

    /**
     * Checks the credentials of an user.
     * 
     * @param u username
     * @param p passwd
     * @return
     */
    @Override
    public boolean exists(String u, String p) {
        return this.users.stream().anyMatch((user) -> user.getUname().compareTo(u) == 0 && user.getPword().compareTo(p) == 0);
        // for (User usr : this.users) {
        //     if (usr.getUname().compareTo(u) == 0 && usr.getPword().compareTo(p) == 0) {
        //         return true;
        //     }
        // }
        // return false;
        //return ((u.equalsIgnoreCase("guest") && p.equalsIgnoreCase("ufp")) ? true : false);
    }

    /**
     * Inserts a new book into the DigLib.
     * 
     * @param t title
     * @param a authors
     */
    @Override
    public void insert(String t, String a) {
        books.add(new Book(t, a));
    }

    /**
     * Looks up for books with given title and author keywords.
     * 
     * @param t title keyword
     * @param a author keyword
     * @return
     */
    @Override
    public Book[] select(String t, String a) {
        Book[] abooks = null;
        ArrayList<Book> vbooks = new ArrayList<>();
        // Find books that match
        for (int i = 0; i < books.size(); i++) {
            Book book = (Book) books.get(i);
            System.out.println("DB - select(): book[" + i + "] = " + book.getTitle() + ", " + book.getAuthor());
            if (book.getTitle().toLowerCase().contains(t.toLowerCase()) && book.getAuthor().toLowerCase().contains(a.toLowerCase())) {
                System.out.println("DB - select(): add book[" + i + "] = " + book.getTitle() + ", " + book.getAuthor());
                vbooks.add(book);
            }
        }
        // Copy Vector->Array
        abooks = new Book[vbooks.size()];
        for (int i = 0; i < vbooks.size(); i++) {
            abooks[i] = (Book) vbooks.get(i);
        }
        return abooks;
    }
}
