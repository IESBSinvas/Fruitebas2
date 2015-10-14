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
public class ListaEquipesTimesAdapter extends ArrayAdapter<JogadorLista> {

    private Context context;
    private List<JogadorLista> jogadores;

    public ListaEquipesTimesAdapter(Context context, List<JogadorLista> jogadores) {
        super(context,0,jogadores);
        this.context = context;
        this.jogadores = jogadores;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        JogadorLista jogador = jogadores.get(position);

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.equipes_lista_times_item, null);

            TextView nome = (TextView) view.findViewById(R.id.equipes_lista_times_item_nome);
            if(jogador.getNome().length() > 20) {
                nome.setText(jogador.getNome().substring(0, 20));
            }else{
                nome.setText(jogador.getNome());
            }

            TextView numero = (TextView) view.findViewById(R.id.equipes_lista_times_item_numero);
            numero.setText(jogador.getNumero()+". ");
        }
        return view;
    }
}
