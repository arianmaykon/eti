import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author maykon
 */
public interface IPushServer extends Remote {

    /**
     * Method is invoked remotely by client to register with server
     *
     * @param userName
     *            - Username with which client is registering.
     * @param clientObject
     *            - Client Remote Object.
     * @throws RemoteException
     */
    public void register(String userName, IPushClient clientObject)
            throws RemoteException;

    /**
     * Method is invoked remotely by client to get a list of users registered
     * with server
     *
     * @return - Array of username's currently registered.
     * @throws RemoteException
     */
    public String[] getAllUser() throws RemoteException;

    /**
     * @param sender
     *            - Username of user sending the message
     * @param userName
     *            - Username of receiver
     * @param message
     *            - actual message to be sent
     * @throws RemoteException
     */
    public void sendMessage(String sender, String userName, String message)
            throws RemoteException;
}
