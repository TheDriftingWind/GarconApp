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
    public Reservation createReservation(Customer customer, String date, String time, long partySize){
        ContentValues values = new ContentValues();

        values.put(ReservationDatabaseHelper.RESERVATION_CUSTOMER_ID, customer.getId());
        values.put(ReservationDatabaseHelper.RESERVATION_DATE, date);
        values.put(ReservationDatabaseHelper.RESERVATION_TIME, time);
        values.put(ReservationDatabaseHelper.RESERVATION_PARTYSIZE, partySize);

        long insertId = db.insert(ReservationDatabaseHelper.RESERVATION_TABLE, null, values);
        Cursor cursor = db.query(ReservationDatabaseHelper.RESERVATION_TABLE,
                reservationColumns, ReservationDatabaseHelper.RESERVATION_KEY +
                " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Reservation newReservation = cursorToReservation(cursor);
        cursor.close();
        return newReservation;
    }

    public void deleteReservation(Reservation reservation){
        long id = reservation.getId();
        db.delete(ReservationDatabaseHelper.RESERVATION_TABLE,
                ReservationDatabaseHelper.RESERVATION_KEY + " = " + id, null);
    }

    public Reservation changeReservation(Reservation reservation, String date, String time, long partySize){
        ContentValues values = new ContentValues();
        values.put(ReservationDatabaseHelper.RESERVATION_DATE, date);
        values.put(ReservationDatabaseHelper.RESERVATION_TIME, time);
        values.put(ReservationDatabaseHelper.RESERVATION_PARTYSIZE, partySize);

        long id = reservation.getId();
        db.update(ReservationDatabaseHelper.RESERVATION_TABLE, values,
                ReservationDatabaseHelper.RESERVATION_KEY + " = " + id, null);
        Cursor cursor = db.query(ReservationDatabaseHelper.RESERVATION_TABLE,
                reservationColumns, ReservationDatabaseHelper.RESERVATION_KEY +
                        " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Reservation newReservation = cursorToReservation(cursor);
        cursor.close();
        return newReservation;

    }

    public List<Reservation> getAllReservations(){
        List<Reservation> reservations = new ArrayList<Reservation>();
        Cursor cursor = db.query(ReservationDatabaseHelper.RESERVATION_TABLE,
                reservationColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast()){
            Reservation reservation = cursorToReservation(cursor);
            reservations.add(reservation);
            cursor.moveToNext();
        }
        cursor.close();

        return reservations;
    }

    public Reservation cursorToReservation(Cursor cursor){
        Reservation reservation = new Reservation();
//        reservation.setId(cursor.getLong(0));
//        reservation.setCustomer(cursor.getString(1));
//        reservation.setDate(cursor.getString(2));
//        reservation.setTime(cursor.getString(3));
//        reservation.setPartySize(cursor.getLong(4));
        reservation.setId(cursor.getLong(0));
        reservation.setCustomer(cursor.getLong(4));
        reservation.setDate(cursor.getString(1));
        reservation.setTime(cursor.getString(2));
        reservation.setPartySize(cursor.getLong(3));
        return reservation;
    }

    //CUSTOMER TABLE
    public Customer addCustomer(String name, String phone){
        ContentValues values = new ContentValues();

        values.put(ReservationDatabaseHelper.CUSTOMER_NAME, name);
        values.put(ReservationDatabaseHelper.CUSTOMER_PHONE, phone);

        long insertId = db.insert(ReservationDatabaseHelper.CUSTOMER_TABLE, null, values);
       Cursor cursor = db.query(ReservationDatabaseHelper.CUSTOMER_TABLE, customerColumns,
               ReservationDatabaseHelper.CUSTOMER_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Customer newCustomer = cursorToCustomer(cursor);
        cursor.close();
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
