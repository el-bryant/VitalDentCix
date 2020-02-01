package com.vitaldentcix.vitaldentcix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeleccionarRegular extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText dia, mes, anno;
    RadioButton rbt1Regular, rbt2Regular, rbt3Regular;
    AutoCompleteTextView etDniRegular, tvApellidoPaternoRegular, tvApellidoMaternoRegular, tvNombresRegular;
    PacienteRegular pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_regular);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View vi = inflater.inflate(R.layout.content_paciente_regular, null);
        lbl01 = (TextView) vi.findViewById(R.id.tvr0900am);
        lbl02 = (TextView) vi.findViewById(R.id.tvr0940am);
        lbl03 = (TextView) vi.findViewById(R.id.tvr1020am);
        lbl04 = (TextView) vi.findViewById(R.id.tvr1100am);
        lbl05 = (TextView) vi.findViewById(R.id.tvr1140am);
        lbl07 = (TextView) vi.findViewById(R.id.tvr0400pm);
        lbl08 = (TextView) vi.findViewById(R.id.tvr0440pm);
        lbl09 = (TextView) vi.findViewById(R.id.tvr0520pm);
        lbl10 = (TextView) vi.findViewById(R.id.tvr0600pm);
        lbl11 = (TextView) vi.findViewById(R.id.tvr0640pm);
        lbl12 = (TextView) vi.findViewById(R.id.tvr0720pm);*/





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

        rbt1Regular = (RadioButton) findViewById(R.id.rbt1Regular);
        rbt2Regular = (RadioButton) findViewById(R.id.rbt2Regular);
        rbt3Regular = (RadioButton) findViewById(R.id.rbt3Regular);
        etDniRegular = (AutoCompleteTextView) findViewById(R.id.etDniRegular);
        tvApellidoPaternoRegular = (AutoCompleteTextView) findViewById(R.id.tvApellidoPaternoRegular);
        tvApellidoMaternoRegular = (AutoCompleteTextView) findViewById(R.id.tvApellidoMaternoRegular);
        tvNombresRegular = (AutoCompleteTextView) findViewById(R.id.tvNombresRegular);
        etDniRegular.requestFocus();
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

    public void onPasarRegular(View view) {
        Log.i("depurar", "click");
        etDniRegular = (AutoCompleteTextView) findViewById(R.id.etDniRegular);
        Thread tr = new Thread() {
            @Override
            public void run() {
                etDniRegular = (AutoCompleteTextView) findViewById(R.id.etDniRegular);
                final String result = enviarDatosGet(etDniRegular.getText().toString());
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
            Intent intent = new Intent(SeleccionarRegular.this, PacienteRegular.class);
            dia = (EditText) findViewById(R.id.etDiaRegular);
            mes = (EditText) findViewById(R.id.etMesRegular);
            anno = (EditText) findViewById(R.id.etAnnoRegular);
            String fecha = anno.getText().toString() + "-" + mes.getText().toString() + "-" + dia.getText().toString();
            onCargarRol();
            if (rbt1Regular.isChecked()) {
                intent.putExtra("doctor", "08889556");
                intent.putExtra("ffecha", fecha);
                intent.putExtra("n_historia", numero_historia);
                startActivity(intent);
                finish();
            } else if (rbt2Regular.isChecked()) {
                intent.putExtra("doctor", "07267871");
                intent.putExtra("ffecha", fecha);
                intent.putExtra("n_historia", numero_historia);
                startActivity(intent);
                finish();
            } else if (rbt3Regular.isChecked()) {
                intent.putExtra("doctor", "73130621");
                intent.putExtra("ffecha", fecha);
                intent.putExtra("n_historia", numero_historia);
                startActivity(intent);
                finish();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCargarRegular(View view) {
        Log.i("depurar", "click");
        etDniRegular = (AutoCompleteTextView) findViewById(R.id.etDniRegular);
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviar_carga(etDniRegular.getText().toString());
                Log.i("depurar", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosCarga(result);
                        if (r > 0) {
                            MostrarCarga(result);
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String enviar_carga(String dnipaciente) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("https://vitaldentcix.com/d/cargar_paciente.php?dni_paciente=" + dnipaciente);
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public int obtDatosCarga(String response) {
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

    public void MostrarCarga(String response) {
        try {
            JSONArray json = new JSONArray(response);
            for (int i = 0; i < json.length(); i++) {
                tvApellidoPaternoRegular.setText(json.getJSONObject(i).getString("apellido_pat"));
                tvApellidoMaternoRegular.setText(json.getJSONObject(i).getString("apellido_mat"));
                tvNombresRegular.setText(json.getJSONObject(i).getString("nombres"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    String doctor = "";

    public void onCargarRol() {
        Log.i("depurar", "click");
        dia = (EditText) findViewById(R.id.etDiaRegular);
        mes = (EditText) findViewById(R.id.etMesRegular);
        anno = (EditText) findViewById(R.id.etAnnoRegular);
        final String fecha = anno.getText().toString() + "-" + mes.getText().toString() + "-" + dia.getText().toString();
        if (rbt1Regular.isChecked()) {
            doctor = "08889556";
            Thread tr = new Thread() {
                @Override
                public void run() {
                    final String resultado = enviarDatosRol(doctor, fecha);
                    Log.i("depurar", resultado);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int r = obtDatosRol(resultado);
                            if (r > 0) {
                                MostrarRol(resultado);
                            }
                        }
                    });
                }
            };
            tr.start();
        } else if (rbt2Regular.isChecked()) {
            doctor = "07267871";
            Thread tr = new Thread() {
                @Override
                public void run() {
                    final String resultado = enviarDatosRol(doctor, fecha);
                    Log.i("depurar", resultado);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int r = obtDatosRol(resultado);
                            if (r > 0) {
                                MostrarRol(resultado);
                            }
                        }
                    });
                }
            };
            tr.start();
        } else if (rbt3Regular.isChecked()) {
            doctor = "73130621";
            Thread tr = new Thread() {
                @Override
                public void run() {
                    final String resultado = enviarDatosRol(doctor, fecha);
                    Log.i("depurar", resultado);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int r = obtDatosRol(resultado);
                            if (r > 0) {
                                MostrarRol(resultado);
                            }
                        }
                    });
                }
            };
            tr.start();
        }
    }

    public String enviarDatosRol(String doctor, String fecha) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("https://vitaldentcix.com/d/cargar_rol.php?dni=" + doctor + "&fecha=" + fecha);
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public int obtDatosRol(String response) {
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

    public void MostrarRol(String response) {
        try {
            JSONArray json = new JSONArray(response);
            pr = new PacienteRegular();
            for (int i = 0; i < json.length(); i++) {
                String resultado = json.getJSONObject(i).getString("hora");
                Log.i("Roles", resultado);
                switch (resultado) {
                    case "09:00am":
                        pr.lbl01.setText("Ocupado");
                        pr.lbl01.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt01.setEnabled(false);
                        break;
                    case "09:40am":
                        pr.lbl02.setText("Ocupado");
                        pr.lbl02.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt02.setEnabled(false);
                        break;
                    case "10:20am":
                        pr.lbl03.setText("Ocupado");
                        pr.lbl03.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt03.setEnabled(false);
                        break;
                    case "11:00am":
                        pr.lbl04.setText("Ocupado");
                        pr.lbl04.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt04.setEnabled(false);
                        break;
                    case "11:40am":
                        pr.lbl05.setText("Ocupado");
                        pr.lbl05.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt05.setEnabled(false);
                        break;
                    case "12:20pm":
                        pr.lbl06.setText("Ocupado");
                        pr.lbl06.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt06.setEnabled(false);
                        break;
                    case "04:00pm":
                        pr.lbl07.setText("Ocupado");
                        pr.lbl07.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt07.setEnabled(false);
                        break;
                    case "04:40pm":
                        pr.lbl08.setText("Ocupado");
                        pr.lbl08.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt08.setEnabled(false);
                        break;
                    case "05:20pm":
                        pr.lbl09.setText("Ocupado");
                        pr.lbl09.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt09.setEnabled(false);
                        break;
                    case "06:00pm":
                        pr.lbl10.setText("Ocupado");
                        pr.lbl10.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt10.setEnabled(false);
                        break;
                    case "06:40pm":
                        pr.lbl11.setText("Ocupado");
                        pr.lbl11.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt11.setEnabled(false);
                        break;
                    case "07:20pm":
                        pr.lbl12.setText("Ocupado");
                        pr.lbl12.setTextColor(getResources().getColor(R.color.rojo));
                        pr.rbt12.setEnabled(false);
                        break;
                    default:
                        pr.lbl13.setText("Seleccione la hora de su cita");
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
