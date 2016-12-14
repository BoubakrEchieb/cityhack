package enis.info.bipolar.service;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import enis.info.bipolar.connection.LoginAsyncTask;
import enis.info.bipolar.models.User;

/**
 * Created by be on 16/10/16.
 */

public class StaticSend {
    public static long T;
    private static String getPostDataString(String token) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        result.append(URLEncoder.encode("token", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(token, "UTF-8"));

        return result.toString();
    }


    public static  void send(){
        if(T+600<System.currentTimeMillis())
        {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    T=System.currentTimeMillis();
                    //
                    URL url;
                    String response = "";
                    try {
                        url = new URL("http://microdev.xyz/alerte/send");

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setReadTimeout(15000);
                        conn.setConnectTimeout(15000);
                        conn.setRequestMethod("POST");
                        conn.setDoInput(true);
                        conn.setDoOutput(true);


                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(os, "UTF-8"));
                        writer.write(getPostDataString(BrainLink.token));

                        writer.flush();
                        writer.close();
                        os.close();
                        int responseCode=conn.getResponseCode();

                        if (responseCode == HttpsURLConnection.HTTP_OK) {
                            String line;
                            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            while ((line=br.readLine()) != null) {
                                response+=line;
                            }
                        }
                        else {
                            response="";

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("responsessssss",response);


                }
            });
            t1.start();

        }


    }
}
