package edu.ufp.inf.sd.rmi._06_visitor.server;

import edu.ufp.inf.sd.rmi._04_diglib.server.db.DBMockup;
import edu.ufp.inf.sd.rmi._04_diglib.server.diglibfactory.DigLibFactoryImpl;
import edu.ufp.inf.sd.rmi._04_diglib.server.diglibfactory.DigLibFactoryRI;
import edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder.ElementFolderImpl;
import edu.ufp.inf.sd.rmi._06_visitor.server.elementfolder.ElementFolderRI;
import edu.ufp.inf.sd.rmi.util.rmisetup.SetupContextRMI;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.function.BiConsumer;
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
public class VisitorServer {

    private SetupContextRMI contextRMI;
    private ElementFolderRI stub;
    private final String booksFolderPATH = "/home/bitor/projects/SD/src/edu/ufp/inf/sd/rmi/_06_visitor/server/books";
    /**
     * 
     * @param args 
     */
    public VisitorServer(String args[]) {
        try {
            //============ List and Set args ============
            SetupContextRMI.printArgs(this.getClass().getName(), args);
            String registryIP = args[0];
            String registryPort = args[1];
            String serviceName = args[2];
            //============ Create a context for RMI setup ============
            this.contextRMI = new SetupContextRMI(this.getClass(), registryIP, registryPort, new String[]{serviceName});
        } catch (RemoteException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    private void rebindService() {
        try {
            //Bind service on rmiregistry and wait for calls
            if (this.contextRMI.getRegistry() != null) {
                this.stub = new ElementFolderImpl(this.booksFolderPATH);
                //Get service url (including servicename)
                String serviceUrl = this.contextRMI.getServicesUrl(0);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going MAIL_TO_ADDR rebind service @ {0}", serviceUrl);
                //============ Rebind servant ============
                this.contextRMI.getRegistry().rebind(serviceUrl, this.stub); // VisitorService -> ElementFolderRI stub
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "service bound and running. :)");
            } else {
                //System.out.println("HelloWorldServer - Constructor(): create registry on port 1099");
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "registry not bound (check IPs). :(");
                //registry = LocateRegistry.createRegistry(1099);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unused")
    private static void loadProperties() throws IOException {

        Logger.getLogger(Thread.currentThread().getName()).log(Level.INFO, "goig MAIL_TO_ADDR load props...");
        // create and load default properties
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream("defaultproperties.txt");
        defaultProps.load(in);
        in.close();

        BiConsumer<Object, Object> bc = (key, value) ->{
            System.out.println(key.toString()+"="+value.toString());
        };
        defaultProps.forEach(bc);

        // create application properties with default
        Properties props = new Properties(defaultProps);

        FileOutputStream out = new FileOutputStream("defaultproperties2.txt");
        props.store(out, "---No Comment---");
        out.close();
    }


    public static void main(String[] args) {
        if (args != null && args.length < 3) {
            System.err.println("usage: java [options] edu.ufp.sd._02_calculator.server.CalculatorServer <rmi_registry_ip> <rmi_registry_port> <service_name>");
            System.exit(-1);
        }
        VisitorServer dls = new VisitorServer(args);
        dls.rebindService();
    }
}
