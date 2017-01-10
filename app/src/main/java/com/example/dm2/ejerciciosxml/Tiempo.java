package com.example.dm2.ejerciciosxml;

public class Tiempo{

    private String fechaT;
    private String ciudad;
    private String temperaturaMax;
    private String tempraturaMin;

    public String getFechaT() {
        return fechaT;
    }
    public String getCiudad() {
        return ciudad;
    }
    public String getTemperaturaMax() {
        return temperaturaMax;
    }
    public String getTempraturaMin() {
        return tempraturaMin;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public void setFechaT(String fechaT) {
        this.fechaT = fechaT;
    }
    public void setTemperaturaMax(String temperaturaMax) {
        this.temperaturaMax = temperaturaMax;
    }
    public void setTempraturaMin(String tempraturaMin) {
        this.tempraturaMin = tempraturaMin;
    }
}
