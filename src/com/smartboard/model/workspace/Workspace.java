package com.smartboard.model.workspace;

import com.smartboard.model.user.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author Carl Karama
 *  @see Profile
 *  @see Project
 *  @version 1.0 */

public class Workspace implements InitializeProject, DeleteProject, RenameProject, DefaultProject {

    // workspace identifier
    private int workspaceID;

    // the profile of the logged-in user
    private Profile profile;

    // the list of projects the logged-in user has
    private List<Project> projects = new ArrayList<>();

    public Workspace(Profile profile, List<Project> projects) {
        this.profile = profile;
        this.projects = projects;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public void setDefaultProject() {

    }

    @Override
    public void deleteExistingProjectBoard() {

    }

    @Override
    public void renameProjectBoard() {

    }

    @Override
    public void addProject() {

    }
}
