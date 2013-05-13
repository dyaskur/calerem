package com.calerem.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.calerem.R;
import com.calerem.classes.Event;
import com.google.gson.Gson;

/**
 * Form that shows all information about an event.
 * @author DarkParadise
 */
public class ViewEvent extends Activity {
	
	private TextView title,name,last,phone,mail;
	private EditText edName, edType, edDate, edDescription, edNameC, edLastC, edPhone, edMail;
	private Event event;
	
	/**
	 * Prepares the form. Reads input values.
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_event);
		initVars();
		Bundle extras = getIntent().getExtras();
		String data = extras.getString("Data");
		String date_format = extras.getString("DateFormat");
		event = new Gson().fromJson(data,Event.class);
		checkContact();
		//print event's info in proper visual objects
		edName.setText(event.getEvent_name());
		edType.setText(event.getEvent_type());
		edDate.setText(new SimpleDateFormat(date_format,Locale.ENGLISH).format(new Date(event.getEvent_date()*1000)));
		edDescription.setText(event.getEvent_description());
		this.disableEdits();
	}

	/**
	 * Links variables to visual objects.
	 */
	private void initVars()
	{
		edName= (EditText) findViewById (R.id.etName);
		edType= (EditText) findViewById (R.id.etType);
		edDate= (EditText) findViewById (R.id.etDt);
		edDescription = (EditText) findViewById (R.id.etDescription);
		edNameC= (EditText) findViewById (R.id.etNameC);
		edLastC= (EditText) findViewById (R.id.etLast);
		edPhone= (EditText) findViewById (R.id.etPhn);
		edMail= (EditText) findViewById (R.id.etMail);
		title= (TextView) findViewById (R.id.tvContact);
		name= (TextView) findViewById (R.id.tvNameC);
		last= (TextView) findViewById (R.id.tvLast);
		phone= (TextView) findViewById (R.id.tvPhone);
		mail= (TextView) findViewById (R.id.tvMail);
	}
	
	/**
	 * Checks if contact exists, and enables contact View.
	 */
	private void checkContact()
	{
		if(event.getEvent_contact() == null)
		{
			edNameC.setVisibility(View.INVISIBLE);
			edLastC.setVisibility(View.INVISIBLE);
			edPhone.setVisibility(View.INVISIBLE);
			edMail.setVisibility(View.INVISIBLE);
			title.setVisibility(View.INVISIBLE);
			name.setVisibility(View.INVISIBLE);
			last.setVisibility(View.INVISIBLE);
			phone.setVisibility(View.INVISIBLE);
			mail.setVisibility(View.INVISIBLE);
		}
		else
		{
			//else print info in these objects
			edNameC.setText(event.getEvent_contact().getName());
			edLastC.setText(event.getEvent_contact().getLastname());
			edPhone.setText(event.getEvent_contact().getPhone());
			edMail.setText(event.getEvent_contact().getEmail());
		}
	}
	/**
	 * Disables Editing for textviews.
	 */
	private void disableEdits()
	{
		edName.setFocusable(false);
		edType.setFocusable(false);
		edDate.setFocusable(false);
		edDescription.setFocusable(false);
		edNameC.setFocusable(false);
		edLastC.setFocusable(false);
		edPhone.setFocusable(false);
		edMail.setFocusable(false);
	}
}
