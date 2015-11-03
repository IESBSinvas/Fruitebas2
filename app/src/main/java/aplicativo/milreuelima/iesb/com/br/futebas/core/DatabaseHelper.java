package aplicativo.milreuelima.iesb.com.br.futebas.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Configuracao;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.EstadoPartida;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Evento;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Jogador;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Partida;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Placar;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Time;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.GenericDatabaseException;

import aplicativo.milreuelima.iesb.com.br.futebas.core.FutebasDefaultValues;

import java.util.Date;

/**
 * Criado por Sinvas em 12/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "MySQLiteDB";
    private static final int VERSAO_DB = 1;
    private Context context;
    //private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO_DB);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Criacao das tabelas
        //db.execSQL("CREATE TABLE usuario (_id INTEGER PRIMARY KEY, nome TEXT, senha TEXT, saldo TEXT);");
        //db.execSQL("CREATE TABLE historico (_id INTEGER PRIMARY KEY, idusuario TEXT, datahora TEXT, pontuacao TEXT);");

        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS jogador (_id INTEGER PRIMARY KEY, " +
                                                            "nome TEXT, " +
                                                            "telefone TEXT);");

            db.execSQL("CREATE TABLE IF NOT EXISTS evento (_id INTEGER PRIMARY KEY, " +
                                                            "nome TEXT, " +
                                                            "data TEXT, " +
                                                            "local TEXT, " +
                                                            "id_organizador INTEGER);");

            db.execSQL("CREATE TABLE IF NOT EXISTS jogador_evento (id_jogador INTEGER, " +
                                                            "id_evento INTEGER, " +
                                                            "hora_chegada LONG, " +
                                                            "numero_gols INTEGER, " +
                                                            "numero_faltas INTEGER, " +
                                                            "UNIQUE(id_jogador, id_evento));");

            db.execSQL("CREATE TABLE IF NOT EXISTS time (_id INTEGER PRIMARY KEY, " +
                                                         "nome TEXT);");

            db.execSQL("CREATE TABLE IF NOT EXISTS jogador_time (posicao_fila_time INTEGER, " +
                                                                "id_jogador INTEGER, " +
                                                                "id_time INTEGER);");

            db.execSQL("CREATE TABLE IF NOT EXISTS partida (_id INTEGER PRIMARY KEY, " +
                                                            "id_evento INTEGER, " +
                                                            "id_time_a INTEGER, " +
                                                            "id_time_b INTEGER, " +
                                                            "data LONG, " +
                                                            "hora_inicio LONG, " +
                                                            "hora_fim LONG, " +
                                                            "gols_time_a INTEGER, " +
                                                            "gols_time_b INTEGER, " +
                                                            "estado INTEGER, " +
                                                            "hora_fim_previsto LONG, " +
                                                            "tempo_decorrido LONG);");

            db.execSQL("CREATE TABLE IF NOT EXISTS configuracao (_id INTEGER PRIMARY KEY, " +
                                                                "num_min_jogadores INTEGER, " +
                                                                "num_max_jogadores INTEGER);");

            carregaConfiguracoesIniciais(db);

        }catch (Exception e){
            Toast.makeText(this.context, "Ocorreu um erro ao criar estruturas de dados. " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void carregaConfiguracoesIniciais(SQLiteDatabase db){

        Cursor cursorConfiguracao = null;
        long result = 0;

        try {

            cursorConfiguracao = db.rawQuery("SELECT _id, num_min_jogadores, num_max_jogadores FROM configuracao WHERE id_evento = 1)", null);
            cursorConfiguracao.moveToFirst();

            if (cursorConfiguracao.getCount() == 0){
                //Já que mão existe regitro de configuracao, cria um com os valores padrão

                ContentValues value = new ContentValues();

                value.put("num_min_jogadores", FutebasDefaultValues.QTD_MIN_JOGADORES_TIME);
                value.put("num_max_jogadores", FutebasDefaultValues.QTD_MAX_JOGADORES_TIME);

                result = db.insert("configuracao", null, value);

                if (result == -1) {
                    throw new Exception("Erro ao gravar configurações iniciais!");
                }
            }
        }catch (Exception ex){

        }finally {
            if(cursorConfiguracao != null){ cursorConfiguracao.close();}
        }
    }

    public Configuracao carregaConfiguracoes(){
        Configuracao retorno = new Configuracao();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorConfiguracao = null;
        long result = 0;

        try {

            cursorConfiguracao = db.rawQuery("SELECT _id, num_min_jogadores, num_max_jogadores FROM configuracao WHERE _id = 1", null);
            cursorConfiguracao.moveToFirst();

            if (cursorConfiguracao.getCount() >= 1){
                //Recupera o registro mais recente
                retorno.setNumeroMinimoJogadores(cursorConfiguracao.getInt(1));
                retorno.setNumeroMaximoJogadores(cursorConfiguracao.getInt(2));
            }
        }catch (Exception ex){
             retorno = new Configuracao();
            retorno.setNumeroMinimoJogadores(FutebasDefaultValues.QTD_MIN_JOGADORES_TIME);
            retorno.setNumeroMaximoJogadores(FutebasDefaultValues.QTD_MAX_JOGADORES_TIME);

        }finally {
            if(cursorConfiguracao != null){ cursorConfiguracao.close();}
        }

        return retorno;
    }

    public void gravaConfiguracao(Configuracao conf) throws GenericDatabaseException {
        Partida retorno;

        SQLiteDatabase db = this.getWritableDatabase();
        long result;

        try {

            ContentValues value = new ContentValues();

            value.put("num_min_jogadores", conf.getNumeroMinimoJogadores());
            value.put("num_max_jogadores", conf.getNumeroMaximoJogadores());

            result =  db.update("configuracao", value, "_id = 1", null );

            if (result == -1) {
                throw new Exception("Erro ao gravar configuração!");
            }

        }catch (Exception ex){
            throw new GenericDatabaseException(ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Escrever aqui o codigo que sera executado num upgrade de versao do banco de dados.
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public Evento retornaEvento(){
        Evento retorno = new Evento();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorEvento = null;
        long result;

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            String dataHoje = formatter.format(new java.util.Date());

            cursorEvento = db.rawQuery("SELECT _id, nome, data, local, id_organizador FROM evento WHERE data = '" + dataHoje + "' ORDER BY data DESC", null);
            cursorEvento.moveToFirst();

            if (cursorEvento.getCount() >= 1) {
                //Já existia um evento hoje, recupera-o.
                retorno.setId(cursorEvento.getInt(0));
                retorno.setNome(cursorEvento.getString(1));
                retorno.setData(formatter.parse(cursorEvento.getString(2)));
                retorno.setLocal(cursorEvento.getString(3));
                retorno.setOrganizador(new Jogador(cursorEvento.getInt(4)));
                //retorno.setJogadores(); //TODO: Carregar os jogadores depois.

            }else{
                //Como não existe evento cadastrado hoje, cria-o.
                ContentValues value = new ContentValues();

                retorno.setNome("Pelada do dia " + dataHoje);
                retorno.setData(formatter.parse(dataHoje));
                retorno.setLocal("Indefinido");
                retorno.setOrganizador(new Jogador(0));

                value.put("nome", retorno.getNome());
                value.put("data", formatter.format(retorno.getData()));
                value.put("local", retorno.getLocal());
                value.put("id_organizador", retorno.getOrganizador().getId());

                result = db.insert("evento", null, value);

                if (result == -1) {
                    throw new Exception("Erro ao cadastrar evento");
                }else{
                    retorno.setId((int)result);
                }
            }
        }catch (Exception ex){
            return retorno;
        }finally {
            if (cursorEvento != null) {
                cursorEvento.close();
            }
        }

        return retorno;
    }

    //Retorna sempre a partida mais recente
    public Partida existePartidaEvento(int idEvento, List<EstadoPartida> listaEstados) {

        SQLiteDatabase db = this.getReadableDatabase();
        Partida retorno;

        Cursor cursorPartida = null;
        long result = 0;

        try {

            cursorPartida = db.rawQuery("SELECT _id, id_time_a, id_time_b, hora_inicio, hora_fim, gols_time_a, gols_time_b, estado, hora_fim_previsto, tempo_decorrido FROM partida WHERE id_evento = " +
                                         idEvento + " AND estado IN (" + retornaInClauseEstados(listaEstados) + ") ORDER BY hora_inicio DESC", null);
            cursorPartida.moveToFirst();

            if (cursorPartida.getCount() >= 1){
                //Recupera o registro mais recente
                Placar placar = new Placar(cursorPartida.getInt(5), cursorPartida.getInt(6));
                Time timeA = new Time(cursorPartida.getInt(1));
                Time timeB = new Time(cursorPartida.getInt(2));

                //lembrar que estaremos guardando todas as datas e horas em milisegundos!!!
                retorno = new Partida(cursorPartida.getInt(0), placar, EstadoPartida.values()[cursorPartida.getInt(7)], timeA, timeB, new Date(cursorPartida.getLong(3)));
                if (!cursorPartida.isNull(8)) {
                    retorno.setHoraFimPrevisto(new Date(cursorPartida.getLong(8)));
                }
                if (!cursorPartida.isNull(9)) {
                    retorno.setTempoDecorrido(new Date(cursorPartida.getLong(9)));
                }else{
                    retorno.setTempoDecorrido(new Date(0));
                }
            }else{

                retorno = new Partida(0);
            }
        }catch (Exception ex){
            return new Partida(0);
        }finally {
            if(cursorPartida != null){ cursorPartida.close();}
        }

        return retorno;
    }

    private String retornaInClauseEstados(List<EstadoPartida> lista){

        short i = 0;
        String retorno = "";
        for (EstadoPartida e : lista) {
            if (i > 0){ retorno = retorno + ", ";}
            retorno = retorno + e.getId();
            i++;
        }
        return retorno;
    }




    public Partida gravaPartidaEvento(Partida partida, Evento evento) throws GenericDatabaseException {

        Partida retorno;

        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursorPartida = null;
        long result;

        try {

            if (partida.getId() != 0) {
                //É uma partida já gravada antes, faz UPDATE.

                ContentValues value = new ContentValues();

                value.put("id_evento", evento.getId());
                value.put("id_time_a", partida.getTimeA().getId());
                value.put("id_time_b", partida.getTimeB().getId());
                value.put("hora_inicio", partida.getHoraInicio().getTime());
                if (partida.getHoraFim() != null){
                    value.put("hora_fim", partida.getHoraFim().getTime());
                }
                value.put("gols_time_a", partida.getPlacar().getGolsA());
                value.put("gols_time_b", partida.getPlacar().getGolsB());
                value.put("estado", partida.getEstado().getId());
                if (partida.getHoraFimPrevisto() != null){
                    value.put("hora_fim_previsto", partida.getHoraFimPrevisto().getTime());
                }

                if (partida.getTempoDecorrido() != null){
                    value.put("tempo_decorrido", partida.getTempoDecorrido().getTime());
                }
                result =  db.update("partida", value, "_id =" + partida.getId(), null );

                if (result == -1) {
                    throw new Exception("Erro ao gravar partida!");
                }else{
                    retorno = partida;
                }

            }else{
                //É uma partida nova, faz INSERT.
                ContentValues value = new ContentValues();

                value.put("id_evento", evento.getId());
                value.put("id_time_a", partida.getTimeA().getId());
                value.put("id_time_b", partida.getTimeB().getId());
                value.put("hora_inicio", partida.getHoraInicio().getTime());
                if (partida.getHoraFim() != null){
                    value.put("hora_fim", partida.getHoraFim().getTime());
                }
                value.put("gols_time_a", partida.getPlacar().getGolsA());
                value.put("gols_time_b", partida.getPlacar().getGolsB());
                value.put("estado", partida.getEstado().getId());
                if (partida.getHoraFimPrevisto() != null){
                    value.put("hora_fim_previsto", partida.getHoraFimPrevisto().getTime());
                }

                if (partida.getTempoDecorrido() != null){
                    value.put("tempo_decorrido", partida.getTempoDecorrido().getTime());
                }

                result = db.insert("partida", null, value);

                if (result == -1) {
                    throw new Exception("Erro ao gravar partida!");
                }else{
                    retorno = new Partida((int)result, partida.getPlacar(), partida.getEstado(), partida.getTimeA(), partida.getTimeB(), partida.getHoraInicio(), partida.getHoraFim());
                    retorno.setHoraFimPrevisto(partida.getHoraFimPrevisto());
                }
            }
        }catch (Exception ex){
            throw new GenericDatabaseException(ex.getMessage());
        }finally {
            //cursorPartida.close();
        }

        return retorno;

    }
}
