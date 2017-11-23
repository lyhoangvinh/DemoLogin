package lyhoangvinh.com.jocky_mvp.Model;

import com.orm.SugarRecord;

/**
 * Created by ADMIN on 10/18/2017.
 */

public class Driver extends SugarRecord {

    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private String firebaseId;
    private String role;
    private String token;

    public Driver() {
    }

    public Driver(String firstname, String lastname, String email, String picture, String firebaseId, String role, String token) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.picture = picture;
        this.firebaseId = firebaseId;
        this.role = role;
        this.token = token;
    }

    public String getFirstname() {
        return firstname;
    }

    public Driver setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Driver setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Driver setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Driver setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public Driver setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Driver setRole(String role) {
        this.role = role;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Driver setToken(String token) {
        this.token = token;
        return this;
    }
}