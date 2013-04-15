package com.calerem.controllers;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.calerem.api.ContactsAPI;
import com.calerem.api.EortologioApi;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;
import com.calerem.classes.FindContacts;
import com.calerem.ui.NewEvent;
import com.calerem.ui.SendEmail;
import com.calerem.ui.ShowContactsHavingNameday;
import com.calerem.ui.ViewEvent;
import com.google.gson.Gson;

/**
 * Controller for the UI objects of our application.
 * @author DarkParadise
 */
public class UIController{
	private Database db;
	private static Gson gson = new Gson();
	private Context basecontext;

	/**
	 * Base Constructor.
	 * @param Context context
	 */
	public UIController(Context context)
	{
		basecontext = context;
		try {
			db = new Database(basecontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a SendEmail form Activity.
	 * @param Integer contact_id
	 * @param String subject
	 * @param String text
	 */
	public void newSendEmail(Integer contact_id,String subject,String text)
	{
		Intent intent = new Intent(basecontext, SendEmail.class);
		if(contact_id != null)
		{
			Contact v_contact = db.get_contact(contact_id);
			intent.putExtra("Data", gson.toJson(v_contact));
		}
		intent.putExtra("Data_subject", subject);
		intent.putExtra("Data_text", text);
		((Activity) basecontext).startActivityForResult(intent, 2);
	}
	
	/**
	 * Creates a ViewEvent form Activity.
	 * @param int v_event_id
	 */
	public void newViewEvent(int v_event_id)
	{
		Intent intent = new Intent(basecontext, ViewEvent.class);
		Event v_event = db.get_event(v_event_id);
		intent.putExtra("Data", gson.toJson(v_event));
		basecontext.startActivity(intent);
	}
	/**
	 * Creates a NewEvent form Activity.
	 */
	public void newNewEvent()
	{
		Intent intent = new Intent(basecontext, NewEvent.class);
		Contact v_contact[] = db.get_contacts();
		intent.putExtra("Data", gson.toJson(v_contact));
		((Activity) basecontext).startActivityForResult(intent, 1);
	}	

	public void newShowContacts () {
		Intent intent = new Intent(basecontext,ShowContactsHavingNameday.class);
		ContactsAPI contApi = new ContactsAPI(basecontext);
		Contact v_contact[];
		EortologioApi eort = new EortologioApi ();
		ArrayList<Integer> ids=FindContacts.searchNames(contApi.getContacts(),eort.returnVal());
		v_contact = FindContacts.parseNames(ids);
		intent.putExtra("Data", gson.toJson(v_contact));
		basecontext.startActivity(intent);
	}
	
}
