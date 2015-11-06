package aplicativo.milreuelima.iesb.com.br.futebas.Atividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import aplicativo.milreuelima.iesb.com.br.futebas.R;
import aplicativo.milreuelima.iesb.com.br.futebas.core.MainRules;
import aplicativo.milreuelima.iesb.com.br.futebas.entidades.Configuracao;

public class Preferencias extends AppCompatActivity {


    private MainRules mainFutebas = null;
    private Configuracao conf = null;
    private EditText editTextMinJogadores;
    private EditText editTextMaxJogadores;
    private RadioButton radioUmTempo;
    private RadioButton radioDoisTempos;
    private EditText editDuracaoTempos;
    private CheckBox checkRecuperaPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(aplicativo.milreuelima.iesb.com.br.futebas.R.layout.activity_preferencias);

        conf = (Configuracao) getIntent().getSerializableExtra("conf");
        //conf = mainFutebas.getConfiguracao();

        //Assinalando objetos da tela
        editTextMinJogadores = (EditText)findViewById(R.id.nmMinJog);
        editTextMaxJogadores = (EditText)findViewById(R.id.nmMaxJog);
        radioUmTempo = (RadioButton)findViewById(R.id.rbUmTempo);
        radioDoisTempos = (RadioButton)findViewById(R.id.rbDoisTempos);
        editDuracaoTempos = (EditText)findViewById(R.id.txtDuracaoTempos);
        checkRecuperaPartida = (CheckBox)findViewById(R.id.ckRecuperaPartida);

        editTextMinJogadores.setText(String.valueOf(conf.getNumeroMinimoJogadores()));
        editTextMaxJogadores.setText(String.valueOf(conf.getNumeroMaximoJogadores()));
        radioUmTempo.setChecked(conf.getNumTempos() == 1);
        radioDoisTempos.setChecked(!radioUmTempo.isChecked());
        editDuracaoTempos.setText(String.valueOf(conf.getDuracaoTempo()));
        checkRecuperaPartida.setChecked(conf.isRecuperaPartida());

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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent itRetorno = new Intent();

        conf.setNumeroMinimoJogadores(Integer.valueOf(editTextMinJogadores.getText().toString()));
        conf.setNumeroMaximoJogadores(Integer.valueOf(editTextMaxJogadores.getText().toString()));
        conf.setNumTempos(radioUmTempo.isChecked()? 1 : 2);
        conf.setDuracaoTempo(Integer.valueOf(editDuracaoTempos.getText().toString()));
        conf.setRecuperaPartida(checkRecuperaPartida.isChecked());

        itRetorno.putExtra("conf", conf);

        Preferencias.this.setResult(RESULT_OK, itRetorno);
        Preferencias.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();


    }
}
