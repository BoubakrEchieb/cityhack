package enis.info.bipolar.connection;

import android.os.AsyncTask;

/**
 * Created by be on 16/10/16.
 */

public class GetRequestAsyncTask extends AsyncTask<Void,Void,Void> {
    private GetRequest connection;

    private static String URL = "http://microdev.xyz/user/getRequest";
    @Override
    protected Void doInBackground(Void... voids) {
        connection = new GetRequest();
        connection.getRequestedList(URL);
        return null;
    }
}
