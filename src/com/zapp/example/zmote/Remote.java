package com.zapp.example.zmote;

import com.zapp.example.api.RemoteSTB;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class Remote extends Activity {
	RemoteSTB api;  
	EditText ip;
	SeekBar vol;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
                
        api = new RemoteSTB();
        addListners();
        
        
    }
    
    
    public void addListners(){
    	ip = (EditText)findViewById(R.id.editIP);
    	api.setAddress(ip.getText().toString());
        ip.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			public void afterTextChanged(Editable s) {
				api.setAddress(ip.getText().toString());				
			}
		});
        
        
        Button menu = (Button) findViewById(R.id.buttonMenu);
        menu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.MENU);
			}
		});
        
        Button back = (Button) findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.BACK);
			}
		});
        
        Button exit = (Button) findViewById(R.id.buttonExit);
        exit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.EXIT);
			}
		});
        
        Button up = (Button) findViewById(R.id.buttonUP);
        up.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.UP);
			}
		});
        
        Button down = (Button) findViewById(R.id.buttonDOWN);
        down.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.DOWN);
			}
		});
        
        Button left = (Button) findViewById(R.id.buttonLEFT);
        left.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.LEFT);
			}
		});
        
        Button right = (Button) findViewById(R.id.buttonRIGHT);
        right.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.RIGHT);
			}
		});
        
        Button ok = (Button) findViewById(R.id.buttonOK);
        ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.OK);
			}
		});
        
        Button volup = (Button) findViewById(R.id.buttonVolUp);
        volup.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.VolUP);
			}
		});
        
        Button voldown = (Button) findViewById(R.id.buttonVolDown);
        voldown.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.VolDOWN);
			}
		});
        
        Button pageup = (Button) findViewById(R.id.buttonPageUp);
        pageup.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.PageUP);
			}
		});
        
        Button pagedown = (Button) findViewById(R.id.buttonPageDown);
        pagedown.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				api.execute(RemoteSTB.PageDOWN);
			}
		});
        
        vol = (SeekBar)findViewById(R.id.volBar);
        vol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			boolean running;
			public void onStopTrackingTouch(SeekBar seekBar) {
				vol.setProgress(50);
				running = false;
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				running = true;
				new Thread(new Runnable() {
					
					public void run() {
						while(running){
							if(vol.getProgress() < 50)
								api.execute(RemoteSTB.VolDOWN);
							else if(vol.getProgress() > 50)
								api.execute(RemoteSTB.VolUP);
							
							try {
								Thread.sleep(500-9*Math.abs(vol.getProgress()-50));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}).start();
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				
			}
		});
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_remote, menu);
        return true;
    }
}
