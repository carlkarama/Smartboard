package com.smartboard.model.workspace;

import com.smartboard.model.database.dto.DataTransferObject;
import com.smartboard.model.user.Profile;
import com.smartboard.model.workspace.AddColumn;
import com.smartboard.model.workspace.Column;
import com.smartboard.model.workspace.DeleteColumn;
import com.smartboard.model.workspace.MoveTaskToNewColumn;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author Carl Karama
 *  @see Column
 *  @see AddColumn
 *  @see MoveTaskToNewColumn
 *  @see DeleteColumn
 *  @version 1.0 */

public class Project implements AddColumn, MoveTaskToNewColumn, DeleteColumn, DataTransferObject {

   private Profile user;

   // the name of the project
   private String project;

   //the list of columns residing in a single project
   private List<Column> columns = new ArrayList<>();

   public Project(String project) {
      this.project = project;
   }

   public Project(String project, Profile profile) {
      this.project = project;
      this.user = profile;
   }

   public Project(String project, List<Column> columns) {
      this.project = project;
      this.columns = columns;
   }

   @Override
   public void moveTaskToNewColumn() {

   }

   @Override
   public boolean deleteExistingColumn(Column column) {
      return columns.remove(column);
   }

   @Override
   public boolean addColumn(Column column) {
      if (!(columns.contains(column))) {
         return this.columns.add(column);
      } else return false;
   }

   public String getProject() {
      return project;
   }

   public void setProject(String project) {
      this.project = project;
   }

   public List<Column> getColumns() {
      return columns;
   }

   public void setColumns(List<Column> columns) {
      this.columns = columns;
   }

   public Profile getUser() {
      return user;
   }

   public void setUser(Profile user) {
      this.user = user;
   }

   @Override
   public void getID() {

   }

   @Override
   public String toString() {
      return "Project{" +
              "user=" + user +
              ", project='" + project + '\'' +
              ", columns=" + columns +
              '}';
   }
}
