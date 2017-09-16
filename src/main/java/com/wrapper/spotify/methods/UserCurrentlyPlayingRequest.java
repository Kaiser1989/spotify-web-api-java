package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import net.sf.json.JSONObject;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.PlayingTrack;

import java.io.IOException;

public class UserCurrentlyPlayingRequest extends AbstractRequest {

	public UserCurrentlyPlayingRequest(Builder builder) {
		super(builder);
	}

	public SettableFuture<PlayingTrack> getAsync() {
		SettableFuture<PlayingTrack> tracksFuture = SettableFuture.create();

		try {
			final String jsonString = getJson();
			if (jsonString.isEmpty()) {
				tracksFuture.set(new PlayingTrack());
			} else {
				final JSONObject jsonObject = JSONObject.fromObject(jsonString);
				tracksFuture.set(JsonUtil.createPlayingTrack(jsonObject));	
			}
		} catch (Exception e) {
			tracksFuture.setException(e);
		}

		return tracksFuture;
	}

	public PlayingTrack get() throws IOException, WebApiException {
		try {
			final String jsonString = getJson();		
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			return JsonUtil.createPlayingTrack(jsonObject);
		} catch (EmptyResponseException e) {
			return new PlayingTrack();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractRequest.Builder<Builder> {

		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}

		public UserCurrentlyPlayingRequest build() {
			path("/v1/me/player/currently-playing");
			return new UserCurrentlyPlayingRequest(this);
		}
	}
}

