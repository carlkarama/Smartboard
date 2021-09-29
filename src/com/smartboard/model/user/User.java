package com.smartboard.model.user;

import com.smartboard.model.database.dto.DataTransferObject;

/**
 *  @author Carl Karama
 *  @implNote {@code User} models an abstraction of a User.
 *  @see Admin
 *  @see BasicUser
 *  @version 1.0 */

public abstract class User implements DataTransferObject {

    protected int userID;

    protected String firstName;

    protected String lastName;

    public User(int userID) {
        this.userID = userID;
        setUserID(userID);
    }

    public User(int userID, String firstName, String lastName) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        setUserID(userID);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        setFirstName(firstName);
        setLastName(lastName);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
}
