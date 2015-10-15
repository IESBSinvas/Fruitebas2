package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.util.Date;

/**
 * Criado por Sinvas em 25/08/2015.
 */
public class Jogador {

    private int id;
    private String nome;
    private String telefone;

   public Jogador(){
       super();
   }

   public Jogador(int id){
       this();
       this.setId(id);
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
