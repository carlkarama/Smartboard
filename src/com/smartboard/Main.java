package com.smartboard;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import com.smartboard.model.user.Profile;
import com.smartboard.model.user.BasicUser;
import com.smartboard.model.database.dao.ProfileDataAccessObject;

import java.sql.Connection;
import java.util.Objects;

/** @author Carl Karama
 *  @version 1.0 */

public class Main extends Application {

    @Override
    public void start(Stage app) throws Exception {

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
