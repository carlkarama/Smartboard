package com.smartboard.controller;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.database.dao.ProfileDataAccessObject;
import com.smartboard.model.user.Profile;
import com.smartboard.view.FactorySceneViewCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/** @author Carl Karama
 *  @version 1.0
 *  */

public class LoginController implements Initializable {

    @FXML private TextField username; // where the user enters their username

    @FXML private PasswordField password; // where the user enters the password

    @FXML private Button loginBtn; // authenticates users details when clicked

    @FXML public Label wrongLogin; // label displayed when login is unsuccessful

    @FXML private Hyperlink accountRegistrationLink; // when clicked take user to SignupController

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void changeToSignupController(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(accountRegistrationLink)) {
           FactorySceneViewCreator.changeScene(actionEvent, "signup");
        }
    }

    public void userLogin(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(loginBtn)) {

            Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

            Profile loginDetails = new Profile(username.getText(), password.getText());

            ProfileDataAccessObject profileDataAccessObject = new ProfileDataAccessObject(connection);

            if (profileDataAccessObject.login(loginDetails) == null) {
                System.out.println("Login failed");
            } else {
                System.out.println("Logged In");
                FactorySceneViewCreator.changeScene(actionEvent, "workspace");
            }
        }
    }
}
