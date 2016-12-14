package enis.info.bipolar.models;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by be on 15/10/16.
 */
public class Patient extends User {
    private ArrayList<User> patientsList;

    public Patient(JSONObject jsonObject) {
        super(jsonObject);
    }
}
