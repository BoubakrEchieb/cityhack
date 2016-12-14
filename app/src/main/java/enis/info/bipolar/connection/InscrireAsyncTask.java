package enis.info.bipolar.connection;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by be on 16/10/16.
 */

public class InscrireAsyncTask extends AsyncTask<Void,Void,Void> {

    private Connection connection;
    private ProgressBar progressBar;
    private static String URL = "http://microdev.xyz/user/signin"; //lien d'indcription
    private List<NameValuePair> params;
    private boolean error = false;

    public InscrireAsyncTask(ProgressBar progressBar, List<NameValuePair> params) {
        this.params = params;
        this.progressBar = progressBar;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        connection.inscrireMakeHttpPost(params,URL);
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
}
