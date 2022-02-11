package com.smartboard.controller;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.database.dao.TaskDataAccessObject;
import com.smartboard.model.workspace.Project;
import com.smartboard.model.workspace.Task;
import com.smartboard.view.FactoryInputDialogueCreator;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.time.format.DateTimeFormatter;

/**@author Carl Karama
 * @implSpec {@code TaskCardController} handles business logic between AddTask view & TaskDataAccessObject and Task model classes */

public class TaskCardController {


    public Button addTaskButton;
    public Label taskLabel;
    public TextField taskTextField;
    public TextArea descriptionTextArea;
    public Button cancelButton;
    public Hyperlink checklistHyperLink;
    public Label dueDateLabel;
    public Text checklistItem;
    public Project project;
    public DatePicker dueDatePicker;

    public void initData(Project project) {
        this.project = project;
    }

    public void addTask(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(addTaskButton)) {

            Connection connection = DatabaseHandlerSingleton.getDatabaseHandlerSingleton();

            TaskDataAccessObject taskDataAccessObject = new TaskDataAccessObject(connection);

            Task task = new Task(taskTextField.getText(), dueDatePicker.getValue(), descriptionTextArea.getText());

            if (!(dueDateLabel.toString().isEmpty() || descriptionTextArea.toString().isEmpty() || taskTextField.getText().isEmpty())) {

                if (taskDataAccessObject.create(task)) {
                    System.out.println("Task Added");
                }
            }
        }
    }


    public void addChecklist(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(checklistHyperLink)) {
            String checklist = FactoryInputDialogueCreator.getInputDialogueBox("Add Checklist", "Create checklist: ", "ac");
            checklistItem.setText(checklist);
            checklistItem.setVisible(true);
        }
    }

    public void taskDatePicker(ActionEvent actionEvent) {

    }

    public void cancelTaskAdding(ActionEvent actionEvent) {

    }


}
