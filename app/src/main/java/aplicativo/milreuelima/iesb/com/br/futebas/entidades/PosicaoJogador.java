package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

/**
 * Created by Sinvas on 25/08/2015.
 */
public enum PosicaoJogador {
    INDEFINIDA(0, "Indefinida"),
    GOLEIRO(1, "Goleiro"),
    LATERAL(2, "Lateral"),
    LATERAL_DIREITO(3, "Lateral Direito"),
    LATERAL_ESQUERDO(4, "Lateral Esquerdo"),
    LIBERO(5, "Líbero"),
    ALA_DIREITO(6, "Ala Direito"),
    ALA_ESQUERDO(7, "Ala Esquerdo"),
    CENTROAVANTE(8, "Centroavante"),
    ZAGUEIRO(9, "Zagueiro"),
    ZAGUEIRO_CENTRAL(10, "Zagueiro Central"),
    QUARTO_ZAGUEIRO(11, "Quarto Zagueiro"),
    VOLANTE(12, "Volante"),
    MEIA_EXTREMO_PONTA(13, "Meia Extremo/Ponta"),
    MEIA_ARTICULADOR(14, "Meia Articulador"),
    ATACANTE(15, "Atacante"),
    MEIO_CAMPISTA(16, "Meio Campista");

    private int id;
    private String nome;

    PosicaoJogador(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
