package com.smartboard.view;

import com.smartboard.controller.*;
import com.smartboard.model.database.dto.DataTransferObject;
import com.smartboard.model.user.Profile;
import com.smartboard.model.workspace.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/** @author Carl Karama
 *  @see com.smartboard.controller.LoginController
 *  @see com.smartboard.controller.SignupController
 *  @implSpec {@code FactorySceneViewCreator} implements the factory method pattern to load a specific view path and
 *  reduce code duplication created by initialising a {@code Stage} all over the codebase. Here we can initialise once, use everywhere
 *  @version 1.0 */


public final class FactorySceneViewCreator {


    /**
     * @implSpec Make sure to insert scene abbreviation as string listed below. {@code changeScene} loads the scene in relation to the scene name entered into the parameter.
     * @apiNote  {@code "workspace"} is an abbreviation for /com/smartboard/view/Workspace.fxml <br>
     *           {@code "signup"} is an abbreviation for /com/smartboard/view/Signup.fxml <br>
     *           {@code "login"} is an abbreviation for /com/smartboard/view/Login.fxml <br>
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


    public static Stage showStage(ActionEvent actionEvent, String pane, DataTransferObject dataTransferObject) {

        try {

            if (pane.toLowerCase().contains("addtask")) {

                URL fxmlLocation = FactorySceneViewCreator.class.getResource("/com/smartboard/view/AddTask.fxml");

                FXMLLoader taskLoader = new FXMLLoader(fxmlLocation);

                Stage stage = new Stage();

                stage.setScene(new Scene(taskLoader.load()));

                TaskCardController controller = taskLoader.getController();

                controller.initData((Project) dataTransferObject);

                stage.show();

                return stage;
            }

            if (pane.toLowerCase().contains("edit")) {

                URL fxmlLocation = FactorySceneViewCreator.class.getResource("/com/smartboard/view/Edit.fxml");

                FXMLLoader loader = new FXMLLoader(fxmlLocation);

                Stage stage = new Stage();

                stage.setScene(new Scene(loader.load()));

                EditController controller = loader.getController();

                controller.initData((Profile) dataTransferObject);

                stage.show();

                return stage;
            }
        } catch (IOException ioException) {
            FactoryAlertViewCreator.getAlert("error:ioexc");
        }


        return null;
    }
    public static void changeScene(ActionEvent actionEvent, String pane, DataTransferObject dataTransferObject) {

        try {
            if (pane.toLowerCase().contains("workspace")) {

                URL fxmlLocation = FactorySceneViewCreator.class.getResource("/com/smartboard/view/Workspace.fxml");

                FXMLLoader loader = new FXMLLoader(fxmlLocation);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(new Scene(loader.load()));

                WorkspaceController controller = loader.getController();

                controller.initData((Profile) dataTransferObject);

                stage.show();
            }
        } catch (IOException ioException) {
            FactoryAlertViewCreator.getAlert("error:ioexc");
        }
    }


}
