package edu.ufp.inf.sd.rmi._06_visitor.server.visitorfoldersoperation;

import java.io.Serializable;
import edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder.ElementFolderRI;

public interface VisitorFoldersOperationI extends Serializable {
    public Object visitConcreteElementStateBooks(ElementFolderRI element);
    public Object visitConcreteElementStateMagazines(ElementFolderRI element);
}
