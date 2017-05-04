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

        //encrypts and stores the owner password
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
            public void onClick(View v) { //when the user clicks sign in
              String enteredPassword = password_et.getText().toString(); // gets the inputed password
                if(!passwordValid(enteredPassword)){
                    owner_greeting.setText("Your password is incorrect. Please try again."); //displays if the password does not match
                }
                else {
                    //if correct, the owner fragment opens
                    startUserActivity("Owner"); //Will be used to open the specific fragment
                    password_et.setVisibility(View.INVISIBLE);
                    signin_button.setVisibility(View.INVISIBLE);
                    owner_greeting.setVisibility(View.INVISIBLE);
                    owner_greeting.setText("Welcome owner. Please enter your password.");
                }
            }
        });
    }

    /***
     * passwordValid:
     * Returns if the user has entered the right password
     */
    public boolean passwordValid(String password){
        String encryptedPass = getSharedPreferences("myPreferences", Context.MODE_PRIVATE).getString("password", ""); //gets stored password from shared preferences
        if(password.equals(new String(Base64.decode(encryptedPass, Base64.DEFAULT)))){ //decrypts the password and compares it to the entered password
            return true;
        }
      else return false;
    }


    /**
     * onClick
     * Opens either the owner fragment or the customer fragment
     */
    public void onClick(View view){
        //if the owner button is clicked, the password prompt appears
        if(view.getId()==R.id.owner_button){
           password_et.setVisibility(View.VISIBLE);
            signin_button.setVisibility(View.VISIBLE);
            owner_greeting.setVisibility(View.VISIBLE);
        }

        //if the customer button is clicked, the user activity opens with only customer privileges
        else if(view.getId() == R.id.customer_button){
            startUserActivity("Customer");
        }

    }

    /****
     * startUserActivity:
     * Sends the type of user to the user activity and starts the activity
     */
   public void startUserActivity(String usertype){
       Intent intent = new Intent(MainActivity.this, UserActivity.class);
       intent.putExtra("UserType", usertype);
       startActivity(intent);


   }


}
