package com.if26.topuv.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.if26.topuv.activities.TabActivity;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.models.Comment;

import java.util.ArrayList;

/**
 * Fragment qui va permettre le popup d'une fenêtre de dialogue et demander à l'utilisateur de choisir entre 3 options :
 * - Renoter et commenter l'Uv
 * - Commenter Uniquement
 * - Annuler
 */
public class CommentDialogFragment extends DialogFragment {
    Activity activity;
    Intent intent;
    String myMark;

    public CommentDialogFragment(Activity activity, Intent intent, String myMark){
        this.activity = activity;
        this.intent = intent;
        this.myMark = myMark;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // liste des éléments de la liste de la fenêtre de dialogue
        String[] items = {"Commenter et renoter", "Commenter uniquement", "Annuler"};
        final String token = activity.getIntent().getStringExtra(IntentConstants.TOKEN);
        final String label_uv = activity.getIntent().getStringExtra(IntentConstants.LABEL_UV);
        final String student_id = activity.getIntent().getStringExtra(IntentConstants.STUDENT_ID);
        final String id_description = activity.getIntent().getStringExtra(IntentConstants.ID_DESCRIPTION);
        final String id_uv = activity.getIntent().getStringExtra(IntentConstants.ID_UV);
        ArrayList<Comment> comments = ((TabActivity) activity).getComments();
        boolean find = false;
        Comment myComment = null;


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // ajout d'une liste à 3 éléments
        builder.setTitle("Vous avez déjà noté et commenté cette Uv. Voulez-vous :")
                .setItems(items, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        intent.putExtra(IntentConstants.STUDENT_ID, student_id);
                        intent.putExtra(IntentConstants.TOKEN, token);
                        intent.putExtra(IntentConstants.LABEL_UV, label_uv);
                        intent.putExtra(IntentConstants.ID_DESCRIPTION, id_description);
                        intent.putExtra(IntentConstants.ID_UV, id_uv);
                        switch (which) {
                            // 1er élément cliqué
                            case 0:
                                // on démarre l'activté d'ajout de commentaire normalement
                                activity.startActivity(intent);
                                activity.finish();
                                break;
                            // 2è élément cliqué
                            case 1:
                                // on démarre l'activté d'ajout de commentaire en indiquant la noté déjà donné
                                intent.putExtra(IntentConstants.MARK, myMark);
                                activity.startActivity(intent);
                                activity.finish();
                                break;
                            //3è élément cliqué
                            case 2:
                                // on annule
                                dialog.cancel();
                                break;
                        }
                    }
                });
        // Créer la fenêtre et la retourne
        return builder.create();
    }

}
