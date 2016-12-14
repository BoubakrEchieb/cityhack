package enis.info.bipolar;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import org.apache.http.message.BasicNameValuePair;

import enis.info.bipolar.connection.AddAssistantAsyncTask;
import enis.info.bipolar.connection.LoginAsyncTask;
import enis.info.bipolar.connection.SendInvitAsyncTask;

public class AddAssistantActivity extends AppCompatActivity implements View.OnClickListener{

    private Button sendEmail;
    private AutoCompleteTextView emailTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assistant);

        emailTextView = (AutoCompleteTextView) findViewById(R.id.email_assistant_edit_text);
        sendEmail = (Button) findViewById(R.id.send_email_assistant);
        sendEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.send_email_assistant){
                //TODO

                String emailString = emailTextView.getText().toString();

                if(emailString.equals("")){
                    Snackbar.make(findViewById(R.id.email_login_form), "Fill all fields .. ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    //InstanceID instanceID = InstanceID.getInstance(this);
                    //String token = null;
                /*try {
                       token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                            GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                    Log.d("my token s",LoginAsyncTask.token);
                    Log.d("my email s",emailString);
                    SendInvitAsyncTask login = new SendInvitAsyncTask(emailString,this);
                    login.execute();

                }



        }
    }
}
