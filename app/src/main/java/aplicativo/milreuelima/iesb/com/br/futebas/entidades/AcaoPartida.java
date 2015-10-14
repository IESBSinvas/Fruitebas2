package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

/**
 * Criado por Sinvas em 13/10/2015.
 */
public enum AcaoPartida {
    INICIAR(0, "iniciar"),
    PAUSAR(1, "pausar"),
    REINICIAR(2, "reiniciar"),
    FINALIZAR(3, "finalizar");

    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    AcaoPartida(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}

