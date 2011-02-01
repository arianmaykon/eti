/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.eti.eti2010.od.prova;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class PushClient implements IPushClient {
/*
    public PushClient() throws RemoteException {
        super();

        try {
            Naming.rebind("IPushClient", this);

            System.out.println("Cliente Registrado!");
        } catch (Exception e) {
            System.out.println("Erro - " + e.getMessage());
        }

        try {
//            IPushClient Inv = (IPushClient) Naming.lookup("//localhost/IPushClient");
            IPushServer server = (IPushServer) Naming.lookup("//localhost/IPushClient");
            System.out.println("Objeto Localizado!");
            for (;;) {
                BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Frase Invertida = " + server);
            }
        } catch (Exception e) {
            System.err.println("Erro");
        }
        System.exit(0);
    }

    public void sendMessage(String message) {
    }
*/
//##############################################################################
    private static PushClient instance;

    /**
     * Export Current Remote to receive incoming calls
     */
    public PushClient() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            instance = this;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see com.vivek.core.rmi.HelloWorldClientInterface#notifyMessage(java.lang.String, java.lang.String)
     */
    public void notifyMessage(String sender, String message)
            throws RemoteException {
        System.out.println("\n>>>> " + sender + " says " + message);

    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();

            IPushServer remoteObj = (IPushServer) registry.lookup("EchoService");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Your User Name : ");
            String username = scanner.next();

            remoteObj.register(username, new PushClient());

            System.out.println("Users registered : "
                    + Arrays.toString(remoteObj.getAllUser()));
            System.out.println("exit - exit from commandline");
            System.out.println("list - list all users");
            System.out.println("chuser - change user");

            String text = "";
            String receiver = null;
            boolean userIsRegistered = false;
            boolean closeSession = false;

            while (!closeSession) {
                System.out.print(">>>> ");
                text = scanner.nextLine();

                if (text.equalsIgnoreCase("list")) {
                    System.out.println("Users registered : "
                            + Arrays.toString(remoteObj.getAllUser()));
                } else if (text.equalsIgnoreCase("chuser")) {
                    userIsRegistered = false;
                    System.out.print(">>>> Enter User Name to chat with : ");
                    receiver = scanner.nextLine();
                    for (String name : remoteObj.getAllUser()) {
                        if (receiver.equalsIgnoreCase(name)) {
                            userIsRegistered = true;
                            break;
                        }
                    }
                    if (!userIsRegistered) {
                        System.out.println("No such user logged in ");
                    }
                } else if (text.equalsIgnoreCase("exit")) {
                    scanner.close();
                    UnicastRemoteObject.unexportObject(instance, true);
                    closeSession = true;
                } else if (userIsRegistered) {
                    remoteObj.sendMessage(username, receiver, text);
                } else {
                    System.out.println("Use chuser to select user.");
                    userIsRegistered = false;
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
