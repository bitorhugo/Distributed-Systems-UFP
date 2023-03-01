package edu.ufp.inf.sd.rmi._03_pingpong.client;


import edu.ufp.inf.sd.rmi.util.rmisetup.SetupContextRMI;
import java.rmi.RemoteException;
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
public class PongClient {

    private SetupContextRMI contextRMI;

    public PongClient(String args[]) {             // args -> localhost 1099 PingPongService
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
            Logger.getLogger(PongClient.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void playService() {
        try {
            for (int i = 0; i < 5; ++i) {
                new PongImpl(contextRMI, i);
            }
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going MAIL_TO_ADDR finish, bye. ;)");
        } catch (RemoteException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.err.println("usage: java [options] edu.ufp.sd.inf.rmi._02_calculator.server.CalculatorClient <rmi_registry_ip> <rmi_registry_port> <service_name>");
            System.exit(-1);
        } else {
            //1. ============ Setup client RMI context ============
            PongClient hwc = new PongClient(args);
            //3. ============ Play with service ============
            hwc.playService();
        }
    }
}
