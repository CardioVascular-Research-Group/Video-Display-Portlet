package edu.jhu.cvrg.portal.videodisplay.backing;
/*
Copyright 2011, 2014 Johns Hopkins University Institute for Computational Medicine

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
/**
* @author Chris Jurado
* 
*/
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.liferay.faces.portal.context.LiferayFacesContext;

import edu.jhu.cvrg.portal.videodisplay.model.FolderList;
import edu.jhu.cvrg.portal.videodisplay.model.VideoList;
import edu.jhu.cvrg.portal.videodisplay.utility.ResourceUtility;

@ManagedBean(name = "preferencesBacking")
@ViewScoped
public class PreferencesBacking implements Serializable{

	private static final long serialVersionUID = 1L;
	private String saved;
	private String selectedFolder, selectedVideo;
	private String selectedControlType;
	private FolderList folderList;
	LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
	private boolean fullscreen = false;
	
	@ManagedProperty(value = "#{videoList}")
	private VideoList videoList;

	public PreferencesBacking(){
		
		selectedControlType = ResourceUtility.getControlType();
		selectedFolder = ResourceUtility.getStoredFolder();
		selectedVideo = ResourceUtility.getStoredVideo();
		fullscreen = ResourceUtility.getFullScreen();
	}
	
	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public void apply(ActionEvent event) {
		ResourceUtility.savePreferences(selectedFolder, selectedVideo, selectedControlType, fullscreen);
		setSaved("Values Saved.");
	}

	public void folderChangedEvent(AjaxBehaviorEvent event) {
			videoList.refresh(selectedFolder);
			saved = "";
	}
	
	public ArrayList<SelectItem> getControls(){
		ArrayList<SelectItem> controls = new ArrayList<SelectItem>();
		controls.add(new SelectItem(1,"Scrolling Buttons"));
		controls.add(new SelectItem(2,"Dropdown List"));
		
		return controls;
	}

	public void changedHostname(ValueChangeEvent event) {
		saved = "";
	}

	
	public void setSaved(String saved) {
		this.saved = saved;
	}

	public String getSaved() {
		return saved;
	}

	public String getSelectedFolder() {
		return selectedFolder;
	}

	public void setSelectedFolder(String selectedFolder) {
		this.selectedFolder = selectedFolder;
	}

	public String getSelectedVideo() {
		return selectedVideo;
	}

	public void setSelectedVideo(String selectedVideo) {
		this.selectedVideo = selectedVideo;
	}

	public void setFolderList(FolderList folderList) {
		this.folderList = folderList;
	}

	public FolderList getFolderList() {
		return folderList;
	}

	public VideoList getVideoList() {
		return videoList;
	}

	public void setVideoList(VideoList videoList) {
		this.videoList = videoList;
	}

	public void setSelectedControlType(String selectedControlType) {
		this.selectedControlType = selectedControlType;
	}

	public String getSelectedControlType() {
		return selectedControlType;
	}
}
