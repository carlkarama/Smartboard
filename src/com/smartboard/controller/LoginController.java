package com.smartboard.controller;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.user.Profile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** @author Carl Karama
 *  @version 1.0
 *  */

public class LoginController implements EventHandler<MouseEvent> {

    @FXML
    private TextField username; // where the user enters their username

    @FXML
    private PasswordField password; // where the user enters the password

    @FXML
    private Button loginBtn; // authenticates users details when clicked

    @FXML
    public Label wrongLogin; // label displayed when login is unsuccessful

    @FXML
    private Hyperlink accountRegistrationLink; // when clicked take user to SignupController

    public LoginController() {

    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }

    public void userLogin(ActionEvent actionEvent) {
        System.out.println("There are fields Neo. Endless fields where humans aren't born. They are grown!");
    }

    public void authentication(Profile userProfileInfo) {
        DatabaseHandlerSingleton   singleton = null;

        try {
            singleton.getDatabaseHandlerSingleton().createStatement().addBatch("");
        } catch (SQLException sqlException) {
            //FXMLLoader.load("");
        }

    }
}
