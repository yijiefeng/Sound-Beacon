package com.example.songbeacon;

import android.content.Context;
import android.telephony.TelephonyManager;

public class User 
{
	private String name;
	private String deviceID;
	private String songID;
	
	
	public User()
	{
		name = "";
		deviceID = "";
		songID = "";
	}
	
	public User(String name)
	{
		this.name = name;
		this.deviceID = "";
		this.songID = "";
	}
	public User(String name, String deviceID)
	{
		this.name = name;
		this.deviceID = deviceID;
		this.songID = "";
	}

	public void setName(String newName){
		this.name = newName;
	}
	public void setDeviceID(String newDeviceID){
		this.deviceID = newDeviceID;
	}
	public void setSongID(String newSongID){
		this.songID = newSongID;
	}
	
	/**
	 *	Getters 
	 */
	public String getName()
	{
		return name;
	}
	public String getDeviceID()
	{
		return deviceID;
	}
	public String getSongID()
	{
		return songID;
	}
}
