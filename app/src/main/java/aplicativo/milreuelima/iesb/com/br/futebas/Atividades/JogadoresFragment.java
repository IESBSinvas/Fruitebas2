package aplicativo.milreuelima.iesb.com.br.futebas.Atividades;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters.GerenciaBase;
import aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters.ListaJogadoresAdapter;
import aplicativo.milreuelima.iesb.com.br.futebas.R;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.JogadorLista;

/**
 * Created by edu on 12/10/15.
 */
public class JogadoresFragment extends Fragment {

    ListView listaJogadores;
    EditText nome;
    //List<JogadorLista> listaTemporaria;
    DateFormat df;
    ListaJogadoresAdapter adapter;
    GerenciaBase gerenciaBase;


    public JogadoresFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_jogadores, container, false);
        listaJogadores = (ListView) view.findViewById(R.id.jogadores_lista_principal);
        gerenciaBase = new GerenciaBase(getActivity());
        adapter = new ListaJogadoresAdapter(getActivity(), gerenciaBase.ConsultaJogadoresLista());
        listaJogadores.setAdapter(adapter);

        ImageButton incluir = (ImageButton) view.findViewById(R.id.jogadores_lista_btn_incluir);
        incluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incluiNovoJogador(v);
            }
        });
        return view;
    }

    public void incluiNovoJogador(View view){
        df = new SimpleDateFormat("h:mm a");
        nome = (EditText) getView().findViewById(R.id.jogadores_inclui_form);
        JogadorLista inclui = new JogadorLista();
        if(!nome.getText().toString().matches("")){
            inclui.setNome(nome.getText().toString());
            inclui.setData(df.format(Calendar.getInstance().getTime()));
            gerenciaBase.InserirJogador(inclui);
            atualizaLista();
            nome.setText("");
            Toast.makeText(getActivity(),"Novo jogador incluído :)",Toast.LENGTH_SHORT).show();
        }
    }

    public void atualizaLista(){
        adapter = new ListaJogadoresAdapter(getActivity(), gerenciaBase.ConsultaJogadoresLista());
        listaJogadores.setAdapter(adapter);
    }

}
