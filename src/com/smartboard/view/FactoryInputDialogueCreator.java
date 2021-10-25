package com.smartboard.view;

import javafx.scene.control.TextInputDialog;

/** @author Carl Karama
 *  @see com.smartboard.controller.WorkspaceController
 *  @implSpec {@code FactoryInputDialogueCreator} implements the factory method pattern to get specific input dialogue and
 *  reduce code duplication created by initialising a {@code Stage} all over the codebase
 *  @version 1.0 */

public final class FactoryInputDialogueCreator {

    private FactoryInputDialogueCreator(String title, String contentText, String tabType) {
        getInputDialogueBox(title, contentText, tabType);
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

        return null;
    }
}