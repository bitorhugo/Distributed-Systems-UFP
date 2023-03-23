package edu.ufp.inf.sd.rmi._06_visitor.server.visitorfoldersoperation;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder.ElementFolderImpl;
import edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder.ElementFolderRI;

public class VisitorFoldersOperationCreateFile implements VisitorFoldersOperationI {

    private String fileToCreate;
    private String fileToCreateWithPrefix;

    public VisitorFoldersOperationCreateFile (String newFolder) throws RemoteException {
        this.fileToCreate = newFolder;
    }
    
    @Override
    public Object visitConcreteElementStateBooks(ElementFolderRI element) {
        ElementFolderImpl el = (ElementFolderImpl) element;
        return el.getStateBooksFolder().createFile(this.fileToCreate);
    }

    @Override
    public Object visitConcreteElementStateMagazines(ElementFolderRI element) {
        return null;
    }

    public String getFileToCreate() {
        return this.fileToCreate;
    }

    public void setFileToCreate(String fileToCreate) {
        this.fileToCreate = fileToCreate;
    }
}
