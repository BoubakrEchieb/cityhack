package enis.info.bipolar.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by be on 15/10/16.
 */
public class User {
    public int id;
    public static User user;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean sexe;
    private boolean state;
    private Date birthday;

    public User(JSONObject jsonObject) {
        try {
            setEmail(jsonObject.getString("email"));
            setPassword(jsonObject.getString("password"));
            setFirstName(jsonObject.getString("nom"));
            setLastName(jsonObject.getString("prenom"));
            setSexe(jsonObject.getBoolean("sexe"));
            setState(jsonObject.getBoolean("state"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
