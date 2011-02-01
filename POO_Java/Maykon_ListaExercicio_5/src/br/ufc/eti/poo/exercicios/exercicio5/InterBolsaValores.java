package br.ufc.eti.poo.exercicios.exercicio5;
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
//			BolsaValores controller = new BolsaValores(qtdAcoes, view);
//			BolsaValoresVector controller = new BolsaValoresVector(qtdAcoes, view);
			BolsaValoresHash controller = new BolsaValoresHash(qtdAcoes, view);
			while (true) {
				view.showAndHandleMenu(controller);
			}
		}
	}

//	private void showAndHandleMenu(BolsaValores controller) {
//	private void showAndHandleMenu(BolsaValoresVector controller) {
	private void showAndHandleMenu(BolsaValoresHash controller) {
		System.out.println("ESCOLHA UMA OPÇÃO:\n" +
			"\t1 - Cadastrar uma Ação (AcaoON, AcaoPNA ou AcaoPNB)\n" +
			"\t2 - Remover uma Ação\n" +
			"\t3 - Disponibilizar uma ação para venda\n" +
			"\t4 - Alterar o valor de venda de uma ação\n" +
			"\t5 - Comprar ação\n" +
			"\t6 - Listar as ações cadastradas\n" +
			"\t7 - Consultar os dados de uma determinada ação\n" +
			"\t8 - Alterar o valor do Dividendo Fixo (variável de classe dividendoFixo)\n" +
			"\t9 - Realizar investimento\n" +
			"\t10 - Votar\n" +
			"\t11 - Atualizar o valor de venda de todas as ações cadastradas (existentes) em x%\n" +
			"\t12 - Sair\n");

		int opcao = this.scan.nextInt();
		switch (opcao) {
			case 1:
				controller.cadastrarAcao();
				break;
			case 2:
				System.out.println("Digite o código da Ação para remover:");
				controller.removerAcao(this.scan.nextInt());
				break;
			case 3:
				System.out.println("Digite o código e em seguida o valor de venda da Ação para disponibilizar a venda:");
				controller.disponibilizarVenda(this.scan.nextInt(), this.scan.nextDouble());
				break;
			case 4:
				System.out.println("Digite o código e em seguida o novo valor de venda da Ação:");
				controller.alterarValorVenda(this.scan.nextInt(), this.scan.nextDouble());
				break;
			case 5:
				System.out.println("Digite o código da Ação que se deseja comprar:");
				controller.comprar(this.scan.nextInt());
				break;
			case 6:
				controller.listar();
				break;
			case 7:
				System.out.println("Digite o código da Ação que se deseja consultar:");
				controller.consultar(this.scan.nextInt());
				break;
			case 8:
				System.out.println("Digite o novo valor de dividendo fixo das Ações PNB:");
				controller.alterarDividendoFixo(this.scan.nextDouble());
				break;
			case 9:
				System.out.println("Digite o código da Ação que se deseja realizar investimento:");
				controller.realizarInvestimento(this.scan.nextInt());
				break;
			case 10:
				System.out.println("Digite o código da Ação Ordinária que se deseja votar:");
				controller.votar(this.scan.nextInt());
				break;
			case 11:
				System.out.println("Digite o valor para acréscimo (em %) para os valores das Ações cadastradas:");
				controller.atualizarValoresVenda(this.scan.nextDouble());
				break;
			case 12:
				System.exit(0);
				break;
		}
	}
}