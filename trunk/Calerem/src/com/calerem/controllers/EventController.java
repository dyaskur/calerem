package com.calerem.controllers;

import android.content.Context;

import com.calerem.classes.Event;
import com.calerem.classes.MockDB;


/**
 * EventController is a class that takes the Events of the next day from the Database
 * and passes them there where they are needed.
 * @author Agapi
 *
 */
public class EventController {
	
	private MockDB mDB;
	Database db;
	Context basecontext;
	Event[] even;
	
	public EventController(MockDB db) {
		this.mDB=db;
	}
	
	public EventController(Database database) {
		this.db = database;
	}
	
	/**
	 * This method generades the string with the upcomming events with the necessary information within it. (Taken from the Database)
	 * @return String
	 */
	public String upcommingEventsDatabase () {
		Event[] even;
		String notifyString = "Upcomming Event: ";
		even = EventController.this.db.get_events(System.currentTimeMillis(),System.currentTimeMillis()+86400000);
		for (int i=0; i<even.length; i++) {
			if (i>0) {
				notifyString += ", ";
			}
			notifyString += even[i].getEvent_name() + "'s " + even[i].getEvent_type();
		}
		return notifyString;
	}
	
	/**
	 * This method generades the string with the upcomming events with the necessary information within it. (Taken from the Mock Database)
	 * @return String
	 */
	public String upcommingEventsMDatabase () {
		Event[] even;
		String notifyString = "Upcomming Event: ";
		even = EventController.this.mDB.get_events();
		for (int i=0; i<even.length; i++) {
			if (i>0) {
				notifyString += ", ";
			}
			notifyString += even[i].getEvent_name() + "'s " + even[i].getEvent_type();
		}
		return notifyString;
	}
		
	public Event getEventById(Integer id) {
		@SuppressWarnings("unused")
		Event ev;
		return ev=EventController.this.mDB.get_events()[id];
	}
}

