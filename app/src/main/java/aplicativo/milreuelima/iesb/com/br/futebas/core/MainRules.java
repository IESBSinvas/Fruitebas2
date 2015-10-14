package aplicativo.milreuelima.iesb.com.br.futebas.core;

import java.util.ArrayList;
import java.util.List;

import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Partida;

/**
 * Criado por Sinvas em 13/10/2015.
 */
public class MainRules {

    private Partida partidaCorrente;

    public MainRules(){

        //Ao iniciar, verifica se havia alguma partida em andamento nesta data, para carregar seus dados


        //Se não existe, registra uma nova partida automaticamente



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





    public void iniciaPartida(){

    }

    public void pausaPartida(){

    }

    public void encerraPartida(){

    }

    public void registraGol(int idTime, int idJogador){

    }

    public void registraFalta(int idJogador){

    }

    public List<Partida> consultarPartidas(){
        List<Partida> retorno = new ArrayList<>();

        //... escrever o código que busca no BD aqui

        return retorno;
    }

}
