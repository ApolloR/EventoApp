package br.com.apolorossi.eventoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemVideoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final String[] arrayAulas = getResources().getStringArray(R.array.array_aulas);

        final List<ItemVideo> itemVideos = new ArrayList<>();
        /*
        itemVideos.add(new ItemVideo("Principais erros","26/09/2016","http://"));
        itemVideos.add(new ItemVideo("Video aula pratica 1","28/09/2016","http://"));
        itemVideos.add(new ItemVideo("Video aula pratica 2","29/09/2016","http://"));
        itemVideos.add(new ItemVideo("Video aula pratica 3","30/09/2016","http://"));*/



        ListView lista = (ListView) findViewById(R.id.lista);
        adapter = new ItemVideoAdapter(getBaseContext(), itemVideos);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getBaseContext(),DetalheActivity.class);
                Bundle bundle = new Bundle();
                ItemVideo aula = itemVideos.get(position);
                intent.putExtra("AULA",aula);
                startActivity(intent);
            }
        });

        MinhaTask minhaTask = new MinhaTask();
        minhaTask.execute();
    }

    public class MinhaTask extends AsyncTask<Void,Void,List<ItemVideo>>{

        List<ItemVideo> list;
        @Override
        protected List<ItemVideo> doInBackground(Void... voids) {
            try {

                list = new ArrayList<>();
                URL url = new URL("http://private-d88f6f-semanadevandroid.apiary-mock.com/listar");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String linha = "";
                StringBuilder builder = new StringBuilder();

                while ((linha = bufferedReader.readLine())!= null){
                    builder.append(linha + "\n");
                }

                list = JsonUtil.fromJson(builder.toString());





            }catch (Exception e){
                e.printStackTrace();
            }
            return  list;
        }

        @Override
        protected void onPostExecute(List<ItemVideo> itemVideos) {
            adapter.clear();
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }
}
