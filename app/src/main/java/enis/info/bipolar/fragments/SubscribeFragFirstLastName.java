package enis.info.bipolar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import enis.info.bipolar.R;


/**
 * Created by be on 15/10/16.
 */
public class SubscribeFragFirstLastName extends Fragment {

    private AutoCompleteTextView firstName;
    private AutoCompleteTextView lastName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_first_last_name,container,false);
        firstName = (AutoCompleteTextView) view.findViewById(R.id.first_name_edit_text);
        lastName = (AutoCompleteTextView) view.findViewById(R.id.last_name_edit_text);
        return view;
    }
    public String getFirstNameText(){
        return firstName.getText().toString();
    }
    public String getLastNameText(){
        return lastName.getText().toString();
    }
}
