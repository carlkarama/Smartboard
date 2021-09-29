package com.smartboard.model.user;

/**
 *  @author Carl Karama
 *  @version 1.0 */

public class BasicUser extends User {

    public BasicUser(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public BasicUser(int userID, String firstName, String lastName) {
        super(userID, firstName, lastName);
    }

    @Override
    public void getID() {

    }
}
