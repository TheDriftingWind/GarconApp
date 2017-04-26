package charleszhu.qunnipiac.edu.gittest;

/**
 * Created by Charles on 4/25/2017.
 */

public class Reservation {
    private long _id;
    private String _customer;
    private String _date;
    private String _time;
    private long _partySize;

    public long getId(){
        return _id;
    }

    public void setId(long id){
        _id = id;
    }

    public String getCustomer(){
        return _customer;
    }

    public void setCustomer(String customer){
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

    public long getPartySize(){
        return _partySize;
    }

    public void setPartySize(long partySize){
        _partySize = partySize;
    }

    //The information that will be displayed on listview
    @Override
    public String toString() {
        return ("Name:" + _customer + " " + "Date:" + _date
                + "\n Time:" + _time);
    }
}
