package charleszhu.qunnipiac.edu.gittest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HelpActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView description = (TextView)findViewById(R.id.description);
        description.setText("Make a reservation:\n To make a reservation navigate to " +
                "the Make Reservation screen and fill out the information requested by the" +
                "text field \n" +
                "View a reservation:\n To view a reservation, navigate to the View Reservation" +
                "screen. Tap on a reservation in the list to view details of that reservation\n" +
                "Edit a reservation:\n To edit a reservation, navigate to the Edit Reservation" +
                "screen and tap on the reservation in the list that you'd like to change." +
                "Enter the new information and press the update button\n" +
                "Delete a reservation:\n To delete a reservation, navigate to the Edit Reservation" +
                "screen and tap on the reservation in the list that you'd like to delete." +
                "Simply press the 'Delete' button to remove. This action cannot be un-done\n");
    }
}
