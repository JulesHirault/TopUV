package com.if26.topuv.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.if26.topuv.R;
import com.if26.topuv.activities.CategoriesActivity;
import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.services.LoginService;


import java.util.concurrent.ExecutionException;


/**
 * Fragment qui va permettre à l'utilisatur de se logger
 * envoyer git à contact@eutech.ssii.com
 * Created by Flo on 10/12/2013.
 */
public class LoginFragment extends Fragment implements OnClickListener {

    private EditText login, password;
    private Button loginButton;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_page, container, false);
        login  = (EditText) v.findViewById(R.id.usernameField);
        password = (EditText) v.findViewById(R.id.pwdField);
        loginButton = (Button) v.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        context = this.getActivity();

        return v;
    }

    @Override
    public void onClick(View view) {

        // validation locale
        boolean error = false;
        if(password.length() == 0)
        {
            error = true;
        }
        if(error)
        {
            return;
        }

        LoginService loginService = new LoginService(context);
        try
        {
            // récupère la réponse du service web
            String[] result = loginService.execute(login.getText().toString(), password.getText().toString()).get();
            String token = result[0];
            String student_id = result[1];

            if(token == null){
                Toast.makeText(getActivity().getBaseContext(), "Login Failed :( !", Toast.LENGTH_SHORT).show();
            } else if(token != null){
                // si succès lance l'activité Catégorie
                Intent intent = new Intent(this.getActivity(), CategoriesActivity.class);
                intent.putExtra(IntentConstants.TOKEN, token);
                intent.putExtra(IntentConstants.STUDENT_ID, student_id);
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
