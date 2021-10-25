package com.smartboard.model.security;

/**
 *  @author Carl Karama
 *  @see com.smartboard.model.database.dao.ProfileDataAccessObject
 *  @implSpec {@code Session} handles user states in the program
 *  @version 1.0 */

public enum Session {

    LOGGED_IN,
    LOGGED_OUT,
    REGISTERED,
    UNREGISTERED
}
