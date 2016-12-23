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
    private int id;   
    private ArrayList<Integer> joinedSquads;    
    
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
    
    public String toString()
    {
        return this.getFirstName() + " " + this.getLastName() + this.joinedSquads;
    }
}
