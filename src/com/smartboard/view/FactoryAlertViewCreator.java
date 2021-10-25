package com.smartboard.view;

import javafx.scene.control.Alert;

/** @author Carl Karama
 *  @see com.smartboard.model.database.dao.ProfileDataAccessObject
 *  @see com.smartboard.controller.SignupController
 *  @see com.smartboard.controller.WorkspaceController
 *  @implSpec {@code FactoryAlertViewCreator} implements the factory method pattern to get specific alert messages and
 *  reduce code duplication created by initialising {@code Alert} all over the codebase
 *  @version 1.0 */

public final class FactoryAlertViewCreator {

    private FactoryAlertViewCreator(String alert) {
        getAlert(alert);
    }

    /**
     * @implSpec Make sure to insert error abbreviation as string listed below. {@code getAlert} shows the alert type in relation to the alert entered into the parameter.
     * @apiNote  {@code "error:uae"} is an abbreviation for Error: Username Already Exists <br>
     *           {@code "error:uopis"} is an abbreviation for Error: Username or Password Is Wrong <br>
     *           {@code "error:sqluive"} is an abbreviation for Error: SQLUsernameIntegrityViolationException <br>
     *           {@code "error:fpd"} is an abbreviation for Error: Failed Project Deletion <br>
     *           {@code "error:fuc"} is  an abbreviation for Error: Failed User Creation <br>
     *           {@code "error:uinf"} is an abbreviation for Error: User ID Not Found <br/>
     * @return   {@code alert} if alert type exists and null if it doesn't <br>
     * */

    public static Alert getAlert(String alert) {

        //uae -> username already exists
        if (alert.toLowerCase().contains("error:uae")) {
            Alert usernameAlreadyExists = new Alert(Alert.AlertType.ERROR);
            usernameAlreadyExists.setContentText("Username already exists");
            usernameAlreadyExists.show();
            return usernameAlreadyExists;
        }

        //uopis -> username or password is wrong
        if (alert.toLowerCase().contains("error:uopis")) {
            Alert usernameOrPasswordIsWrong = new Alert(Alert.AlertType.ERROR);
            usernameOrPasswordIsWrong.setContentText("Username or Password is wrong");
            usernameOrPasswordIsWrong.show();
            return usernameOrPasswordIsWrong;
        }

        //sqluive -> SQLUsernameIntegrityViolationException
        if (alert.toLowerCase().contains("error:sqluive")) {
            Alert usernameTaken = new Alert(Alert.AlertType.ERROR);
            usernameTaken.setContentText("Username or Password is wrong");
            usernameTaken.show();
            return usernameTaken;
        }

        //fuc -> Failed User Creation
        if (alert.toLowerCase().contains("error:fuc")) {
            Alert failedUserCreation = new Alert(Alert.AlertType.ERROR);
            failedUserCreation.setContentText("Failed to create user");
            failedUserCreation.show();
            return failedUserCreation;
        }

        //fpc -> Failed Project Creation
        if (alert.toLowerCase().contains("error:fpc")) {
            Alert failedProjectCreation = new Alert(Alert.AlertType.ERROR);
            failedProjectCreation.setContentText("Failed to create project");
            failedProjectCreation.show();
            return failedProjectCreation;
        }

        //fpd -> Failed Project Deletion
        if (alert.toLowerCase().contains("error:fpd")) {
            Alert failedProjectDeletion = new Alert(Alert.AlertType.ERROR);
            failedProjectDeletion.setContentText("Failed to delete project");
            failedProjectDeletion.show();
            return failedProjectDeletion;
        }

        //pae -> Project Already Exists
        if (alert.toLowerCase().contains("error:pae")) {
            Alert projectAlreadyCrated = new Alert(Alert.AlertType.ERROR);
            projectAlreadyCrated.setContentText("Project already exists!");
            projectAlreadyCrated.show();
            return projectAlreadyCrated;
        }

        //sqle -> SQLException
        if (alert.toLowerCase().contains("error:sqle")) {
            Alert failedProjectCreation = new Alert(Alert.AlertType.ERROR);
            failedProjectCreation.setContentText("Error: SQL Exception Occurred!");
            failedProjectCreation.show();
            return failedProjectCreation;
        }

        //ioexc -> IOException
        if (alert.toLowerCase().contains("error:ioexc")) {
            Alert inputOutputException = new Alert(Alert.AlertType.ERROR);
            inputOutputException.setContentText("Error: Failed to load window due to IOException!");
            inputOutputException.show();
            return inputOutputException;
        }

        //uinf -> User ID Not Found
        if (alert.toLowerCase().contains("error:uinf")) {
            Alert userIDNotFound = new Alert(Alert.AlertType.ERROR);
            userIDNotFound.setContentText("Error: Failed find userID!");
            userIDNotFound.show();
            return userIDNotFound;
        }
        return null;
    }
}
