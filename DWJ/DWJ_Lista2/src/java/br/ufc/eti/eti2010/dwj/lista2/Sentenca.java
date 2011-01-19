package br.ufc.eti.eti2010.dwj.lista2;

/**
 * @author Arian Maykon de Araújo Diógenes <arian.maykon@gmail.com>
 */
public class Sentenca {

    private String texto;

    public Sentenca(String texto) {
        this.texto = texto;
    }

    public String toUpper() {
        return this.texto.toUpperCase();
    }

    public String toLower() {
        return this.texto.toLowerCase();
    }

    public String invert() {
//TODO:
        return this.texto;
    }
}