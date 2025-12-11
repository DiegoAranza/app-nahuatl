package com.example.tabs.ui;

public class Pregunta {
    private String tipo;
    private String pregunta;
    private String respuesta;
    private String imagenUrl;

    public Pregunta() {} // Constructor vacío requerido por Firestore

    public Pregunta(String tipo, String pregunta, String respuesta, String imagenUrl) {
        this.tipo = tipo;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.imagenUrl = imagenUrl;
    }

    public String getTipo() { return tipo; }
    public String getPregunta() { return pregunta; }
    public String getRespuesta() { return respuesta; }
    public String getImagenUrl() { return imagenUrl; }
}
