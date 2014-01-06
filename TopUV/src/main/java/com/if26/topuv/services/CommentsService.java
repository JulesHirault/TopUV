package com.if26.topuv.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.if26.topuv.constants.WSConstants;
import com.if26.topuv.models.Comment;
import com.if26.topuv.models.Student;

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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Classe qui va communiquer avec le service web permettant la récupération de commentaires dans la DB
 */
public class CommentsService extends AsyncTask<String, Void, ArrayList<Comment>> {
    Context context;
    ProgressDialog progDialog;

    public CommentsService(Context context){
        this.context = context;
    }

    @Override
    protected ArrayList<Comment> doInBackground(String... args) {
        String token = args[0];
        String id_uv = args[1];

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        ArrayList<Comment> result = new ArrayList<Comment>();

        // Requête HTTP
        try {

            HttpPost httpPost = new HttpPost(WSConstants.COMMENT.URI);

            DefaultHttpClient httpClient = new DefaultHttpClient();


            nameValuePairs.add(new BasicNameValuePair(WSConstants.COMMENT.TOKEN, token));
            nameValuePairs.add(new BasicNameValuePair(WSConstants.COMMENT.ID_UV, id_uv));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost, new BasicHttpContext());
            String response = EntityUtils.toString(httpResponse.getEntity());

            // récupère l'objet JSON
            JSONObject jsonObject = new JSONObject(response);

            if(jsonObject.has(WSConstants.COMMENT.COMMENT))
            {
                JSONArray jsonArray = jsonObject.getJSONArray(WSConstants.COMMENT.COMMENT);
                for(int index = 0; index < jsonArray.length(); index++)
                {
                    // création d'objets commentaires pour chaque commentaire récupéré
                    Comment comment = new Comment();
                    comment.id = Integer.parseInt(jsonArray.getJSONObject(index).getString(WSConstants.COMMENT.ID));
                    comment.text = jsonArray.getJSONObject(index).getString(WSConstants.COMMENT.TEXT);
                    comment.id_student = Integer.parseInt(jsonArray.getJSONObject(index).getString(WSConstants.COMMENT.ID_STUDENT));
                    comment.id_uv = jsonArray.getJSONObject(index).getString(WSConstants.COMMENT.ID_UV);
                    comment.mark = Integer.parseInt(jsonArray.getJSONObject(index).getString(WSConstants.COMMENT.MARK));


                    Student student = new Student();
                    JSONObject userObject = jsonArray.getJSONObject(index).getJSONObject(WSConstants.STUDENT.STUDENT);
                    student.id = Integer.parseInt(userObject.getString(WSConstants.STUDENT.ID));
                    student.name = userObject.getString(WSConstants.STUDENT.NAME);
                    student.surname = userObject.getString(WSConstants.STUDENT.SURNAME);

                    String urlPicture = WSConstants.STUDENT.PICTURE_URI + userObject.getString(WSConstants.STUDENT.PICTURE);
                    student.picture = this.getImage(urlPicture);

                    comment.student = student;
                    result.add(comment);
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

        return result;
    }

    /**
     * Récupère le bitmap d'une image à partir d'une URL
     * @param url l'url de l'image
     * @return le bitmap de l'image
     * @throws IOException
     */
    public Bitmap getImage(String url) throws IOException {
        final URLConnection conn = new URL(url).openConnection();
        conn.connect();
        final InputStream is = conn.getInputStream();

        final BufferedInputStream bis = new BufferedInputStream(is, 100000);

        final Bitmap bm = BitmapFactory.decodeStream(bis);
        bis.close();
        is.close();

        return bm;
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
