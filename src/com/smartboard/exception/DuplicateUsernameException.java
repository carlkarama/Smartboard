package com.smartboard.exception;

import java.sql.SQLIntegrityConstraintViolationException;

public class DuplicateUsernameException extends SQLIntegrityConstraintViolationException {

    public DuplicateUsernameException(String message) {
        super(message);
    }
}
