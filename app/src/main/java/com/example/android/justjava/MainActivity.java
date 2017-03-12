package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0; //declare global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        int price = calculatePrice(quantity, 5);
        Log.v("MainActivity", "The price is " + price); //logs a variable to console

        String message = createOrderSummary(price, hasWhippedCream);
        displayMessage(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity    is the number of cups of coffee ordered
     * @param pricePerCup is the price per cups of coffee
     */
    private int calculatePrice(int quantity, int pricePerCup) {
        int price = quantity * pricePerCup;
        return price;
    }

    /**
     * Returns a message with the order number and the relevant price.
     *
     * @param priceOfOrder is the total price of cups of coffee
     * @param hasWhippedCream is the state of the checkbox
     * @return text summary
     */
    private String createOrderSummary(int priceOfOrder, boolean hasWhippedCream) {
        String message = "Name: Captain Kunal \nAdd whipped cream? " + hasWhippedCream + "\nQuantity: " + quantity + " \nTotal: $" + priceOfOrder + " \nThank you!";
        return message;
    }

    // Method to increase amount of coffees via button
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    // Method to decrease amount of coffees via button
    public void decrement(View view) {
        if (quantity > 0) {
            quantity--;
        } else {
            quantity = 0;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param number is the number of coffees
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     *
     * @param message is the total amount of money + thank you message
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}