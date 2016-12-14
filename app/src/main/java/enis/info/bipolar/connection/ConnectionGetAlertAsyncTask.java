package enis.info.bipolar.connection;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import enis.info.bipolar.ConfigurationActivity;
import enis.info.bipolar.LoginActivity;
import enis.info.bipolar.SplashActivity;
import enis.info.bipolar.models.Alert;
import enis.info.bipolar.models.User;

/**
 * Created by be on 15/10/16.
 */
public class ConnectionGetAlertAsyncTask extends AsyncTask<Void,Void,Void> {
    private ConnectionGetAlert connection;

    private static String URL = "http://microdev.xyz/alerte/viewnew";


    private ArrayList<Alert> alerts;
    private boolean error = false;
    private Context c;
    public ConnectionGetAlertAsyncTask( Context context) {
        c=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        connection = new ConnectionGetAlert();
        ArrayList<Alert> alerts = connection.loginMakehttpPost(URL);
        this.alerts = alerts;
        if(alerts==null){
            error = true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public boolean isError() {
        return error;
    }

    public ArrayList<Alert> getAlerts() {
        return alerts;
    }
}
