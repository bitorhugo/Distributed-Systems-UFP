package edu.ufp.inf.sd.rmi._03_pingpong.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ufp.inf.sd.rmi._03_pingpong.client.PongRI;
import edu.ufp.inf.sd.rmi.util.threading.ThreadPool;

public class PingImpl extends UnicastRemoteObject implements PingRI, Runnable {

    private ThreadPool tp;
    private PongRI stub;
    private Ball ball;
    
    private class PingThread extends Thread {

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
        this.tp = new ThreadPool(5);
    }
    
    @Override
    public void ping(Ball ball, PongRI clientPongRI) throws RemoteException { // will receive proxy from client
        if (this.getClass().getAnnotatedInterfaces()[1].getType().getTypeName() == Runnable.class.getTypeName()) {
            this.stub = clientPongRI;
            this.ball = ball;
            this.tp.execute(this);
        }
        else {
            PingThread pt =  new PingThread(clientPongRI, ball);
            pt.start();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Ping: ball=" + this.ball.getPlayerID());
            this.stub.pong(this.ball);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
    }

}
