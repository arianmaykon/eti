import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements InverterItf {

	public Servidor() throws RemoteException{
		super();
		System.out.println("Servidor criado!");
	}

	public String inverter(String msg) {
		StringBuffer strbuf = new StringBuffer(msg);
		System.out.println("Recebido: "+msg);
		String retorno = (strbuf.reverse()).toString();
		return retorno;
	}
}