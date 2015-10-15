package aplicativo.milreuelima.iesb.com.br.futebas.exceptions;

import aplicativo.milreuelima.iesb.com.br.futebas.entidades.AcaoPartida;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Partida;

/**
 * Criado por Sinvas em 13/10/2015.
 */
public class PartidaEstadoInvalidoException extends Exception{

    public PartidaEstadoInvalidoException(AcaoPartida acaoDesejada, Partida partida){
        super("Não é possível " + acaoDesejada.getNome() + " a partida, pois a mesma está " + partida.getEstado().getNome() + "!");
    }

    public PartidaEstadoInvalidoException(AcaoPartida acaoDesejada, Partida partida, Throwable throwable){
        super("Não é possível " + acaoDesejada.getNome() + " a partida, pois a mesma está " + partida.getEstado().getNome() + "!", throwable);
    }

}
