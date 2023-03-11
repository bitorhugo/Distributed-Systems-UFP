/**
 * <p>Title: Projecto SD</p>
 * <p>Description: Projecto apoio aulas SD</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: UFP </p>
 * @author Rui Moreira
 * @version 2.0
 */
package edu.ufp.inf.sd.rmi._05_observer.server.state;

import java.io.Serializable;

/**
 * 
 * @author rui
 */
public class State implements Serializable {

    private String id;
    private String info;

    /**
     * 
     * @param id
     * @param m 
     */
    public State(String id, String info) {
        this.id = id;
        this.info = info;
    }

    /**
     * 
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @return 
     */
    public String getInfo(){
        return this.info;
    }

    /**
     * 
     * @param m 
     */
    public void setInfo(String info){
        this.info = info;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.info;
    }
}
