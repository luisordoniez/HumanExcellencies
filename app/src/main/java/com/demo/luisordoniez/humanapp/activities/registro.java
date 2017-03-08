package com.demo.luisordoniez.humanapp.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.luisordoniez.humanapp.R;
import com.demo.luisordoniez.humanapp.conexion.AsyncTask;
import com.demo.luisordoniez.humanapp.models.Parametros;
import com.demo.luisordoniez.humanapp.utils.Constants;
import com.demo.luisordoniez.humanapp.utils.GlobalClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class registro extends AppCompatActivity implements AsyncTask.HttpI{

    private TextView nombre;
    private TextView edad;
    private TextView email;
    private TextView pass;
    private GlobalClass globalvariable;
    private AsyncTask asyncTask;
    private ArrayList<Parametros> parametros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            // Habilitar el Up Button
            actionBar.setDisplayHomeAsUpEnabled(true);
            // Cambiar icono del Up Button
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
            actionBar.setTitle(Constants.Nuevo);
        }

        globalvariable = (GlobalClass) this.getApplicationContext();

        nombre = (TextView) findViewById(R.id.nombre);
        edad = (TextView) findViewById(R.id.edad);
        email = (TextView) findViewById(R.id.email);
        pass = (TextView) findViewById(R.id.password);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void volver(View v){
        onBackPressed();
    }

    @Override
    public void setResult(JSONObject retornar) throws JSONException {
        if (!retornar.isNull("code"))
            if (retornar.getInt("code") != 200 )
                globalvariable.mostrarMensajeLargo("Ocurrio un problema intentalo nuevamente");
            else {
                globalvariable.mostrarMensajeLargo("Su registro se realizo correctamente");
                this.finish();
            }
    }

    public void registrar(View v){

        parametros = new ArrayList<Parametros>();
        ArrayList<String> val1 = new ArrayList<String>();
        ArrayList<String> val2 = new ArrayList<String>();
        ArrayList<String> val3 = new ArrayList<String>();
        ArrayList<String> val4 = new ArrayList<String>();
        ArrayList<String> val5 = new ArrayList<String>();

        val1.add(nombre.getText().toString());
        parametros.add(new Parametros(1, "nombre", val1));
        val2.add(email.getText().toString());
        parametros.add(new Parametros(1, "email", val2));
        val3.add(edad.getText().toString());
        parametros.add(new Parametros(1, "edad", val3));
        val4.add("kjnawlknljeahrkuackjbzakfusidfvkdhcksjsfvakjfakjdfvakjfvakh");
        parametros.add(new Parametros(1, "token", val4));
        val5.add(pass.getText().toString());
        parametros.add(new Parametros(1, "pass", val5));

        if (globalvariable.TestConection()) {

//            lanzarCirculo();
            asyncTask = new AsyncTask(this, "registrar", "http://service/registrar",parametros);
            asyncTask.execute("");


        } else
            globalvariable.mostrarMensajeCorto(Constants.SIN_CONEXION);
    }
}
