package aplicativo.milreuelima.iesb.com.br.futebas.entidades;

import java.util.Date;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.GenericBusinessException;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.PartidaEstadoInvalidoException;

/**
 * Criado por Sinvas em 25/08/2015.
 */
public class Partida {

    private int id;
    private Time timeA;
    private Time timeB;
    private Date horaInicio;
    private Date horaFim;
    private Date horaFimPrevisto;
    private Placar placar;
    private EstadoPartida estado;

    public Partida(int id){
        //Se foi instanciada uma nova partida, coloca automaticamente seu estado inicial;
        this.estado = EstadoPartida.NAO_INICIADA;
    }

    public Partida(int id, Placar placar, EstadoPartida estado, Time timeA, Time timeB, Date horaInicio){
        //Se é uma partida carregada da base, seta seus valores.

        this.id = id;
        this.placar = placar;
        this.estado = estado;
        this.timeA = timeA;
        this.timeB = timeB;
        this.horaInicio = horaInicio;
    }

    public EstadoPartida getEstado() {
        return estado;
    }

    public int getId() {
        return id;
    }

    public Time getTimeA() {
        return timeA;
    }

    public Time getTimeB() {
        return timeB;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public Date getHoraFimPrevisto() {
        return horaFimPrevisto;
    }

    public void setHoraFimPrevisto(Date horaFimPrevisto) throws GenericBusinessException {
        if (!horaFimPrevisto.after(this.horaInicio)){
            throw new GenericBusinessException("A hora de fim previsto para a partida precisa ser maior que a hora de início!");
        }else{
            this.horaFimPrevisto = horaFimPrevisto;
        }

    }

    public Placar getPlacar() {
        //Não retorna o placar diretamente para que nineguém possa alterar o placar externamente.
        Placar retorno = new Placar();
        try {
            retorno = (Placar)placar.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public void iniciar() throws PartidaEstadoInvalidoException {
        if (this.estado == EstadoPartida.NAO_INICIADA){

            this.estado = EstadoPartida.INICIADA;
            this.horaInicio = new java.util.Date();
            this.placar = new Placar();
            //... escrever as demais regras de negócio aqui (validar equilíbrio de times? validar mais alguma coisa?
            //Controlar o cronômetro aqui.

        }else{
            throw new PartidaEstadoInvalidoException(AcaoPartida.INICIAR, this);
        }
    }

    public void pausar() throws PartidaEstadoInvalidoException {
        if (this.estado == EstadoPartida.INICIADA){

            this.estado = EstadoPartida.PARADA;
            //... escrever as demais regras de negócio aqui
            //Controlar o cronômetro aqui.
        }else{
            throw new PartidaEstadoInvalidoException(AcaoPartida.PAUSAR, this);
        }
    }

    public void recomecar() throws PartidaEstadoInvalidoException {
        if (this.estado == EstadoPartida.PARADA){

            this.estado = EstadoPartida.INICIADA;
            //... escrever as demais regras de negócio aqui
            //Controlar o cronômetro aqui.
        }else{
            throw new PartidaEstadoInvalidoException(AcaoPartida.REINICIAR, this);
        }
    }

    public void finalizar() throws PartidaEstadoInvalidoException {
        if (this.estado == EstadoPartida.INICIADA || this.estado == EstadoPartida.PARADA){
            //Mesmo em pausa será possível finalizar o jogo - emergencias.
            this.estado = EstadoPartida.FINALIZADA;
            this.horaFim = new java.util.Date();
            //... escrever as demais regras de negócio aqui.
            //Controlar o cronômetro aqui.
        }else{
            throw new PartidaEstadoInvalidoException(AcaoPartida.PAUSAR, this);
        }
    }

    public void registrarGolTimeA(int idJogador) throws GenericBusinessException {
        registrarGol(Placar.TimesPlacar.TIME_A, idJogador);
    }

    public void registrarGolTimeB(int idJogador) throws GenericBusinessException {
        registrarGol(Placar.TimesPlacar.TIME_B, idJogador);
    }

    private void registrarGol(Placar.TimesPlacar time, int idJogador) throws GenericBusinessException {

       if (time != Placar.TimesPlacar.TIME_INDEFINIDO){
           this.placar.adicionaGol(time);
       }else{
           throw new GenericBusinessException("É necessário definir o time que fez o gol!");
       }
    }

    public void desfazUltimoGol(){
        this.placar.desfazerJogada();
    }

    public EstadoPartida avaliaFinalPartida() throws GenericBusinessException {
        EstadoPartida retorno = this.estado;

        Date horaAtual = new java.util.Date();

        if (this.estado == EstadoPartida.INICIADA){
            //Avalia se já está na hora de encerrar a partida
            if (!horaAtual.after(this.horaFimPrevisto)){
                try {
                    this.finalizar();
                } catch (PartidaEstadoInvalidoException e) {
                    e.printStackTrace();
                    throw new GenericBusinessException(e.getMessage());
                }
            }
        }

        return retorno;
    }

}
