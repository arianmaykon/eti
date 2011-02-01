package br.ufc.eti.poo.exercicios.exercicio3;
/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class AcaoPNB extends AcaoPN {

	public static double dividendoFixo = 0;


	public void realizarInvestimento() {
		if (Double.isNaN(AcaoPNB.dividendoFixo)) {
			System.out.println("Configure um valor para o dividendo fixo!");
			return;
		}
		this.disponibilizarVenda(AcaoPNB.dividendoFixo);
		this.comprarAcao();
	}
}