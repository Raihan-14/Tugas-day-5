package com.example.day5;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);


        int totalPrice = getIntent().getIntExtra("totalPrice", 0);
        String membershipLevel = getIntent().getStringExtra("membershipLevel");

        double discountPercentage;
        switch (membershipLevel) {
            case "gold":
                discountPercentage = 0.10; // 10% discount for gold level
                break;
            case "silver":
                discountPercentage = 0.05; // 5% discount for silver level
                break;
            default:
                discountPercentage = 0.02; // 2% discount for normal level
                break;
        }

        double discountAmount = totalPrice * discountPercentage;
        double totalPriceAfterDiscount = totalPrice - discountAmount;

        TextView textViewItemsSelected = findViewById(R.id.textViewItemsSelected);
        textViewItemsSelected.setText(String.format("Total Harga: Rp %d", totalPrice));

        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        textViewTotalPrice.setText(String.format("Total Harga Setelah Diskon: Rp %.0f", totalPriceAfterDiscount));

        TextView textViewDiscount = findViewById(R.id.textViewDiscount);
        textViewDiscount.setText(String.format("Total Diskon: Rp %.0f", discountAmount));
    }
}
