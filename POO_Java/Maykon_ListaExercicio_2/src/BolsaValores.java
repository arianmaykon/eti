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

	public void showAndHandleMenu() {
		System.out.println("ESCOLHA UMA OPÇÃO:\n" +
			"\t1 - Cadastrar uma ação\n" +
			"\t2 - Remover uma ação\n" +
			"\t3 - Disponibilizar uma ação para venda\n" +
			"\t4 - Alterar o valor de venda de uma ação\n" +
			"\t5 - Comprar ação\n" +
			"\t6 - Listar as ações cadastradas\n" +
			"\t7 - Consultar os dados de uma determinada ação\n" +
			"\t8 - Sair\n");
		
		int opcao = this.view.scan.nextInt();
		switch (opcao) {
			case 1:
				this.cadastrarAcao();
				break;
			case 2:
				System.out.println("Digite o código da Ação para remover:");
				this.removerAcao(this.view.scan.nextInt());
				break;
			case 3:
				System.out.println("Digite o código e em seguida o valor de venda da Ação para disponibilizar a venda:");
				this.disponibilizarVenda(this.view.scan.nextInt(), this.view.scan.nextDouble());
				break;
			case 4:
				System.out.println("Digite o código e em seguida o novo valor de venda da Ação:");
				this.alterarValorVenda(this.view.scan.nextInt(), this.view.scan.nextDouble());
				break;
			case 5:
				System.out.println("Digite o código da Ação que se deseja comprar:");
				this.comprar(this.view.scan.nextInt());
				break;
			case 6:
				this.listar();
				break;
			case 7:
				System.out.println("Digite o código da Ação que se deseja consultar:");
				this.consultar(this.view.scan.nextInt());
				break;
			case 8:
				System.exit(0);
				break;
		}
	}

	private void cadastrarAcao() {
		if (this.indices < this.acoes.length) {
			Acao acao = new Acao();
			System.out.println("Digite o código da Ação :");
			int codigo = this.view.scan.nextInt();
			if (this.getIndice(codigo) < 0) {
				acao.setCodigo(codigo);
		
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
				System.out.println("[ERRO] Já existe uma Ação cadastrada com o código informado!");
			}
		} else {
			System.out.println("[INFO] Não foi possível ADICIONAR a ação, limite estourado!");
		}
	}

	private void removerAcao(int codigo) {
		int indice = this.getIndice(codigo);
		if (indice >= 0) {
			this.acoes[indice] = null;
			System.out.println("[INFO] Ação removida com sucesso!");
			this.indices--;
		} else {
			System.out.println("[INFO] Ação não encontrada para remoção!");
		}
	}

	private void disponibilizarVenda(int codigo, double valorVenda) {
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

	private void alterarValorVenda(int codigo, double valorVenda) {
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

	private void comprar(int codigo) {
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