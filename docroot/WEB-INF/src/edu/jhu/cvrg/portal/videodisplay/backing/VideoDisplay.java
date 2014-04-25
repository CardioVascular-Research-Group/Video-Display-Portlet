package edu.jhu.cvrg.portal.videodisplay.backing;
/*
 Copyright 2011 Johns Hopkins University Institute for Computational Medicine

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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.portlet.GenericPortlet;

import edu.jhu.cvrg.portal.videodisplay.model.VideoList;
import edu.jhu.cvrg.portal.videodisplay.utility.ResourceUtility;

@ManagedBean(name = "videoDisplay")
@ViewScoped
public class VideoDisplay extends GenericPortlet implements Serializable{

	private static final long serialVersionUID = 1L;
	private String selectedFolder, selectedVideo, selectedVideoURL, filename;
	private boolean noVideo, maximized, control;
	private int width, height;

	private static int MAX_WIDTH = 1000;
	private static int MAX_HEIGHT = 600;
	private static int MIN_WIDTH = 300;
	private static int MIN_HEIGHT = 200;

	@ManagedProperty(name = "videoList", value = "#{videoList}")
	private VideoList videoList;

	public VideoDisplay() {

		setSelectedFolder(ResourceUtility.getStoredFolder());
		setSelectedVideo(ResourceUtility.getStoredVideo());

		if (selectedVideo.equals("0")){
			noVideo = true;
		}
		else {
			noVideo = false;
			setSelectedVideoURL(ResourceUtility.getVideoURL(Long.valueOf(selectedVideo)));
		}

		if (this.isMaximized()) {
			width = MAX_WIDTH;
			height = MAX_HEIGHT;
		} 
		else {
			if(ResourceUtility.getFullScreen()){
				width = MAX_WIDTH;
				height = MAX_HEIGHT;
			}
			else {
				width = MIN_WIDTH;
				height = MIN_HEIGHT;
			}
		}

		if (ResourceUtility.getControlType().equals("1")) {
			control = true;
		} 
		else {
			control = false;
		}
	}

	public void back(ActionEvent event) {
		setSelectedVideo(videoList.getPreviousVideo(selectedVideo));
	}

	public void forward(ActionEvent event) {
		setSelectedVideo(videoList.getNextVideo(selectedVideo));
	}

	public void changedVideoEvent(AjaxBehaviorEvent event) {
		setSelectedVideoURL(ResourceUtility.getVideoURL(Long.valueOf(selectedVideo)));
	}

	public String getSelectedVideoURL() {
		return selectedVideoURL;
	}

	public void setSelectedVideoURL(String selectedVideoURL) {
		this.selectedVideoURL = selectedVideoURL;
	}

	public void setSelectedFolder(String selectedFolder) {
		this.selectedFolder = selectedFolder;
	}

	public String getSelectedFolder() {
		if(!selectedFolder.equals("0")){
			return selectedFolder;
		}
		else {
			return "";
		}
	}

	public void setSelectedVideo(String selectedVideo) {
		this.selectedVideo = selectedVideo;
	}

	public String getSelectedVideo() {
		return selectedVideo;
	}

	public void setNoVideo(boolean noVideo) {
		this.noVideo = noVideo;
	}

	public boolean isNoVideo() {
		return noVideo;
	}

	public void setMaximized(boolean maximized) {
		if (maximized || ResourceUtility.getFullScreen()) {
			width = MAX_WIDTH;
			height = MAX_HEIGHT;
		} else {
			width = MIN_WIDTH;
			height = MIN_HEIGHT;
		}
		this.maximized = maximized;
	}

	public boolean isMaximized() {
		return maximized;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public VideoList getVideoList() {
		return videoList;
	}

	public void setVideoList(VideoList videoList) {
		this.videoList = videoList;
	}

	public void setControl(boolean control) {
		this.control = control;
	}

	public boolean isControl() {
		return control;
	}
}