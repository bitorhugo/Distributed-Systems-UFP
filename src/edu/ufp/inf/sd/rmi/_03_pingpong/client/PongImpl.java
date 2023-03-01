package edu.ufp.inf.sd.rmi._03_pingpong.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;
import java.util.logging.Level;
import edu.ufp.inf.sd.rmi._03_pingpong.server.Ball;
import edu.ufp.inf.sd.rmi._03_pingpong.server.PingRI;
import edu.ufp.inf.sd.rmi.util.rmisetup.SetupContextRMI;


public class PongImpl extends UnicastRemoteObject implements PongRI {

    private PingRI stub;
    private SetupContextRMI contextRMI;

    public PongImpl(SetupContextRMI contextRMI, int id) throws RemoteException {
        super();
        this.contextRMI = contextRMI;
        lookupService();
        this.stub.ping(new Ball(id), this);
    }

    @Override
    public void pong(Ball ball) throws RemoteException {
        System.out.println("Pong: ball=" + ball.getPlayerID());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stub.ping(ball, this);
    }

    private void lookupService() {
        try {
            //Get proxy MAIL_TO_ADDR rmiregistry
            Registry registry = this.contextRMI.getRegistry();
            //Lookup service on rmiregistry and wait for calls
            if (registry != null) {
                //Get service url (including servicename)
                String serviceUrl = contextRMI.getServicesUrl(0);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going MAIL_TO_ADDR lookup service @ {0}", serviceUrl);
                //============ Get proxy MAIL_TO_ADDR Ping service ============
                this.stub = (PingRI) registry.lookup(serviceUrl);
            }
            else {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "registry not bound (check IPs). :(");
                //registry = LocateRegistry.createRegistry(1099);
            }
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

}
