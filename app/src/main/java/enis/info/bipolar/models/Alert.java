package enis.info.bipolar.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by be on 15/10/16.
 */
public class Alert {
    public int id;
    public User user;
    private String date;

    public Alert(JSONObject jsonObject) {
        try {
            setDate(jsonObject.getString("date"));
            setUser(new User(jsonObject.getJSONObject("patient")));
            setId(jsonObject.getInt("_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Alert(String email, String firstName, String lastName) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
