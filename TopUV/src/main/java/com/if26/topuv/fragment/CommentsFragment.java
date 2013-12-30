package com.if26.topuv.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.if26.topuv.R;
import com.if26.topuv.activities.TabActivity;
import com.if26.topuv.models.Comment;

import java.util.ArrayList;

/**
 * Created by Flo on 27/12/2013.
 */
public class CommentsFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Comment> comments = ((TabActivity) this.getActivity()).getComments();

        this.setListAdapter(new CommentsAdapter(this.getActivity(), comments));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {

    }



    private class CommentsAdapter extends ArrayAdapter<Comment>
    {
        public CommentsAdapter(Context context, ArrayList<Comment> uvs)
        {
            super(context, R.layout.comments_page, uvs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(R.layout.comments_page, null);


            ((ImageView) viewGroup.findViewById(R.id.studentPicture)).setImageBitmap(this.getItem(position).student.picture);
            ((TextView) viewGroup.findViewById(R.id.studentName)).setText(this.getItem(position).student.getIdentity());
            ((RatingBar) viewGroup.findViewById(R.id.studentMark)).setRating(this.getItem(position).mark);
            ((TextView) viewGroup.findViewById(R.id.commentTextView)).setText(this.getItem(position).text);

            return viewGroup;
        }
    }
}