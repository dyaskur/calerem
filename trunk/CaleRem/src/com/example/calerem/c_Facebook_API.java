package com.example.calerem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;



public class c_facebook_API extends Activity {
	
	private Session.StatusCallback statusCallback = new SessionStatusCallback();
	private Session session;
	private Bundle saved_Instance_State;
	
	public c_facebook_API(String username, String password)
	{	
		loginActivity();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		saved_Instance_State = savedInstanceState;
	}
	
	public void loginActivity () {
		Intent intent = new Intent(c_facebook_API.this, c_facebook_API.class);
        startActivity(intent);
		
		Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
		
		session = Session.getActiveSession();
		if(session == null) {
			if(saved_Instance_State != null) {
				session = Session.restoreSession(this, null, statusCallback, saved_Instance_State);
			}
			if (session == null) {
				session = new Session(this);
			}
			Session.setActiveSession(session);
			if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
				session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
			}
		}	
	}
	
	private class SessionStatusCallback implements Session.StatusCallback {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			
		}
	}
}
