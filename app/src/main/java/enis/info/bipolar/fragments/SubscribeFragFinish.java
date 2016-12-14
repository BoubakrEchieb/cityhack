package enis.info.bipolar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import enis.info.bipolar.R;


/**
 * Created by be on 15/10/16.
 */
public class SubscribeFragFinish extends Fragment {
    private RadioButton radioPatient;
    private RadioButton radioAssistant;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_subscribe_finish,null);
        radioAssistant = (RadioButton) view.findViewById(R.id.radio_assistant);
        radioPatient = (RadioButton) view.findViewById(R.id.radio_patient);
        return view;
    }
    public String getState(){
        String state = "patient";
        if(radioAssistant.isSelected()){
            state = "assistant";
        }
        return state;
    }
}
