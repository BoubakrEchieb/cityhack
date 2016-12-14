package enis.info.bipolar;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import enis.info.bipolar.adapters.CustomPagerAdapter;
import enis.info.bipolar.fragments.SubscribeFragEmailPassword;
import enis.info.bipolar.fragments.SubscribeFragFinish;
import enis.info.bipolar.fragments.SubscribeFragFirstLastName;
import enis.info.bipolar.fragments.SubscribeFragSexeBirthday;


public class SubscribeActivity extends AppCompatActivity implements View.OnClickListener {


    private SubscribeFragFirstLastName fragFirstLastName;
    private SubscribeFragEmailPassword fragEmailPassword;
    private SubscribeFragSexeBirthday fragSexeBirthday;
    private SubscribeFragFinish fragFinish;

    //private FrameLayout fragmentContainer;
    private ViewPager viewPager;
    private Button next;
    private View mainView;

    private DefaultHttpClient httpClient;

    private ArrayList<Fragment> items = new ArrayList<>();
    private CustomPagerAdapter customPagerAdapter;

    private List<NameValuePair> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        fragFirstLastName = new SubscribeFragFirstLastName();
        fragEmailPassword = new SubscribeFragEmailPassword();
        fragSexeBirthday = new SubscribeFragSexeBirthday();
        fragFinish = new SubscribeFragFinish();

        items.add(fragFirstLastName); // id in list 0
        items.add(fragEmailPassword); // id in list 1
        items.add(fragSexeBirthday); // id in list 2
        items.add(fragFinish); // id in list 3

        FragmentManager fragmentManager = getSupportFragmentManager();
        Context c = getApplicationContext();
        customPagerAdapter = new CustomPagerAdapter(c, items, fragmentManager);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        next = (Button) findViewById(R.id.next_button);
        next.setOnClickListener(this);
        mainView = findViewById(R.id.subscribe);

        viewPager.setAdapter(customPagerAdapter);

        httpClient = new DefaultHttpClient();
params =new ArrayList<>();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.next_button) {
            if (viewPager.getCurrentItem() == 0) {
                if ((fragFirstLastName.getFirstNameText().equals("") || fragFirstLastName.getLastNameText().equals(""))) {
                    Snackbar.make(mainView, "You must enter your last and first name .. ", Snackbar.LENGTH_LONG)

                            .setAction("Action", null).show();

                } else {
                    params.add(new BasicNameValuePair("firstname", fragFirstLastName.getFirstNameText()));
                    params.add(new BasicNameValuePair("lastname", fragFirstLastName.getLastNameText()));
                    viewPager.setCurrentItem(1);
                }

            } else if (viewPager.getCurrentItem() == 1) {
                if ((fragEmailPassword.getPasswordConfirmText().equals("") || fragEmailPassword.getPasswordText().equals("") || fragEmailPassword.getEmailText().equals(""))) {
                    Snackbar.make(mainView, "Fill all fields .. ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else {
                    if (!(fragEmailPassword.getPasswordConfirmText().equals(fragEmailPassword.getPasswordText()))) {
                        Snackbar.make(mainView, "Verify your password..  ", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        params.add(new BasicNameValuePair("email", fragEmailPassword.getEmailText()));
                        params.add(new BasicNameValuePair("password", fragEmailPassword.getPasswordText()));
                        viewPager.setCurrentItem(2);
                    }

                }
            } else if (viewPager.getCurrentItem() == 2) {
                if (fragSexeBirthday.getBirthday().equalsIgnoreCase("")) {
                    Snackbar.make(mainView, "Fill all fields .. ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    next.setText("Finish");
                } else {
                    params.add(new BasicNameValuePair("birthday", fragSexeBirthday.getBirthday()));
                    params.add(new BasicNameValuePair("sexe", fragSexeBirthday.getTheSexe() + ""));
                    viewPager.setCurrentItem(3);
                }
            } else if (viewPager.getCurrentItem() == 3) {
                params.add(new BasicNameValuePair("state", fragFinish.getState()));
                //new LoginAsyncTask().execute();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
