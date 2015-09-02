package com.example.kotchaphan.android_mysql;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class AllProductsActivity extends ListActivity {

    private ProgressDialog pDialog;
    //json object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    //url
    private static String url_all_products = "http://localhost/android_mysql/get_all_products.php";

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
                String pid = ((TextView)view.findViewById(R.id.pid)).getText().toString();

                //Start new intent
                Intent intent = new Intent(getApplicationContext() , EditProductActivity.class);
                //send pid to Edit Activity
                intent.putExtra(TAG_PID , pid);

            }
        });
    }
}

class LoadAllProcuts extends AsyncTask<String, String, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
