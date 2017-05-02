package charleszhu.qunnipiac.edu.gittest;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends Activity {

    ReservationDataSource dataSource;
    RadioGroup colorrg;
    String chosenColor;
    EditText name_et, phone_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
       name_et = (EditText)findViewById(R.id.name_et);
        phone_et = (EditText)findViewById(R.id.phone_et);

        dataSource = new ReservationDataSource(getBaseContext());
        dataSource.open();
        if(dataSource.getAllCustomers().size()>0) {
            Customer customer = dataSource.getAllCustomers().get(0);
            name_et.setText(customer.getName());
            phone_et.setText(customer.getPhoneNum());
        }

        colorrg = (RadioGroup)findViewById(R.id.colorrg);
        colorrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(((RadioButton)group.findViewById(checkedId)).getText().toString().equals("Orange"))
                    chosenColor = "Orange";

                else chosenColor = "White";
            }
        });

    }

    public void onClick(View view){

        if(dataSource.getAllCustomers().size()>0)
            dataSource.forgetCustomer( dataSource.getAllCustomers().get(0));
        dataSource.addCustomer(name_et.getText().toString(), phone_et.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("Background", chosenColor);
        setResult(0, intent);
        finish();
    }



}
