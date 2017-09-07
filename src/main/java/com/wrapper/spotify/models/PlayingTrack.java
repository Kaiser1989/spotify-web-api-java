package com.wrapper.spotify.models;

public class PlayingTrack {

	private Track track;
	private Integer timestamp;
	private Integer progress;
	private boolean isPlaying;
	private Context context;
	
	public Track getTrack() {
		return track;
	}
	
	public void setTrack(Track track) {
		this.track = track;
	}
	
	public Integer getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}
	
	public Integer getProgress() {
		return progress;
	}
	
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	public Context getContext() {
		return context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
}
