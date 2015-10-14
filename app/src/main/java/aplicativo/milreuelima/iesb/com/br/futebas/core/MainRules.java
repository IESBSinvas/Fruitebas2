package aplicativo.milreuelima.iesb.com.br.futebas.core;

import java.util.ArrayList;
import java.util.List;

import aplicativo.milreuelima.iesb.com.br.futebas.entidades.EstadoPartida;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Partida;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Placar;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.GenericBusinessException;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.PartidaEstadoInvalidoException;

/**
 * Criado por Sinvas em 13/10/2015.
 */
public class MainRules {

    public final int CONST_ID_TIME_A = 1;
    public final int CONST_ID_TIME_B = 2;

    private static int idProximaPartida;

    private Partida partidaCorrente;

    public MainRules(){

        //TODO: Buscar no BD o valor do id da proxima Partida (max).
        idProximaPartida = 0;

        if (!false){
            //Ao iniciar, verifica se havia alguma partida em andamento nesta data, para carregar seus dados
            //TODO: Buscar no BD estas informações
        }else{
            //Se não existe, registra uma nova partida automaticamente
            idProximaPartida = idProximaPartida + 1;
            partidaCorrente = new Partida(idProximaPartida);

        }
    }

    /*
        Registra a chegada de um novo jogador
    */
    public void registraJogador(int id, String nome, String telefone){

        if (id != 0){
            //É um novo jogador, então cadastra este novo jogador, e registra sua hora de chegada neste evento.

            //...
        }else{
            //É um jogador existente, só registra sua hora de chegada neste evento.

            //...
        }

    }


    public EstadoPartida getEstadoPartidaCorrente(){
        return this.partidaCorrente.getEstado();
    }

    public Placar getPlacarPartidaCorrente(){
        return this.partidaCorrente.getPlacar();

    }

    public void iniciaPartida() throws PartidaEstadoInvalidoException {
        this.partidaCorrente.iniciar();
    }

    public void pausaPartida() throws PartidaEstadoInvalidoException {
        this.partidaCorrente.pausar();
    }

    public void encerraPartida() throws PartidaEstadoInvalidoException {
        this.partidaCorrente.finalizar();
    }

    public void registraGol(int idTime, int idJogador) throws GenericBusinessException {
        if (idTime == this.partidaCorrente.getTimeA().getId()){
            this.partidaCorrente.registrarGolTimeA(idJogador);
        }else{
            this.partidaCorrente.registrarGolTimeB(idJogador);
        }
    }

    public void registraFalta(int idJogador){
        //TODO: Colocar código aqui
    }

    public List<Partida> consultarPartidas(){
        List<Partida> retorno = new ArrayList<>();

        //TODO: escrever o código que busca no BD aqui

        return retorno;
    }

    //Avalia se já está na hora de encerrar esta partida
    public EstadoPartida avaliaPartida() throws GenericBusinessException {
        return this.partidaCorrente.avaliaFinalPartida();
    }

    public void prepararNovaPartida() throws GenericBusinessException {

        if (this.partidaCorrente.getEstado() == EstadoPartida.INICIADA || this.partidaCorrente.getEstado() == EstadoPartida.PARADA){
            throw new GenericBusinessException("Primeiro encerre a partida atual!");
        }else if (this.partidaCorrente.getEstado() == EstadoPartida.FINALIZADA){
            //Inicializa uma nova partida
            idProximaPartida = idProximaPartida + 1;
            partidaCorrente = new Partida(idProximaPartida);
        }


    }

}
