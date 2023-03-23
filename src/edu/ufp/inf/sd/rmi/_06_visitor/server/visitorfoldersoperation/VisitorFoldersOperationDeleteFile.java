package edu.ufp.inf.sd.rmi._06_visitor.server.visitorfoldersoperation;

import java.rmi.RemoteException;

import edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder.ElementFolderImpl;
import edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder.ElementFolderRI;

public class VisitorFoldersOperationDeleteFile extends RemoteException implements VisitorFoldersOperationI {

    private String fileToDelete;
    private String fileToDeleteWithPrefix;

    public VisitorFoldersOperationDeleteFile (String fileToDelete) throws RemoteException{
        this.fileToDelete = fileToDelete;
    }
    
    @Override
    public Object visitConcreteElementStateBooks(ElementFolderRI element) {
        ElementFolderImpl el = (ElementFolderImpl) element;
        return el.getStateBooksFolder().deleteFile(this.fileToDelete);
    }

    @Override
    public Object visitConcreteElementStateMagazines(ElementFolderRI element) {
        return null;
    }
}
