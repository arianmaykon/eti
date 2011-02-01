package br.ufc.eti.poo.exercicios.exercicio3.questao1;
/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class AcaoPNA extends AcaoPN {

	public void realizarInvestimento() {
		double valor, valorVenda = this.getValorVenda(),
			valorCompraPorcentagem = this.getValorCompra() * 0.1;
		
		if (valorVenda > valorCompraPorcentagem) {
			valor = valorVenda;
		} else {
			valor = valorCompraPorcentagem;
		}
		this.disponibilizarVenda(valor);
		this.comprarAcao();		
	}
}