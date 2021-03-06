package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

/**
 * Created by Sinvas on 25/08/2015.
 */
public class JogadorTime extends Jogador {
    private Time time;
    private PosicaoJogador posicao;

    JogadorTime(Jogador jogador){
        this.setId(jogador.getId());
        this.setNome(jogador.getNome());
        this.setTelefone(jogador.getTelefone());
    }

    //JogadorTime(){
    //
    //}

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public PosicaoJogador getPosicao() {
        return posicao;
    }

    public void setPosicao(PosicaoJogador posicao) {
        this.posicao = posicao;
    }
}
