package charleszhu.qunnipiac.edu.gittest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
    public Customer addCustomer(String name, String phone){
        ContentValues values = new ContentValues();
        values.put(dbHelper.CUSTOMER_NAME, name);
        values.put(dbHelper.CUSTOMER_PHONE, phone);
        long insertId = db.insert(dbHelper.CUSTOMER_TABLE, null, values);
       Cursor cursor = db.query(dbHelper.CUSTOMER_TABLE, customerColumns, dbHelper.CUSTOMER_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Customer newCustomer = cursorToCustomer(cursor);
        cursor.close();
        return newCustomer;
    }


   public List<Customer> getAllCustomers(){
       List<Customer> customers = new ArrayList<Customer>();
       Cursor cursor = db.query(dbHelper.CUSTOMER_TABLE, customerColumns, null, null, null, null, null);

       while(!cursor.isAfterLast()){
           Customer customer = cursorToCustomer(cursor);
           customers.add(customer);
           cursor.moveToNext();
       }
       cursor.close();
       return customers;
    }

    public Customer cursorToCustomer(Cursor cursor) {
        Customer customer = new Customer();
        customer.setId(cursor.getLong(0));
        customer.setName(cursor.getString(1));
        customer.setPhone(cursor.getString(2));
        return customer;
    }
    public void forgetCustomer(Customer customer){
        long id = customer.getId();
        db.delete(dbHelper.CUSTOMER_TABLE, dbHelper.CUSTOMER_NAME+ " = " + id, null);
    }




}
