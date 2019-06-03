package scholify.app.cybernoob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        Button btnSignUp = findViewById(R.id.signup_btn);
        inputEmail =  findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                    if(task.getException().getLocalizedMessage().contains("in use by another account"))
                                    {
                                        Toast.makeText(SignUpActivity.this, "User Already present logging in", Toast.LENGTH_SHORT).show();
                                        auth.signInWithEmailAndPassword(email, password)
                                                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        // If sign in fails, display a message to the user. If sign in succeeds
                                                        // the auth state listener will be notified and logic to handle the
                                                        // signed in user can be handled in the listener.
                                                        progressBar.setVisibility(View.GONE);
                                                        if (!task.isSuccessful()) {
                                                            // there was an error
                                                            if (password.length() < 6) {
                                                                inputPassword.setError(getString(R.string.minimum_password));
                                                            } else {
                                                                Toast.makeText(SignUpActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                                            }
                                                        } else {
                                                            Intent intent = new Intent(SignUpActivity.this, home.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                    }
                                } else {
                                    startActivity(new Intent(SignUpActivity.this, home.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}