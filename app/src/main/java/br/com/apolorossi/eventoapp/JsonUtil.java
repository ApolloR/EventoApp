package br.com.apolorossi.eventoapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apolo on 05-10-2016.
 */

public class JsonUtil {

    public static List<ItemVideo> fromJson (String json) throws JSONException {
        List<ItemVideo> lista = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);

        for (int i =0 ;i < jsonArray.length(); i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String titulo = (String) jsonObject.get("titulo");
            String data = (String) jsonObject.get("data");
            String url = (String) jsonObject.get("url");

            lista.add(new ItemVideo(titulo,data,url));
        }

        return  lista;
    }
}
