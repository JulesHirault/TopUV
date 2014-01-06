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
 * Activité permettant de lancer le fragment gérant 3 fragments :
 *  - le fragment pour la description d l'Uv
 *  - le fragment pour le détail d'une Uv
 *  - le fragment pour l'affichage des commentaires d'une Uv
 */
public class TabActivity extends Activity {

    // descriptions et détails de l'Uv récupérée
    Description description;

    // commnentaires de l'Uv récupérée
    ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_page);
    }

    /**
     * Getter de description
     * @return objet de type Description
     */
    public Description getDescription(){
        return description;
    }

    /**
     * Setter de description
     * @param description Objet de type Description
     */
    public void setDescription(Description description){
        this.description = description;
    }

    /**
     * Getter de comments
     * @return une arrayList de commentaires
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * Setter de comments
     * @param comments arrayList de commentaires
     */
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    /**
     * initialise les différentes options de l'actionBar
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Effectue des opérations différentes en fonction du bouton ActionBar sélectionné
     * @param item item du menu ActionBar
     * @return un booléen
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        //récupération des variables intent
        String student_id = this.getIntent().getStringExtra(IntentConstants.STUDENT_ID);
        String label_uv = this.getIntent().getStringExtra(IntentConstants.LABEL_UV);
        String id_description = this.getIntent().getStringExtra(IntentConstants.ID_DESCRIPTION);
        String id_uv = this.getIntent().getStringExtra(IntentConstants.ID_UV);
        String token = this.getIntent().getStringExtra(IntentConstants.TOKEN);

        // quand on presse un bouton
        switch (item.getItemId()) {

            // le bouton pour commenter
            case R.id.action_send:
                intent = new Intent(this,AddCommentActivity.class);
                Boolean find = false;

                String myMark = null;

                // ajoute les variables nécessaires pour la prochaine activité
                intent.putExtra(IntentConstants.STUDENT_ID, student_id);
                intent.putExtra(IntentConstants.TOKEN, token);
                intent.putExtra(IntentConstants.LABEL_UV, label_uv);
                intent.putExtra(IntentConstants.ID_DESCRIPTION, id_description);
                intent.putExtra(IntentConstants.ID_UV, id_uv);

                // vérifie que l'étudiant loggé a déjà commenté et noté l'Uv
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
                    // Si oui, un message lui demandera l'opération qu'il veut effectué (renoter ou non)
                    FragmentManager fm = getFragmentManager();
                    new CommentDialogFragment(this, intent, myMark).show(fm, "AlerDialog");
                } else {
                    // si non, lance l'activité d'ajout de commentaire et termine l'activité actuelle
                    this.startActivity(intent);
                    this.finish();
                }
                return true;

            // le bouton pour retourner au menu principal
            case R.id.action_menu:

                // termine l'activité actuelle et toutes les activités lancées et lance l'activité catégorie
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
