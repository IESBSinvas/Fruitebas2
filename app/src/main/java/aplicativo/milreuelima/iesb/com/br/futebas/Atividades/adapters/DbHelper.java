package aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edu on 12/10/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "FutebasDB";
    private static int VERSAO = 1;

    public DbHelper(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE jogadores_lista(_id INTEGER PRIMARY KEY, nome TEXT, data TEXT, favorito TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
