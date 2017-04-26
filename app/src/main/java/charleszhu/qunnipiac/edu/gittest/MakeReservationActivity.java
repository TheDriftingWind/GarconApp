package charleszhu.qunnipiac.edu.gittest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class MakeReservationActivity extends Activity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText name_et, phone_et, party_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        timePicker = (TimePicker)findViewById(R.id.timePicker2);
        name_et = (EditText)findViewById(R.id.name_et);
    }
}
