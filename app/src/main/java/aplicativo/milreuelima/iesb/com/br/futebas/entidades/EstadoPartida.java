package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

/**
 * Criado por Sinvas em 13/10/2015.
 */
public enum EstadoPartida {
    NAO_INICIADA(0, "Não iniciada"),
    INICIADA(1, "Iniciada"),
    PARADA(2, "Em pausa"),
    FINALIZADA(3, "Finalizada");

    private int id;

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    private String nome;

    EstadoPartida(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
