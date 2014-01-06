package com.if26.topuv.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
 * Fragment qui affiche une liste des commentaires de l'Uv
 */
public class CommentsFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        // récupère les commentaires
        ArrayList<Comment> comments = ((TabActivity) this.getActivity()).getComments();

        // Si aucun commentaire
        if(comments.size() == 0){
            Comment comment = new Comment();
            comment.mark = -1;
            comments.add(comment);
        }

        this.setListAdapter(new CommentsAdapter(this.getActivity(), comments));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {

    }

    /**
     * Classe pour customiser la liste en y incorporant une image et une ratingBar notamment
     */
    private class CommentsAdapter extends ArrayAdapter<Comment> implements View.OnTouchListener
    {
        public CommentsAdapter(Context context, ArrayList<Comment> uvs)
        {
            super(context, R.layout.comments_page, uvs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(R.layout.comments_page, null);

            // vérification à chaque fois si il y a bien au moins un commentaire
            if(this.getItem(position).student != null){
                ((ImageView) viewGroup.findViewById(R.id.studentPicture)).setImageBitmap(this.getItem(position).student.picture);
                ((TextView) viewGroup.findViewById(R.id.studentName)).setText(this.getItem(position).student.getIdentity());
            }

            if(this.getItem(position).mark != -1){
                ((RatingBar) viewGroup.findViewById(R.id.studentMark)).setRating((float) this.getItem(position).mark / 4);
                (viewGroup.findViewById(R.id.studentMark)).setFocusable(false);
                (viewGroup.findViewById(R.id.studentMark)).setOnTouchListener(this);
            } else {
                (viewGroup.findViewById(R.id.studentMark)).setVisibility(View.INVISIBLE);
            }

            if(this.getItem(position).text != null){
                 ((TextView) viewGroup.findViewById(R.id.commentTextView)).setText(this.getItem(position).text);
            }


            return viewGroup;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }
}