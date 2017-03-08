package com.demo.luisordoniez.humanapp.activities;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.luisordoniez.humanapp.R;
import com.demo.luisordoniez.humanapp.adapters.RecyclerViewAdapter;
import com.demo.luisordoniez.humanapp.conexion.AsyncTask;
import com.demo.luisordoniez.humanapp.models.Parametros;
import com.demo.luisordoniez.humanapp.models.Usuario;
import com.demo.luisordoniez.humanapp.utils.Constants;
import com.demo.luisordoniez.humanapp.utils.GlobalClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncTask.HttpI{

    /**
     * Atributos
     */

    private ProgressDialog circuloProgres;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rv;
    private ArrayList<String> list = new ArrayList<String>();
    private AsyncTask asyncTask;
    private ArrayList<Parametros> parametros_get_del;
    private GlobalClass globalvariable = new GlobalClass();
    private ArrayList<Usuario> usuario = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        globalvariable = (GlobalClass) this.getApplicationContext();

        if (globalvariable.TestConection()) {

//            lanzarCirculo();
            asyncTask = new AsyncTask(this, "getInfo", "http://service/getInfo");
            asyncTask.execute("");


        } else
            globalvariable.mostrarMensajeCorto(Constants.SIN_CONEXION);
    }

    @Override
    public void setResult(JSONObject retornar) throws JSONException {

        Type listType;
        JSONArray jsonTratamiento = new JSONArray();
        if (!retornar.isNull("result")) {
            jsonTratamiento = (JSONArray) retornar.get("result");
            listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            usuario = new Gson().fromJson(jsonTratamiento.toString(), listType);

            RecyclerViewAdapter adapter = new RecyclerViewAdapter(usuario);
            rv.setAdapter(adapter);
        }

    }
}
