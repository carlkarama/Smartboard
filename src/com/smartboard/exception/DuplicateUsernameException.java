package com.smartboard.exception;

import java.sql.SQLIntegrityConstraintViolationException;

/**@author Carl Karama
 * @implNote If a username is attempted to be registered into the system & it already exists fire this exception
 * @version 1.0 */
public class DuplicateUsernameException extends SQLIntegrityConstraintViolationException {

    public DuplicateUsernameException(String message) {
        super(message);
    }
}
