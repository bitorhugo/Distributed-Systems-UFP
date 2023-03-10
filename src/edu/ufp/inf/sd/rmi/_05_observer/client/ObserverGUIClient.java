/**
 * <p>
 * Title: Projecto SD</p>
 * <p>
 * Description: Projecto apoio aulas SD</p>
 * <p>
 * Copyright: Copyright (c) 2011</p>
 * <p>
 * Company: UFP </p>
 *
 * @author Rui Moreira
 * @version 2.0
 */
package edu.ufp.inf.sd.rmi._05_observer.client;

import edu.ufp.inf.sd.rmi._05_observer.server.subject.*;
import edu.ufp.inf.sd.rmi._05_observer.server.state.*;
import edu.ufp.inf.sd.rmi.util.rmisetup.SetupContextRMI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 * @author rjm
 */
public class ObserverGUIClient extends javax.swing.JFrame {

    /**
     * Context for connecting a RMI client to a RMI Servant
     */
    private SetupContextRMI contextRMI;
    /**
     * Remote interface that will hold the Servant proxy
     */
    private SubjectRI subjectRI;

    private ObserverImpl observer;

    /**
     * Creates new form ChatClientFrame
     *
     * @param args main args with ip, port and service name
     */
    public ObserverGUIClient(String args[]) {
        //1. Init the GUI components
        initComponents();
        //2. Init the RMI context (load security manager, lookup subject, etc.)
        initContext(args);
        //3. Create observer (which attaches himself to subject)
        initObserver(args);
    }

    private void initContext(String args[]) {
        try {
            //List ans set args
            SetupContextRMI.printArgs(this.getClass().getName(), args);
            String registryIP=args[0];
            String registryPort=args[1];
            String serviceName=args[2];
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going to setup RMI context...");
            //Create a context for RMI setup
            this.contextRMI = new SetupContextRMI(this.getClass(), registryIP, registryPort, new String[]{serviceName});
            lookupService();
        } catch (RemoteException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    private void initObserver(String args[]) {
        try {
            String username = this.jTextFieldUsername.getText();
            //observer = new ObserverImpl(username, this, args);
            this.observer = new ObserverImpl(username, this, this.subjectRI);
            System.out.println("Observer Created");
        } catch (Exception e) {
            Logger.getLogger(ObserverGUIClient.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    private void lookupService() {
        try {
            //Get proxy to rmiregistry
            Registry registry = this.contextRMI.getRegistry();
            //Lookup service on rmiregistry and wait for calls
            if (registry != null) {
                //Get service url (including servicename)
                String serviceUrl = contextRMI.getServicesUrl(0);
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "going to lookup service @ {0}", serviceUrl);

                //============ Get proxy to HelloWorld service ============
                this.subjectRI = (SubjectRI) registry.lookup(serviceUrl);
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "registry not bound (check IPs). :(");
                //registry = LocateRegistry.createRegistry(1099);
            }
        } catch (RemoteException | NotBoundException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }


    protected void updateTextArea() {
        try {
            String msg = "[" + this.observer.getLastObserverState().getId() + "] " + this.observer.getLastObserverState().getInfo();
            this.jTextAreaChatHistory.append(msg + '\n');
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1        = new javax.swing.JFileChooser();
        jScrollPane1         = new javax.swing.JScrollPane();
        jTextAreaChatHistory = new javax.swing.JTextArea();
        jButtonSend          = new javax.swing.JButton();
        jTextFieldMsg        = new javax.swing.JTextField();
        jLabelUserID         = new javax.swing.JLabel();
        jTextFieldUsername   = new javax.swing.JTextField();
        jMenuBar1            = new javax.swing.JMenuBar();
        jMenu1               = new javax.swing.JMenu();
        jMenuItemExit        = new javax.swing.JMenuItem();
        jMenuItemSave        = new javax.swing.JMenuItem();
        jMenu2               = new javax.swing.JMenu();
        jMenuItemCopy        = new javax.swing.JMenuItem();
        jMenuItemPaste       = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaChatHistory.setColumns(20);
        jTextAreaChatHistory.setLineWrap(true);
        jTextAreaChatHistory.setRows(5);
        jTextAreaChatHistory.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaChatHistory);

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jTextFieldMsg.setText("msg");
        jTextFieldMsg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldMsgKeyPressed(evt);
            }
        });

        jLabelUserID.setText("User ID");

        jTextFieldUsername.setText("rmoreira");

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuItemSave.setText("Save");
        jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSave);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItemCopy.setText("Copy");
        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopyActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCopy);

        jMenuItemPaste.setText("Paste");
        jMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPasteActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemPaste);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout=new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabelUserID)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                                        .addComponent(jTextFieldMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSend))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSend))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelUserID)
                                        .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {
        this.jTextAreaChatHistory.selectAll();
        this.jTextAreaChatHistory.copy(); // TODO: ?????
    }

    private void jMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {
        this.jTextAreaChatHistory.paste();
    }

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        if (!this.jTextFieldMsg.getText().isBlank()) {
            try {
                State s = new State(this.jTextFieldUsername.getText(), this.jTextFieldMsg.getText());
                this.observer.getSubject().setState(s);
                this.jTextFieldMsg.setText(""); // clear msg field
            } catch (RemoteException ex) {
                Logger.getLogger(ObserverGUIClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jTextFieldMsgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMsgKeyPressed
        char c = evt.getKeyChar(); // retrieve keyboard input from user
        if (c == '\n' || c == '\r') {
            if (!this.jTextFieldMsg.getText().isBlank()) {
                try { // notify subject
                    State s = new State(this.jTextFieldUsername.getText(), this.jTextFieldMsg.getText());
                    this.observer.getSubject().setState(s);

                    this.jTextFieldMsg.setText(""); // clear msg field
                } catch (RemoteException ex) {
                    Logger.getLogger(ObserverGUIClient.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        try {
            this.observer.getSubject().detach(this.observer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.dispose();
        System.exit(0);
    }

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void jMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {
        FileWriter fw=null;
        try {
            this.jFileChooser1 = new JFileChooser(new File("C:\\Temp"));
            this.jFileChooser1.showSaveDialog(this);
            File f = this.jFileChooser1.getSelectedFile();
            if (f != null) {
                fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(this.jTextAreaChatHistory.getText());
                pw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ObserverGUIClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ObserverGUIClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                if (args.length >= 3) {
                    new ObserverGUIClient(args).setVisible(true);
                } else {
                    System.out.println(ObserverGUIClient.class + ": call must have the following args: <rmi_ip> <rmi_port> <rmi_service_prefix>");
                }
            }
        });
    }


    private javax.swing.JButton jButtonSend;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabelUserID;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemPaste;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTextArea jTextAreaChatHistory;
    private javax.swing.JTextField jTextFieldMsg;
    private javax.swing.JTextField jTextFieldUsername;

}
