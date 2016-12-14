package enis.info.bipolar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import enis.info.bipolar.adapters.AcceptAssistantListAdapter;
import enis.info.bipolar.connection.GetRequestListAsyncTask;
import enis.info.bipolar.models.User;


public class AcceptAssistantActivity extends AppCompatActivity {

    private ListView listView;
    private AcceptAssistantListAdapter customListAdapter;
    private ArrayList<User> requestUsers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_assistant);

        listView = (ListView) findViewById(R.id.list_view);

        new GetRequestListAsyncTask(this){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                requestUsers = getAlerts();
                customListAdapter = new AcceptAssistantListAdapter(requestUsers,getApplicationContext());
                listView.setAdapter(customListAdapter);
            }
        };

    }
}
