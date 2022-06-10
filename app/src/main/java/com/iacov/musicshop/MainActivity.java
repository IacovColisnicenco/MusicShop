package com.iacov.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();
        createMap();

    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard", 1000.0);

    }

    public void increaseQuantity(View view) {
        quantity += 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText(" " + quantity);

        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity * price);
    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
       // Log.d("userName",order.userName);
        order.goodsName = goodsName;
        //Log.d("goodsName",order.goodsName);
        order.quantity = quantity;
        order.price = price;
        //Log.d("quantity",""+order.quantity);
        order.orderPrice = quantity * price;
        //Log.d("orderPrice",""+order.orderPrice);

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
       orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsNameForIntent", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);

    }

    public void decreaseQuantity(View view) {
        if (quantity > 0) {
            quantity -= 1;
        } else if (quantity < 0)
            quantity = 0;

        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText(" " + quantity);

        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
//        if(goodsName.equals("guitar")) {
//            goodsImageView.setImageResource(R.drawable.guitar);
//        } else if(goodsName.equals("drums")){
//            goodsImageView.setImageResource(R.drawable.drums);
//        }
//        else if(goodsName.equals("keyboard")){
//            goodsImageView.setImageResource(R.drawable.keyboard);
//        }
        switch (goodsName){
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}