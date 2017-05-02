package charleszhu.qunnipiac.edu.gittest;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

   private EditText password_et;
    private Button signin_button;
    private TextView owner_greeting;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       String password = Base64.encodeToString(("food1234").getBytes(), Base64.DEFAULT);
        SharedPreferences.Editor editor = getSharedPreferences("myPreferences", Context.MODE_PRIVATE).edit();
        editor.putString("password", password);
        editor.apply();
        setContentView(R.layout.activity_main);
        password_et = (EditText)findViewById(R.id.password_et);
        signin_button = (Button) findViewById(R.id.signin_button);
        owner_greeting = (TextView) findViewById(R.id.owner_greeting);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String enteredPassword = password_et.getText().toString();
                if(!passwordValid(enteredPassword)){
                    owner_greeting.setText("Your password is incorrect. Please try again.");
                }
                else {
                    startUserActivity("Owner"); //Will be used to open the specific fragment
                    password_et.setVisibility(View.INVISIBLE);
                    signin_button.setVisibility(View.INVISIBLE);
                    owner_greeting.setVisibility(View.INVISIBLE);
                    owner_greeting.setText("Welcome owner. Please enter your password.");
                }
            }
        });
    }

    public boolean passwordValid(String password){
        String encryptedPass = getSharedPreferences("myPreferences", Context.MODE_PRIVATE).getString("password", "");
        if(password.equals(new String(Base64.decode(encryptedPass, Base64.DEFAULT)))){
            return true;
        }
      else return false;
    }
    public void onClick(View view){
        if(view.getId()==R.id.owner_button){
           password_et.setVisibility(View.VISIBLE);
            signin_button.setVisibility(View.VISIBLE);
            owner_greeting.setVisibility(View.VISIBLE);
        }

        else if(view.getId() == R.id.customer_button){
            startUserActivity("charleszhu.qunnipiac.edu.gittest.Customer");
        }

    }

   public void startUserActivity(String usertype){
       Intent intent = new Intent(MainActivity.this, UserActivity.class);
       intent.putExtra("UserType", usertype);
       startActivity(intent);


   }


}
