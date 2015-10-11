package aplicativo.milreuelima.iesb.com.br.futebas.Atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Preferencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(aplicativo.milreuelima.iesb.com.br.futebas.R.layout.activity_preferencias);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(aplicativo.milreuelima.iesb.com.br.futebas.R.menu.menu_preferencias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int idSelecionado = item.getItemId();

        switch (idSelecionado) {
            case aplicativo.milreuelima.iesb.com.br.futebas.R.id.menu_novo_jogo:
                Intent intentNovoJogo = new Intent(this, MainFutebasActivity.class);
                startActivity(intentNovoJogo);
            case aplicativo.milreuelima.iesb.com.br.futebas.R.id.menu_pagamento:
                Intent intentPagamento = new Intent(this, Pagamento.class);
                startActivity(intentPagamento);
            case aplicativo.milreuelima.iesb.com.br.futebas.R.id.menu_preferencias:
                Intent intentPreferencia = new Intent(this, Preferencias.class);
                startActivity(intentPreferencia);
            case aplicativo.milreuelima.iesb.com.br.futebas.R.id.menu_ListaJogador:
                Intent intentListaJogador = new Intent(this, ListaJogadores.class);
                startActivity(intentListaJogador);

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
