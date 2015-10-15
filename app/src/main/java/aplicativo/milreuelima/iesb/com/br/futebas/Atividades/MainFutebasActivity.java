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
import aplicativo.milreuelima.iesb.com.br.futebas.core.MainRules;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.EstadoPartida;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.GenericBusinessException;
import aplicativo.milreuelima.iesb.com.br.futebas.exceptions.PartidaEstadoInvalidoException;

public class MainFutebasActivity extends AppCompatActivity {
    boolean click = true;
    long tempoPausado = 0;
    int placarVisitanteContador = 0;
    int placarCasaContadoer = 0;
    int acrescimos = 0;
    long horafinal = 10;

    //SBL
    MainRules mainFutebas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mainFutebas = new MainRules(this);
        } catch (GenericBusinessException e) {
            trataGenericBusinessException(e);
        }

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
 //              Toast.makeText(MainFutebasActivity.this, mensagem, Toast.LENGTH
 //
 // if ((horafinal <= horaAtual)) {

               try {
                   EstadoPartida estadoAtualPartida = mainFutebas.getEstadoPartidaCorrente();

                   if ((estadoAtualPartida != EstadoPartida.INICIADA) && (mainFutebas.avaliaPartida() == EstadoPartida.FINALIZADA)) {
                       String mensagem = "Fim de Jogo!!!!";
                       Toast.makeText(MainFutebasActivity.this, mensagem, Toast.LENGTH_LONG).show();

                       Intent intent = new Intent(MainFutebasActivity.this, MainFutebasActivity.class);
                       startActivity(intent);
                   }
               } catch (GenericBusinessException e) {
                    trataGenericBusinessException(e);
               }
           }
       });



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mainFutebas.iniciaPartida(); //SBL

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
                } catch (PartidaEstadoInvalidoException e) {
                    trataGenericBusinessException(e);
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mainFutebas.pausaPartida();

                    tempoPausado = (SystemClock.elapsedRealtime() - cronometro.getBase());
                    click = true;
                    cronometro.stop();

                    btnPause.setEnabled(false);
                    btnStart.setEnabled(true);
                    btnAcrescimo.setEnabled(true);
                } catch (PartidaEstadoInvalidoException e) {
                    trataGenericBusinessException(e);
                }
            }
        });
/*

//      Botao De parar ainda em Duvida se sera implementado

        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                click = true;
                mainFutebas.encerraPartida();
                cronometro.stop();
                cronometro.setText("00:00");

            }
        });
    */

        //  implemantacao Placar

        btnMarcarGolCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //placarCasaContadoer = placarCasaContadoer + 1;
                try {
                    mainFutebas.registraGol(mainFutebas.CONST_ID_TIME_A, 0);
                    placarCasaContadoer = mainFutebas.getPlacarPartidaCorrente().getGolsA();

                    textoPlacarCasa.setText(" " + placarCasaContadoer + " ");
                } catch (GenericBusinessException e) {
                    trataGenericBusinessException(e);
                }
            }
        });

        btnMarcarGolVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //placarVisitanteContador = placarVisitanteContador + 1;
                try {
                    mainFutebas.registraGol(mainFutebas.CONST_ID_TIME_B, 0);
                    placarVisitanteContador = mainFutebas.getPlacarPartidaCorrente().getGolsB();

                    textoPlacarVisitante.setText(" " + placarVisitanteContador + " ");
                } catch (GenericBusinessException e) {
                    trataGenericBusinessException(e);
                }
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

        try {
            switch (idSelecionado) {
                case R.id.menu_novo_jogo:
                    this.mainFutebas.prepararNovaPartida();

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

        } catch (GenericBusinessException e) {
            trataGenericBusinessException(e);
        }
        return super.onOptionsItemSelected(item);
    }

    private void trataGenericBusinessException(Exception e){
        e.printStackTrace();
        Toast.makeText(MainFutebasActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

}
