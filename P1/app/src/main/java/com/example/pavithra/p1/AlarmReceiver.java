package com.example.pavithra.p1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		
		Toast.makeText(arg0, "Alarm received! "+arg1.getStringExtra("text"), Toast.LENGTH_SHORT).show();

		Uri alarmUri = RingtoneManager. getDefaultUri (RingtoneManager. TYPE_ALARM );
		if (alarmUri == null )
		{
			alarmUri = RingtoneManager. getDefaultUri (RingtoneManager. TYPE_NOTIFICATION );
		} Ringtone ringtone = RingtoneManager. getRingtone (arg0, alarmUri);
		ringtone.play();

        
		Intent intent = new Intent(arg0,MyAlarmService.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("text", arg1.getStringExtra("text"));
		arg0.startService(intent);   

          
      

	}

}


