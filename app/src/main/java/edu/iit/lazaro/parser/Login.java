package edu.iit.lazaro.parser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Lazaro on 3/1/16.
 */
public class Login extends Activity {
    Button login_button;
    EditText username_editText, password_editText;

    TextView attemps;
    int counter = 3;
    private final int REDIRECT_LENGTH = 4000;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username_editText = (EditText)findViewById(R.id.username_editText);
        password_editText = (EditText)findViewById(R.id.password_editText);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        attemps = (TextView)findViewById(R.id.attemps_textView);
        attemps.setVisibility(View.GONE);
        login_button = (Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username_editText.getText().toString().equals("admin") && password_editText.getText().toString().equals("admin")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_LONG).show();

                    //Create a Timer
                    Timer runSplash = new Timer();

                    //Task to do when the timer ends
                    TimerTask showMain = new TimerTask() {
                        @Override
                        public void run() {
                            //Close Login.class
                            finish();

                            //Start MainActivity.class
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                        }
                    };
                    //Start the timer
                    runSplash.schedule(showMain, REDIRECT_LENGTH);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                    attemps.setVisibility(View.VISIBLE);
                    attemps.setBackgroundColor(Color.RED);
                    counter--;
                    attemps.setText(Integer.toString(counter));
                    if (counter == 0) {
                        login_button.setEnabled(false);
                    }
                }
            }
        });
    }
}