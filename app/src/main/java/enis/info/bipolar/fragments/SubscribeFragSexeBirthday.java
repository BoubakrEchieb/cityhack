package enis.info.bipolar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import enis.info.bipolar.R;


/**
 * Created by be on 15/10/16.
 */

public class SubscribeFragSexeBirthday extends Fragment {

    //private RadioGroup radioGroup;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private EditText birthday;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_sexe_birthday,null);
        radioButtonFemale = (RadioButton) view.findViewById(R.id.radio_female);
        radioButtonMale = (RadioButton) view.findViewById(R.id.radio_male);
        birthday = (EditText) view.findViewById(R.id.birthday_edit_text);
        return view;
    }
    public boolean getTheSexe(){
        if(radioButtonFemale.isSelected()){
            return  true;
        }
        if(radioButtonMale.isSelected()){
            return false;
        }
        return false;
    }

    public String getBirthday() {
        return birthday.getText().toString();
    }
}
