package com.smartboard.controller;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.database.dao.ProjectDataAccessObject;
import com.smartboard.model.workspace.Project;
import com.smartboard.view.FactoryAlertViewCreator;
import com.smartboard.view.FactoryInputDialogueCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Objects;
import java.util.ResourceBundle;

public class WorkspaceController implements Initializable {

    @FXML public Tab tab;
    @FXML public Pane pane;
    @FXML public TabPane tabPane;
    @FXML public MenuItem projectMenuItem;
    @FXML public MenuItem deleteMenuItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void createNewProjectMenuItem(ActionEvent actionEvent) {

        // Create a connection to the db
        Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

        // Pass the connection to the ProjectDataAccessObject DAO
        ProjectDataAccessObject projectDataAccessObject = new ProjectDataAccessObject(connection);

        //Get the name of the dialogue box you want create
        String tabName = FactoryInputDialogueCreator.getInputDialogueBox("Create Project", "Create Project: ", "cp");

        //Create a project instance & inject the FXML values into the project constructor
        Project project = new Project(tabName);

        // Call the create method from the ProfileDataAccessObject to add the basic user to the database
        if (projectDataAccessObject.create(project)) {

            tabPane.getTabs().add(new Tab(tabName));

            //use this to get the total amount projects running currently
            System.out.println(tabPane.getTabs().size());
        }
    }

    public void deleteProjectMenuItem(ActionEvent actionEvent) {

        // Create a connection to the db
        Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

        // Pass the connection to the ProjectDataAccessObject DAO
        ProjectDataAccessObject projectDataAccessObject = new ProjectDataAccessObject(connection);

        //Get the name of the dialogue box you want delete
        String tabName = FactoryInputDialogueCreator.getInputDialogueBox("Delete", "Delete Project: ", "dp");

        //Create a project instance & inject the FXML values into the project constructor

        Project project = new Project(tabName);

        if (projectDataAccessObject.delete(project)) {
            tab = new Tab(tabName);
            tabPane.getSelectionModel().select(tab);
            tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
        } else {
            FactoryAlertViewCreator.getAlert("error:fpd");
        }
    }

    public void logoutUser(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/smartboard/view/Login.fxml")));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
