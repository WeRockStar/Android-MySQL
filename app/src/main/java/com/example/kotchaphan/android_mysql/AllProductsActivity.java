package com.example.kotchaphan.android_mysql;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllProductsActivity extends ListActivity {

    private ProgressDialog pDialog;
    //json object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    //url
    private static String url_all_products = "http://192.168.56.1/android_mysql/get_all_products.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";

    //producs json arrsy
    JSONArray product = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        productsList = new ArrayList<HashMap<String, String>>();
        new LoadAllProcuts().execute();

        //get list view
        ListView lv = getListView();

        //on select
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get pid from edit text
                String pid = ((TextView) view.findViewById(R.id.pid)).getText().toString();

                //Start new intent
                Intent intent = new Intent(getApplicationContext(), EditProductActivity.class);
                //send pid to Edit Activity
                intent.putExtra(TAG_PID, pid);
                //response back value
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }

    class LoadAllProcuts extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllProductsActivity.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //getting JSON string from url
            JSONObject json = jParser.makeHttpRequest(url_all_products, "POST", params);

            //check json response
            Log.d("all products", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);

                // 1 == success
                if (success == 1) {
                    //getting array of products
                    product = json.getJSONArray(TAG_PRODUCTS);

                    for (int i = 0; i < product.length(); i++) {
                        JSONObject c = product.getJSONObject(i);

                        //store json item in value
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);

                        //create new HasMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        //adding HashList to ArrayList
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);

                        productsList.add(map);
                    }
                } else {
                    Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            pDialog.dismiss();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //progress dialog cancel
            pDialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(AllProductsActivity.this,
                            productsList, R.layout.list_item, new String[]{TAG_PID, TAG_NAME},
                            new int[]{R.id.pid, R.id.name});

                    setListAdapter(adapter);
                }
            });
        }
    }

}

