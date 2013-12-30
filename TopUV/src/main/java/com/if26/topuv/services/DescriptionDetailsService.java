package com.if26.topuv.services;

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
 * Created by Flo on 29/12/2013.
 */
public class DescriptionDetailsService extends AsyncTask<String, Void, Description> {

    @Override
    protected Description doInBackground(String... args) {
        String token = args[0];
        String id_description = args[1];

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        Description description = new Description();
        // Making HTTP request
        try {

            HttpPost httpPost = new HttpPost(WSConstants.DESCRIPTION.URI);
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();


            nameValuePairs.add(new BasicNameValuePair(WSConstants.DESCRIPTION.TOKEN, token));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.UVS.ID_DESCRIPTION, id_description));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);


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

}
