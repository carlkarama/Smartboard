package com.smartboard.controller;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.database.dao.ProfileDataAccessObject;
import com.smartboard.model.user.BasicUser;
import com.smartboard.model.user.Profile;
import com.smartboard.view.FactoryAlertViewCreator;
import com.smartboard.view.FactorySceneViewCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


/** @author Carl Karama
 *  @see Profile
 *  @see ProfileDataAccessObject
 *  @version 1.0
 *  */

public class SignupController implements Initializable {

    @FXML public ImageView imagePicker;
    @FXML public Button pickImgBtn;
    @FXML public Button backBtn;
    @FXML public Button createUserBtn;
    @FXML public Pane pane;
    @FXML private TextField username;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField password;

    private String avatar;
    private Profile basic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void register(ActionEvent actionEvent) {

        // Create a connection to the db
        Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

        // Pass the connection to the ProfileDataAccessObject DAO
        ProfileDataAccessObject profileDataAccessObject = new ProfileDataAccessObject(connection);

        //Create a profile instance & inject the FXML values into the profile constructor
        this.basic = new Profile(new BasicUser(firstName.getText(), lastName.getText()), username.getText().toLowerCase(Locale.ROOT), password.getText(), getAvatar());

        // Call the create method from the ProfileDataAccessObject to add the basic user to the database

        if (profileDataAccessObject.create(basic)) {
            FactorySceneViewCreator.changeScene(actionEvent, "workspace", basic);
        } else {
            FactoryAlertViewCreator.getAlert("error:fuc");
        }
    }

    public void goBack(ActionEvent actionEvent) {
        FactorySceneViewCreator.changeScene(actionEvent, "login");
    }


    @FXML
    private void pickImg(ActionEvent mouseEvent) {

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(stage);

        try (FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath())) {


            Image image = new Image(fileInputStream);
            imagePicker = new ImageView(image);

            File destination = new File("src/com/smartboard/resource/image/logo/" + file.getName());

            if (destination.createNewFile()) {

                System.out.println(destination.getName());

                setAvatar(destination.getName());
            }



        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

