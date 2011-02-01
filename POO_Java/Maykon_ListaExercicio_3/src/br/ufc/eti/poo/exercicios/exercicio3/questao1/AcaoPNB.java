package br.ufc.eti.poo.exercicios.exercicio3.questao1;
/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class AcaoPNB extends AcaoPN {

	public static double dividendoFixo = 0;


	public void realizarInvestimento() {
		if (Double.isNaN(this.dividendoFixo)) {
			System.out.println("Configure um valor para o dividendo fixo!");
			return;
		}
		this.disponibilizarVenda(this.dividendoFixo);
		this.comprarAcao();
	}

	public static double getDividendoFixo() {
		return dividendoFixo;
	}


	public static void setDividendoFixo(double dividendoFixo) {
		AcaoPNB.dividendoFixo = dividendoFixo;
	}
}