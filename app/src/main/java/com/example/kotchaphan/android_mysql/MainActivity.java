package com.example.kotchaphan.android_mysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnViewProducts;
    private Button btnCreateProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);
        btnViewProducts = (Button) findViewById(R.id.btnViewProducts);

        btnViewProducts.setOnClickListener(this);
        btnCreateProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnViewProducts:
                Intent intent = new Intent(getApplicationContext() , AllProductsAci)
                break;
            case R.id.btnCreateProduct:
                break;
        }
    }
}
