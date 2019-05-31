package scholify.app.cybernoob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }
        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean first = pref.getBoolean("first",true);
        boolean Logged = pref.getBoolean("logged",false);
        if(first)
        {
            Toast.makeText(this, "This is your first time,move to tabview", Toast.LENGTH_LONG).show();
            pref.edit().putBoolean("first",false).apply();
        }else if(Logged)
        {
            Toast.makeText(this, "You are logged in,move to home", Toast.LENGTH_LONG).show();
            //TODO dont do anything here
        }else
        {
            if(pref.getBoolean("first",false))
            {
                //TODO move to tab layout
            }else
            {
                new CountDownTimer(1500,500){
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "You are not Logged in,move to login page", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,lsochooser.class));
                        MainActivity.this.finish();
                    }
                }.start();
            }
        }
    }
}
