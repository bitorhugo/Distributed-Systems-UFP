package edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder;

import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.ufp.inf.sd.rmi._06_visitor.server.visitorfoldersoperation.VisitorFoldersOperationI;

public interface ElementFolderRI extends Remote {
    public Object acceptVisitor(VisitorFoldersOperationI visitor) throws RemoteException;
}
