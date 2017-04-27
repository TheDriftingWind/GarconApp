package charleszhu.qunnipiac.edu.gittest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Charles on 4/24/2017.
 */

public class ReservationDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "reservations"; //database name
    private static final int DB_VERSION = 1; //database version

    //charleszhu.qunnipiac.edu.gittest.Customer Table -----------------------------------------------
    public static final String CUSTOMER_TABLE = "customer_table";
    public static final String CUSTOMER_ID = "id";
    public static final String CUSTOMER_NAME = "name";
    public static final String CUSTOMER_PHONE = "phone";

    private static final String CUSTOMER_TABLE_CREATE = "create table " + CUSTOMER_TABLE +
            "(" + CUSTOMER_ID + " integer primary key autoincrement, " + CUSTOMER_NAME + " text not null, " +
            CUSTOMER_PHONE + " text not null);";
    //Reservation Table --------------------------------------------
    public static final String RESERVATION_TABLE = "reservations_table";
    public static final String RESERVATION_KEY = "_id";
    public static final String RESERVATION_CUSTOMER = "customer";
    public static final String RESERVATION_DATE = "date";
    public static final String RESERVATION_TIME = "time";
    public static final String RESERVATION_PARTYSIZE = "party";

    private static final String DB_CREATE = "create table " +
            RESERVATION_TABLE + "(" + RESERVATION_KEY + " integer primary key autoincrement, "
            + RESERVATION_CUSTOMER + " text not null, " + RESERVATION_DATE + " text not null, "
            + RESERVATION_TIME + " text not null, "
            + RESERVATION_PARTYSIZE + " integer not null);";
    // --------------------------------------------------------------
    public ReservationDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);
        sqLiteDatabase.execSQL(CUSTOMER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RESERVATION_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        onCreate(sqLiteDatabase);
    }



}
