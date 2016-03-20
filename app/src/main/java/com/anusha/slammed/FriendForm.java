package com.anusha.slammed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;

public class FriendForm extends AppCompatActivity {
    ParseQuery<ParseObject> query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_form);
        Button done=(Button)findViewById(R.id.doneButton);
        TextView friendnum= (TextView) findViewById(R.id.friendNum);
        final TextView aboutFriend= (TextView) findViewById(R.id.aboutFriend);
        query= ParseQuery.getQuery(friendnum.getText().toString());
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject friendObject, com.parse.ParseException e) {
                        if (e == null) {
                            friendObject.put(ParseUser.getCurrentUser().getUsername().toString(),aboutFriend.getText().toString());
                            friendObject.saveInBackground();
                        }
                    }
                });

                Intent intent = new Intent(v.getContext(), Welcome.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_form, menu);
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
