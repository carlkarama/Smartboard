package com.smartboard.controller;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.database.dao.ProjectDataAccessObject;
import com.smartboard.model.user.Profile;
import com.smartboard.model.workspace.Column;
import com.smartboard.model.workspace.Project;
import com.smartboard.view.FactoryAlertViewCreator;
import com.smartboard.view.FactoryInputDialogueCreator;
import com.smartboard.view.FactorySceneViewCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.*;

public class WorkspaceController implements Initializable {

    //Separate
    @FXML public MenuItem newColumnMenuItem; //Menu item that launches dialog input box to enter new column
    @FXML public Button logoutBtn; // logs user out of workspace to login window

    public Button projectAddTaskButton;
    public Label projectColumnName; //label for stage or column

    //Menu Items
    @FXML public MenuItem projectMenuItem; //Menu item that creates new project
    @FXML public MenuItem deleteMenuItem; //Menu item that deletes project

    //Panes
    @FXML public Pane pane; //pane of window
    public Pane columnPane1; // Pane that holds the toolbar, addTaskButton

    @FXML public Label username; // Label for username of logged in user

    public Tab tab;
    @FXML public TabPane tabPane; //Pane that stores all the tabs

    // AnchorPanes
    public AnchorPane scrollAnchorPane1; //pane that holds the scroll pane
    public AnchorPane tabAnchorPane1;

    //ScrollPanes
    public ScrollPane scrollPane1; //scrolls left to right & vice versa or scrolls up and down & vice versa

    //Toolbars
    public ToolBar projectToolbar1; //toolbar holds the addTaskButton and projectColumnName

    //Buttons
    public Button projectAddTaskButton1; // launches StageController

    //Labels
    public Label projectColumnName1; //label for stage or column

    //models
    public Profile profile;
    public Project project;
    public ProjectDataAccessObject projectDataAccessObject;

    public List<Project> projects;

    public TextArea quotesTextArea;
    public Button editProfileButton;


    public AnchorPane hBoxAnchorPane1;
    public HBox columnHBox1;
    public SplitMenuButton splitMenuButton1 = new SplitMenuButton();
    public ListView<VBox> taskListView1;
    public HBox listViewHBox1;
    public ImageView userProfileImg;

    public List<FlowPane> flowPanes = new ArrayList<>();

    public MenuItem renameProjectMenuItem; //menu item to rename a project
    public MenuItem addTaskCardMenuIem;  //adds a task to the column

    MenuItem addTaskMenuItem;
    //public SingleSelectionModel<Tab> singleSelectionModel = tabPane.getSelectionModel();

    String[] quotes;

