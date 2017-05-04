package charleszhu.qunnipiac.edu.gittest;

import android.app.Activity;
import android.os.Bundle;

/*
 * Created by Carly, Edited by Charles
 * Help Activities gives instructions to the user for how to use the primary functionality of the
 * application.
 */

public class HelpActivity extends Activity {

    //HelpActivity simply displays text instructions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

    }
}
