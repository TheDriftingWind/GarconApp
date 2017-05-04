package charleszhu.qunnipiac.edu.gittest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MakeReservationActivity extends Activity {
    private ReservationDataSource dataSource;
    private EditText name_et, phone_et, party_et, minute_et, hour_et, day_et, month_et, year_et, ampm_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        final View rootView = findViewById(android.R.id.content); //gets the activity view

        //gets the chosen background color from the settings and sets the activity background
        String background = getIntent().getStringExtra("Background");
        if(background!=null) {
            if (background.equals( "Orange"))
                rootView.setBackgroundColor(Color.parseColor("#ff6102"));
            else if(background.equals("White")) rootView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        //edit texts
        day_et = (EditText)findViewById(R.id.day_et);
        month_et = (EditText)findViewById(R.id.month_et);
        year_et = (EditText)findViewById(R.id.year_et);
        minute_et = (EditText)findViewById(R.id.minute_et);
        ampm_et = (EditText)findViewById(R.id.ampm_et);
        hour_et = (EditText)findViewById(R.id.hour_et);
        name_et = (EditText)findViewById(R.id.name_et);
        phone_et = (EditText)findViewById(R.id.phone_et);
        party_et = (EditText)findViewById(R.id.partysize_et);

        dataSource = new ReservationDataSource(this);
        dataSource.open();

        //If the customer has already entered their information, the information will automatically be entered for the customer
        if(dataSource.getAllCustomers().size() > 0){
           Customer customer= dataSource.getAllCustomers().get(0);
            name_et.setClickable(false);
            phone_et.setClickable(false);
            name_et.setText(customer.getName());
            phone_et.setText(customer.getPhoneNum());
        }

    }

    /***
     * onClick
     * when the reserve button is clicked, the reservation is added to the database
     */
    public void onClick(View view){
        Customer customer;
        if(dataSource.getAllCustomers().size()<1){ // If there is no customer information in the database, add new information
           customer =  dataSource.addCustomer(name_et.getText().toString(), phone_et.getText().toString());
        }
        else customer = dataSource.getAllCustomers().get(0);
      String time = hour_et.getText().toString()+":"+minute_et.getText().toString() + " " + ampm_et.getText().toString(); //combines and formats time
       String date =  month_et.getText().toString()+"/"+day_et.getText().toString()+"/"+year_et.getText().toString();//combines and formats date
        dataSource.createReservation(customer, date, time, party_et.getText().toString()); //adds a new reservation to the database with entered values
        finish();
    }

}
