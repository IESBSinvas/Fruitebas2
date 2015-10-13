package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.util.Date;

/**
 * Created by edu on 12/10/15.
 */
public class JogadorLista {
    private String data;
    private String nome;
    private boolean favorito;
    private int numero;

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isFavorito() {
        return favorito;
    }
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

}
