package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1; //declare global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String nameEditTextContent = nameEditText.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(quantity, 5, hasWhippedCream, hasChocolate);
        Log.v("MainActivity", "The price is " + price); //logs a variable to console

        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, nameEditTextContent);
        displayMessage(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity        is the number of cups of coffee ordered
     * @param pricePerCup     is the price per cups of coffee
     * @param hasWhippedCream is the state of the whipped cream checkbox
     * @param hasChocolate    is the state of chocolate checkbox
     */
    private int calculatePrice(int quantity, int pricePerCup, boolean hasWhippedCream, boolean hasChocolate) {
        int price = quantity * pricePerCup;

        if (hasWhippedCream && hasChocolate) {
            price += 3;
        } else if (hasWhippedCream) {
            price += 1;
        } else if (hasChocolate) {
            price += 2;
        } else {
            return price;
        }
        return price;
    }

    /**
     * Returns a message with the order number and the relevant price.
     *
     * @param priceOfOrder    is the total price of cups of coffee
     * @param hasWhippedCream is the state of the whipped cream checkbox
     * @param hasChocolate    is the state of chocolate checkbox
     * @return text summary
     * @nameEditTextContent is the value of name edit text
     */
    private String createOrderSummary(int priceOfOrder, boolean hasWhippedCream, boolean hasChocolate, String nameEditTextContent) {
        String message = "Name: " + nameEditTextContent;
        message += "\nAdd whipped cream? " + hasWhippedCream;
        message += "\nAdd chocolate? " + hasChocolate;
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + priceOfOrder + " \nThank you!";
        return message;
    }

    // Method to increase amount of coffees via button
    public void increment(View view) {
        if (quantity < 100) {
            quantity++;
            displayQuantity(quantity);
        } else {
            toast("You cannot have more than 100 coffees!");
        }
    }

    // Method to decrease amount of coffees via button
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity(quantity);
        } else {
            toast("You cannot have less than 1 coffee!");
        }
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

    private void toast(String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}