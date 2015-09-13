package android.milreuelima.iesb.com.br.futebas;

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

public class MainFutebasActivity extends AppCompatActivity {
    boolean click = true;
    long tempoPausado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Chronometer cronometro = (Chronometer) findViewById(R.id.chronometer);
        ImageButton btnStart = (ImageButton) findViewById(R.id.play);
        ImageButton btnPause = (ImageButton) findViewById(R.id.pause);
        ImageButton btnStop = (ImageButton) findViewById(R.id.stop);



        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (click)
                    cronometro.setBase(SystemClock.elapsedRealtime() - tempoPausado);
                cronometro.start();


            }
        });;


        btnPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                tempoPausado = (SystemClock.elapsedRealtime() - cronometro.getBase());
                click = false;
                cronometro.stop();



            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                click = true;
                cronometro.stop();
                cronometro.setText("00:00:00");

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
