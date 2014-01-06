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
 * Classe qui va communiquer avec le service web permettant l'ajout de commentaire dans la DB
 */
public class AddCommentService extends AsyncTask<String, Void, String> {

    Context context;
    ProgressDialog progDialog;

    public AddCommentService(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... args) {
        String token = args[0];
        String mark = args[1];
        String id_student = args[2];
        String id_uv = args[3];
        String text = args[4];
        String id_description = args[5];

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        // Requête HTTP
        try {

            HttpPost httpPost = new HttpPost(WSConstants.COMMENT.URI2);

            DefaultHttpClient httpClient = new DefaultHttpClient();


            nameValuePairs.add(new BasicNameValuePair(WSConstants.COMMENT.TOKEN, token));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.COMMENT.TEXT, text));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.COMMENT.ID_STUDENT, id_student));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.COMMENT.ID_UV, id_uv));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.COMMENT.MARK, mark));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.ID_DESCRIPTION, id_description));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            // Réponse sous JSON
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("result");

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
    protected void onPostExecute(String unused) {
        super.onPostExecute(unused);
        progDialog.dismiss();
    }
}
