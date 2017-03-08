package com.demo.luisordoniez.humanapp.models;

import java.util.ArrayList;

/**
 * Created by luisordoniez on 8/03/17.
 */

public class Parametros {
    int tipo;
    String nombre_variable;
    ArrayList<String> valores;
    public Parametros(int tipo, String nombre_v, ArrayList<String> val){
        this.tipo = tipo;
        this.nombre_variable = nombre_v;
        this.valores= val ;
    }
    public String getNombre_variable() {
        return nombre_variable;
    }
    public void setNombre_variable(String nombre_variable) {
        this.nombre_variable = nombre_variable;
    }
    public ArrayList<String> getValores() {
        return valores;
    }
    public void setValores(ArrayList<String> valores) {
        this.valores = valores;
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
