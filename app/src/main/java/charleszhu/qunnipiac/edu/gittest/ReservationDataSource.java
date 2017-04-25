package charleszhu.qunnipiac.edu.gittest;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Charles on 4/25/2017.
 */

//TODO: add both reservation table and customer table implementation in this 1 class
public class ReservationDataSource {

    private SQLiteDatabase db;
    private ReservationDatabaseHelper dbHelper;
    private String[] reservationColumns = {dbHelper.RESERVATION_KEY, dbHelper.RESERVATION_CUSTOMER,
            dbHelper.RESERVATION_DATE, dbHelper.RESERVATION_TIME, dbHelper.RESERVATION_PARTYSIZE};
    private String[] customerColumns = {dbHelper.CUSTOMER_ID, dbHelper.CUSTOMER_NAME,
            dbHelper.CUSTOMER_PHONE};

    //RESERVATION TABLE
    public void createReservation(){
        //TODO: Make reservation
        //test
    }

    public void deleteReservation(){
        //TODO: Delete reservation
    }

    public void changeReservation( ){
        //TODO: Change reservation
    }

    public void getALlReservations(){
        //TODO: Get all reservations
    }

    public void CursorToReservation(){
        //TODO: Convert Cursor to Reservation
    }

    //CUSTOMER TABLE
    public void addCustomer(){
        //TODO: Add customer to table
    }

    public void forgetCustomer(){
        //TODO: Remove customer from table
    }




}
