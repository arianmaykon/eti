package br.ufc.eti.poo.exercicios.exercicio5;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class BolsaValoresHash {

	private HashMap <Integer, Acao> acoes;

	private InterBolsaValores view;

	public BolsaValoresHash(int qtdAcoes, InterBolsaValores view) {
//TODO: Tratar o qtdAcoes (Controlar quantidade de Acoes)!
		this.acoes = new HashMap();  
		this.view = view;
	}

	public void cadastrarAcao() {
		System.out.println("ESCOLHA O TIPO DE AÇÃO A SER CADASTRADA:\n" +
			"\t1 - AcaoON \n" +
			"\t2 - AcaoPNA \n" +
			"\t3 - AcaoPNB \n" +
			"\t4 - Sair \n");
		int opcao = this.view.scan.nextInt();
		if (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4) {
			System.out.println("Opção inválida!");
			return;
		}
		Acao acao = new AcaoON();
		switch (opcao) {
			case 1:
				acao = new AcaoON();
				break;
			case 2:
				acao = new AcaoPNA();
				break;
			case 3:
				acao = new AcaoPNB();
				break;
		}

//TODO: Tratar o autoincremento de AcaoPNA para não conflitar com os códigos das ações no array
		if (!(acao instanceof AcaoPNA)) {
			System.out.println("Digite o código da Ação :");
			int codigo = this.view.scan.nextInt();
			if (this.find(codigo) == null) {
				acao.setCodigo(codigo);
			} else {
				System.out.println("[ERRO] Já existe uma Ação cadastrada com o código informado!");
				return;
			}
		}

		System.out.println("Digite a descrição da Ação :");
		acao.setDescricao(this.view.scan.next());

		System.out.println("Digite o valor de compra da Ação :");
		acao.setValorCompra(this.view.scan.nextDouble());

		System.out.println("Digite o valor de venda da Ação :");
		acao.setValorVenda(this.view.scan.nextDouble());
//TODO: Corrigir conflito de códigos gerados da AcaoPNA com os digitados manualmente.
		this.acoes.put(acao.getCodigo(), acao);
		System.out.println("[INFO] Ação adicionada com sucesso [" + acao.getDescricao() + "]!");
	}

	public void removerAcao(int codigo) {
		Acao acao = this.find(codigo);
		if (acao != null) {
			this.acoes.remove(acao);
			System.out.println("[INFO] Ação removida com sucesso!");
		} else {
			System.out.println("[INFO] Ação não encontrada para remoção!");
		}
	}

	public void disponibilizarVenda(int codigo, double valorVenda) {
		Acao acao = this.find(codigo);
		if (acao != null) {
			System.out.println("[INFO] DISPONIBILIZAÇÃO VENDA, valor anterior: " + acao.getValorVenda());
			acao.disponibilizarVenda(valorVenda);
			System.out.println("[INFO] DISPONIBILIZAÇÃO VENDA, valor posterior: " + acao.getValorVenda());
			System.out.println("[INFO] Ação disponibilizada para venda com sucesso!");
		} else {
			System.out.println("[INFO] Ação não encontrada para disponibilização de venda!");
		}
	}

	public void alterarValorVenda(int codigo, double valorVenda) {
		Acao acao = this.find(codigo);
		if (acao != null) {
			System.out.println("[INFO] ALTERAÇÃO VALOR, valor anterior: " + acao.getValorVenda());
			acao.setValorVenda(valorVenda);
			System.out.println("[INFO] ALTERAÇÃO VALOR, valor posterior: " + acao.getValorVenda());
			System.out.println("[INFO] Valor de venda da ação allterado sucesso!");
		} else {
			System.out.println("[INFO] Ação não encontrada para alteração!");
		}
	}

	public void comprar(int codigo) {
		Acao acao = this.find(codigo);
		if (acao != null) {
			acao.comprarAcao();
			System.out.println("[INFO] Ação comprada com sucesso!");
		} else {
			System.out.println("[INFO] Ação não encontrada para compra!");
		}
	}

	public void listar() {
		Acao acao;
		Set<Entry<Integer,Acao>> entries = this.acoes.entrySet();
		for (Entry<Integer, Acao> entry : entries) {
			acao = entry.getValue();
			System.out.println("Código: " + acao.getCodigo());
			System.out.println("Descrição: " + acao.getDescricao());
			System.out.println("Valor Compra: " + acao.getValorCompra());
			System.out.println("Valor venda: " + acao.getValorVenda());
			System.out.println("Status: " + acao.getStatus());
			System.out.println("=========================================");
		}
	}

	public void consultar(int codigo) {
		Acao acao = this.find(codigo);
		if (acao != null) {
			System.out.println("==================== Dados da Ação =====================");
			System.out.println("Código: " + acao.getCodigo());
			System.out.println("Descrição: " + acao.getDescricao());
			System.out.println("Valor Compra: " + acao.getValorCompra());
			System.out.println("Valor venda: " + acao.getValorVenda());
			System.out.println("Status: " + acao.getStatus());
			System.out.println("========================================================");
			System.out.println("[INFO] Ação consultada com sucesso!");
		} else {
			System.out.println("[INFO] Ação não encontrada para consulta!");
		}
	}

	public void alterarDividendoFixo(double dividendoFixo) {
		AcaoPNB.dividendoFixo = dividendoFixo;
	}

	public void realizarInvestimento(int codigo) {
		Acao acao = this.find(codigo);
		if (acao != null) {
			if ( acao instanceof AcaoPN ) {
				((AcaoPN)acao).realizarInvestimento();
				System.out.println("[INFO] Investimento realizado com sucesso!");
			} else {
				System.out.println("[ERRO] O código informado não corresponde a uma AçãoPN (AcaoPNA / AcaoPNB) !");
			}
		} else {
			System.out.println("[INFO] Ação não encontrada para realização de investimento!");
		}
	}

	public void votar(int codigo) {
		Acao acao = this.find(codigo);
		if (acao != null) {
			if ( acao instanceof AcaoON ) {
				System.out.println("ESCOLHA UMA OPÇÃO DE VOTO:\n" +
					"\tS - Sim\n" +
					"\tN - Não\n" +
					"\tX - Sair\n");
				String votoStr = this.view.scan.next();
				boolean voto = false;
				if (votoStr.equalsIgnoreCase("S") || votoStr.equalsIgnoreCase("N")) {
					voto = votoStr.equalsIgnoreCase("S");
					((AcaoON)acao).votar(voto);
					System.out.println("[INFO] Voto realizado com sucesso!");
				} else {
					System.out.println("[INFO] Voto NÃO realizado!");
				}
			} else {
				System.out.println("[ERRO] O código informado não corresponde a uma AçãoON !");
			}
		} else {
			System.out.println("[INFO] Ação não encontrada para votação!");
		}
	}

	public void atualizarValoresVenda(double porcentagem) {
/*		if (!Double.isNaN(porcentagem) && porcentagem > 0) {
			double valorVenda;
			Enumeration vEnum = this.acoes.elements();
			Acao acao;
			while (vEnum.hasMoreElements()) {
				acao = (Acao) vEnum.nextElement();
				valorVenda = acao.getValorVenda();
				if (!Double.isNaN(valorVenda)) {
					valorVenda *= porcentagem;
					acao.setValorVenda(valorVenda);
				}
			}
		} else {
			System.out.println("[INFO] Porcentagem inválida!");
		}*/
	}

	private Acao find(int codigo) {
		Acao acao = new AcaoON();
		acao.setCodigo(codigo);
		Acao finded;
		Set<Entry<Integer,Acao>> entries = this.acoes.entrySet();
		for (Entry<Integer, Acao> entry : entries) {
			finded = entry.getValue();
			if (finded.equals(acao)) {
				return finded;
			}
		}
		return null;
	}
}