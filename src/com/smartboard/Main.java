package com.smartboard;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.database.dao.ProfileDataAccessObject;
import com.smartboard.model.database.dao.UserDataAccessObject;
import com.smartboard.model.user.BasicUser;
import com.smartboard.model.user.Profile;
import com.smartboard.model.user.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/** @author Carl Karama
 *  @version 1.0 */

public class Main extends Application {

    @Override
    public void start(Stage app) throws Exception {

        Runnable runnable = () -> {

            Connection singleton = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

            ProfileDataAccessObject profileDataAccessObject = new ProfileDataAccessObject(singleton);

            Profile basic = new Profile(new BasicUser("George", "Orwell"), "judgeJudy", "ccw$^addr", "/C:/Desktop/pic/gh.png");

            profileDataAccessObject.create(basic);

        };

        runnable.run();

        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/smartboard/view/Login.fxml")));
        app.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("resource/image/icon/icon-1.png"))));
        app.setTitle("Smartboard v1.0");
        app.setScene(new Scene(pane));
        app.setResizable(false);
        app.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
