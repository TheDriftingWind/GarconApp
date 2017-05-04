package charleszhu.qunnipiac.edu.gittest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Charles on 5/4/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private static final String STRING_TO_BE_TYPED = "food1234";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
    MainActivity.class);

    @Test
    public void enter_Password() {
        //test selects the owner button in splash screen
        onView(withId(R.id.owner_button)).perform(click());
        //logs into owner user
        onView(withId(R.id.password_et))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.signin_button)).perform(click());

        //opens the editReservation activity, which only the owner has access to.
        onView(withId(R.id.edit_button)).perform(click());

    }

}