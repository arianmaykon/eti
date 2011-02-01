package br.ufc.eti.poo.exercicios.exercicio3;

/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class BolsaValores {

	private Acao[] acoes;

	private int indices = 0;

	private InterBolsaValores view;

	public BolsaValores(int qtdAcoes, InterBolsaValores view) {
		this.acoes = new Acao[qtdAcoes];  
		this.view = view;
	}

	public void cadastrarAcao() {
		if (this.indices < this.acoes.length) {
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
				if (this.getIndice(codigo) < 0) {
					acao.setCodigo(codigo);
				} else {
					System.out.println("[ERRO] Já existe uma Ação cadastrada com o código informado!");
				}
			}

			System.out.println("Digite a descrição da Ação :");
			acao.setDescricao(this.view.scan.next());

			System.out.println("Digite o valor de compra da Ação :");
			acao.setValorCompra(this.view.scan.nextDouble());

			System.out.println("Digite o valor de venda da Ação :");
			acao.setValorVenda(this.view.scan.nextDouble());

			this.acoes[this.indices] = acao;
			this.indices++;
			System.out.println("[INFO] Ação adicionada com sucesso [" + acao.getDescricao() + "]!");
		} else {
			System.out.println("[INFO] Não foi possível ADICIONAR a ação, limite estourado!");
		}
	}

	public void removerAcao(int codigo) {
		int indice = this.getIndice(codigo);
		if (indice >= 0) {
			this.acoes[indice] = null;
			System.out.println("[INFO] Ação removida com sucesso!");
			this.indices--;
		} else {
			System.out.println("[INFO] Ação não encontrada para remoção!");
		}
	}

	public void disponibilizarVenda(int codigo, double valorVenda) {
		int indice = this.getIndice(codigo);
		if (indice >= 0) {
			System.out.println("[INFO] DISPONIBILIZAÇÃO VENDA, valor anterior: " + this.acoes[indice].getValorVenda());
			this.acoes[indice].disponibilizarVenda(valorVenda);
			System.out.println("[INFO] DISPONIBILIZAÇÃO VENDA, valor posterior: " + this.acoes[indice].getValorVenda());
			System.out.println("[INFO] Ação disponibilizada para venda com sucesso!");
		} else {
			System.out.println("[INFO] Ação não encontrada para disponibilização de venda!");
		}
	}

	public void alterarValorVenda(int codigo, double valorVenda) {
		int indice = this.getIndice(codigo);
		if (indice >= 0) {
			System.out.println("[INFO] ALTERAÇÃO VALOR, valor anterior: " + this.acoes[indice].getValorVenda());
			this.acoes[indice].setValorVenda(valorVenda);
			System.out.println("[INFO] ALTERAÇÃO VALOR, valor posterior: " + this.acoes[indice].getValorVenda());
			System.out.println("[INFO] Valor de venda da ação allterado sucesso!");
		} else {
			System.out.println("[INFO] Ação não encontrada para alteração!");
		}
	}

	public void comprar(int codigo) {
		int indice = this.getIndice(codigo);
		if (indice >= 0) {
			this.acoes[indice].comprarAcao();
			System.out.println("[INFO] Ação comprada com sucesso!");
		} else {
			System.out.println("[INFO] Ação não encontrada para compra!");
		}
	}

	public void listar() {
		Acao acao;
		for(int x=0; x<this.acoes.length; x++) {
			if (this.acoes[x] != null) {
				acao = this.acoes[x];
				System.out.println("Código: " + acao.getCodigo());
				System.out.println("Descrição: " + acao.getDescricao());
				System.out.println("Valor Compra: " + acao.getValorCompra());
				System.out.println("Valor venda: " + acao.getValorVenda());
				System.out.println("Status: " + acao.getStatus());
				System.out.println("=========================================");
			}
		}
	}

	public void consultar(int codigo) {
		int indice = this.getIndice(codigo);
		if (indice >= 0) {
			System.out.println("==================== Dados da Ação =====================");
			System.out.println("Código: " + this.acoes[indice].getCodigo());
			System.out.println("Descrição: " + this.acoes[indice].getDescricao());
			System.out.println("Valor Compra: " + this.acoes[indice].getValorCompra());
			System.out.println("Valor venda: " + this.acoes[indice].getValorVenda());
			System.out.println("Status: " + this.acoes[indice].getStatus());
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
		int indice = this.getIndice(codigo);
		if (indice >= 0) {
			if ( this.acoes[indice] instanceof AcaoPN ) {
				((AcaoPN)this.acoes[indice]).realizarInvestimento();
				System.out.println("[INFO] Investimento realizado com sucesso!");
			} else {
				System.out.println("[ERRO] O código informado não corresponde a uma AçãoPN (AcaoPNA / AcaoPNB) !");
			}
		} else {
			System.out.println("[INFO] Ação não encontrada para realização de investimento!");
		}
	}

	public void votar(int codigo) {
		int indice = this.getIndice(codigo);
		if (indice >= 0) {
			if ( this.acoes[indice] instanceof AcaoON ) {
				System.out.println("ESCOLHA UMA OPÇÃO DE VOTO:\n" +
					"\tS - Sim\n" +
					"\tN - Não\n" +
					"\tX - Sair\n");
				String votoStr = this.view.scan.next();
				boolean voto = false;
				if (votoStr.equalsIgnoreCase("S") || votoStr.equalsIgnoreCase("N")) {
					voto = votoStr.equalsIgnoreCase("S");
					((AcaoON)this.acoes[indice]).votar(voto);
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
		if (!Double.isNaN(porcentagem) && porcentagem > 0) {
			double valorVenda;
			for(int x=0; x<this.acoes.length; x++) {
				if (this.acoes[x] != null) {
					valorVenda = this.acoes[x].getValorVenda();
					if (!Double.isNaN(valorVenda)) {
						valorVenda *= porcentagem;
						this.acoes[x].setValorVenda(valorVenda);
					}
				}
			}
		} else {
			System.out.println("[INFO] Porcentagem inválida!");
		}
	}

	private int getIndice(int codigo) {
		int indice = -1;
		for(int x=0; x<this.acoes.length; x++) {
			if (this.acoes[x] != null && this.acoes[x].getCodigo() == codigo) {
				indice = x;
				break;
			}
		}
		return indice;
	}
}