package aplicativo.milreuelima.iesb.com.br.futebas.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sinvas on 12/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "MySQLiteDB";
    private static final int VERSAO_DB = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Criacao das tabelas
        //db.execSQL("CREATE TABLE usuario (_id INTEGER PRIMARY KEY, nome TEXT, senha TEXT, saldo TEXT);");
        //db.execSQL("CREATE TABLE historico (_id INTEGER PRIMARY KEY, idusuario TEXT, datahora TEXT, pontuacao TEXT);");

        db.execSQL("CREATE TABLE jogador (_id INTEGER PRIMARY KEY, nome TEXT, telefone TEXT);");
        db.execSQL("CREATE TABLE evento (_id INTEGER PRIMARY KEY, nome TEXT, data TEXT, local TEXT, id_organizador INTEGER);");
        db.execSQL("CREATE TABLE jogador_evento (id_jogador INTEGER PRIMARY KEY, id_evento INTEGER PRIMARY KEY, hora_chegada TIME, numero_gols INTEGER, numero_faltas INTEGER);");

        db.execSQL("CREATE TABLE time (_id INTEGER PRIMARY KEY, nome TEXT);");
        db.execSQL("CREATE TABLE jogador_time (posicao_fila_time INTEGER, id_jogador INTEGER, id_time INTEGER);");

        db.execSQL("CREATE TABLE partida (_id INTEGER PRIMARY KEY, id_evento INTEGER, id_time_a INTEGER, id_time_b INTEGER, data DATE, hora_inicio TIME, hora_fim TIME, " +
                                          "gols_time_a INTEGER, gols_time_b INTEGER);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Escrever aqui o codigo que sera executado num upgrade de versao do banco de dados.
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
