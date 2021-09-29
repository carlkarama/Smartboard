package com.smartboard.model.user;

/**
 *  @author Carl Karama
 *  @version 1.0 */

public class Admin extends User {

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Admin(int userID, String firstName, String lastName) {
        super(userID, firstName, lastName);
    }

    @Override
    public void getID() {

    }
}
