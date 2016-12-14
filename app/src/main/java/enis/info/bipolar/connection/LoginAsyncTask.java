package enis.info.bipolar.connection;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.NameValuePair;

import java.util.List;

import enis.info.bipolar.ConfigurationActivity;
import enis.info.bipolar.DoctorActivity;
import enis.info.bipolar.LoginActivity;
import enis.info.bipolar.models.User;

/**
 * Created by be on 15/10/16.
 */
public class LoginAsyncTask extends AsyncTask<Void,Void,Void> {
    private Connection connection;
    private ProgressBar progressBar;
    private static String URL = "http://microdev.xyz/user/login";

    public static String token = "-";
    private String password;
    private String email;
    private boolean error = false;
    private LoginActivity c;
    public LoginAsyncTask(ProgressBar progressBar, String email,String password,LoginActivity context) {
        this.progressBar = progressBar;this.password=password;
        this.email=email;
        c=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        connection = new Connection();
        User u = connection.loginMakehttpPost(email,password,URL);
        Log.i("zzzedtdrfhg","esxdfcjbh");
        User.user=u;
        if(u==null){
            error = true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressBar.setVisibility(View.INVISIBLE);
        if(isError()){
            Toast.makeText(c, "Email/Passord incorrect..", Toast.LENGTH_SHORT).show();
        }else{
            User u = User.user;
            if(u.getState())
            {
                Intent intent = new Intent(c, DoctorActivity.class);
                c.startActivity(intent);
                c.finish();
            }
            else
            {
                Intent intent = new Intent(c, ConfigurationActivity.class);
                c.startActivity(intent);
                c.finish();
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    public boolean isError() {
        return error;
    }
}
