package enis.info.bipolar.connection;

import android.content.Intent;
import android.os.AsyncTask;

import enis.info.bipolar.ConfigurationActivity;
import enis.info.bipolar.LoginActivity;
import enis.info.bipolar.SplashActivity;
import enis.info.bipolar.models.User;

/**
 * Created by be on 15/10/16.
 */
public class AddAssistantAsyncTask extends AsyncTask<Void,Void,Void> {
    private Connection connection;

    private static String URL = "http://microdev.xyz/user/login";

    public static String email = "-";
    private boolean error = false;
    private SplashActivity c;
    public AddAssistantAsyncTask(String email,SplashActivity context) {
        this.email=email;
        c=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        connection = new Connection();
        User u = connection.loginMakehttpPost("","",URL);
        User.user=u;
        if(u==null){
            error = true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(isError()){
            Intent intent = new Intent(c, LoginActivity.class);
            c.startActivity(intent);
            c.finish();
        }else{
            Intent intent = new Intent(c, ConfigurationActivity.class);
            c.startActivity(intent);
            c.finish();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public boolean isError() {
        return error;
    }
}
