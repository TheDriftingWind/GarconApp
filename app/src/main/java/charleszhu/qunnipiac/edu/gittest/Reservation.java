package charleszhu.qunnipiac.edu.gittest;

/**
 * Created by Charles on 4/25/2017.
 * Reservation class is the data type to store reservation information taken from the database
 */

public class Reservation {
    private long _id;
    private long _customer;
    private String _date;
    private String _time;
    private String _partySize;

    public long getId(){
        return _id;
    }

    public void setId(long id){
        _id = id;
    }

    public long getCustomer(){
        return _customer;
    }
    //setters and getters
    public void setCustomer(long customer){
        _customer = customer;
    }

    public String getDate(){
        return _date;
    }

    public void setDate(String date){
        _date = date;
    }

    public String getTime(){
        return _time;
    }

    public void setTime(String time){
        _time = time;
    }

    public String getPartySize(){
        return _partySize;
    }

    public void setPartySize(String partySize){
        _partySize = partySize;
    }

    //The information that will be displayed on listview
    @Override
    public String toString() {
        return ("Customer Id:" + _customer + " " + "Date:" + _date
                + "\n Time:" + _time);
    }
}
