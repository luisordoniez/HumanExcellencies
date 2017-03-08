package com.demo.luisordoniez.humanapp.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;


/**
 * Created by luisordoniez on 8/03/17.
 * <p/>
 * Clase que permite ser instanciada en cualquier activity con el fin hacer
 * utilidad de sus metodos que son generales y de frecuente uso.
 */

public class GlobalClass extends Application {

    /**
     * Constructor por defecto
     */

    public GlobalClass() {
        super();
    }

    /**
     * Metrodo que indica si el dispositivo cuneta con internet WIFI o no
     *
     * @return 'false'  no hay internet WIFI
     */

    public Boolean conectWifi() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //NetworkInfo[] redes = connectivity.getAllNetworkInfo();
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metrodo que indica si el dispositivo cuneta con internet MOBILE o no
     *
     * @return 'false'  no hay internet MOBILE
     */

    public Boolean conectRedMovil() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Metrodo que indica si el dispositivo cuneta con internet MOBILE o WIFI
     *
     * @return 'false'  no hay internet MOBILE o WIFI
     */

    public Boolean TestConection() {

        return conectWifi() || conectRedMovil();

    }

    public void mostrarMensajeLargo (CharSequence mostrar)
    {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        mostrar, Toast.LENGTH_LONG);
        toast1.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);

        toast1.show();
    }
    public void mostrarMensajeCorto (String mostrar)
    {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        mostrar, Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);

        toast1.show();
    }

}
