package enis.info.bipolar.models;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by be on 15/10/16.
 */
public class Assistant extends User {
    private  ArrayList<User> patients;

    public Assistant(JSONObject jsonObject) {
        super(jsonObject);
    }

}
