package com.vitaldentcix.vitaldentcix;

import android.app.VoiceInteractor;
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
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Consulta extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AutoCompleteTextView etDni, etApPat, etApMat, etNombres, etMedico, etFecha, etHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        etDni = (AutoCompleteTextView) findViewById(R.id.etDni);
        etDni.requestFocus();
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

    public void onConsulta(View view) {
        Log.i("depurar","click");
        etApPat = (AutoCompleteTextView) findViewById(R.id.etApPat);
        etApMat = (AutoCompleteTextView) findViewById(R.id.etApMat);
        etNombres = (AutoCompleteTextView) findViewById(R.id.etNombres);
        etMedico = (AutoCompleteTextView) findViewById(R.id.etMedico);
        etFecha = (AutoCompleteTextView) findViewById(R.id.etFecha);
        etHora = (AutoCompleteTextView) findViewById(R.id.etHora);
        Thread tr = new Thread() {
            @Override
            public void run() {
                etDni = (AutoCompleteTextView) findViewById(R.id.etDni);
                final String result = enviarDatosGet(etDni.getText().toString());
                Log.i("depurar",result);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosJson(result);
                        if(r>0) {
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

            url = new URL("https://vitaldentcix.com/d/buscar_paciente.php?dni_paciente=" + dnipaciente);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();

            result = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void MostrarData(String reponse) {
        try {
            JSONArray json = new JSONArray(reponse);
            for (int i = 0; i < json.length(); i++) {
                etApPat.setText(json.getJSONObject(i).getString("apellido_pat"));
                etApMat.setText(json.getJSONObject(i).getString("apellido_mat"));
                etNombres.setText(json.getJSONObject(i).getString("nombres"));
                etFecha.setText(json.getJSONObject(i).getString("fecha"));
                etHora.setText(json.getJSONObject(i).getString("hora"));
                etMedico.setText(json.getJSONObject(i).getString("medico"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
