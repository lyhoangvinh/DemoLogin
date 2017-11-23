package lyhoangvinh.com.jocky_mvp.Model;

/**
 * Created by ADMIN on 10/18/2017.
 */

public class Driver {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String picture;

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

    public String getPassword() {
        return password;
    }

    public Driver setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Driver setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
