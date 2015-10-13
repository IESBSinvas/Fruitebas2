package aplicativo.milreuelima.iesb.com.br.futebas.Atividades;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import aplicativo.milreuelima.iesb.com.br.futebas.R;

public class MainFutebasActivity extends AppCompatActivity {
    boolean click = true;
    long tempoPausado = 0;
    int placarVisitanteContador = 0;
    int placarCasaContadoer = 0;
    int acrescimos = 0;
    long horafinal = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//      Cronometro
        final Chronometer cronometro = (Chronometer) findViewById(R.id.chronometer);
        final ImageButton btnStart = (ImageButton) findViewById(R.id.play);
        final ImageButton btnPause = (ImageButton) findViewById(R.id.pause);
//      ImageButton btnStop = (ImageButton) findViewById(R.id.stop);
        final ImageButton btnAcrescimo = (ImageButton) findViewById(R.id.Acrescimo);

        // Placar
        ImageButton btnMarcarGolCasa = (ImageButton) findViewById(R.id.marcarGolCasa);
        ImageButton btnMarcarGolVisitante = (ImageButton) findViewById(R.id.marcarGolVisitante);
        final TextView textoPlacarCasa = (TextView) findViewById(R.id.placarCasa);
        final TextView textoPlacarVisitante = (TextView) findViewById(R.id.placarVisitante);

        //Botoes habilitados no inicio
        btnPause.setEnabled(false);
        btnStart.setEnabled(true);
        btnAcrescimo.setEnabled(false);


        // implemntacao cronometro

       cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
           @Override
           public void onChronometerTick(Chronometer chronometer) {


               long horaAtual = ((SystemClock.elapsedRealtime()- cronometro.getBase())/1000)/60;

 //              String mensagem = "Hora atual " + horaAtual + "Hora Final " + horafinal;
 //              Toast.makeText(MainFutebasActivity.this, mensagem, Toast.LENGTH_LONG).show();

               if ((horafinal <= horaAtual)) {

                   String mensagem = "Fim de Jogo!!!!";
                   Toast.makeText(MainFutebasActivity.this, mensagem, Toast.LENGTH_LONG).show();

                   Intent intent = new Intent(MainFutebasActivity.this, MainFutebasActivity.class);
                   startActivity(intent);


               }

           }
       });



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
                btnStart.setEnabled(false);
                btnAcrescimo.setEnabled(true);
            }
        });;

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempoPausado = (SystemClock.elapsedRealtime() - cronometro.getBase());
                click = true;
                cronometro.stop();

                btnPause.setEnabled(false);
                btnStart.setEnabled(true);
                btnAcrescimo.setEnabled(true);
            }
        });
/*

//      Botao De parar ainda em Duvida se sera implementado

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

        Chronometer pararCronometro = (Chronometer) findViewById(R.id.chronometer);

        int idSelecionado = item.getItemId();

        switch (idSelecionado) {
            case R.id.menu_novo_jogo:
                pararCronometro.stop();
                Intent intentNovoJogo = new Intent(this, MainFutebasActivity.class);
                startActivity(intentNovoJogo);
            break;
           case R.id.menu_pagamento:
                pararCronometro.stop();
              Intent intentPagamento = new Intent(this, Pagamento.class);
              startActivity(intentPagamento);
                break;
            case R.id.menu_preferencias:
                pararCronometro.stop();
                Intent intentPreferencia = new Intent(this, Preferencias.class);
                startActivity(intentPreferencia);
                break;
            case R.id.menu_espera:
                pararCronometro.stop();
                Intent intentListaJogador = new Intent(this, ListaJogadores.class);
                startActivity(intentListaJogador);
                break;


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
