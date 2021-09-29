package com.smartboard.controller;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.database.dao.ProfileDataAccessObject;
import com.smartboard.model.database.dao.UserDataAccessObject;
import com.smartboard.model.user.Profile;
import com.smartboard.model.user.User;

import java.sql.Connection;

/** @author Carl Karama
 *  @see Profile
 *  @see ProfileDataAccessObject
 *  @version 1.0
 *  */

public class SignupController {

    private UserDataAccessObject userObject;
    private ProfileDataAccessObject profileDataAccessObject;
    private final Connection singleton = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

    public void signup(Profile user) {
        profileDataAccessObject = new ProfileDataAccessObject(singleton);
        profileDataAccessObject.create(user);
    }
}
