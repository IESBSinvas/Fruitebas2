package aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import aplicativo.milreuelima.iesb.com.br.futebas.entidades.JogadorLista;

/**
 * Created by edu on 12/10/15.
 */
public class GerenciaBase {

    private Context contexto;
    private DbHelper dbHelper;

    public GerenciaBase(Context contexto) {
        this.contexto = contexto;
        dbHelper = new DbHelper(contexto);
    }

    public List<JogadorLista> ConsultaJogadoresLista(){
        List<JogadorLista> resultado = new ArrayList<JogadorLista>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nome, data, favorito FROM jogadores_lista ORDER BY _id", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            JogadorLista j = new JogadorLista();
            j.setNome(cursor.getString(1));
            j.setData(cursor.getString(2));
            j.setFavorito(Boolean.valueOf(cursor.getString(3)));
            resultado.add(j);
            cursor.moveToNext();
        }
        return resultado;
    }

    public long InserirJogador(JogadorLista novo){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", novo.getNome());
        values.put("data",novo.getData());
        values.put("favorito", novo.isFavorito());
        long result = database.insert("jogadores_lista", null, values);
        return  result;
    }
}
