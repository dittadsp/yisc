package entity;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class BroadcastService extends Service {

    public static String str_receiver = "com.countdowntimerservice.receiver";

    private Handler mHandler = new Handler();
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String strDate;
    Date date_current, date_diff;
    SharedPreferences mpref;
    SharedPreferences.Editor mEditor;

    private Timer mTimer = null;
    public static final long NOTIFY_INTERVAL = 1000;
    Intent intent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mpref = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mpref.edit();
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 5, NOTIFY_INTERVAL);
        intent = new Intent(str_receiver);
    }


    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {

                @Override
                public void run() {

                    calendar = Calendar.getInstance();
                    simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    strDate = simpleDateFormat.format(calendar.getTime());
                    Log.e("datetime", strDate);
                    twoDatesBetweenTime();

                }

            });
        }

    }

    public String twoDatesBetweenTime() {


        try {
            date_current = simpleDateFormat.parse(strDate);
        } catch (Exception e) {

        }

        try {
            date_diff = simpleDateFormat.parse(mpref.getString("calendar", ""));

        } catch (Exception e) {

        }

        try {


            long diff = date_current.getTime() - date_diff.getTime();
            int int_hours = Integer.valueOf(mpref.getString("datettime", ""));
            String str_testing = "";
            long int_timer = TimeUnit.MINUTES.toMillis(int_hours);
            if(int_timer !=0) {
                long long_hours = int_timer - diff;
                long diffSeconds2 = long_hours / 1000 % 60;
                long diffMinutes2 = long_hours / (60 * 1000) % 60;
//            long diffHours2 = long_hours / (60 * 60 * 1000) % 24;


                if (long_hours > 0) {
                     str_testing = diffMinutes2 + ":" + diffSeconds2;

                    Log.e("TIME", str_testing);

                    fn_update(str_testing);
                } else {
                    fn_finish("finish");
                    mEditor.putBoolean("finish", true).commit();
                    mTimer.cancel();
                }
            }
        }catch (Exception e){
            mTimer.cancel();
            mTimer.purge();


        }

        return "";

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Service finish","Finish");
    }

    public void fn_update(String str_time){

        intent.putExtra("time",str_time);
        sendBroadcast(intent);
    }

    public void fn_finish(String finish){

        intent.putExtra("finish",finish);
        sendBroadcast(intent);
    }
}