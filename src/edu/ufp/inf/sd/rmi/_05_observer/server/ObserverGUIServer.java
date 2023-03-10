package edu.ufp.inf.sd.rmi._05_observer.server;

import edu.ufp.inf.sd.rmi._05_observer.server.subject.SubjectImpl;
import edu.ufp.inf.sd.rmi._05_observer.server.subject.SubjectRI;
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
public class ObserverGUIServer {

    private SetupContextRMI contextRMI;
    private SubjectRI stub;

    /**
     * 
     * @param args 
     */
    public ObserverGUIServer(String args[]) {
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
                this.stub = new SubjectImpl();
                //Get service url (including servicename)
                String serviceUrl = this.contextRMI.getServicesUrl(0);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going MAIL_TO_ADDR rebind service @ {0}", serviceUrl);
                //============ Rebind servant ============
                this.contextRMI.getRegistry().rebind(serviceUrl, this.stub); // DigLibService -> DigLibRI stub
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "service bound and running. :)");
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "registry not bound (check IPs). :(");
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
        ObserverGUIServer dls = new ObserverGUIServer(args);
        dls.rebindService();
    }
}

