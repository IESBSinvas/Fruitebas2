package aplicativo.milreuelima.iesb.com.br.futebas.Atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import aplicativo.milreuelima.iesb.com.br.futebas.R;

public class ListaJogadores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jogadores);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_jogadores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int idSelecionado = item.getItemId();

        switch (idSelecionado) {
            case R.id.menu_novo_jogo:
                Intent intentNovoJogo = new Intent(this, MainFutebasActivity.class);
                startActivity(intentNovoJogo);
                break;
            case R.id.menu_pagamento:
                Intent intentPagamento = new Intent(this, Pagamento.class);
                startActivity(intentPagamento);
                break;
            case R.id.menu_preferencias:
                Intent intentPreferencia = new Intent(this, Preferencias.class);
                startActivity(intentPreferencia);
                break;
            case R.id.menu_espera:
                Intent intentListaJogador = new Intent(this, ListaJogadores.class);
                startActivity(intentListaJogador);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
