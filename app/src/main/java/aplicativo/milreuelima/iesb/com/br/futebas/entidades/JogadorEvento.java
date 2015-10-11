package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.util.Date;

/**
 * Created by Sinvas on 25/08/2015.
 */
public class JogadorEvento extends Jogador {
    private Date horaChegada = null;
    private int numeroGols = 0;

    public int getNumeroGols() {
        return numeroGols;
    }

    public Date getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(Date horaChegada) {
        this.horaChegada = horaChegada;
    }

    public void adicionaGol(){
        this.numeroGols = this.numeroGols + 1;
    }
}
