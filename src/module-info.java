module SmartBoard {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.java;
    requires org.junit.jupiter.api;

    opens com.smartboard.controller;
    opens com.smartboard.view;
    opens com.smartboard;
    opens com.smartboard.model.user;
    opens com.smartboard.model.security;
    opens com.smartboard.model.database;
    opens com.smartboard.model.workspace;
    opens com.smartboard.model.database.dao;
}