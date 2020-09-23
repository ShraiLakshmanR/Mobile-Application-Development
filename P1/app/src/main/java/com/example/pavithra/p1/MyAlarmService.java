package com.example.pavithra.p1;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class MyAlarmService extends Service implements OnInitListener,OnUtteranceCompletedListener {
	
	
    private TextToSpeech mTts;
    private String spokenText= "";
   
	 
	
    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	//Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();


    
    	Log.d("this is daya","daya alaram");
 	
    }

    @Override   
    public IBinder onBind(Intent intent) {
    	// TODO Auto-generated method stub
    	//Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();
 
    	Log.d("this is daya","daya alaram");
    	return null;
    }
 
    @Override
    public void onDestroy() {
	
    	// TODO Auto-generated method stub
	
    	if (mTts != null) {
    		mTts.stop();
    		mTts.shutdown();
    	}
	         
    	super.onDestroy();
    	Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {
	
    	// TODO Auto-generated method stub
    	super.onStart(intent, startId);
    	
    	//Notification(intent.getStringExtra("text"));
    	spokenText = "";
    	spokenText += intent.getStringExtra("text")+"";
 
    	
    	mTts = new TextToSpeech(this, this);
    	//Toast.makeText(this, "hii"+spokenText, Toast.LENGTH_LONG).show();
    	
   
    }

    @Override
    public boolean onUnbind(Intent intent) {
    	// TODO Auto-generated method stub
    	Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
    	return super.onUnbind(intent);
    }
   
    @Override
    public void onUtteranceCompleted(String utteranceId) {
	// TODO Auto-generated method stub
    	stopSelf();
  	Intent intent = new Intent(this,MyAlarmService.class);
    	startService(intent);
    }
    
    @Override
    public void onInit(int status) {
	// TODO Auto-generated method stub

	if (status == TextToSpeech.SUCCESS) {

        int result = mTts.setLanguage(Locale.KOREAN);
        if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
        	spokenText += spokenText+""+spokenText+"";
           // mTts.speak("Dear sir your class is finished!! Dear sir your class is finished!! Dear sir your class is finished!!", TextToSpeech.QUEUE_FLUSH, null);
            mTts.speak(spokenText, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}



}