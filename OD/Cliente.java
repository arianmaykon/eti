import java.io.*;
import java.rmi.*;

public class Cliente {

	public static void main(String args[]) {
		try {
			InverterItf Inv =
			(InverterItf)Naming.lookup("//localhost/InverterRef");
			System.out.println("Objeto Localizado!");
			for(;;) {
				System.out.print("Digite a Frase:");
				BufferedReader r = new BufferedReader(
				new InputStreamReader(System.in));
				String line = r.readLine();
				String retorno = Inv.inverter(line);
				System.out.println("Frase Invertida = " + retorno);
			}
		} catch(Exception e) {
			System.err.println("Erro");
		}
		System.exit(0);
	}
}