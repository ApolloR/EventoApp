package br.com.apolorossi.eventoapp;

import java.io.Serializable;

/**
 * Created by Apolo on 04-10-2016.
 */
public class ItemVideo implements Serializable{
    /*O serializable permite que a classe seja passado entre as Activities */
    private String titulo;
    private String data;
    private String url;

    public ItemVideo(String titulo, String data, String url) {
        this.titulo = titulo;
        this.data = data;
        this.url = url;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
