package com.zapp.example.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpResponse; import org.apache.http.NameValuePair; import org.apache.http.client.HttpClient; import org.apache.http.client.entity.UrlEncodedFormEntity; import org.apache.http.client.methods.HttpPost; import org.apache.http.impl.client.DefaultHttpClient; import org.apache.http.message.BasicNameValuePair;

import android.view.View;

public class RemoteSTB {
	private String address;
	private String postText;
	
	
	public static final String MENU="pmenu";
	public static final String VolUP = "pvolplus";
	public static final String VolDOWN="pvolminus";
	public static final String PageUP="pchannelplus";
	public static final String PageDOWN="pchannelminus";
	public static final String UP="pup";
	public static final String DOWN="pdown";
	public static final String LEFT="pleft";
	public static final String RIGHT="pright";
	public static final String OK="pok";
	public static final String BACK="pback";
	public static final String EXIT="pexit";
	
	public void setAddress(String address){
		this.address = address.trim();
	}

	
	public void execute(String command){
		postText = command;
		System.out.println("Starting Thread");
		new Thread(new Runnable() {
			
			public void run() {
				// Create a new HttpClient and Post Header
			    HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://" + address + "/cgi-bin/writepipe_key");

			    try {
			        httppost.setEntity(new StringEntity(postText));
			        // Execute HTTP Post Request
			        System.out.println("Sending Post: " + postText);
			        httpclient.execute(httppost);
			        
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }
				
			}
		}).start();
		
		
	}
	
	
	
	
	
}
