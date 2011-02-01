package br.ufc.eti.poo.exercicios.exercicio5;
/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
abstract public class Acao {

	private int codigo;

	private String descricao;

	private double valorCompra = 0;

	private double valorVenda = 0;

	private char status;

	private static final char DISPONIVEL_VENDA = 'D';

	private static final char INDISPONIVEL_VENDA = 'I';


	public Acao() {
		this.status = Acao.DISPONIVEL_VENDA;
	}

	public Acao(double valor) {
		this.valorVenda = valor;
		this.status = Acao.DISPONIVEL_VENDA;
	}


	public void disponibilizarVenda(double valorVenda) {
		this.valorVenda = valorVenda;
		this.status = Acao.DISPONIVEL_VENDA;
	}

	public void comprarAcao() {
		this.status = Acao.INDISPONIVEL_VENDA;
		this.valorCompra = this.valorVenda;
		this.valorVenda = 0;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) { 
		return this.getCodigo() == ((Acao) obj).getCodigo();
	}
}