package aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aplicativo.milreuelima.iesb.com.br.futebas.Atividades.ListaJogadores;
import aplicativo.milreuelima.iesb.com.br.futebas.R;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.JogadorLista;

/**
 * Created by edu on 12/10/15.
 */
public class ListaJogadoresAdapter extends ArrayAdapter<JogadorLista> {

    private Context context;
    private List<JogadorLista> jogadores;

    public ListaJogadoresAdapter(Context context, List<JogadorLista> jogadores) {
        super(context,0,jogadores);
        this.context = context;
        this.jogadores = jogadores;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        JogadorLista jogador = jogadores.get(position);

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.jogadores_lista_item, null);

            TextView nome = (TextView) view.findViewById(R.id.jogadores_lista_item_nome);
            if(jogador.getNome().length() > 25) {
                nome.setText(jogador.getNome().substring(0, 25));
            }else{
                nome.setText(jogador.getNome());
            }

            TextView data = (TextView) view.findViewById(R.id.jogadores_lista_item_hora);
            data.setText(jogador.getData().toString());

        }
        return view;
    }
}
