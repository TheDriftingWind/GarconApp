package charleszhu.qunnipiac.edu.gittest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.acl.Owner;


/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerFragment extends Fragment {


    public OwnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner, container, false);
    }


    public void onClick(View view){

        if(view.getId()==R.id.view_button){
          //  startActivity(new Intent(getContext(), ViewReservationActivity.class));
        }

        if(view.getId()==R.id.add_button){
              startActivity(new Intent(getActivity(), MakeReservationActivity.class));
        }

        else{
              startActivity(new Intent(getActivity(), EditReservationActivity.class));
        }
    }
}
