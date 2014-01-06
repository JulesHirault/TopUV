package com.if26.topuv.fragment;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Classe qui permet la fusion des onglet de TabFragment dans l'ActionBar
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;

    /** Constructeur utilisé à chaque nouvel onglet créé
     * @param activity  l'activité hôte du fragment
     * @param tag  l'identifiant du fragment
     * @param clz  la classe du fragment
     */
    public TabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
    }

   // les méthodes suivantes sont les callback des onglets

    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // vérifie si le fragment a été initialisé
        if (mFragment == null) {
            // Si non, on l'instancie et on l'ajoute à l'activité
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            ft.add(android.R.id.content, mFragment, mTag);
        } else {
            // Si oui, on le montre simplement
            ft.attach(mFragment);
        }
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            // Détache le fragment car un autre viens d'être attaché
            ft.detach(mFragment);
        }
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // en cas de resélection, ici aucun intérêt
    }
}