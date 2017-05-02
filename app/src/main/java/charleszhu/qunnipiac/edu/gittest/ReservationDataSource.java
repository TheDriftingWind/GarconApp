package charleszhu.qunnipiac.edu.gittest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.service.restrictions.RestrictionsReceiver;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 4/25/2017.
 */

public class ReservationDataSource {

    private SQLiteDatabase db;
    private ReservationDatabaseHelper dbHelper;
    private String[] reservationColumns = {ReservationDatabaseHelper.RESERVATION_KEY,
            ReservationDatabaseHelper.RESERVATION_DATE,
            ReservationDatabaseHelper.RESERVATION_TIME,
            ReservationDatabaseHelper.RESERVATION_PARTYSIZE,
            ReservationDatabaseHelper.RESERVATION_CUSTOMER_ID};
    private String[] customerColumns = {ReservationDatabaseHelper.CUSTOMER_ID,
            ReservationDatabaseHelper.CUSTOMER_NAME, ReservationDatabaseHelper.CUSTOMER_PHONE};

    public ReservationDataSource(Context context){
        dbHelper = new ReservationDatabaseHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }
    //RESERVATION TABLE
    public Reservation createReservation(Customer customer, String date, String time, String partySize){
        //create values to insert into the table
        ContentValues values = new ContentValues();
        values.put(ReservationDatabaseHelper.RESERVATION_CUSTOMER_ID, customer.getId());
        values.put(ReservationDatabaseHelper.RESERVATION_DATE, date);
        values.put(ReservationDatabaseHelper.RESERVATION_TIME, time);
        values.put(ReservationDatabaseHelper.RESERVATION_PARTYSIZE, partySize);
        //insert values in respective columns
        long insertId = db.insert(ReservationDatabaseHelper.RESERVATION_TABLE, null, values);
        Cursor cursor = db.query(ReservationDatabaseHelper.RESERVATION_TABLE,
                reservationColumns, ReservationDatabaseHelper.RESERVATION_KEY +
                " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        //convert cursor to reservation data type to return information
        Reservation newReservation = cursorToReservation(cursor);
        cursor.close();
        return newReservation;
    }

    public void deleteReservation(Reservation reservation){
        //get id of reservation to find
        long id = reservation.getId();
        //find and delete where the id is found
        db.delete(ReservationDatabaseHelper.RESERVATION_TABLE,
                ReservationDatabaseHelper.RESERVATION_KEY + " = " + id, null);
    }

    public Reservation changeReservation(Reservation reservation, String date, String time, String partySize){
        //create value to be used to update respective columns
        ContentValues values = new ContentValues();
        values.put(ReservationDatabaseHelper.RESERVATION_DATE, date);
        values.put(ReservationDatabaseHelper.RESERVATION_TIME, time);
        values.put(ReservationDatabaseHelper.RESERVATION_PARTYSIZE, partySize);
        //get the id of the reservation selected to find in the table
        long id = reservation.getId();
        //update where id is found
        db.update(ReservationDatabaseHelper.RESERVATION_TABLE, values,
                ReservationDatabaseHelper.RESERVATION_KEY + " = " + id, null);
        Cursor cursor = db.query(ReservationDatabaseHelper.RESERVATION_TABLE,
                reservationColumns, ReservationDatabaseHelper.RESERVATION_KEY +
                        " = " + id, null, null, null, null);
        cursor.moveToFirst();
        //convert to reservation to return information
        Reservation newReservation = cursorToReservation(cursor);
        cursor.close();
        return newReservation;

    }

    public List<Reservation> getAllReservations(){
        //create list of reservations
        List<Reservation> reservations = new ArrayList<Reservation>();
        Cursor cursor = db.query(ReservationDatabaseHelper.RESERVATION_TABLE,
                reservationColumns, null, null, null, null, null);
        cursor.moveToFirst();
        //retrieve every reservation in the table
        while(! cursor.isAfterLast()){
            //convert to reservation data type before adding it to list
            Reservation reservation = cursorToReservation(cursor);
            reservations.add(reservation);
            cursor.moveToNext();
        }
        cursor.close();
        //return the list of reservations
        return reservations;
    }

    public Reservation cursorToReservation(Cursor cursor){
        Reservation reservation = new Reservation();
        reservation.setId(cursor.getLong(0));
        reservation.setCustomer(cursor.getLong(4));
        reservation.setDate(cursor.getString(1));
        reservation.setTime(cursor.getString(2));
        reservation.setPartySize(cursor.getString(3));
        return reservation;
    }

    //CUSTOMER TABLE
    public Customer addCustomer(String name, String phone){
        ContentValues values = new ContentValues();
        //define values to put in respective columns
        values.put(ReservationDatabaseHelper.CUSTOMER_NAME, name);
        values.put(ReservationDatabaseHelper.CUSTOMER_PHONE, phone);
        //add the customer information save the id for the return
        long insertId = db.insert(ReservationDatabaseHelper.CUSTOMER_TABLE, null, values);
        //use id to query for information
       Cursor cursor = db.query(ReservationDatabaseHelper.CUSTOMER_TABLE, customerColumns,
               ReservationDatabaseHelper.CUSTOMER_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        //convert information to customer data type
        Customer newCustomer = cursorToCustomer(cursor);
        cursor.close();
        //return data type for displaying information
        return newCustomer;
    }


   public List<Customer> getAllCustomers(){
       List<Customer> customers = new ArrayList<Customer>();
       Cursor cursor = db.query(ReservationDatabaseHelper.CUSTOMER_TABLE,
               customerColumns, null, null, null, null, null);
       cursor.moveToFirst();
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
        db.delete(ReservationDatabaseHelper.CUSTOMER_TABLE,
                ReservationDatabaseHelper.CUSTOMER_ID+ " = " + id, null);
    }





}
