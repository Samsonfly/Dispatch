package dispatch;

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person
{
    // instance variables - replace the example below with your own
    private String firstName;
    private String lastName;

    /**
     * Constructor for objects of class Person
     */
    public Person()
    {
        // initialise instance variables
        firstName = "";
        lastName = "";
    }

    /**
     * Constructor for objects of class Person
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName)
    {
        // initialise instance variables
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getLastName()
    {
        return this.lastName;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }          
    
    public void setLastName(String value)
    {
        this.lastName = value;
    }
    
    public void setFirstName(String value)
    {
        this.firstName = value;
    }
}
