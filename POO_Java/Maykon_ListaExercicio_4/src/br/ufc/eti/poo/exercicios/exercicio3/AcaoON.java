package br.ufc.eti.poo.exercicios.exercicio3;
/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class AcaoON extends Acao {

	private boolean resultadoVoto;


	public void votar(boolean voto) {
		this.resultadoVoto = voto;
	}
}