package charleszhu.qunnipiac.edu.gittest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

/**
 * Created by Charles on 4/26/2017.
 */
@RunWith(AndroidJUnit4.class)
public class ReservationDataSourceTest {

    private ReservationDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(ReservationDatabaseHelper.DB_NAME);
        dataSource = new ReservationDataSource(getTargetContext());
        dataSource.open();
    }

    @After
    public void tearDown() throws Exception {
        dataSource.close();
    }

    @Test
    public void createReservation() throws Exception {
        Customer customer = dataSource.addCustomer("Rick", "137-250-8828");
        dataSource.createReservation(customer,"06/25/2017","6:00PM", 6);
        List<Reservation> reservations = dataSource.getAllReservations();
        assertEquals(reservations.size(), 1);
        assertTrue(reservations.get(0).getCustomer() == (customer.getId())
        && reservations.get(0).getDate().equals("06/25/2017")
                && reservations.get(0).getTime().equals("6:00PM")
                && (reservations.get(0).getPartySize() == 6) );
    }

    @Test
    public void deleteReservation() throws Exception {
        Customer customer = dataSource.addCustomer("Rick","137-250-8828");
        dataSource.createReservation(customer,"06/25/2017","6:00PM", 6);
        List<Reservation> reservations = dataSource.getAllReservations();
        dataSource.deleteReservation(reservations.get(0));
        reservations = dataSource.getAllReservations();
        assertEquals(reservations.size(), 0);
    }

    @Test
    public void changeReservation() throws Exception {
        Customer customer = dataSource.addCustomer("Morty","137-250-8829");
        dataSource.createReservation(customer,"06/25/2017","6:00PM", 6);
        List<Reservation> reservations = dataSource.getAllReservations();
        dataSource.changeReservation(reservations.get(0), "07/1/2017", "9:30AM", 4);
        reservations = dataSource.getAllReservations();
        assertTrue(reservations.get(0).getCustomer() == (customer.getId())
                && reservations.get(0).getDate().equals("07/1/2017")
                && reservations.get(0).getTime().equals("9:30AM")
                && (reservations.get(0).getPartySize() == 4) );
    }

    @Test
    public void addCustomer() throws Exception {
        dataSource.addCustomer("Morty","203-612-0875");
        List<Customer> customers = dataSource.getAllCustomers();
        assertEquals(customers.size(), 1);
        assertTrue(customers.get(0).getName().equals("Morty")
                && customers.get(0).getPhoneNum().equals("203-612-0875"));
    }

    @Test
    public void forgetCustomer() throws Exception {
        dataSource.addCustomer("Morty","420-690-1337");
        List<Customer> customers = dataSource.getAllCustomers();
        dataSource.forgetCustomer(customers.get(0));
        customers = dataSource.getAllCustomers();
        assertEquals(customers.size(), 0);

    }

}