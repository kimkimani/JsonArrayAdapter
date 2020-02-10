package com.example.kim.jsonarrayadapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PrivilegedActionException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

    ArrayList<Actors> actorsList;

    ActorAdapter adapter;
    private String EntityUtils;
    private Object entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actorsList = new ArrayList<Actors>();
        new JSONAsyncTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");

        ListView listview = (ListView)findViewById(R.id.list);
        adapter = new ActorAdapter(getApplicationContext(), R.layout.row, actorsList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {

                Toast.makeText(getApplicationContext(), actorsList.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {

            
            int status = 200;


            {
                return false;
            }


            JSONArray jarray = null;
            try {
                jarray = jarray.getJSONArray("actors");
            } catch (JSONException e) {
                e.printStackTrace ();
            }

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject object = null;
                try {
                    object = jarray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace ();
                }

                Actors actor = new Actors();

                try {
                    actor.setName(object.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
                try {
                    actor.setDescription(object.getString("description"));
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
                try {
                    actor.setDob(object.getString("dob"));
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
                try {
                    actor.setCountry(object.getString("country"));
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
                try {
                    actor.setHeight(object.getString("height"));
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
                try {
                    actor.setSpouse(object.getString("spouse"));
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
                try {
                    actor.setChildren(object.getString("children"));
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
                try {
                    actor.setImage(object.getString("image"));
                } catch (JSONException e) {
                    e.printStackTrace ();
                }

                actorsList.add(actor);
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }






}
