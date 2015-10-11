package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sinvas on 25/08/2015.
 */
public class Time {
    private int id;
    private String nome;
    private List<JogadorTime> jogadores;

    Time(){
        jogadores = new ArrayList<>();
    }

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

        JogadorTime novoJogador = new JogadorTime(jogador);
        if(!jogadores.contains(novoJogador)){
            //É um jogador novo para o time.
            jogadores.add(novoJogador);

        }else{
            //O time já tem este Jogador.
        }
    }

    public Jogador removeJogador(){
        Jogador retorno = null;
        if (!jogadores.isEmpty()){
            retorno = jogadores.get(0);
        }
        return retorno;
    }
}
