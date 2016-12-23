package dispatch;

import java.util.*;
import java.util.prefs.*;
import java.io.*;
import com.google.gson.*;
import java.text.*;

/**
 * Write a description of class Main here.
 * 
 * @author Salem Gbian 
 * @version 1.0.0
 */
public class Dispatch extends Squad
{
    // Preference keys for this package
    private static final String ROUND_INDEX = "round_index";
    private static final String CYCLE_INDEX = "cycle_index";
    private static final File confFile    = new File("C:\\Users\\gbian\\Documents\\Briefcase\\UQAM\\BlueJ\\Dispatcher\\configFiles\\conf.json");
    private static final File membersFile = new File("C:\\Users\\gbian\\Documents\\Briefcase\\UQAM\\BlueJ\\Dispatcher\\configFiles\\members.json");
    
    public void writeStringToJsonFile(String jsonString, File filename)
    {
        try 
        {
            try (FileOutputStream fOut = new FileOutputStream(filename);
               OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut))
            {
                myOutWriter.write(jsonString);
            }
        } catch (IOException e) {
        }        
    }
    
    public Squad[] loadSquads()
    {
        Gson gson = new Gson();
        Squad[] squads = new Squad[5];
        try
        {
            com.google.gson.stream.JsonReader reader = gson.newJsonReader(new FileReader(confFile));
            squads = gson.fromJson(reader, Squad[].class);
        }catch(FileNotFoundException ex)
        {}
        return squads;
    }
    
    public void saveSquads(Squad[] squads)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(squads, Squad[].class);
        //System.out.println(jsonString);
        writeStringToJsonFile(jsonString, confFile);
    }

    public Lieutnant[] loadMembers()
    {
        Gson gson = new Gson();
        Lieutnant[] members = new Lieutnant[20];
        try
        {
            com.google.gson.stream.JsonReader reader = gson.newJsonReader(new FileReader(membersFile));
            members = gson.fromJson(reader, Lieutnant[].class);            
        }catch(FileNotFoundException ex){}
        return members;        
    }        

    public void saveMembers(Lieutnant[] members)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(members, Lieutnant[].class);
        //System.out.println(jsonString);
        writeStringToJsonFile(jsonString, membersFile);
    }    
    
    public Date loadDate()
    {
        File jsonInputFile = new File("C:\\Users\\gbian\\Documents\\Briefcase\\UQAM\\BlueJ\\Dispatcher\\configFiles\\scheduler.json");
        Gson gson = new Gson();
        Date d = new Date();
        try
        {
            com.google.gson.stream.JsonReader reader = gson.newJsonReader(new FileReader(jsonInputFile));
            d = gson.fromJson(reader, Date.class);
        }catch(FileNotFoundException ex){}
        return d;
    }
        
    public void addMembersToSquadHistory(Squad squad, ArrayList<Integer> members)
    {
        squad.addToHistory(members);        
    }

    public ArrayList<Integer> updateSquadAvailability (Squad[] squads)
    {
        ArrayList<Integer> availableSquads = new ArrayList();

        for (int i=0; i<5; ++i)
        {
            if (squads[i].getMembers().size() < 4)
            {
                availableSquads.add(squads[i].getID());
            }
        }        
        return availableSquads;
    }

    public void updateSquadHistoryList(Squad[] squads)
    {
        for (int i=0; i<5; ++i)
        {
            addMembersToSquadHistory (squads[i], squads[i].getMembers());
        }        
    }     

    public void resetSquadsMembersList(Squad[] squads)
    {
        for (int i=0; i<5; ++i)
        {
            squads[i].getMembers().clear();
        }        
    }     

    public void resetJoinedSquadList(Lieutnant[] members)
    {
        for (int i=0; i<members.length; ++i)
        {
            members[i].getJoinedSquads().clear();
        }        
    }

    public void removeRangeSquadsHistory(Squad[] squads)
    {
        for (int i=0; i<5; ++i)
        {
            squads[i].getHistory().subList(0, squads[i].getHistory().size()-4).clear();
        }
    }    
    
    public void removeJoinedSquadsListLastEntries(Lieutnant[] members, int round)
    {
        for (int i=0; i<members.length; ++i)
        {
            if (members[i].getJoinedSquads().size() >  round)
            {
                members[i].getJoinedSquads().remove(members[i].getJoinedSquads().size()-1);
            }            
        }        
    }        

    public Boolean joinASquad (Lieutnant member, Squad[] squads, ArrayList<Integer> availableSquads)
    {
        boolean joinedASquad = false;
        Random randomizer = new Random();
        int index = 0, squadIndex = 0;
        
        if (!availableSquads.isEmpty())
        {
            while ((joinedASquad == false) && (index < availableSquads.size()))
            {
                index++;
                int random = availableSquads.get(randomizer.nextInt(availableSquads.size()));
                squadIndex = random - 1;
                if ( member.getJoinedSquads().isEmpty() ||(member.getJoinedSquads().contains(random) == false) )
                {
                    squads[squadIndex].addMember(member.getID());
                    //System.out.println(">> "+squads[squadIndex].getName()+">> "+squads[squadIndex].getMembers());            
                    member.getJoinedSquads().add(random);
                    joinedASquad = true;
                }
            }
        }
        else
        {
            System.out.println("No available squad !");
        }
        return joinedASquad;
    }    
    
    public void launchRound(Lieutnant[] members, Squad[] squads, int roundIndex)
    {
        boolean joinedSquad = false;
        ArrayList<Integer> availableSquads = new ArrayList();
        
        for (int i=0; i < members.length; ++i)
        {
            do
            {
                availableSquads.clear();
                availableSquads = updateSquadAvailability(squads);                
                joinedSquad = joinASquad(members[i], squads, availableSquads);
                if ( joinedSquad == false)
                {
                    i=0;
                    //System.out.println("Bad luck !");
                    // Bad luck ! Retry round.
                    resetSquadsMembersList(squads);            
                    removeJoinedSquadsListLastEntries(members, roundIndex);
                }
            }while(joinedSquad == false);
        }
        updateSquadHistoryList(squads);
        resetSquadsMembersList(squads);
    }

    public void printResult(Squad[] squads)
    {
        for (int i=0; i<5; ++i)
        {
            System.out.println("=> " + squads[i].getName() + "  " + squads[i].getHistory());
        }
    }

    public Dispatch()
    {        
        Preferences prefs = Preferences.userNodeForPackage(Dispatch.class);
        int roundIndex = prefs.getInt(ROUND_INDEX, -1);
        
        if(roundIndex == -1)
        {
            System.out.println("Round index null");
            prefs.putInt(ROUND_INDEX, 0);
            roundIndex=0;
        }
        else
        {
            System.out.println("=>"+roundIndex);            
        }
        
        // Load squads and members from configuration files
        Squad[] squads = loadSquads();
        Lieutnant[] members = loadMembers();
        System.out.println("\n---History---");
        printResult(squads);
        System.out.println("---End---\n");
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date d = loadDate();
        if(sdf.format(d).equals(sdf.format(today)) == false)
        {        
            if(roundIndex < 5)
            {
                launchRound(members, squads, roundIndex);
                System.out.println("\n---Members---");
                printResult(squads);
                System.out.println("---End---\n");
                if(roundIndex == 0)
                {
                    removeRangeSquadsHistory(squads);
                }
                
                if(roundIndex == 4)
                {
                    System.out.println("Reached max round");
                    roundIndex=0;
                    removeRangeSquadsHistory(squads);
                    resetJoinedSquadList(members);
                }
                else
                {
                    roundIndex++;                    
                }
                saveSquads(squads);
                saveMembers(members);
            }
            prefs.putInt(ROUND_INDEX, roundIndex);
        }
    }
}