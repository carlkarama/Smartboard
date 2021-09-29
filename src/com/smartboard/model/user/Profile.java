package com.smartboard.model.user;

import com.smartboard.model.database.dto.DataTransferObject;
import com.smartboard.model.security.Encryption;
import com.smartboard.model.security.Session;
import com.smartboard.model.workspace.EditProfileTaskable;

/**
 *  @author Carl Karama
 *  @see User
 *  @see Session
 *  @see Encryption
 *  @version 1.0 */

public class Profile implements EditProfileTaskable, DataTransferObject {

    //each user has a specific ID to reference them
    private int id;

    //each user has a distinct email
    private String email;

    //each user has a distinct username
    private String username;

    //each user has a secret password to login
    private String password;

    //user information
    private User user;

    //logged-in, logged-out, registered, unregistered
    private Session session;

    //user profile picture
    private String profilePicturePath;

    //encryption of user info
    private Encryption encryption;

    public Profile(User user, String username, String password, String profilePicturePath) {
        this.username = username;
        this.password = password;
        this.user = user;
        this.profilePicturePath = profilePicturePath;
    }

    public Profile(int id, String email, String username, String password, User user, Session session, String profilePicturePath, Encryption encryption) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.user = user;
        this.session = session;
        this.profilePicturePath = profilePicturePath;
        this.encryption = encryption;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public Encryption getAuthorization() {
        return encryption;
    }

    public void setAuthorization(Encryption encryption) {
        this.encryption = encryption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void editProfile() {

    }

    @Override
    public void logout() {

    }

    @Override
    public void getID() {

    }
}
