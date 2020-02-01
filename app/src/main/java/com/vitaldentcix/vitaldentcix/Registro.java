package com.vitaldentcix.vitaldentcix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import org.json.JSONArray;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Registro extends AppCompatActivity{
    AutoCompleteTextView etDniPaciente, etApellidoPat, etApellidoMat, etNombres, etTelefono, etCelular, etDnacimiento,
            etMnacimiento, etAnacimiento, etDireccion, etCorreo;
    String dni_pac = "", apellido_pat_paciente = "", apellido_mat_paciente = "", nombres_paciente = "", telefono_paciente = "",
    celular_paciente = "", d_nacimiento_paciente = "", direccion_paciente = "", correo_paciente = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
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

        etDniPaciente = (AutoCompleteTextView) findViewById(R.id.RegistroDni);
        etApellidoPat = (AutoCompleteTextView) findViewById(R.id.RegistroApellidoPaterno);
        etApellidoMat = (AutoCompleteTextView) findViewById(R.id.RegistroApellidoMaterno);
        etNombres = (AutoCompleteTextView) findViewById(R.id.RegistroNombres);
        etTelefono = (AutoCompleteTextView) findViewById(R.id.RegistroTelefono);
        etCelular = (AutoCompleteTextView) findViewById(R.id.RegistroCelular);
        etDnacimiento = (AutoCompleteTextView) findViewById(R.id.RegistroDiaNacimiento);
        etMnacimiento = (AutoCompleteTextView) findViewById(R.id.RegistroMesNacimiento);
        etAnacimiento = (AutoCompleteTextView) findViewById(R.id.RegistroAnnoNaciminto);
        etDireccion = (AutoCompleteTextView) findViewById(R.id.RegistroDireccion);
        etCorreo = (AutoCompleteTextView) findViewById(R.id.RegistroCorreoEl);
        etDniPaciente.requestFocus();
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
        getMenuInflater().inflate(R.menu.registro, menu);
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
    @Override
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
    }*/

    public void onReg(View view) {
        dni_pac = etDniPaciente.getText().toString();
        Log.i("depurar","click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                apellido_pat_paciente = etApellidoPat.getText().toString();
                apellido_mat_paciente = etApellidoMat.getText().toString();
                nombres_paciente = etNombres.getText().toString();
                telefono_paciente = etTelefono.getText().toString();
                celular_paciente = etCelular.getText().toString();
                d_nacimiento_paciente = etAnacimiento.getText().toString() + "-" + etMnacimiento.getText().toString() + "-" +
                        etDnacimiento.getText().toString();
                direccion_paciente = etDireccion.getText().toString();
                correo_paciente = etCorreo.getText().toString();
                final String result = enviarDatosGet(dni_pac, apellido_pat_paciente, apellido_mat_paciente, nombres_paciente,
                        telefono_paciente, celular_paciente, d_nacimiento_paciente, direccion_paciente, correo_paciente);
                Log.i("depurar",result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosJson(result);
                        if(r > 0) {
                            MostrarData();
                        }
                    }
                });
            }
        };
        tr.start();
    }


    public String enviarDatosGet(String dni, String paterno, String materno, String nom, String
            telf, String cel, String date, String address, String email) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {

            url = new URL("https://vitaldentcix.com/d/registrar_paciente.php?dni_paciente=" + dni + "&apellido_pat=" +
                    paterno + "&apellido_mat=" + materno + "&nombres=" + nom + "&telefono=" + telf +
                    "&celular=" + cel + "&f_nacimiento=" + date + "&direccion=" + address + "&correo=" + email);

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
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public void MostrarData() {
            dni_pac = etDniPaciente.getText().toString();
            Intent intent = new Intent(Registro.this, Seleccionar.class);
            intent.putExtra("n_dni", dni_pac);
            startActivity(intent);
            finish();
    }
}
