package com.example.kotchaphan.android_mysql;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewProductActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputName;
    EditText inputPrice;
    EditText inputDesc;

    //url
    private static String url_create_product = "http://192.168.56.1/android_mysql/create_product.php";

    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        inputName = (EditText)findViewById(R.id.inputName);
        inputPrice = (EditText)findViewById(R.id.inputPrice);
        inputDesc = (EditText)findViewById(R.id.inputDesc);

        Button btnCreateProducts = (Button)findViewById(R.id.btnCreateProduct);
        btnCreateProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call async
                new CreateNewProduct().execute();
            }
        });
    }

    class CreateNewProduct extends AsyncTask<String , String , String>{
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
}
