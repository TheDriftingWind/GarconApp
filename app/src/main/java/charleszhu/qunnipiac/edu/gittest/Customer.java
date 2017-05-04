package charleszhu.qunnipiac.edu.gittest;

/**
 * Created by Carly on 4/26/2017.
 * Customer data type used to store information taken from the customers table in the database
 */

public class Customer {

    //variables
    private long id;
    private String name;
    private String phone;


    //setters and getters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId(){
        return id;
    }

    public String getName(){return name;}

    public String getPhoneNum(){return phone;}
}
