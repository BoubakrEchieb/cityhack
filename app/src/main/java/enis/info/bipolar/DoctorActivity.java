package enis.info.bipolar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import enis.info.bipolar.adapters.CustomListAdapter;
import enis.info.bipolar.connection.ConnectionGetAlertAsyncTask;
import enis.info.bipolar.models.Alert;
import enis.info.bipolar.models.User;

public class DoctorActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private ArrayList<Alert> patients;
    private CustomListAdapter adapter;

    private Button buttonAddAssistant;
    private Button buttonAcceptAssistant;
    private Button buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        listView = (ListView) findViewById(R.id.list_view_doctor);
        new ConnectionGetAlertAsyncTask(this){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                patients = getAlerts();
                adapter = new CustomListAdapter(patients,getApplicationContext());
                listView.setAdapter(adapter);
            }
        }.execute();

        buttonAddAssistant = (Button) findViewById(R.id.button_add_patient);
        buttonAcceptAssistant = (Button) findViewById(R.id.button_accept_patient);
        buttonEdit = (Button) findViewById(R.id.button_logout_doctor);

        buttonEdit.setOnClickListener(this);
        buttonAcceptAssistant.setOnClickListener(this);
        buttonAddAssistant.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.button_logout_doctor){
            //edit
            Intent intent = new Intent(this,EditActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button_add_patient){
            //add
            Intent intent = new Intent(this,AddAssistantActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button_accept_patient){
            //accept
            Intent intent = new Intent(this,AcceptAssistantActivity.class);
            startActivity(intent);
        }
    }
}
