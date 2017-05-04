package charleszhu.qunnipiac.edu.gittest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;

public class EditReservationActivity extends Activity {
    private ReservationDataSource dataSource;
    private EditText party_et, minute_et, hour_et, day_et, month_et, year_et, ampm_et;
    private Reservation selectedReservation;
    private ListView reservationlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_reservation);

        final View rootView = findViewById(android.R.id.content); //Gets the activity view

        //gets the chosen background color from the settings and sets the activity background
        String background = getIntent().getStringExtra("Background");
        if(background!=null) {
            if (background.equals( "Orange"))
                rootView.setBackgroundColor(Color.parseColor("#ff6102"));
            else if(background.equals("White")) rootView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }

        //EditTexts
        day_et = (EditText) findViewById(R.id.day_et);
        month_et = (EditText) findViewById(R.id.month_et);
        year_et = (EditText) findViewById(R.id.year_et);
        minute_et = (EditText) findViewById(R.id.minute_et);
        ampm_et = (EditText) findViewById(R.id.ampm_et);
        hour_et = (EditText) findViewById(R.id.hour_et);
        party_et = (EditText) findViewById(R.id.partysize_et);

        dataSource = new ReservationDataSource(this);
        dataSource.open();

        //Creates the reservation list and displays reservations from database
        reservationlv = (ListView) findViewById(R.id.reservation_list);
        List<Reservation> allReservations = dataSource.getAllReservations();
        ArrayAdapter<Reservation> adapter = new ArrayAdapter<Reservation>(this, android.R.layout.simple_list_item_1, allReservations);


        reservationlv.setAdapter(adapter);

        //Displays current data from selected database
        reservationlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /***
             * onItemClick:
             * Gets the chosen reservation and inputs the current data into the edit texts
             *
             */
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     selectedReservation = (Reservation) reservationlv.getAdapter().getItem(position);
                     String date[] = selectedReservation.getDate().split("/"); //Separates month/day/year
                     String time[] = selectedReservation.getTime().split(":");//seperates hour and minutes
                     String partySize = selectedReservation.getPartySize(); //gets current party size

                     findViewById(R.id.deletebtn).setVisibility(View.VISIBLE); //Delete button appears

                     //inputs data into edit texts
                     month_et.setText(date[0]);
                     day_et.setText(date[1]);
                     year_et.setText(date[2]);
                     party_et.setText(partySize);

                     hour_et.setText(time[0]);
                     minute_et.setText(time[1].split(" ")[0]);//separates the minutes from AM/PM
                     ampm_et.setText(time[1].split(" ")[1]);

                 }
             }
        );

    }

    public void onClick(View view) {
        if (view.getId() == R.id.reservebtn) { // if the reserve button was clicked
            String time = hour_et.getText().toString() + ":" + minute_et.getText().toString() +" "+ ampm_et.getText().toString(); //combines and formats the time
            String date = month_et.getText().toString() + "/" + day_et.getText().toString() + "/" + year_et.getText().toString();//combines and formats the date
              dataSource.changeReservation(selectedReservation, date, time, party_et.getText().toString());//changes reservation information in database
        } else if (view.getId() == R.id.deletebtn) { //if the delete button is clicked, remove reservation from database
            dataSource.deleteReservation(selectedReservation);
        }
        finish();
    }
}
