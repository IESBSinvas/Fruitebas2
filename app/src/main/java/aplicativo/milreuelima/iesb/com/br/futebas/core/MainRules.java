package aplicativo.milreuelima.iesb.com.br.futebas.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aplicativo.milreuelima.iesb.com.br.futebas.entidades.AcaoPartida;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.EstadoPartida;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Evento;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Partida;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Placar;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.GenericBusinessException;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.GenericDatabaseException;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.PartidaEstadoInvalidoException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Criado por Sinvas em 13/10/2015.
 */
public class MainRules {

    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public final int CONST_ID_TIME_A = 1;
    public final int CONST_ID_TIME_B = 2;

    //private static int idProximaPartida;

    private Partida partidaCorrente;
    private Evento eventoCorrente;


    public MainRules(Context context) throws GenericBusinessException {

        this.context = context;
        dbHelper = new DatabaseHelper(this.context);

        //Retorna evento de hoje se houver
        eventoCorrente = dbHelper.retornaEvento();

        //Recupera partida em andamento se houver.
        List<EstadoPartida> listaEstados = new ArrayList<>();
        listaEstados.add(EstadoPartida.INICIADA);
        listaEstados.add(EstadoPartida.NAO_INICIADA);
        listaEstados.add(EstadoPartida.PARADA);
        Partida ultimaPartida = dbHelper.existePartidaEvento(eventoCorrente.getId(), listaEstados);

        //TODO: Buscar no BD o valor do id da proxima Partida (max).
        //idProximaPartida = 0;

        if (ultimaPartida.getId() != 0){
            //Ao iniciar, verifica se havia alguma partida em andamento nesta data, para carregar seus dados
            partidaCorrente = ultimaPartida;
            //Recuoera demais dados da partida (times, etc)
            //TODO: Buscar no BD estas informações
        }else{
            //Se não existe, registra uma nova partida automaticamente
            //idProximaPartida = idProximaPartida + 1;
            //partidaCorrente = new Partida(idProximaPartida);
            partidaCorrente = new Partida(0);
            partidaCorrente.getTimeA().setId(CONST_ID_TIME_A);
            partidaCorrente.getTimeB().setId(CONST_ID_TIME_B);
            try {
                partidaCorrente = dbHelper.gravaPartidaEvento(partidaCorrente, eventoCorrente);
            } catch (GenericDatabaseException e) {
                throw new GenericBusinessException(e.getMessage());
            }

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

        if (this.partidaCorrente.getEstado() == EstadoPartida.PARADA){
            this.partidaCorrente.recomecar();
        }else{
            this.partidaCorrente.iniciar();
        }
        try {
            partidaCorrente = dbHelper.gravaPartidaEvento(partidaCorrente, eventoCorrente);
        } catch (GenericDatabaseException e) {
            throw new PartidaEstadoInvalidoException(AcaoPartida.INICIAR, partidaCorrente, e);
        }

    }

    public void pausaPartida() throws PartidaEstadoInvalidoException {
        this.partidaCorrente.pausar();

        try {
            partidaCorrente = dbHelper.gravaPartidaEvento(partidaCorrente, eventoCorrente);
        } catch (GenericDatabaseException e) {
            throw new PartidaEstadoInvalidoException(AcaoPartida.INICIAR, partidaCorrente, e);
        }
    }

    public void encerraPartida() throws PartidaEstadoInvalidoException {
        this.partidaCorrente.finalizar();

        try {
            partidaCorrente = dbHelper.gravaPartidaEvento(partidaCorrente, eventoCorrente);
        } catch (GenericDatabaseException e) {
            throw new PartidaEstadoInvalidoException(AcaoPartida.INICIAR, partidaCorrente, e);
        }
    }

    public void registraGol(int idTime, int idJogador) throws GenericBusinessException {
        if (this.partidaCorrente.getEstado() == EstadoPartida.INICIADA) {
            if (idTime == this.partidaCorrente.getTimeA().getId()) {
                this.partidaCorrente.registrarGolTimeA(idJogador);
            } else {
                this.partidaCorrente.registrarGolTimeB(idJogador);
            }

            try {
                partidaCorrente = dbHelper.gravaPartidaEvento(partidaCorrente, eventoCorrente);
            } catch (GenericDatabaseException e) {
                throw new GenericBusinessException(e.getMessage());
            }
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

    public long retornaTempoDecorridoPartida(){
        return this.partidaCorrente.getTempoDecorrido().getTime();
    }

    public void setaTempoDecorridoPartida(long tempo) throws GenericBusinessException {
        this.partidaCorrente.setTempoDecorrido(new Date(tempo));
        try {
            partidaCorrente = dbHelper.gravaPartidaEvento(partidaCorrente, eventoCorrente);
        } catch (GenericDatabaseException e) {
            throw new GenericBusinessException(e.getMessage());
        }
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
            //idProximaPartida = idProximaPartida + 1;
            partidaCorrente = new Partida(0);
            partidaCorrente.getTimeA().setId(CONST_ID_TIME_A);
            partidaCorrente.getTimeB().setId(CONST_ID_TIME_B);
            try {
                partidaCorrente = dbHelper.gravaPartidaEvento(partidaCorrente, eventoCorrente);
            } catch (GenericDatabaseException e) {
                throw new GenericBusinessException(e.getMessage());
            }
        }


    }

}
