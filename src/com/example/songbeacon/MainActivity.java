package com.example.songbeacon;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;


public class MainActivity extends Activity{
		
	//views for register layout
	View registerLayout;
	EditText namefield;
	Button registerbutton;
	SharedPreferences prefs;
	
	//views for application layout
	View applicationLayout;
	TextView name, deviceID, songID;
	
	User bigbootyuser;
	List <User> userList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//obtain sharedpreferences
		prefs = this.getSharedPreferences("com.example.songbeacon", Context.MODE_PRIVATE);
		
		if(prefs.getBoolean("authtoken", false) == false){
			
			Log.i("EEE", "not logged in - the authtoken is false");
			
			setContentView(R.layout.activity_main);
			
			registerLayout = findViewById(R.id.registerLayout);
			namefield = (EditText) findViewById(R.id.et_name);
			registerbutton = (Button) findViewById(R.id.b_register);
		}else{
			Log.i("EEE", "logged in - the authtoken is true");
			
			//if logged in, then start the UserList activity
			Intent i = new Intent(this, UserList.class);
	        i.putExtra(UserList.EXTRAS_TARGET_ACTIVITY, UserList.class.getName());
			startActivity(i);
		}
		
	}

	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}


	public void register(View v){
		//get a reference to the FireBase root
		
		String name;
		name = namefield.getText().toString();

		TelephonyManager tMan = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		bigbootyuser = new User(name, tMan.getDeviceId());
		
		Firebase newpushref = new Firebase("https://resplendent-fire-3957.firebaseio.com/" + name);
		newpushref.setValue(bigbootyuser);
		
		//create a new editor for the prefs object
		Editor editor = prefs.edit();
				
		//store the authtoken as "true"
		editor.putBoolean("authtoken", true);
		editor.putString("name", bigbootyuser.getName());
		editor.putString("deviceID", bigbootyuser.getDeviceID());
		editor.putString("songID", bigbootyuser.getSongID());
		editor.commit();
		
		//after registering, start the UserList activity
		Intent i = new Intent(this, UserList.class);
		startActivity(i);
		
	}
	
	
}


































