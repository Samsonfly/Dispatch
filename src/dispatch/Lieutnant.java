package dispatch;
import java.util.*;

/**
 * Write a description of class Lieutnant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lieutnant extends Person
{
    /**
     * Constructor for objects of class Lieutnant
     */
    private final int id;   
    private final ArrayList<Integer> joinedSquads;    
    
    public Lieutnant(String firstName, String lastName, int id, ArrayList<Integer> joinedSquads)
    {
        super(firstName, lastName);
        this.id = id;
        this.joinedSquads = joinedSquads;
    }
    
    public ArrayList<Integer> getJoinedSquads()
    {
        return this.joinedSquads;
    }
    
    public int getID()
    {
        return this.id;
    }
    
    /**
     *
     * @return
     */
    public String to_string()
    {
        return this.getFirstName() + " " + this.getLastName();
    }
}
