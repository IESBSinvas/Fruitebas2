package aplicativo.milreuelima.iesb.com.br.futebas.Atividades;

/**
 * Created by edu on 12/10/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters.GerenciaBase;
import aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters.ListaEquipesTimesAdapter;
import aplicativo.milreuelima.iesb.com.br.futebas.R;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.JogadorLista;

public class EquipesFragment extends Fragment {

    GerenciaBase gerenciaBase;
    ListView listaViewTimeA;
    ListView listaViewTimeB;
    ListView listaViewProximos;
    List<JogadorLista> listaTimeA;
    List<JogadorLista> listaTimeB;
    List<JogadorLista> listaProximos;
    List<JogadorLista> listaCompleta;
    ListaEquipesTimesAdapter listaEquipesTimesAdapter;

    public EquipesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_equipes, container, false);
        gerenciaBase = new GerenciaBase(getActivity());
        listaViewTimeA = (ListView) view.findViewById(R.id.lista_equipes_time_a);
        listaViewTimeB = (ListView) view.findViewById(R.id.lista_equipes_time_b);
        listaViewProximos = (ListView) view.findViewById(R.id.lista_equipes_proximos);

        listaTimeA = new ArrayList<JogadorLista>();
        listaTimeB = new ArrayList<JogadorLista>();
        listaProximos = new ArrayList<JogadorLista>();

        listaCompleta =  gerenciaBase.ConsultaJogadoresLista();

        if(listaCompleta.size()>0) {
            separaTimes();
            montaListas();
        }

        return view;
    }

    public void separaTimes(){

        int timeA = 1;
        int timeB = 1;
        int maximoTime = 5;
        int proximoNumero = 1;

        for(int i=0; i < listaCompleta.size(); i++){
            if(i < maximoTime *2){
                if(timeA <= timeB){
                    JogadorLista a = new JogadorLista();
                    a.setNome(listaCompleta.get(i).getNome());
                    a.setNumero(timeA);
                    a.setFavorito(false);
                    listaTimeA.add(a);
                    timeA++;
                }else{
                    JogadorLista b = new JogadorLista();
                    b.setNome(listaCompleta.get(i).getNome());
                    b.setNumero(timeB);
                    b.setFavorito(false);
                    listaTimeB.add(b);
                    timeB++;
                }
            }else{
                JogadorLista proximo = new JogadorLista();
                proximo.setNumero(proximoNumero);
                proximo.setNome(listaCompleta.get(i).getNome());
                proximo.setFavorito(false);
                listaProximos.add(proximo);
                proximoNumero++;
            }
        }
    }

    public  void montaListas(){
        listaEquipesTimesAdapter = new ListaEquipesTimesAdapter(getActivity(),listaTimeA);
        listaViewTimeA.setAdapter(listaEquipesTimesAdapter);

        listaEquipesTimesAdapter = new ListaEquipesTimesAdapter(getActivity(),listaTimeB);
        listaViewTimeB.setAdapter(listaEquipesTimesAdapter);

        listaEquipesTimesAdapter = new ListaEquipesTimesAdapter(getActivity(),listaProximos);
        listaViewProximos.setAdapter(listaEquipesTimesAdapter);
    }

}
