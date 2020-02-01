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
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PacienteNuevo extends AppCompatActivity {
    String fecha_consulta = "", dni_doctor = "", numero_historia = "", hora_consulta = "";
    RadioButton n0900am, n0940am, n1020am, n1100am, n1140am, n1220pm, n0400pm, n0440pm, n0520pm, n0600pm, n0640pm, n0720pm;
    TextView tvDoctor, tvFecha, tvHistoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_nuevo);
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

        n0900am = (RadioButton) findViewById(R.id.n0900am);
        n0940am = (RadioButton) findViewById(R.id.n0940am);
        n1020am = (RadioButton) findViewById(R.id.n1020am);
        n1100am = (RadioButton) findViewById(R.id.n1100am);
        n1140am = (RadioButton) findViewById(R.id.n1140am);
        n1220pm = (RadioButton) findViewById(R.id.n1220pm);
        n0400pm = (RadioButton) findViewById(R.id.n0400pm);
        n0440pm = (RadioButton) findViewById(R.id.n0440pm);
        n0520pm = (RadioButton) findViewById(R.id.n0520pm);
        n0600pm = (RadioButton) findViewById(R.id.n0600pm);
        n0640pm = (RadioButton) findViewById(R.id.n0640pm);
        n0720pm = (RadioButton) findViewById(R.id.n0720pm);
        tvDoctor = (TextView) findViewById(R.id.tvDoctorSeleccionado);
        tvHistoria = (TextView) findViewById(R.id.tvHistoriaClinica);
        tvFecha = (TextView) findViewById(R.id.tvFechaConsulta);
        String recup_historia = getIntent().getStringExtra("n_historia");
        String recup_fecha = getIntent().getStringExtra("ffecha");
        String recup_doctor = getIntent().getStringExtra("doctor");
        tvDoctor.setText(recup_doctor);
        tvHistoria.setText(recup_historia);
        tvFecha.setText(recup_fecha);
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

    public void onConsultaPacienteNuevo(View view) {
        if (n0900am.isChecked()) {
            hora_consulta = "09:00am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n0940am.isChecked()) {
            hora_consulta = "09:40am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n1020am.isChecked()) {
            hora_consulta = "10:20am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n1100am.isChecked()) {
            hora_consulta = "11:00am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n1140am.isChecked()) {
            hora_consulta = "11:40am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n1220pm.isChecked()) {
            hora_consulta = "12:20pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n0400pm.isChecked()) {
            hora_consulta = "04:00pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n0440pm.isChecked()) {
            hora_consulta = "04:40pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n0520pm.isChecked()) {
            hora_consulta = "05:20pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n0600pm.isChecked()) {
            hora_consulta = "06:00pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n0640pm.isChecked()) {
            hora_consulta = "06:40pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
        } else if (n0720pm.isChecked()) {
            hora_consulta = "07:20pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia = tvHistoria.getText().toString();
                    fecha_consulta = tvFecha.getText().toString();
                    dni_doctor = tvDoctor.getText().toString();
                    final String result = enviarDatosGet(numero_historia, fecha_consulta, dni_doctor, hora_consulta);
                    Log.i("depurar", result);
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
    }

    public String enviarDatosGet(String historia, String fecha, String doctor, String hora) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("https://vitaldentcix.com/d/registrar_consulta.php?numero_historia=" + historia +
                    "&fecha_consulta=" + fecha + "&dni_doctor=" + doctor + "&hora_consulta=" + hora);
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
        Toast.makeText(PacienteNuevo.this, "Consulta registrada con éxito", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PacienteNuevo.this, Inicio.class);
        startActivity(intent);
        finish();
    }

    /*public int obtDatosJson(String response) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.paciente_nuevo, menu);
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
}