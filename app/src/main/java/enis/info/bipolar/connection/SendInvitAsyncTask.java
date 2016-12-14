package enis.info.bipolar.connection;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import enis.info.bipolar.AddAssistantActivity;
import enis.info.bipolar.ConfigurationActivity;
import enis.info.bipolar.LoginActivity;
import enis.info.bipolar.models.User;

/**
 * Created by be on 15/10/16.
 */
public class SendInvitAsyncTask extends AsyncTask<Void,Void,Void> {
    private SendInvit connection;
    private static String URL = "http://microdev.xyz/user/";

    private String email;
    private boolean error = false;
    private AddAssistantActivity c;
    public SendInvitAsyncTask(String email, AddAssistantActivity context) {
        this.email=email;
        c=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        connection = new SendInvit();
        boolean u = connection.loginMakehttpPost(email,URL);
        if(!u){

            error = true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(isError()){
            Toast.makeText(c, "Not sent..", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(c, "Si cet Utilisateur existe il va recevoir..", Toast.LENGTH_SHORT).show();
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
