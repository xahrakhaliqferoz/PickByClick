package com.abidingtech.pick_by_click;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NotifyUtil extends AsyncTask<String, Void, String> {


    public static void send(String topic, String title, String body){
        new NotifyUtil().execute(topic, title, body);
    }
    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            Log.e( "doInBackground: ", "Init");
            url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            JSONObject notify = new JSONObject();
            notify.put("to", "/topics/"+strings[0]);

            JSONObject data = new JSONObject();
            data.put("title", strings[1]);
            data.put("body", strings[2]);

            notify.put("data", data);

            String jsonInputString = notify.toString();
            Log.e("doInBackground: ",jsonInputString );
            conn.setRequestProperty ("Authorization", "key=AAAAhJoaac0:APA91bGIwYsLQ3zW5w4_e8UEUfZ9VIkYnPNyAAJi7OcxYlYMsE1HxEQMsPMcFpdpJm_Diz_wcUJil-fKsWgGyKc5ahcDUTmaX7DelRBtVbQ_a94r_HZ7PtByTgsprkk3ziQsNhVFybVj");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            conn.connect();
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            InputStream in = conn.getInputStream();
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            in.close();
            Log.e( "doInBackground: ", "Result-> "+result);


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
