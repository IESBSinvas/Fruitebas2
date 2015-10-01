package android.milreuelima.iesb.com.br.futebas.Atividades;

import android.media.MediaPlayer;
import android.milreuelima.iesb.com.br.futebas.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.sax.StartElementListener;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFutebasActivity extends AppCompatActivity {
    boolean click = true;
    long tempoPausado = 0;
    int placarVisitanteContador = 0;
    int placarCasaContadoer = 0;
    int acrescimos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//      Cronometro
        final Chronometer cronometro = (Chronometer) findViewById(R.id.chronometer);
        ImageButton btnStart = (ImageButton) findViewById(R.id.play);
        final ImageButton btnPause = (ImageButton) findViewById(R.id.pause);
//      ImageButton btnStop = (ImageButton) findViewById(R.id.stop);

        // Placar
        ImageButton btnMarcarGolCasa = (ImageButton) findViewById(R.id.marcarGolCasa);
        ImageButton btnMarcarGolVisitante = (ImageButton) findViewById(R.id.marcarGolVisitante);
        final TextView textoPlacarCasa = (TextView) findViewById(R.id.placarCasa);
        final TextView textoPlacarVisitante = (TextView) findViewById(R.id.placarVisitante);


        // implemntacao cronometro
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click)
                {
                    cronometro.setBase(SystemClock.elapsedRealtime() - tempoPausado);
                    // Som de apito
                    MediaPlayer player = MediaPlayer.create(MainFutebasActivity.this, R.raw.apitodefutebol);
                player.start();

                }
                    cronometro.start();
                btnPause.setEnabled(true);
            }
        });;

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempoPausado = (SystemClock.elapsedRealtime() - cronometro.getBase());
                click = true;
                cronometro.stop();
                btnPause.setEnabled(false);
            }
        });
/*

//      Botao De para ainda em Duvida se sera implementado

        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                click = true;
                cronometro.stop();
                cronometro.setText("00:00");

            }
        });
    */

        //  implemantacao Placar

        btnMarcarGolCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placarCasaContadoer = placarCasaContadoer + 1;

                textoPlacarCasa.setText(" " + placarCasaContadoer + " ");
            }
        });

        btnMarcarGolVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placarVisitanteContador = placarVisitanteContador + 1;
                textoPlacarVisitante.setText(" " + placarVisitanteContador + " ");
            }
        });
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idSelecionado = item.getItemId();

        switch (idSelecionado) {
            case R.id.menu_novo_jogo:

                break;
            case R.id.menu_pagamento:
    //           Intent intent = new Intent(this, Pagamento.class);
    //            startActivity(intent);

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
