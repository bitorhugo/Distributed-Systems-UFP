package edu.ufp.inf.sd.rmi._03_pingpong.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ufp.inf.sd.rmi._03_pingpong.client.PongRI;

public class PingImpl extends UnicastRemoteObject implements PingRI {

    class PingThread extends Thread {

        private PongRI stub;
        private Ball ball;
        
        PingThread(PongRI clientPongRI, Ball ball) {
            this.ball = ball;
            this.stub = clientPongRI;
        }
        
        @Override
        public void run() { // Code to be run on thread
            try {
                System.out.println("Ping: ball=" + ball.getPlayerID());
                this.stub.pong(this.ball);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }    
    }

    public PingImpl() throws RemoteException {
        super();
    }
    
    @Override
    public void ping(Ball ball, PongRI clientPongRI) throws RemoteException { // will receive proxy from client
        PingThread pt =  new PingThread(clientPongRI, ball);
        pt.start();
    }

}
