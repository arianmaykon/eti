/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.eti.eti2010.od.prova;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

/**
 *
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class PushServer implements IPushServer {
//public class PushServer  {

//    private Vector clients = new Vector();
    private UserStore userStore = new UserStore();

    public UserStore getUserStore() {
        return userStore;
    }

    public String[] getAllUser() throws RemoteException {
        return this.getUserStore().getAllUsers();
    }

//    public PushServer() throws RemoteException {
//        super();
//        System.out.println("Servidor criado!");
//    }

    public void subscribe(String name, IPushClient clientObject) {
//        this.clients.add(name);

        this.getUserStore().addUser(name, clientObject);

        try {
            clientObject.notifyMessage("Server", "Welcome " + name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

//    public void unsubscribe(String name) {
//        Iterator it = this.clients.iterator();
//        String row = null;
//        while(it.hasNext()) {
//            row = (String) it.next();
//            if (row.equals(name)) {
//                this.clients.remove(row);
//                break;
//            }
//        }
//    }

//    public void sendMessage(String message) {
        //TODO: Percorrer todos os clientes registrados e enviar as mensagens recebidas
//    }
    public void sendMessage(String sender, String userName, String message)
            throws RemoteException {
        IPushClient client = this.getUserStore().getClientByName(userName);
        if (client != null) {
            client.notifyMessage(sender, message);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        PushServer server = new PushServer();

        try {
            IPushServer stub = (IPushServer) UnicastRemoteObject.exportObject(server, 0);
            LocateRegistry.getRegistry().bind("EchoService", stub);

            System.out.println("Server binded object successfully");

            while (server.userStore.getAllUsers().length == 0) {
                System.out.println("No Client Registered");
                Thread.sleep(5000);
            }

            while (true) {
                System.out.println("Users registered : "
                        + Arrays.toString(server.userStore.getAllUsers()));
                Thread.sleep(15000);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void register(String userName, IPushClient clientObject) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}