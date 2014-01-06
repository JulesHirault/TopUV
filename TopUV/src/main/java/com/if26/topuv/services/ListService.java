package com.if26.topuv.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.if26.topuv.constants.IntentConstants;
import com.if26.topuv.constants.WSConstants;
import com.if26.topuv.models.Category;
import com.if26.topuv.models.Uv;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Classe qui va communiquer avec le service web permettant la récuprération des listes d'Uv
 */
public class ListService extends AsyncTask<String, Void, ArrayList<Uv>> {
    Context context;
    ProgressDialog progDialog;

    public ListService(Context context){
        this.context = context;
    }

    @Override
    protected ArrayList<Uv> doInBackground(String... args) {
        String token = args[0];
        String id_category = args[1];
        String id_uv = args[2];

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        ArrayList<Uv> uvs = new ArrayList<Uv>();

        // Requête HTTP
        try {

            HttpPost httpPost;
            DefaultHttpClient httpClient;

            // Si recherche particulière (pas la même URL appelée)
            if(id_category.equals("search")){
                httpPost = new HttpPost(WSConstants.UVS.URI_ONE);

                httpClient = new DefaultHttpClient();

                nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.TOKEN, token));
                nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.ID, id_category));
                nameValuePairs.add(new BasicNameValuePair(IntentConstants.ID_UV, id_uv));
            } else {

                // si recherche normale (par catégorie)
                httpPost = new HttpPost(WSConstants.UVS.URI);

                httpClient = new DefaultHttpClient();

                nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.TOKEN, token));
                nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.ID_CATEGORY, id_category));
            }


            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            // réponse en JSON
            JSONObject jsonObject = new JSONObject(response);

            if(jsonObject.has(WSConstants.UVS.UVS))
            {
                JSONArray jsonArray = jsonObject.getJSONArray(WSConstants.UVS.UVS);
                for(int index = 0; index < jsonArray.length(); index++)
                {
                    // création d'objets Uvdans une liste d'Uvs à partir du JSON récupéré
                    Uv uv = new Uv();
                    uv.id = jsonArray.getJSONObject(index).getString(WSConstants.UVS.ID);
                    uv.label = jsonArray.getJSONObject(index).getString(WSConstants.UVS.LABEL);
                    uv.id_description = jsonArray.getJSONObject(index).getString(WSConstants.UVS.ID_DESCRIPTION);
                    uv.id_category = jsonArray.getJSONObject(index).getString(WSConstants.UVS.ID_CATEGORY);


                    Category category = new Category();
                    JSONObject userObject = jsonArray.getJSONObject(index).getJSONObject(WSConstants.CATEGORY.CATEGORY);
                    category.id = userObject.getInt(WSConstants.CATEGORY.ID);
                    category.label = userObject.getString(WSConstants.CATEGORY.LABEL);

                    uv.category = category;

                    uvs.add(uv);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uvs;
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
    protected void onPostExecute(ArrayList unused) {
        super.onPostExecute(unused);
        progDialog.dismiss();
    }
}
