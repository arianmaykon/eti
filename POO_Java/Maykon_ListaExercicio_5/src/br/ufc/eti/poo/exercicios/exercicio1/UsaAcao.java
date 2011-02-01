package br.ufc.eti.poo.exercicios.exercicio1;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Questão 2.
 *
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class UsaAcao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Acao[] acoes = new Acao[10];
		
		Acao acao;
		Scanner scan = new Scanner(System.in);
		for(int x=0; x<4; x++) {
			acao = new Acao();
			System.out.println("Digite a descrição da Ação " + (x+1) + ":");
			acao.setDescricao(scan.next());
			System.out.println("Digite o valor de venda da Ação " + (x+1) + ":");
			acao.setValorVenda(scan.nextDouble());
			acoes[x] = acao;
			System.out.println();
		}

        Arrays.sort(acoes,
            new Comparator() {
                public int compare(final Object obj1, final Object obj2) {
                	try {
                		return Double.compare(((Acao)obj2).getValorVenda(), ((Acao)obj1).getValorVenda());
                	} catch (final Exception e) {
					}
					return 0;
                }
            }
        );

		for(int x=0; x<acoes.length; x++) {
			if (acoes[x] != null) {
				System.out.println("Descrição: " + acoes[x].getDescricao());
				System.out.println("Valor venda: " + acoes[x].getValorVenda());
				System.out.println("========================================================");
			}
		}
	}
}