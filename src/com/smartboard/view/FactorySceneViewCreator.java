package com.smartboard.view;

import com.smartboard.controller.LoginController;
import com.smartboard.controller.SignupController;
import com.smartboard.controller.WorkspaceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/** @author Carl Karama
 *  @see com.smartboard.controller.LoginController
 *  @see com.smartboard.controller.SignupController
 *  @implSpec {@code FactorySceneViewCreator} implements the factory method pattern to load a specific view path and
 *  reduce code duplication created by initialising a {@code Stage} all over the codebase. Here we can initialise once, use everywhere
 *  @version 1.0 */


public final class FactorySceneViewCreator {

    private FactorySceneViewCreator(ActionEvent actionEvent, String pane) {
        changeScene(actionEvent, pane);
    }

    /**
     * @implSpec Make sure to insert scene abbreviation as string listed below. {@code changeScene} loads the scene in relation to the scene name entered into the parameter.
     * @apiNote  {@code "workspace"} is an abbreviation for /com/smartboard/view/Workspace.fxml <br>
     *           {@code "signup"} is an abbreviation for /com/smartboard/view/Signup.fxml <br>
     *           {@code "login"} is an abbreviation for /com/smartboard/view/Login.fxml <br>
     * @return   {@code alert} if alert type exists and null if it doesn't <br>
     * */

    public static void changeScene(ActionEvent actionEvent, String pane) {

        try {
            if (pane.contains("workspace")) {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Parent parent = FXMLLoader.load(Objects.requireNonNull(WorkspaceController.class.getResource("/com/smartboard/view/Workspace.fxml")));
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            }

            if (pane.contains("signup")) {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Parent parent = FXMLLoader.load(Objects.requireNonNull(SignupController.class.getResource("/com/smartboard/view/Signup.fxml")));
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            }

            if (pane.contains("login")) {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Parent parent = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("/com/smartboard/view/Login.fxml")));
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            }

        } catch (IOException ioException) {
            FactoryAlertViewCreator.getAlert("error:ioexc");
        }
    }
}
