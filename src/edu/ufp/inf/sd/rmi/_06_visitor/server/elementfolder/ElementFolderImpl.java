package edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ufp.inf.sd.rmi._06_visitor.server.singletonfoldersoperation.SingletonFolderOperationsBooks;
import edu.ufp.inf.sd.rmi._06_visitor.server.singletonfoldersoperation.SingletonFoldersOperationsI;
import edu.ufp.inf.sd.rmi._06_visitor.server.visitorfoldersoperation.VisitorFoldersOperationI;

public class ElementFolderImpl extends UnicastRemoteObject implements ElementFolderRI {
 
    SingletonFoldersOperationsI stateBooksFolder;

    /**
     * Create singleton containing remote file system (e.g. /books/...
     *                                                      /magazines/...
     *                                                )
     */
    public ElementFolderImpl(String bookfolders) throws RemoteException {
        super();
        this.stateBooksFolder = SingletonFolderOperationsBooks
            .createSingletonFolderOperationsBooks(bookfolders);
    }

    public SingletonFoldersOperationsI getStateBooksFolder() {
        return this.stateBooksFolder;
    }
    
    @Override
    public Object acceptVisitor(VisitorFoldersOperationI visitor) throws RemoteException {
        return visitor.visitConcreteElementStateBooks(this);
    }

}

