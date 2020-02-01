package com.vitaldentcix.vitaldentcix;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Seleccionar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText dia, mes, anno;
    RadioButton rbt1, rbt2, rbt3;
    TextView tvDni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        tvDni = (TextView) findViewById(R.id.tvDniNuevo);
        rbt1 = (RadioButton) findViewById(R.id.rbt1Nuevo);
        rbt2 = (RadioButton) findViewById(R.id.rbt2Nuevo);
        rbt3 = (RadioButton) findViewById(R.id.rbt3Nuevo);
        String recup_dni = getIntent().getStringExtra("n_dni");
        tvDni.setText(recup_dni);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seleccionar, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override*/
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            Intent intent = new Intent(this, Inicio.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_solicitar) {
            Intent intent = new Intent(this, SolicitarCitas.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_promociones) {
            Intent intent = new Intent(this, Servicios.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_contacto) {
            Intent intent = new Intent(this, Contacto.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_consulta) {
            Intent intent = new Intent(this, Consulta.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_sobre) {
            Intent intent = new Intent(this, QuienesSomos.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onPasar(View view) {
        Log.i("depurar", "click");
        tvDni = (TextView) findViewById(R.id.tvDniNuevo);
        Thread tr = new Thread() {
            @Override
            public void run() {
                tvDni = (TextView) findViewById(R.id.tvDniNuevo);
                final String result = enviarDatosGet(tvDni.getText().toString());
                Log.i("depurar", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosJson(result);
                        if (r > 0) {
                            MostrarData(result);
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String enviarDatosGet(String dnipaciente) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("https://vitaldentcix.com/d/buscar_historia.php?dni_paciente=" + dnipaciente);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
                ;
            }
        }
        catch (Exception e) {
         e.printStackTrace();
        }
        return result.toString();
    }

    public int obtDatosJson(String response) {
        int res = 0;
        try {
            JSONArray json = new JSONArray(response);
            if (json.length() > 0) {
                res = 1;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void MostrarData(String response) {
        String numero_historia = "";
        try {
            JSONArray json = new JSONArray(response);
            numero_historia = json.getJSONObject(0).getString("n_historia");
            Intent intent = new Intent(Seleccionar.this, PacienteNuevo.class);
            dia = (EditText) findViewById(R.id.etDiaNuevo);
            mes = (EditText) findViewById(R.id.etMesNuevo);
            anno = (EditText) findViewById(R.id.etAnnoNuevo);
            String fecha = anno.getText().toString() + "-" + mes.getText().toString() + "-" + dia.getText().toString();
            if (rbt1.isChecked()) {
                intent.putExtra("doctor", "08889556");
                intent.putExtra("ffecha", fecha);
                intent.putExtra("n_historia", numero_historia);
            } else if (rbt2.isChecked()) {
                intent.putExtra("doctor", "07267871");
                intent.putExtra("ffecha", fecha);
                intent.putExtra("n_historia", numero_historia);
            } else if (rbt3.isChecked()) {
                intent.putExtra("doctor", "73130621");
                intent.putExtra("ffecha", fecha);
                intent.putExtra("n_historia", numero_historia);
            }
            startActivity(intent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
