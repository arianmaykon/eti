package br.ufc.eti.eti2010.dwj.lista2;

/**
 * @author Arian Maykon de Araújo Diógenes
 */
public class Sentenca {

    private String texto;
    private String operacao;

    public Sentenca() {
    }

    public void setTexto(String txt) {
        this.texto = txt;
    }

    public String getTexto() {
        return this.texto;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String toUpper() {
        return this.texto.toUpperCase();
    }

    public String toLower() {
        return this.texto.toLowerCase();
    }

    public String invert() {
        return new StringBuffer(this.texto).reverse().toString();
    }
}