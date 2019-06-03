package scholify.app.cybernoob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class lsochooser extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lsochooser);
        findViewById(R.id.loginbtn).setOnClickListener(this);
        findViewById(R.id.signupbtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.loginbtn:startActivity(new Intent(lsochooser.this,LoginActivity.class));break;
            case R.id.signupbtn:startActivity(new Intent(lsochooser.this,SignUpActivity.class));break;
        }
    }
}
