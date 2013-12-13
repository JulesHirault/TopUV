package com.if26.topuv.services;


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
 * Created by Flo on 29/11/2013.
 */
public class LoginService extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... args) {
        String login = args[0];
        String password = args[1];

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        // Making HTTP request
        try {

            HttpPost httpPost = new HttpPost(WSConstants.LOGIN.URI);
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();


            nameValuePairs.add(new BasicNameValuePair(WSConstants.LOGIN.LOGIN, login));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.LOGIN.PASSWORD, password));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(WSConstants.LOGIN.TOKEN);

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
}
