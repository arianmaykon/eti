package br.ufc.eti.eti2010.dwj.lista2;

/**
 * @author Arian Maykon de Araújo Diógenes
 */
public class Calculadora {

    private static final String SOMAR = "som";
    private static final String SUBTRAIR = "sub";
    private static final String MULTIPLICAR = "mul";
    private static final String DIVIDIR = "div";

    private Integer valor1;
    private Integer valor2;
    private String operacao;

    public Calculadora() {
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Integer processar() {
        Integer retorno = null;
        if (!this.operacao.equalsIgnoreCase("")) {
            if (this.operacao.equalsIgnoreCase(Calculadora.SOMAR)) {
            } else if (this.operacao.equalsIgnoreCase(Calculadora.SUBTRAIR)) {
                retorno = this.subtrair();
            } else if (this.operacao.equalsIgnoreCase(Calculadora.MULTIPLICAR)) {
                retorno = this.multiplicar();
            } else if (this.operacao.equalsIgnoreCase(Calculadora.DIVIDIR)) {
                retorno = this.dividir();
            }
        }
        return retorno;
    }

    public Integer getValor1() {
        return valor1;
    }

    public void setValor1(Integer valor1) {
        this.valor1 = valor1;
    }

    public Integer getValor2() {
        return valor2;
    }

    public void setValor2(Integer valor2) {
        this.valor2 = valor2;
    }

    public Integer somar() {
        return this.valor1 + this.valor2;
    }

    public Integer subtrair() {
        return this.valor1 - this.valor2;
    }

    public Integer multiplicar() {
        return this.valor1 * this.valor2;
    }

    public Integer dividir() {
        return this.valor1 / this.valor2;
    }
}
