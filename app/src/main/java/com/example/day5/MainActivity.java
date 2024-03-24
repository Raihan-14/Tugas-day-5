package com.example.day5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxItem1, checkBoxItem2, checkBoxItem3;
    private EditText editTextQuantity1, editTextQuantity2, editTextQuantity3;
    private RadioGroup radioGroupMembership;
    private Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxItem1 = findViewById(R.id.checkBoxItem1);
        checkBoxItem2 = findViewById(R.id.checkBoxItem2);
        checkBoxItem3 = findViewById(R.id.checkBoxItem3);
        editTextQuantity1 = findViewById(R.id.editTextQuantity1);
        editTextQuantity2 = findViewById(R.id.editTextQuantity2);
        editTextQuantity3 = findViewById(R.id.editTextQuantity3);
        radioGroupMembership = findViewById(R.id.radioGroupMembership);
        btnBuy = findViewById(R.id.btnBuy);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalPrice = 0;

                if (checkBoxItem1.isChecked()) {
                    int quantity1 = Integer.parseInt(editTextQuantity1.getText().toString());
                    totalPrice += calculateItemPrice("PCO", 2730551, quantity1);
                }

                if (checkBoxItem2.isChecked()) {
                    int quantity2 = Integer.parseInt(editTextQuantity2.getText().toString());
                    totalPrice += calculateItemPrice("O17", 2500999, quantity2);
                }

                if (checkBoxItem3.isChecked()) {
                    int quantity3 = Integer.parseInt(editTextQuantity3.getText().toString());
                    totalPrice += calculateItemPrice("OAS", 1989123, quantity3);
                }

                String membershipLevel = "";
                int checkedRadioButtonId = radioGroupMembership.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(checkedRadioButtonId);
                    membershipLevel = selectedRadioButton.getText().toString().toLowerCase();
                }

                if (totalPrice > 0 && !membershipLevel.isEmpty()) {
                    showReceipt(totalPrice, membershipLevel);
                }
            }
        });
    }

    private int calculateItemPrice(String itemName, int itemPrice, int quantity) {
        return itemPrice * quantity;
    }

    private void showReceipt(int totalPrice, String membershipLevel) {
        Intent intent = new Intent(this, ReceiptActivity.class);
        intent.putExtra("totalPrice", totalPrice);
        intent.putExtra("membershipLevel", membershipLevel);
        startActivity(intent);
    }
}
