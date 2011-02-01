import java.util.Scanner;

/**
 * Questão 3.
 *
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class InterBolsaValores {

	public Scanner scan;

	
	public InterBolsaValores() {
		this.scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		int qtdAcoes = 0;
		System.out.println("Digite a quantidade de índices do array:");
		InterBolsaValores view = new InterBolsaValores();
		qtdAcoes = view.scan.nextInt();
		if (qtdAcoes > 0) {
			BolsaValores controller = new BolsaValores(qtdAcoes, view);
			while (true) {
				controller.showAndHandleMenu();
			}
		}

	}
}