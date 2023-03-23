package edu.ufp.inf.sd.rmi._06_visitor.client;

import edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder.ElementFolderRI;
import edu.ufp.inf.sd.rmi._06_visitor.server.visitorfoldersoperation.VisitorFoldersOperationCreateFile;
import edu.ufp.inf.sd.rmi._06_visitor.server.visitorfoldersoperation.VisitorFoldersOperationDeleteFile;
import edu.ufp.inf.sd.rmi._06_visitor.server.visitorfoldersoperation.VisitorFoldersOperationI;
import edu.ufp.inf.sd.rmi.util.rmisetup.SetupContextRMI;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Projecto SD</p>
 * <p>
 * Description: Projecto apoio aulas SD</p>
 * <p>
 * Copyright: Copyright (c) 2017</p>
 * <p>
 * Company: UFP </p>
 *
 * @author Rui S. Moreira
 * @version 3.0
 */
public class VisitorClient {

    private SetupContextRMI contextRMI;
    private ElementFolderRI stub;


    public VisitorClient(String args[]) {
        try {
            //List ans set args
            SetupContextRMI.printArgs(this.getClass().getName(), args);
            String registryIP = args[0];
            String registryPort = args[1];
            String serviceName = args[2];
            //Create a context for RMI setup
            // http:localhost:1099/{ServiceName}
            this.contextRMI = new SetupContextRMI(this.getClass(), registryIP, registryPort, new String[]{serviceName});
        } catch (Exception e) {
            Logger.getLogger(VisitorClient.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void lookup() {
        Registry reg = this.contextRMI.getRegistry();
        if (reg == null) {
            System.out.println("Registry is null");
        }
        String serviceUrl = contextRMI.getServicesUrl(0);
        try {
            this.stub = (ElementFolderRI) reg.lookup(serviceUrl);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
    
    private void playService() {
        try {
             VisitorFoldersOperationI create = new VisitorFoldersOperationCreateFile("test.txt"); // first create an operation
             VisitorFoldersOperationI delete = new VisitorFoldersOperationDeleteFile("test.txt"); // first create an operation
             this.stub.acceptVisitor(delete); // execute the operation
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.err.println("usage: java [options] edu.ufp.sd.inf.rmi._02_calculator.server.CalculatorClient <rmi_registry_ip> <rmi_registry_port> <service_name>");
            System.exit(-1);
        } else {
            VisitorClient dlc = new VisitorClient(args);
            dlc.lookup();
            dlc.playService();
        }
    }
}
