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

    private UserStore userStore = new UserStore();

    public UserStore getUserStore() {
        return userStore;
    }

    public String[] getAllUser() throws RemoteException {
        return this.getUserStore().getAllUsers();
    }

    public void register(String name, IPushClient clientObject) {
        this.getUserStore().addUser(name, clientObject);

        try {
            clientObject.notifyMessage("Server", "Welcome " + name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

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
}
