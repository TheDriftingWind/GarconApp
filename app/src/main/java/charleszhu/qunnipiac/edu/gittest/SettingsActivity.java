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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        colorrg = (RadioGroup)findViewById(R.id.colorrg);
        colorrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(((RadioButton)findViewById(checkedId)).getText()=="Orange")
                    chosenColor = "Orange";

                else chosenColor = "White";
            }
        });
        dataSource = new ReservationDataSource(getBaseContext());
        dataSource.open();
    }

    public void onClick(View view){
        EditText name_et = (EditText)findViewById(R.id.name_et);
        EditText phone_et = (EditText)findViewById(R.id.phone_et);
        if(dataSource.getAllCustomers().size()>0)
            dataSource.forgetCustomer( dataSource.getAllCustomers().get(0));
        dataSource.addCustomer(name_et.getText().toString(), phone_et.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("Background", chosenColor);
        setResult(2, intent);
        finish();
    }



}
