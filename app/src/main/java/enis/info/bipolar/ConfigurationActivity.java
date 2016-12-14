package enis.info.bipolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import enis.info.bipolar.connection.LoginAsyncTask;
import enis.info.bipolar.service.BrainLink;
import enis.info.bipolar.service.StaticSend;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSend;
    private Button buttonAddAssistant;
    private Button buttonAcceptAssistant;
    private Button buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        getSupportActionBar().hide();
        buttonSend = (Button) findViewById(R.id.button_send);
        buttonAddAssistant = (Button) findViewById(R.id.button_add_assistant);
        buttonAcceptAssistant = (Button) findViewById(R.id.button_accept_assistant);
        buttonEdit = (Button) findViewById(R.id.button_Edit);

        buttonSend.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);
        buttonAcceptAssistant.setOnClickListener(this);
        buttonAddAssistant.setOnClickListener(this);
        Intent intentt = new Intent(this, BrainLink.class);
        startService(intentt);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.button_send){
            //send

            StaticSend.send();

        }
        if(view.getId() == R.id.button_Edit){
            //edit
            Intent intent = new Intent(this,EditActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button_add_assistant){
            //add
            Intent intent = new Intent(this,AddAssistantActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button_accept_assistant){
            //accept
            Intent intent = new Intent(this,AcceptAssistantActivity.class);
            startActivity(intent);
        }
    }
}
