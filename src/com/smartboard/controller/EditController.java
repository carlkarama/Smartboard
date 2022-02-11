package com.smartboard.controller;

import com.smartboard.model.user.Profile;
import javafx.event.ActionEvent;

public class EditController {
    Profile profile;

    public void initData(Profile profile) {

        this.profile = profile;

        System.out.println(profile);
    }

    public void updateBtn(ActionEvent actionEvent) {
    }

    public void cancelButton(ActionEvent actionEvent) {
    }
}
