package com.anusha.slammed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

public class SignUp extends AppCompatActivity {
    private EditText nameText,rollText,genderText,phoneText,pwdText;
    private ParseUser user;
    Integer iter= new Integer(61);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameText= (EditText) findViewById(R.id.nameText);
        pwdText= (EditText) findViewById(R.id.passwordText);
        rollText= (EditText) findViewById(R.id.rollNoText);
        genderText= (EditText) findViewById(R.id.genderText);
        phoneText= (EditText) findViewById(R.id.phoneText);
        user = new ParseUser();
        Button signedUp= (Button) findViewById(R.id.signUpclick);
        signedUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(rollText.getText().toString());
                user.setPassword(pwdText.getText().toString());
                user.put("Gender", genderText.getText().toString());
                user.put("Name", nameText.getText().toString());
                user.put("Phone", phoneText.getText().toString());
                user.put("Colors","NONE");
                user.put("Nickname","NONE");
                user.put("Interests","NONE");
                user.put("Ambition","NONE");
                /*ParseObject newObject= new ParseObject(user.getUsername().toString());
                for(iter=61;iter<=120;iter++)
                    newObject.put(iter.toString(),"");
                */
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            Toast.makeText(SignUp.this,"Sign Up Successfull!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUp.this, Welcome.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
