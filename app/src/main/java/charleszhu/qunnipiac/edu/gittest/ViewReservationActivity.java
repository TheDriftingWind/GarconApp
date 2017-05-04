package charleszhu.qunnipiac.edu.gittest;

import android.app.FragmentTransaction;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;


public class ViewReservationActivity extends Activity {

    private ReservationDataSource dataSource;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        final View rootView = findViewById(android.R.id.content);

        //gets the chosen background color from the settings and sets the activity background
        String background = getIntent().getStringExtra("Background");
        if(background!=null) {
            if (background.equals("Orange"))
                rootView.setBackgroundColor(Color.parseColor("#ff6102"));
            else if(background.equals("White")) rootView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }

        //Creates the reservation list and displays reservations from database
        list = (ListView) findViewById(R.id.reservation_list);
          dataSource = new ReservationDataSource(this);
        dataSource.open();
        List<Reservation> allReservations = dataSource.getAllReservations();


        ArrayAdapter<Reservation> adapter = new ArrayAdapter<Reservation>(this, android.R.layout.simple_list_item_1, allReservations);
        list.setAdapter(adapter);

        //Displays the details of a reservation when selected
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reservation reservation = (Reservation) list.getAdapter().getItem(position); //gets the selected reservation
                Customer customer = dataSource.getAllCustomers().get(0); // gets the current customer

                View fragmentContainer = findViewById(R.id.fragmentContainer);

                //sets the selected reservation details and replaces the fragment
                if (fragmentContainer != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ReservationDetailFragment detailFragment = new ReservationDetailFragment();
                    ft.replace(R.id.fragmentContainer, detailFragment);
                    detailFragment.setDetails(customer.getName(), customer.getPhoneNum(), reservation.getDate(), reservation.getTime(), reservation.getPartySize());

                    ft.addToBackStack(null);
                    ft.commit();

                }
            }
        });

    }
}
