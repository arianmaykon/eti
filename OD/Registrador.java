import java.rmi.*;
import java.rmi.server.*;

public class Registrador {

	public static void main(String args[]) {
		try {
			Servidor obj = new Servidor ();
			Naming.rebind("InverterRef",obj);

			System.out.println("Servidor Registrado!");
		} catch (Exception e){
			System.out.println("Erro");
		}
	}
}