package mena.gov.bf.alerteNotification.entity;

import java.io.Serializable;

public class Account implements Serializable {

    private Long id;
    private String displayName;
    private String firstName;
    private String imageURL;
    private String name;
    private String rpDisplayName;
    private String email;
    private Boolean activated;
    private String lastName;
    private String login;
    private String password;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRpDisplayName() {
        return rpDisplayName;
    }

    public void setRpDisplayName(String rpDisplayName) {
        this.rpDisplayName = rpDisplayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", displayName='" + displayName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", imageURL='" + imageURL + '\'' +
            ", name='" + name + '\'' +
            ", rpDisplayName='" + rpDisplayName + '\'' +
            ", email='" + email + '\'' +
            ", activated=" + activated +
            ", lastName='" + lastName + '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
