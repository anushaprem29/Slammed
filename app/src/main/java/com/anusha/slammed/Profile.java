package com.anusha.slammed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button editprofile =(Button)findViewById(R.id.editProfile);
        Button backButton =(Button)findViewById(R.id.backButton);
        TextView interests= (TextView) findViewById(R.id.interestList);
        TextView color= (TextView) findViewById(R.id.favColor);
        TextView ambition= (TextView) findViewById(R.id.becomeText);
        TextView nickname= (TextView) findViewById(R.id.Nickname);
        ParseUser user=ParseUser.getCurrentUser();
        TextView heyUserName= (TextView) findViewById(R.id.userNameTV);
        heyUserName.setText("Hey "+user.getString("Name"));
        interests.setText(user.getString("Interests"));
        color.setText(user.getString("Colors"));
        ambition.setText(user.getString("Ambition"));
        nickname.setText(user.getString("Nickname"));
        user.saveInBackground();
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditProfile.class);
                startActivityForResult(intent, 0);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Welcome.class);
                startActivityForResult(intent, 0);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
