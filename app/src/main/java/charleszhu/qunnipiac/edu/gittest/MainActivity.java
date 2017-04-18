package charleszhu.qunnipiac.edu.gittest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//Backup 1 - 4/18/2017
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        if(view.getId()==R.id.owner_button){
            intent.putExtra("UserType", "Owner"); //Will be used to open the specific fragment
        }

        else if(view.getId() == R.id.customer_button){
            intent.putExtra("UserType", "Customer");
        }

        startActivity(intent);
    }
}
