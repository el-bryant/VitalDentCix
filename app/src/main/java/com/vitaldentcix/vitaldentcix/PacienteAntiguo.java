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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PacienteAntiguo extends AppCompatActivity {
    String fecha_consulta_antiguo = "", dni_doctor_antiguo = "", numero_historia_antiguo = "", hora_consulta_antiguo = "";
    public static RadioButton rbt01, rbt02, rbt03, rbt04, rbt05, rbt06, rbt07, rbt08, rbt09, rbt10, rbt11, rbt12;
    TextView tvDoctor_antiguo, tvFecha_antiguo, tvHistoria_antiguo;
    public static TextView lbl01, lbl02, lbl03, lbl04, lbl05, lbl06, lbl07, lbl08, lbl09, lbl10, lbl11, lbl12, lbl13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_antiguo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lbl01 = (TextView) findViewById(R.id.tva0900am);
        lbl02 = (TextView) findViewById(R.id.tva0940am);
        lbl03 = (TextView) findViewById(R.id.tva1020am);
        lbl04 = (TextView) findViewById(R.id.tva1100am);
        lbl05 = (TextView) findViewById(R.id.tva1140am);
        lbl06 = (TextView) findViewById(R.id.tva1220pm);
        lbl07 = (TextView) findViewById(R.id.tva0400pm);
        lbl08 = (TextView) findViewById(R.id.tva0440pm);
        lbl09 = (TextView) findViewById(R.id.tva0520pm);
        lbl10 = (TextView) findViewById(R.id.tva0600pm);
        lbl11 = (TextView) findViewById(R.id.tva0640pm);
        lbl12 = (TextView) findViewById(R.id.tva0720pm);
        lbl13 = (TextView) findViewById(R.id.lbl13a);


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

        rbt01 = (RadioButton) findViewById(R.id.a0900am);
        rbt02 = (RadioButton) findViewById(R.id.a0940am);
        rbt03 = (RadioButton) findViewById(R.id.a1020am);
        rbt04 = (RadioButton) findViewById(R.id.a1100am);
        rbt05 = (RadioButton) findViewById(R.id.a1140am);
        rbt06 = (RadioButton) findViewById(R.id.a1220pm);
        rbt07 = (RadioButton) findViewById(R.id.a0400pm);
        rbt08 = (RadioButton) findViewById(R.id.a0440pm);
        rbt09 = (RadioButton) findViewById(R.id.a0520pm);
        rbt10 = (RadioButton) findViewById(R.id.a0600pm);
        rbt11 = (RadioButton) findViewById(R.id.a0640pm);
        rbt12 = (RadioButton) findViewById(R.id.a0720pm);
        tvDoctor_antiguo = (TextView) findViewById(R.id.tvDoctorAntiguo);
        tvHistoria_antiguo = (TextView) findViewById(R.id.tvHistoriaAntiguo);
        tvFecha_antiguo = (TextView) findViewById(R.id.tvFechaAntiguo);
        String recup_historia_antiguo = getIntent().getStringExtra("n_historia");
        String recup_fecha_antiguo = getIntent().getStringExtra("ffecha");
        String recup_doctor_antiguo = getIntent().getStringExtra("doctor");
        tvDoctor_antiguo.setText(recup_doctor_antiguo);
        tvHistoria_antiguo.setText(recup_historia_antiguo);
        tvFecha_antiguo.setText(recup_fecha_antiguo);
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

    public void onConsultaPacienteAntiguo(View view) {
        if (rbt01.isChecked()) {
            hora_consulta_antiguo = "09:00am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt02.isChecked()) {
            hora_consulta_antiguo = "09:40am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt03.isChecked()) {
            hora_consulta_antiguo = "10:20am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt04.isChecked()) {
            hora_consulta_antiguo = "11:00am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt05.isChecked()) {
            hora_consulta_antiguo = "11:40am";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt06.isChecked()) {
            hora_consulta_antiguo = "12:20pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt07.isChecked()) {
            hora_consulta_antiguo = "04:00pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt08.isChecked()) {
            hora_consulta_antiguo = "04:40pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt09.isChecked()) {
            hora_consulta_antiguo = "05:20pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt10.isChecked()) {
            hora_consulta_antiguo = "06:00pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt11.isChecked()) {
            hora_consulta_antiguo = "06:40pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        } else if (rbt12.isChecked()) {
            hora_consulta_antiguo = "07:20pm";
            Log.i("depurar", "click");
            Thread tr = new Thread() {
                @Override
                public void run() {
                    numero_historia_antiguo = tvHistoria_antiguo.getText().toString();
                    fecha_consulta_antiguo = tvFecha_antiguo.getText().toString();
                    dni_doctor_antiguo = tvDoctor_antiguo.getText().toString();
                    final String result = enviarDatosGet(numero_historia_antiguo, fecha_consulta_antiguo, dni_doctor_antiguo,
                            hora_consulta_antiguo);
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
        Toast.makeText(PacienteAntiguo.this, "Consulta registrada con éxito", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PacienteAntiguo.this, Inicio.class);
        startActivity(intent);
        finish();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.paciente_regular, menu);
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
