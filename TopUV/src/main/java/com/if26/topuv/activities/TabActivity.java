package com.if26.topuv.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.if26.topuv.R;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.models.Comment;
import com.if26.topuv.models.Description;

import java.util.ArrayList;

/**
 * Created by Flo on 27/12/2013.
 */
public class TabActivity extends Activity {

    Description description;

    ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_page);
    }

    public Description getDescription(){
        return description;
    }

    public void setDescription(Description description){
        this.description = description;
    }


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_send:
                return true;
            case R.id.action_menu:
                Intent intent = new Intent(this, CategoriesActivity.class);
                String token = this.getIntent().getStringExtra(IntentConstants.TOKEN);
                intent.putExtra(IntentConstants.TOKEN, token);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
