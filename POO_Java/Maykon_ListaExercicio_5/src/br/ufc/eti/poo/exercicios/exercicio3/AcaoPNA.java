package br.ufc.eti.poo.exercicios.exercicio3;
/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class AcaoPNA extends AcaoPN {

	public static int contador = 1;


	public AcaoPNA () {
		this.setCodigo(AcaoPNA.contador);
		AcaoPNA.contador++;
	}

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