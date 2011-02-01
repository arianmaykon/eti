/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author maykon
 */
public interface IPushClient extends Remote {

	/**
	 * Method is invoked remotely by server to notify client of an incoming
	 * message
	 *
	 * @param sender
	 *            - username of user sending message
	 * @param message
	 *            - actual message
	 * @throws RemoteException
	 */
	public void notifyMessage(String sender, String message)
			throws RemoteException;
}
