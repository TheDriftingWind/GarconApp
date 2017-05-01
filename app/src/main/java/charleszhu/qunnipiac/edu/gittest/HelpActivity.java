package charleszhu.qunnipiac.edu.gittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HelpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView description = (TextView)findViewById(R.id.description);
        description.setText("Make a reservation:\n" +
                "View a reservation:\n" +
                "Edit a reservation:\n" +
                "Delete a reservation:\n");
    }
}
