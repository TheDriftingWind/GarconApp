package charleszhu.qunnipiac.edu.gittest;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/*
 * A simple {@link Fragment} subclass.
 * Created by Carly
 * Detail fragment displays specific information about the reservation that is selected in the view
 * reservation activity.
 */
public class ReservationDetailFragment extends Fragment {

    private String name, phone, date, time, partySize;
    public ReservationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation_detail, container, false);

    }

    /**
     * setDetails:
     * Sets current values from the selected reservation
     */
    public void setDetails(String name, String phone, String date, String time, String partySize){
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.partySize = partySize;

    }

    /***
     * onViewCreated:
     * Sets the textviews to display the values of the clicked reservation
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.name_tv)).setText("Name: "+name);
        ((TextView) view.findViewById(R.id.phone_tv)).setText("Phone: " +phone);
        ((TextView) view.findViewById(R.id.date_tv)).setText("Date: "+date);
        ((TextView) view.findViewById(R.id.time_tv)).setText("Time: "+time);
        ((TextView) view.findViewById(R.id.partysize_tv)).setText("Party Size: "+partySize);
    }
}
