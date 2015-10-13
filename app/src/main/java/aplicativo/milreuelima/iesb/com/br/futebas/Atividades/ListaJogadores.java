package aplicativo.milreuelima.iesb.com.br.futebas.Atividades;

import aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters.TabsPageAdapter;
import aplicativo.milreuelima.iesb.com.br.futebas.R;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Created by edu on 12/10/15.
 */

public class ListaJogadores extends AppCompatActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPageAdapter mAdapter;
    private ActionBar actionBar;
    private String[] tabs = { "Jogadores", "Equipes" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jogadores);

        viewPager = (ViewPager) findViewById(R.id.pager);
        //actionBar = getActionBar();
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.verde));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.verde)));
        mAdapter = new TabsPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}
            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {}

    /**
     * Métodos anteriormente criados
     * Controle de menus da ActionBar
     */

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
            case R.id.menu_ListaJogador:
                Intent intentListaJogador = new Intent(this, ListaJogadores.class);
                startActivity(intentListaJogador);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
