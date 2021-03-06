package com.if26.topuv.services;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.if26.topuv.constants.WSConstants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Classe qui va communiquer avec le service web permettant de se logger à l'application
 */
public class LoginService extends AsyncTask<String, Void, String[]> {
    Context context;
    ProgressDialog progDialog;

    public LoginService(Context context){
        this.context = context;
    }

    @Override
    protected String[] doInBackground(String... args) {
        String login = args[0];
        String password = args[1];

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        String[] result = new String[2];
        // Requête HTTP
        try {

            HttpPost httpPost = new HttpPost(WSConstants.LOGIN.URI);
            DefaultHttpClient httpClient = new DefaultHttpClient();


            nameValuePairs.add(new BasicNameValuePair(WSConstants.LOGIN.LOGIN, login));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.LOGIN.PASSWORD, password));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            // réponse en JSON
            JSONObject jsonObject = new JSONObject(response);
            result[0] = jsonObject.getString(WSConstants.LOGIN.TOKEN);
            result[1] = jsonObject.getString(WSConstants.STUDENT.ID);
            return result;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    /**
     * affiche un spinner dans une fnêtre de dialogue pendant la récupération des données
     */
    protected void onPreExecute() {
        super.onPreExecute();
        progDialog = new ProgressDialog(this.context);
        progDialog.setMessage("Loading...");
        progDialog.setIndeterminate(false);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setCancelable(true);
        progDialog.show();
    }

    @Override
    /**
     * Ferme la fenêtre de dialogue une fois la récupération de données terminée
     */
    protected void onPostExecute(String[] unused) {
        super.onPostExecute(unused);
        progDialog.dismiss();
    }
}
