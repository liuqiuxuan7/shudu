package com.example.shudu;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity{
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.prefs);
}public static boolean getBackMusic(Context context){
	return	PreferenceManager.getDefaultSharedPreferences(context).getBoolean("music",true);
}
public static boolean getSoundSet(Context context){
	return	PreferenceManager.getDefaultSharedPreferences(context).getBoolean("sound",true);
}
  
}
