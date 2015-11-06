package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.io.Serializable;

/**
 * Criado por Sinvas em 02/11/2015.
 */
public class Configuracao implements Serializable{
    private int numeroMinimoJogadores;
    private int numeroMaximoJogadores;
    private int numTempos;
    private int duracaoTempo; //Em minutos
    private boolean recuperaPartida;

    public boolean isRecuperaPartida() {
        return recuperaPartida;
    }

    public void setRecuperaPartida(boolean recuperaPartida) {
        this.recuperaPartida = recuperaPartida;
    }

    public int getNumTempos() {
        return numTempos;
    }

    public void setNumTempos(int numTempos) {
        this.numTempos = numTempos;
    }

    public int getDuracaoTempo() {
        return duracaoTempo;
    }

    public void setDuracaoTempo(int duracaoTempo) {
        this.duracaoTempo = duracaoTempo;
    }

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
