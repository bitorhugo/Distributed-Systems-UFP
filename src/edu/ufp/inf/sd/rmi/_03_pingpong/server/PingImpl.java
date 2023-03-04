package edu.ufp.inf.sd.rmi._03_pingpong.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.ufp.inf.sd.rmi._03_pingpong.client.PongRI;
import edu.ufp.inf.sd.rmi.util.threading.ThreadPool;

public class PingImpl extends UnicastRemoteObject implements PingRI, Runnable {

    private ThreadPool tp;
    private static final int numThreads = 5;
    private PongRI stub;
    private Ball ball;
    
    private class PingRunnable extends Thread {

        private PongRI stub;
        private Ball ball;
        
        PingRunnable(PongRI clientPongRI, Ball ball) {
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
        this.tp = new ThreadPool(numThreads);
    }
    
    @Override
    public void ping(Ball ball, PongRI clientPongRI) throws RemoteException { // will receive proxy from client
        if (this.getClass().getAnnotatedInterfaces()[1].getType().getTypeName() == Runnable.class.getTypeName()) {
            this.stub = clientPongRI;
            this.ball = ball;
            this.tp.execute(this);
        }
        else {
            PingRunnable pt =  new PingRunnable(clientPongRI, ball);
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
