package enis.info.bipolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.phenotype.Configuration;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import enis.info.bipolar.connection.LoginAsyncTask;
import enis.info.bipolar.service.RegistrationIntentService;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private AutoCompleteTextView email;
    private EditText password;
    private Button connect;
    private Button register;
    private List<NameValuePair> params;
    private ProgressBar progressBar;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    private View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        mainView = findViewById(R.id.email_login_form);
        email = (AutoCompleteTextView) findViewById(R.id.email_edit_text);
        password = (EditText) findViewById(R.id.password_edit_text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        connect = (Button) findViewById(R.id.log_in_button);
        register = (Button) findViewById(R.id.register_button);

        params = new ArrayList<>();

        connect.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.log_in_button){
            //TODO

            String emailString = email.getText().toString();
            String passwordString = password.getText().toString();

            if(emailString.equals("") || passwordString.equals("")){
                Snackbar.make(mainView, "Fill all fields .. ", Snackbar.LENGTH_LONG)
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
                params.add(new BasicNameValuePair("email",emailString));
                params.add(new BasicNameValuePair("password",passwordString));
                params.add(new BasicNameValuePair("token",LoginAsyncTask.token));
                Log.d("my token s",LoginAsyncTask.token);
                Log.d("my email s",emailString);
                Log.d("my password s",passwordString);
                LoginAsyncTask login = new LoginAsyncTask(progressBar,emailString,passwordString,this);
                login.execute();

            }


        }
        if(view.getId() == R.id.register_button){
            //TODO
            //ouvrire interface d'inscription
            Intent intent = new Intent(this,SubscribeActivity.class);
            startActivity(intent);
            this.finish();
        }

    }
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
