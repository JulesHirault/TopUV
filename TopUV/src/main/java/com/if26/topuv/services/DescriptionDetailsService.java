package com.if26.topuv.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.if26.topuv.constants.WSConstants;
import com.if26.topuv.models.Description;

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
 * Classe qui va communiquer avec le service web permettant la récupération de la description
 * et du détail d'une Uv dans la DB
 */
public class DescriptionDetailsService extends AsyncTask<String, Void, Description> {

    Context context;
    ProgressDialog progDialog;

    public DescriptionDetailsService(Context context){
        this.context = context;
    }

    @Override
    protected Description doInBackground(String... args) {
        String token = args[0];
        String id_description = args[1];

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        Description description = new Description();
        // Requête HTTP
        try {

            HttpPost httpPost = new HttpPost(WSConstants.DESCRIPTION.URI);

            DefaultHttpClient httpClient = new DefaultHttpClient();


            nameValuePairs.add(new BasicNameValuePair(WSConstants.DESCRIPTION.TOKEN, token));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.ID_DESCRIPTION, id_description));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            // Réponde en JSON
            JSONObject jsonObject = new JSONObject(response);

            // création d'un objet description à partir du JSON
            description.id = Integer.parseInt(jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.ID));
            description.curricula = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.CURRICULA);
            description.objectives = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.OBJECTIVES);
            description.type_uv = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.TYPE_UV);
            description.credit = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.CREDITS);
            description.availibility = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.AVAILABALITY);
            description.lectures = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.LECTURES);
            description.tutorials = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.TUTORIALS);
            description.practicals = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.PRACTICALS);
            description.personnal = jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.PERSONNAL);
            description.avg_mark = Integer.parseInt(jsonObject.getJSONObject(WSConstants.DESCRIPTION.DESCRIPTION).getString(WSConstants.DESCRIPTION.AVG_MARK));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return description;
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
    protected void onPostExecute(Description unused) {
        super.onPostExecute(unused);
        progDialog.dismiss();
    }
}
