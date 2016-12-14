package enis.info.bipolar.connection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import enis.info.bipolar.LoginActivity;
import enis.info.bipolar.models.User;


/**
 * Created by be on 15/10/16.
 */
public class Connection{
    private static InputStream inputStream;
    private static JSONObject jsonObject;
    private static String json="";
    //constructor
    public Connection(){

    }
    private String getPostDataString(String email,String password , String token) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        result.append(URLEncoder.encode("email", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(email, "UTF-8"));
        result.append("&");
        result.append(URLEncoder.encode("password", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(password, "UTF-8"));
        result.append("&");
        result.append(URLEncoder.encode("token", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(token, "UTF-8"));

        return result.toString();
    }


    public  User loginMakehttpPost(String email,String password, String urll){
        User user = null;
        URL url;
        String response = "";
        try {
            url = new URL(urll);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            System.out.println(getPostDataString(email,password,LoginAsyncTask.token));
            writer.write(getPostDataString(email,password,LoginAsyncTask.token));

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
        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if(jsonObject.getInt("error") == 0){
                user = new User(jsonObject.getJSONObject("user"));
            }
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            HttpParams p = new BasicHttpParams();
            p.setParameter("email",email);
            p.setParameter("password",password);
            p.setParameter("token", LoginAsyncTask.token);
            Log.i("trtrtrtr",p.getParameter("token").toString());
            post.setParams(p);
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            inputStream=entity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line=reader.readLine())!=null){
                sb.append(line + "\n");
            }
            inputStream.close();
            reader.close();
            json = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("ddddddddd",json);
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if(jsonObject.getInt("error") == 0){
                user = new User(jsonObject.getJSONObject("user"));
            }
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        return user;
    }



    public boolean inscrireMakeHttpPost(List<NameValuePair> params, String url) {
        try {
            params.add(new BasicNameValuePair("token",LoginAsyncTask.token));
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            inputStream=entity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line=reader.readLine())!=null){
                sb.append(line + "\n");
            }
            inputStream.close();
            reader.close();
            json = sb.toString();
            System.out.println(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        //TODO

        return false;
    }
}
