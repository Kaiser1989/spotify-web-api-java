package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.wrapper.spotify.exceptions.*;

import java.io.IOException;
import java.util.List;

public class UserPlayRequest extends AbstractRequest {

	public UserPlayRequest(Builder builder) {
		super(builder);
	}

	public SettableFuture<Object> getAsync() {
		SettableFuture<Object> future = SettableFuture.create();
		try {
			putJson();
			future.set(null);
		} catch (Exception e) {
			future.setException(e);
		}
		return future;
	}

	public void get() throws IOException, WebApiException {
		try {
			putJson();
		} catch (EmptyResponseException e) {
			// ignore, due to empty response
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractRequest.Builder<Builder> {
		
		private JSONObject jsonBody = new JSONObject();
		
		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}
		
		public Builder device(String device) {
			return parameter("device_id", device);
		}
		
		public Builder context(String context_uri) {			
			jsonBody.put("context_uri", context_uri);
			return this;
		}
		
		public Builder tracks(List<String> tracks) {		
			jsonBody.put("uris", JSONArray.fromObject(tracks).toString());
			return this;
		}

		public UserPlayRequest build() {
	        header("Content-Type", "application/json");
			path("/v1/me/player/play");
			body(jsonBody);			
			return new UserPlayRequest(this);
		}
	}
}

