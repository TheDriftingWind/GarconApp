package charleszhu.qunnipiac.edu.gittest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerFragment extends Fragment {


    static interface OwnerFragmentListener{
        void onOwnerClick(View view);
    }
private OwnerFragmentListener listener;
    public OwnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //attach listener so that the activity can implement onClick methods
        this.listener = (OwnerFragmentListener)activity;
    }

    public void onOwnerClick(View view){

        if(view.getId()==R.id.view_button){
            //starts View Reservation activity
            startActivity(new Intent(getActivity(),ViewReservationActivity.class));
        }

        if(view.getId()==R.id.add_button){
            //starts Make Reservation activity
              startActivity(new Intent(getActivity(), MakeReservationActivity.class));
        }

        else{
            //starts Edit Reservation activity
              startActivity(new Intent(getActivity(), EditReservationActivity.class));
        }
    }
}
