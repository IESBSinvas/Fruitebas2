package android.milreuelima.iesb.com.br.futebas.Atividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.milreuelima.iesb.com.br.futebas.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Pagamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        View btnPagamento = findViewById(R.id.calcularJogador);

        btnPagamento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText qtdjogadores = (EditText) findViewById(R.id.quantidadeJogadores);
                EditText valorTotal = (EditText) findViewById(R.id.valorTotal);
                TextView pgPessoa = (TextView) findViewById(R.id.pagamentoPorPessoa);

                String qtdJogadoresString = qtdjogadores.getEditableText().toString();
                Double qtdJogadoresInteger = Double.parseDouble(qtdJogadoresString);

                String valorTotalString = valorTotal.getEditableText().toString();
                Double valorTotalInteger = Double.parseDouble(valorTotalString);

                Double pagamento = (valorTotalInteger/qtdJogadoresInteger);

                pgPessoa.setText(pagamento + " ");


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pagamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idSelecionado = item.getItemId();

        switch (idSelecionado) {
            case R.id.menu_novo_jogo:
                Intent intentNovoJogo = new Intent(this, MainFutebasActivity.class);
                startActivity(intentNovoJogo);
                break;
            case R.id.menu_pagamento:
                Intent intentPagamento = new Intent(this, Pagamento.class);
                startActivity(intentPagamento);
            case R.id.menu_preferencias:
                Intent intentPreferencia = new Intent(this, Preferencias.class);
                startActivity(intentPreferencia);
            case R.id.menu_ListaJogador:
                Intent intentListaJogador = new Intent(this, ListaJogadores.class);
                startActivity(intentListaJogador);

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
