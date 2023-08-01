package com.example.tareafirebase;

public class Registros {

    private String uid;

    private String foro;
    private String nombres;
    private String apellidos;
    private String genero;
    private String fechanacimiento;

    public Registros(String uid) {
        this.uid = uid;
    }

    public Registros(String foro, String nombres, String apellidos, String genero, String fechanacimiento) {
        this.foro = foro;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fechanacimiento = fechanacimiento;
    }

    public Registros() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getForo() {
        return foro;
    }

    public void setForo(String foro) {
        this.foro = foro;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }



}
