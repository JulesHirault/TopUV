package com.if26.topuv.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.if26.topuv.R;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.fragment.CommentDialogFragment;
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

        Intent intent;
        String student_id = this.getIntent().getStringExtra(IntentConstants.STUDENT_ID);
        String label_uv = this.getIntent().getStringExtra(IntentConstants.LABEL_UV);
        String id_description = this.getIntent().getStringExtra(IntentConstants.ID_DESCRIPTION);
        String id_uv = this.getIntent().getStringExtra(IntentConstants.ID_UV);
        String token = this.getIntent().getStringExtra(IntentConstants.TOKEN);
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_send:
                intent = new Intent(this,AddCommentActivity.class);
                Boolean find = false;

                String myMark = null;

                intent.putExtra(IntentConstants.STUDENT_ID, student_id);
                intent.putExtra(IntentConstants.TOKEN, token);
                intent.putExtra(IntentConstants.LABEL_UV, label_uv);
                intent.putExtra(IntentConstants.ID_DESCRIPTION, id_description);
                intent.putExtra(IntentConstants.ID_UV, id_uv);

                for(Comment comment : comments){
                    if(comment.student != null){
                        if(comment.student.id == Integer.parseInt(student_id)){
                            find = true;
                            myMark = Integer.toString(comment.mark);
                            break;
                        }
                    }
                }

                if(find){
                    FragmentManager fm = getFragmentManager();
                    new CommentDialogFragment(this, intent, myMark).show(fm, "AlerDialog");
                } else {
                    this.startActivity(intent);
                    this.finish();
                }


                return true;
            case R.id.action_menu:
                intent = new Intent(this, CategoriesActivity.class);
                intent.putExtra(IntentConstants.TOKEN, token);
                intent.putExtra(IntentConstants.STUDENT_ID, student_id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
