package edu.ufp.inf.sd.rmi._03_pingpong.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import edu.ufp.inf.sd.rmi._03_pingpong.server.Ball;

public interface PongRI extends Remote {
    public void pong(Ball ball) throws RemoteException;
}
