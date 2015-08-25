package android.milreuelima.iesb.com.br.futebas.entidades;

import java.util.List;

/**
 * Created by Sinvas on 25/08/2015.
 */
public class Time {
    private int id;
    private String nome;
    List<JogadorTime> jogadores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<JogadorTime> getJogadores() {
        return jogadores;
    }

    public void adicionaJogador(Jogador jogador){
        if (!jogadores.contains(jogador)){

        }
    }


}
