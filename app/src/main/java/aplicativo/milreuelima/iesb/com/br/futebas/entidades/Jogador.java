package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.util.Date;

/**
 * Created by Sinvas on 25/08/2015.
 */
public class Jogador {

    private int id;
    private String nome;
    private String telefone;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        //TODO: implementação específica
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        //TODO: implementação específica
        return super.hashCode();
    }
}
