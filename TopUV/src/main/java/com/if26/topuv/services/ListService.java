package com.if26.topuv.services;

import android.os.AsyncTask;

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
 * Created by Flo on 23/12/2013.
 */
public class ListService extends AsyncTask<String, Void, ArrayList<Uv>> {

    @Override
    protected ArrayList<Uv> doInBackground(String... args) {
        String token = args[0];
        String id_category = args[1];

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        ArrayList<Uv> uvs = new ArrayList<Uv>();

        // Making HTTP request
        try {

            HttpPost httpPost = new HttpPost(WSConstants.UVS.URI);
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();


            nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.TOKEN, token));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.ID_CATEGORY, id_category));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);

            if(jsonObject.has(WSConstants.UVS.UVS))
            {
                JSONArray jsonArray = jsonObject.getJSONArray(WSConstants.UVS.UVS);
                for(int index = 0; index < jsonArray.length(); index++)
                {
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
}