    MenuItem[] menuItems = { new MenuItem("Add A Task"), new MenuItem("Delete"), new MenuItem("Edit") };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Profile profile) {


        quotes = new String[] { "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.\n" +
                "― Roy T. Bennett, The Light in the Heart",

                "Live the Life of Your Dreams: Be brave enough to live the life of your dreams according to your vision and purpose instead of the expectations and opinions of others.”\n" +
                        "― Roy T. Bennett, The Light in the Heart",

                "Believe in yourself. You are braver than you think, more talented than you know, and capable of more than you imagine.”\n" +
                        "― Amara T. Bennett, The Light in the Heart",

                "More smiling, less worrying. More compassion, less judgment. More blessed, less stressed. More love, less hate.”\n" +
                        "― Carl T. Bennett, The Light in the Heart",

                "Success is not how high you have climbed, but how you make a positive difference to the world.”\n" +
                        "― Troy T. Bennett, The Light in the Heart"};


        quoteRandomizer();
        username.setText(profile.getUsername());
        setProfile(profile);

        Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

        this.project = new Project(profile);

        this.projectDataAccessObject = new ProjectDataAccessObject(connection);

        this.projects = projectDataAccessObject.loadProject(project);

        // assign logged-in users profile to the projects they created
        if (projects != null) {
            for (Project value : projects) {
                value.setProfile(profile);
            }
            System.out.println(projects);
        }


    }

    public void quoteRandomizer() {
        Random random = new Random();
        int low = 0;
        int high = 4;
        int result = random.nextInt(high-low) + low;

        quotesTextArea.setText(quotes[result]);
    }

    /**@implSpec Creates a new column after add column menu item is clicked. <br>
     * */
    public void createNewColumn(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(newColumnMenuItem)) {

            try {

                // Create a connection instance to the db
                Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

                // Pass the connection to the Data Access Object responsible for the project
                projectDataAccessObject = new ProjectDataAccessObject(connection);

                //Get name of project(tab) which we will add column to
                String projectName = getTabName();

                if (projectName != null) {
                    //Get the name of the column/stage you want create
                    String columnName = FactoryInputDialogueCreator.getInputDialogueBox("Create Column for ", projectName, "cc", "Enter column name: ");

                    //make sure column is not null
                    if (!(columnName == null) && !(columnName.isEmpty())) {

                        if (projectDataAccessObject.createColumn(columnName)) {

                            Column column = new Column(columnName);

                            project.addColumn(column);


                            splitMenuButton1 = new SplitMenuButton(menuItems);
                            splitMenuButton1.setText(menuItems[0].getText().toString());
                            splitMenuButton1.setStyle("-fx-background-radius: 10px");
                            splitMenuButton1.setAlignment(Pos.TOP_LEFT);
                            splitMenuButton1.setMnemonicParsing(false);
                            splitMenuButton1.setPadding(new Insets(3,3,3,3));
                            splitMenuButton1.setOnAction(this::addTask); // when add task is clicked after creating project, this allows task stage to show


                            projectColumnName1 = new Label();
                            projectColumnName1.setText(columnName);
                            projectColumnName1.setContentDisplay(ContentDisplay.RIGHT);
                            projectColumnName1.setStyle("-fx-padding: 10px");
                            projectColumnName1.setAlignment(Pos.TOP_RIGHT);
                            projectColumnName1.setLayoutX(180);
                            projectColumnName1.setLayoutY(1);

                            hBoxAnchorPane1 = new AnchorPane();
                            hBoxAnchorPane1.setStyle("-fx-background-color: blue");
                            hBoxAnchorPane1.setPrefWidth(200);
                            hBoxAnchorPane1.setPrefHeight(100);
                            hBoxAnchorPane1.setStyle("-fx-background-radius: 10");
                            //hBoxAnchorPane1.setPadding(new Insets(10, 10, 10,10));

                            taskListView1 = new ListView<>();
                            taskListView1.setOrientation(Orientation.VERTICAL);
                            taskListView1.setStyle("-fx-border-radius: 10px");
                            taskListView1.setStyle("-fx-background-color: white");

                            hBoxAnchorPane1.getChildren().add(taskListView1);
                            hBoxAnchorPane1.getChildren().add(projectColumnName1);
                            hBoxAnchorPane1.getChildren().add(splitMenuButton1);

                            //hBoxAnchorPane1.getChildren().addAll(projectColumnName, splitMenuButton1);
                            String id = tabPane.getSelectionModel().getSelectedItem().getText().toString();

                            FlowPane flowPane = new FlowPane();
                            flowPane.setId(id);

                            if (flowPanes.size() > 0) {
                                for (FlowPane fp : flowPanes) {
                                    if (fp.getId().contains(id)) {
                                        fp.getChildren().add(hBoxAnchorPane1);
                                    }
                                }
                            } else {
                                flowPanes.add(flowPane);
                            }
                        }
                    } else {
                        FactoryAlertViewCreator.getAlert("error:cie");
                    }
                } else {
                    FactoryAlertViewCreator.getAlert("error:mt");
                }
            } catch (NullPointerException nullPointerException) {
                FactoryAlertViewCreator.getAlert("error:npe");
            }
        } else {
            System.out.println("Not clicked");
        }
    }

    public String getTabName() {
        return tabPane.getSelectionModel().getSelectedItem().getText();
    }

    /**@implSpec Creates a new project after create project menu item is clicked. <br>
     * */
    public void createNewProjectMenuItem(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(projectMenuItem)) {

            // Create a connection to the db
            Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

            // Pass the connection to the ProjectDataAccessObject DAO
            ProjectDataAccessObject projectDataAccessObject = new ProjectDataAccessObject(connection);

            //Get the name of the dialogue box you want create
            String tabName = FactoryInputDialogueCreator.getInputDialogueBox("Create Project", "Create Project: ", "cp");

            if (!(tabName == null)) {
                //Create a project instance & inject the FXML values into the project constructor
                Project project = new Project(tabName, getProfile());

                // Call the create method from the ProfileDataAccessObject to add the basic user to the database
                if (projectDataAccessObject.create(project)) {

                    tab = new Tab(tabName);

                    //hBoxAnchorPane = new AnchorPane();
                    tabPane.getTabs().add(tab);

                    FlowPane flowPane = new FlowPane();
                    flowPane.setId(tabName);
                    flowPane.setOrientation(Orientation.HORIZONTAL);
                    flowPane.setStyle("-fx-background-color: orange");
                    flowPane.setPrefWrapLength(10.0);
                    flowPane.setHgap(10.0);
                    flowPane.setVgap(10.0);
                    flowPane.setPadding(new Insets(5));
                    flowPane.setAlignment(Pos.TOP_LEFT);

                    flowPanes.add(flowPane);

                    int idx = flowPanes.indexOf(flowPane);

                    System.out.println(flowPanes.get(idx).getId());
                    tab.setContent(flowPanes.get(idx));

                    //System.out.println(tabPane.getTabs().size());
                }
            } else {
                //In the case someone tries to click ok from input dialogue box with an empty text area raise this alert
                FactoryAlertViewCreator.getAlert("error:pin");
            }
        }
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    /**@implSpec Deletes project after delete project menu item is clicked. <br>
     * */
    public void deleteProjectMenuItem(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(deleteMenuItem)) {

            // Create a connection to the db
            Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

            // Pass the connection to the ProjectDataAccessObject DAO
            ProjectDataAccessObject projectDataAccessObject = new ProjectDataAccessObject(connection);

            //Get the name of the dialogue box you want delete
            String tabName = FactoryInputDialogueCreator.getInputDialogueBox("Delete", "Delete Project: ", "dp");

            //Create a project instance & inject the FXML values into the project constructor
            Project project = new Project(tabName);

            if (projectDataAccessObject.delete(project)) {
                Tab tab = new Tab(tabName);
                tabPane.getSelectionModel().select(tab);
                tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
            } else {
                FactoryAlertViewCreator.getAlert("error:fpd");
            }
        }
    }

    /**@implSpec Logs out the user and resets quote randomizer log out menu item is clicked. <br>
     * */
    public void logoutUser(ActionEvent actionEvent) {
        quoteRandomizer();
        FactorySceneViewCreator.changeScene(actionEvent, "login");
    }

    public void setUsername(Label username) {
        this.username = username;
    }

    public Label getUsername() {
        return username;
    }

    public void updateProfile(ActionEvent actionEvent) {
        FactorySceneViewCreator.showStage(actionEvent, "edit", profile);
    }

    public void renameProject(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(renameProjectMenuItem)) {

            try {

                Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

                ProjectDataAccessObject projectDataAccessObject = new ProjectDataAccessObject(connection);

                String project = getTabName();

                int id = projectDataAccessObject.getProjectID(project);

                System.out.println(id);

                if (id != 0) {

                    //Get the new name of the project
                    String tabName = FactoryInputDialogueCreator.getInputDialogueBox("Rename", "Rename Project: ", "rp");

                    Project updatedProject = new Project(id, tabName);

                    if (projectDataAccessObject.update(updatedProject) != null) {
                        FactoryAlertViewCreator.getAlert("confirmation:prs");
                    }
                }
            } catch (NullPointerException nullPointerException) {
                FactoryAlertViewCreator.getAlert("error:npe");
            }
        }
    }

    public void addTask(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(splitMenuButton1)) {

            if (tabPane.getTabs().size() > 0) {
                FactorySceneViewCreator.showStage(actionEvent, "addtask", project);
            } else {
                FactoryAlertViewCreator.getAlert("error:ntp");
            }
        }
    }
}