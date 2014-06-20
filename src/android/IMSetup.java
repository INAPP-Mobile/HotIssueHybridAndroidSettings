package kr.imapp.hybrid.imhotissue.settings;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlSerializer;

import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;



public class IMSetup extends PreferenceActivity
{

	@Override
	public void onDestroy()
	{		
		super.onDestroy();
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.setup);
      
        if ( DataHolder.mTStoreEnabled ) {
        	PreferenceScreen ps = this.getPreferenceScreen();
	        Preference pr = ps.findPreference("rateit");
	        if ( pr != null ) ps.removePreference(pr);
	        pr = ps.findPreference("other_app");
	        if ( pr != null ) ps.removePreference(pr);
	        pr = ps.findPreference("disclaimer_screen");
	        if ( pr != null ) ps.removePreference(pr);
	    }
        
        setResult(RESULT_OK);
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, final Preference preference)
	{
		if ( preference.getKey().equals("rateit") )
		{
			Uri uri = Uri.parse( "market://details?id=com.blogspot.imapp.imhotissue");
			startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
		}
		else
		if ( preference.getKey().equals("other_app") )
		{
			Uri uri = Uri.parse( "market://search?q=pub:INAPP");
			startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
		}
		else
		if ( preference.getKey().equals("disclaimer_screen") )
		{
			Uri uri = Uri.parse( "http://imapp.blogspot.com" );
			startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
		}
		else
		if ( preference.getKey().equals("widget_text_color") )
		{
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

			ColorPickerDialog.OnColorChangedListener listen = new ColorPickerDialog.OnColorChangedListener() {
				
				@Override
				public void colorChanged(int color) {
					Editor e = preference.getEditor();
					e.putInt("widget_text_color", color);
					e.commit();
				}
			};
			
			ColorPickerDialog dialog = new ColorPickerDialog(this, listen, prefs.getInt("widget_text_color", Color.rgb(255, 255, 255)) );
			dialog.show();
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
	
};

