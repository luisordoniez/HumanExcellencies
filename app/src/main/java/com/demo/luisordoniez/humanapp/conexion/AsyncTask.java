package com.demo.luisordoniez.humanapp.conexion;

import android.util.Log;

import com.demo.luisordoniez.humanapp.models.Parametros;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;


/**
 * Created by luisordoniez on 08/3/17.
 */


public class AsyncTask extends android.os.AsyncTask<String, Integer, JSONObject> {

    HttpI httpI;
    private String namespace;
    private String metodo;
    private String url;
    private String accionSoap;
    private ArrayList<Parametros> parametros;
    private String respuesta = "";
    private JSONObject retornar;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    ArrayList<HeaderProperty> headerPropertyArrayList = new ArrayList<HeaderProperty>();
    SoapObject request;


    public interface HttpI {
        public void setResult(JSONObject retornar) throws JSONException;

    }

    public AsyncTask(HttpI httpI, String metodo, String accionSoap, ArrayList<Parametros> parametros) {
        this.httpI = httpI;
        this.metodo = metodo;
        this.namespace = "http://service/";
        this.accionSoap = accionSoap;
        this.parametros = parametros;

        this.url = "http://192.168.0.15:8084/humanService/WSServer?wsdl";
        headerPropertyArrayList.add(new HeaderProperty("Connection", "close"));
    }

    public AsyncTask(HttpI httpI, String metodo, String accionSoap) {
        this.httpI = httpI;
        this.metodo = metodo;
        this.namespace = "http://service/";
        this.accionSoap = accionSoap;
        this.parametros = new ArrayList<Parametros>();

        this.url = "http://192.168.0.15:8084/humanService/WSServer?wsdl";
        headerPropertyArrayList.add(new HeaderProperty("Connection", "close"));
    }

    @Override
    protected JSONObject doInBackground(String... arg0) {

        try {
            retornar = obtener_respuesta();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
    }


    protected void onPostExecute(JSONObject result) {
        try {
            httpI.setResult(retornar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject obtener_respuesta() throws JSONException {
        SoapObject resultado = null;
        try {
            SoapObject request = new SoapObject(namespace, metodo);

            if (this.parametros.size()!=0)
                for (Parametros parametro : this.parametros) {
                    switch (parametro.getTipo()) {
                        case 1: //parametros string
                            for (String valor : parametro.getValores()) {
                                request.addProperty(parametro.getNombre_variable(), valor);
                                //System.out.println("nombreVariable: "+parametro.getNombre_variable()+ " valor"+ valor);
                            }
                            break;
                        case 2: //parametros int
                            for (String valor : parametro.getValores()) {
                                request.addProperty(parametro.getNombre_variable(), Integer.parseInt(valor));
                                //System.out.println("nombreVariable: "+parametro.getNombre_variable()+ " valor"+ valor);
                            }
                            break;

                    }
                }


            SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            //sobre.XSD dotNet = true;
            sobre.setOutputSoapObject(request);

            // Modelo el transporte

            HttpTransportSE transporte = new HttpTransportSE(url);
            transporte.debug = true;

            // Llamada
            transporte.call(accionSoap, sobre, headerPropertyArrayList);
            System.out.println(transporte.requestDump);


            // Resultado
            // String resultado = (String) sobre.bodyIn;
            resultado = (SoapObject) sobre.bodyIn;
            Object property =  resultado.getProperty(0);
            retornar = new JSONObject(property.toString());
            retornar.put("service", this.metodo);

            Log.i("Resultado", resultado.toString());
            //  Log.i("1 Curso", resultado.getProperty(0).toString());
            // MostrarMensaje ("REsultado: "+ resultado.getAttributeAsString(0) );

            if (resultado.toString() != null) {
                //MostrarMensaje("Conexion Exitosa!");
                System.out.println("Conexión Exitosa");
            }
            return (retornar);
        } catch (Exception e) {
            retornar = new JSONObject();
            retornar.put("code","404");

            return retornar;
        }

    }
}
