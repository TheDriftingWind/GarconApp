package charleszhu.qunnipiac.edu.gittest;

import android.content.Intent;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;

public class UserActivity extends Activity implements OwnerFragment.OwnerFragmentListener , CustomerFragment.CustomerFragmentListener{

    private String userType;
    private OwnerFragment fragmentOwner;
    private CustomerFragment fragmentCustomer;
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent i = getIntent();
        userType = i.getStringExtra("UserType");

        }

    @Override
    protected void onStart() {
        super.onStart();

        //if i.getString == "customer" put customerfragment in fragment container
        //if i.getString == "ownerfragment" put ownerfragment in the fragment container
        View fragmentContainer = findViewById(R.id.fragmentContainer);
        if (fragmentContainer != null) {
            fragmentOwner = new OwnerFragment();
            fragmentCustomer = new CustomerFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (userType.equals("Owner")) {
                ft.replace(R.id.fragmentContainer, fragmentOwner);
            } else if (userType.equals("Customer")) {
                ft.replace(R.id.fragmentContainer, fragmentCustomer);
            }
            /*
            Exclude addToBackStack. Want to go straight back to MainActivity if
            backbutton is pressed
             */
//            ft.addToBackStack(null); -- Do not need to add to backstack.
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();


        }
    }

    public void onOwnerClick(View view){

        if(view.getId()==R.id.view_button){
            startActivity(new Intent(UserActivity.this,ViewReservationActivity.class));
        }

        if(view.getId()==R.id.add_button){
            startActivity(new Intent(UserActivity.this, MakeReservationActivity.class));
        }

        if (view.getId()==R.id.edit_button) {
            startActivity(new Intent(UserActivity.this, EditReservationActivity.class));
        }
    }

    public void onCustomerClick(View view){

        if(view.getId()==R.id.view_button){
            startActivity(new Intent(UserActivity.this,ViewReservationActivity.class));
        }

        if(view.getId()==R.id.add_button){
            startActivity(new Intent(UserActivity.this, MakeReservationActivity.class));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        //prepare the share action for the share item in the action bar
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        return super.onCreateOptionsMenu(menu);
    }
}