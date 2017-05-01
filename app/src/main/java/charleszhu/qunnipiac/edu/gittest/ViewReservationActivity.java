package charleszhu.qunnipiac.edu.gittest;

import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.database.DataSetObserver;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ViewReservationActivity extends Activity {

    private ReservationDataSource dataSource;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        list = (ListView) findViewById(R.id.reservation_list);
          dataSource = new ReservationDataSource(this);
        dataSource.open();
        List<Reservation> allReservations = dataSource.getALlReservations();


        ArrayAdapter<Reservation> adapter = new ArrayAdapter<Reservation>(this, android.R.layout.simple_list_item_1, allReservations);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reservation reservation = (Reservation) list.getAdapter().getItem(position);
                String customer = reservation.getCustomer();
                System.out.println(customer);
//open fragment
                View fragmentContainer = findViewById(R.id.fragmentContainer);

                if (fragmentContainer != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ReservationDetailFragment detailFragment = new ReservationDetailFragment();
                    ft.replace(R.id.fragmentContainer, detailFragment);
                    detailFragment.setDetails(customer, "", reservation.getDate(), reservation.getTime(), Long.toString(reservation.getPartySize()));

                    ft.addToBackStack(null);
                    ft.commit();

                }
            }
        });

    }
}
