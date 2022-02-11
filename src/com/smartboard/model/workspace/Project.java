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

   private int projectID;

   private Profile profile;

   // the name of the project
   private String project;

   //the list of columns residing in a single project
   private List<Column> columns = new ArrayList<>();

   public Project() {

   }

   public Project(int projectID) {
      this.projectID = projectID;
   }

   public Project(int projectID, String project) {
      this.projectID = projectID;
      this.project = project;
   }

   public Project(String project) {
      this.project = project;
   }

   public Project(Profile profile) {
      this.profile = profile;
   }

   public Project(String project, Profile profile) {
      this.project = project;
      this.profile = profile;
   }

   public Project(String project, List<Column> columns) {
      this.project = project;
      this.columns = columns;
   }

   @Override
   public void moveTaskToNewColumn(Column c1, Column c2) {
      try {
         c2.getTasks().add(new Task(c1.getTasks().get(c1.getTasks().size()-1).getTask()));
      } catch (NullPointerException nullPointerException) {
         System.out.println("Error: No object (task) found in order to move! ");
      }
   }

   @Override
   public boolean deleteExistingColumn(Column column) {
      try {
         return columns.remove(column);
      } catch (NullPointerException nullPointerException) {
         System.out.println("Error: No object (column) found in order to remove! ");
      }
      return false;
   }

   @Override
   public boolean addColumn(Column column) {
      try {
         return this.columns.add(column);
      } catch (NullPointerException nullPointerException) {
         System.out.println("Error: No object (column) found in order to add! ");
      }
      return false;
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

   public Profile getProfile() {
      return profile;
   }

   public void setProfile(Profile profile) {
      this.profile = profile;
   }

   public int getProjectID() {
      return projectID;
   }

   public void setProjectID(int projectID) {
      this.projectID = projectID;
   }

   @Override
   public void getID() {

   }

   @Override
   public String toString() {
      return "Project{" +
              "profile=" + profile +
              ", project='" + project + '\'' +
              ", columns=" + columns +
              '}';
   }
}
