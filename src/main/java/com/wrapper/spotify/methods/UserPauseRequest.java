package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import java.io.IOException;

public class UserPauseRequest extends AbstractRequest {

	public UserPauseRequest(Builder builder) {
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
				
		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}
		
		public Builder device(String device) {
			return parameter("device_id", device);
		}

		public UserPauseRequest build() {
			path("/v1/me/player/pause");			
			return new UserPauseRequest(this);
		}
	}
}

