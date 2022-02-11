package com.smartboard.view;

import javafx.scene.control.TextInputDialog;

/** @author Carl Karama
 *  @see com.smartboard.controller.WorkspaceController
 *  @implSpec {@code FactoryInputDialogueCreator} implements the factory method pattern to get specific input dialogue and
 *  reduce code duplication created by initialising a {@code TextInputDialog} all over the codebase
 *  @version 1.0 */

public final class FactoryInputDialogueCreator {


    /** @implSpec {@code getInputDialogueBox} is a simple method that handles the creation of input dialogue boxes
     *            {@code title} The main title at the top of the dialogue box
     *            {@code project} The name of the project the user is going to enter
     *            {@code tab type} An abbreviation of the type of tab you are trying to create e.g. cc is short for create column
     *            {@code content text} The text next to the text input area*/
    public static String getInputDialogueBox(String title, String project, String tabType, String contentText) {

        if (tabType.toLowerCase().contains("cc")) {

            TextInputDialog createNewColumn = new TextInputDialog();

            createNewColumn.setTitle(title + " " + project);

            createNewColumn.getDialogPane().setContentText(contentText);

            createNewColumn.showAndWait();

            return createNewColumn.getEditor().getText();
        }
        return null;
    }

    public static String getInputDialogueBox(String title, String contentText, String tabType) {

        if (tabType.toLowerCase().contains("cp")) {

            TextInputDialog textInputDialog = new TextInputDialog();

            textInputDialog.setTitle(title);

            textInputDialog.getDialogPane().setContentText(contentText);

            textInputDialog.showAndWait();

            return textInputDialog.getEditor().getText();
        }

        if (tabType.toLowerCase().contains("dp")) {

            TextInputDialog deleteInputDialog = new TextInputDialog();

            deleteInputDialog.setTitle(title);

            deleteInputDialog.getDialogPane().setContentText(contentText);

            deleteInputDialog.showAndWait();

            return deleteInputDialog.getEditor().getText();
        }

        if (tabType.toLowerCase().contains("rp")) {

            TextInputDialog renameInputDialog = new TextInputDialog();

            renameInputDialog.setTitle(title);

            renameInputDialog.getDialogPane().setContentText(contentText);

            renameInputDialog.showAndWait();

            return renameInputDialog.getEditor().getText();
        }

        return null;
    }
}