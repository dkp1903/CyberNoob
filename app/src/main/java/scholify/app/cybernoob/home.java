package scholify.app.cybernoob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        TextView htv = findViewById(R.id.textView);
        Button sobtn = findViewById(R.id.signoutbtn);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            htv.setText(String.format("Welcome to the Cybernoob app %s", user.getEmail()));
        }else
        {
            startActivity(new Intent(this,MainActivity.class));
        }
        sobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(home.this,MainActivity.class));
            }
        });

    }
}
