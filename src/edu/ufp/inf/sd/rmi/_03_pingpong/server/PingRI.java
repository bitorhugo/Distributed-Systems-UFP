package edu.ufp.inf.sd.rmi._03_pingpong.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.ufp.inf.sd.rmi._03_pingpong.client.PongRI;

public interface PingRI extends Remote {
    public void ping(Ball ball, PongRI clientPongRI) throws RemoteException;
}
