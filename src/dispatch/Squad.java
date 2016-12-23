package dispatch;

import java.util.*;
/**
 * Write a description of class Squad here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Squad
{
    // instance variables - replace the example below with your own
    private int id;
    private String name;
    private Captain leader;
    private ArrayList<Integer> members;
    private ArrayList<Integer> history;

    public Squad()
    {}
    
    public ArrayList<Integer> getMembers()
    {
        return this.members;
    }
    
    public void setMembers(int value)
    {
        this.members.add(value);
    }    
    
    public ArrayList<Integer> getHistory()
    {
        return this.history;
    }
        
    public String getName()
    {
        return this.name;
    }    
    
    public int getID()
    {
        return this.id;
    }    
    
    public void addToHistory(ArrayList<Integer> members)
    {
        this.history.addAll(members);
    }
    
    public void addMember(int value)
    {
        this.members.add(value);
    }
        
    public String toString()
    {
        return "Squad Leader = " + this.leader.getFirstName() +
         " " + this.leader.getLastName() + "\n" + "Squad Name = " + this.name + "\n";
    }
}
