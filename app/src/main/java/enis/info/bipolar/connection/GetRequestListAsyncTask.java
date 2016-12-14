package enis.info.bipolar.connection;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import enis.info.bipolar.models.Alert;
import enis.info.bipolar.models.User;

/**
 * Created by be on 15/10/16.
 */
public class GetRequestListAsyncTask extends AsyncTask<Void,Void,Void> {
    private ConnectionGetRequestsList connection;

    private static String URL = "http://microdev.xyz/alerte/viewnew";


    private ArrayList<User> users;
    private boolean error = false;
    private Context c;
    public GetRequestListAsyncTask(Context context) {
        c=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        connection = new ConnectionGetRequestsList();
        ArrayList<User> users= connection.getRequestList(URL);
        this.users = users;
        if(users==null){
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

    public ArrayList<User> getAlerts() {
        return users
                ;
    }
}
