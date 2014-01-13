package com.if26.topuv.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.if26.topuv.R;
import com.if26.topuv.activities.TabActivity;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.services.AddCommentService;

import java.util.concurrent.ExecutionException;

/**
 * Fragment qui permet l'ajout de commentaire pour une Uv
 */
public class AddCommentFragment extends Fragment implements View.OnTouchListener, View.OnClickListener {

    View myView;
    Context context;
    private TextView title_uv;
    private EditText text;
    private Button commentButton;
    private RatingBar ratingBar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.add_comment_page, container, false);
        this.context = this.getActivity();
        ratingBar = (RatingBar) myView.findViewById(R.id.ratingBar);
        text = (EditText) myView.findViewById(R.id.commentEditText);
        commentButton = (Button) myView.findViewById(R.id.commentButton);
        title_uv = (TextView) myView.findViewById(R.id.uvTitleTextView);

        title_uv.setText(this.getActivity().getIntent().getStringExtra(IntentConstants.LABEL_UV));
        commentButton.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        String mark = this.getActivity().getIntent().getStringExtra(IntentConstants.MARK);

        // fait en sorte que le ratingBar soit non cliquable et non changeable dans le cas ou l'étudiant
        // a déjà noté l'Uv et veux uniquement commenter une nouvelle fois
        if(mark != null){
            ratingBar.setFocusable(false);
            ratingBar.setOnTouchListener(this);
            ratingBar.setRating(((float) Integer.parseInt(mark)) / 4);
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onClick(View view) {

        // récupère les variables d'intent
        String token = this.getActivity().getIntent().getStringExtra(IntentConstants.TOKEN);
        String id_description = this.getActivity().getIntent().getStringExtra(IntentConstants.ID_DESCRIPTION);
        String id_uv = this.getActivity().getIntent().getStringExtra(IntentConstants.ID_UV);
        String label_uv = this.getActivity().getIntent().getStringExtra(IntentConstants.LABEL_UV);
        String student_id = this.getActivity().getIntent().getStringExtra(IntentConstants.STUDENT_ID);
        String mark = this.getActivity().getIntent().getStringExtra(IntentConstants.MARK);

        // validation locale
        boolean error = false;
        if(text.length() == 0)
        {
            error = true;
        }
        if(error)
        {
            Toast.makeText(getActivity().getBaseContext(), "Votre Commentaire est vide", Toast.LENGTH_SHORT).show();
            return;
        }

        AddCommentService addComment = new AddCommentService(context);
        try
        {
            // récupère la réponse du service web appelé
            String[] result = addComment.execute(token, Float.toString(ratingBar.getRating()*4), student_id, id_uv, text.getText().toString(), id_description).get();

            if(result[0] == null){
                Toast.makeText(getActivity().getBaseContext(), "Erreur de publication, essayez encore !", Toast.LENGTH_SHORT).show();
            } else if(result[0] != null){

                // si succès, ferme l'activté du fragment et lance l'activté TabActivity
                Toast.makeText(getActivity().getBaseContext(), "Commentaire et note envoyés", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getActivity(), TabActivity.class);
                intent.putExtra(IntentConstants.TOKEN, token);
                intent.putExtra(IntentConstants.STUDENT_ID, student_id);
                intent.putExtra(IntentConstants.ID_UV, id_uv);

                if(result[1] != null){
                    intent.putExtra(IntentConstants.ID_DESCRIPTION, result[1]);
                } else {
                    intent.putExtra(IntentConstants.ID_DESCRIPTION, id_description);
                }

                intent.putExtra(IntentConstants.LABEL_UV, label_uv);
                this.startActivity(intent);
                this.getActivity().finish();
            } else {
                Toast.makeText(getActivity().getBaseContext(), "Something else wrong", Toast.LENGTH_SHORT).show();
            }
        }
        catch(InterruptedException interruptedException)
        {

        }
        catch(ExecutionException executionException)
        {

        }
        catch(NullPointerException nullPointerException)
        {

        }
    }
}