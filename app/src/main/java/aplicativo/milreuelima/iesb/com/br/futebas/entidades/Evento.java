package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.util.Date;
import java.util.List;

/**
 * Created by Sinvas on 25/08/2015.
 */
public class Evento {

    private int id;
    private String nome;
    private Date data;
    private String local;
    private Jogador organizador;

    public List<JogadorEvento> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<JogadorEvento> jogadores) {
        this.jogadores = jogadores;
    }

    private List<JogadorEvento> jogadores;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Jogador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Jogador organizador) {
        this.organizador = organizador;
    }
}
