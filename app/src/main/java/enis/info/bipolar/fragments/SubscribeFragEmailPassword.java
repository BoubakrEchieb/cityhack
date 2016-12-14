package enis.info.bipolar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import enis.info.bipolar.R;


/**
 * Created by be on 15/10/16.
 */
public class SubscribeFragEmailPassword extends Fragment {
    private AutoCompleteTextView email;
    private EditText password;
    private EditText confirmPassword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_email_password,null);
        email = (AutoCompleteTextView) view.findViewById(R.id.email_edit_text);
        password = (EditText) view.findViewById(R.id.password_edit_text);
        confirmPassword = (EditText) view.findViewById(R.id.confirm_password_edit_text);
        return view;
    }
    public String getEmailText(){
        return email.getText().toString();
    }
    public String getPasswordText(){
        return password.getText().toString();
    }
    public String getPasswordConfirmText(){
        return confirmPassword.getText().toString();
    }
}
