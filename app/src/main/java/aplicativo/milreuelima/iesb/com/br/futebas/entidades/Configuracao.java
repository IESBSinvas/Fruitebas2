package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.io.Serializable;

/**
 * Criado por Sinvas em 02/11/2015.
 */
public class Configuracao implements Serializable{
    private int numeroMinimoJogadores;
    private int numeroMaximoJogadores;

    public int getNumeroMinimoJogadores() {
        return numeroMinimoJogadores;
    }

    public void setNumeroMinimoJogadores(int numeroMinimoJogadores) {
        this.numeroMinimoJogadores = numeroMinimoJogadores;
    }

    public int getNumeroMaximoJogadores() {
        return numeroMaximoJogadores;
    }

    public void setNumeroMaximoJogadores(int numeroMaximoJogadores) {
        this.numeroMaximoJogadores = numeroMaximoJogadores;
    }

}
