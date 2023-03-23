package edu.ufp.inf.sd.rmi._06_visitor.server.singletonfoldersoperation;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonFolderOperationsBooks implements SingletonFoldersOperationsI {
    
    private static SingletonFolderOperationsBooks singletonFolderOperationsBooks;
    private final File folderBooks;

    /** private - Avoid direct instantiation */
    private SingletonFolderOperationsBooks(String folder) {
        folderBooks = new File(folder);
    }
    
    public synchronized static SingletonFolderOperationsBooks createSingletonFolderOperationsBooks(String folder){
        if (singletonFolderOperationsBooks == null){
            singletonFolderOperationsBooks = new SingletonFolderOperationsBooks(folder);
        }
        return singletonFolderOperationsBooks;
    }
    
    @Override
    public Boolean createFile(String fname) {
        try {
            File newFile = new File(this.folderBooks.getAbsolutePath() + "/" + fname);
            return newFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(SingletonFolderOperationsBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean deleteFile(String fname) {
            File existingFile = new File(this.folderBooks.getAbsolutePath() + "/" + fname);
            return existingFile.delete();
    }

    
}

