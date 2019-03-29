package com.daviddev.mise_en_oeuvre_recherche_balise;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class CourseSelectionActivity extends Activity {

    TextView textView2;
    HttpsURLConnection conn;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    JSONObject jsonObject;
    URL url;
    String result;
    String result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView2 = findViewById(R.id.textView2);

        new getCourses().execute();
    }

    private class getCourses extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings){

            try {
                url = new URL("https://gfw0648.phpnet.org/SALS_GEOCACHING/api.php");
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

                inputStreamReader = new InputStreamReader(conn.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                jsonObject = new JSONObject(bufferedReader.readLine());

                result = jsonObject.toString();

                String id = jsonObject.getString("id");
                String question = jsonObject.getString("question");
                String numAnswer = jsonObject.getString("reponse");
                String prop_1 = jsonObject.getString("prop_1");
                String prop_2 = jsonObject.getString("prop_2");
                String prop_3 = jsonObject.getString("prop_3");
                String indice_1 = jsonObject.getString("indice_1");
                String x = jsonObject.getString("coord_x");
                String y = jsonObject.getString("coord_y");

                result2 = "ID :  " + id + "\nQuestion :  " + question + "\nRéponse :  " + numAnswer + "\nP1 :  " + prop_1 + "\nP2 :  " + prop_2 + "\nP3 :  " + prop_3 + "\nP3 :  " + indice_1 + "\nP3 :  " + x + "\nP3 :  " + y;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);


          /*  Log.i("------------->", result);

            String id = jsonObject.getString("id");

            JSONArray jsonCourses = jsonObject.getJSONArray("parcours");
            ArrayList<String> courses = new ArrayList<String>();

            if (jsonCourses != null) {
                for (int i=0; i<jsonCourses.length(); i++){
                    courses.add(jsonCourses.get(i).toString());
                }
            }*/

            textView2.setText(result);

        }
    }
}
