package android.milreuelima.iesb.com.br.futebas.Atividades;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.milreuelima.iesb.com.br.futebas.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListaJogadoresFragment extends Fragment {

    public ListaJogadoresFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_jogadores, container, false);
    }
}
