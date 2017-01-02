package dispatch;

import java.util.*;
import java.util.prefs.*;
import java.io.*;
import com.google.gson.*;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Main here.
 * 
 * @author Salem Gbian 
 * @version 1.0.0
 */
public class Dispatch extends Squad
{
    private void writeStringToJsonFile(String jsonString, File filename)
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
    
    private Squad[] loadSquads()
    {
        Gson gson = new Gson();
        Squad[] squads = new Squad[5];
        try
        {
            com.google.gson.stream.JsonReader reader = gson.newJsonReader(new FileReader(CONF_FILE));
            squads = gson.fromJson(reader, Squad[].class);
        }catch(FileNotFoundException ex)
        {}
        return squads;
    }
    
    private Lieutnant[] loadMembers()
    {
        Gson gson = new Gson();
        Lieutnant[] members = new Lieutnant[20];
        try
        {
            com.google.gson.stream.JsonReader reader = gson.newJsonReader(new FileReader(MEMBERS_FILE));
            members = gson.fromJson(reader, Lieutnant[].class);            
        }catch(FileNotFoundException ex){}
        return members;        
    }        
    
    public void saveSquads()
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(m_squads, Squad[].class);
        writeStringToJsonFile(jsonString, CONF_FILE);
    }
    
    public void saveMembers()
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(m_members, Lieutnant[].class);
        writeStringToJsonFile(jsonString, MEMBERS_FILE);
    }    
    
    private Date loadDate()
    {
        Gson gson = new Gson();
        Date ret = new Date();
        try
        {
            com.google.gson.stream.JsonReader reader = gson.newJsonReader(new FileReader(DATE_FILE));
            ret = gson.fromJson(reader, Date.class);
        }catch(FileNotFoundException ex){}
        return ret;
    }

    public void setSquadsAndMembers(ArrayList<String> squadsAndMembers)
    {
        String[] str;
        for (int i=0; i<m_squads.length;++i)
        {
            str = squadsAndMembers.get(i).split(" ", 2);
            m_squads[i].setLeaderFirstName(str[0]);
            m_squads[i].setLeaderLastName(str[1]);
        }
        for (int i=0; i<m_members.length;++i)
        {
            str = squadsAndMembers.get(i+5).split(" ", 2);
            m_members[i].setFirstName(str[0]);
            m_members[i].setLastName(str[1]);
        }
    }    

    private void addMembersToSquadHistory(Squad squad, ArrayList<Integer> members)
    {
        squad.addToHistory(members);        
    }

    private ArrayList<Integer> updateSquadAvailability ()
    {
        ArrayList<Integer> availableSquads = new ArrayList();

        for (int i=0; i<5; ++i)
        {
            if (m_squads[i].getMembers().size() < 4)
            {
                availableSquads.add(m_squads[i].getID());
            }
        }        
        return availableSquads;
    }

    private void updateSquadsHistoryList()
    {
        for (int i=0; i<m_squads.length; ++i)
        {
            addMembersToSquadHistory (m_squads[i], m_squads[i].getMembers());
        }        
    }     

    public void resetSquadsMembersList()
    {
        for (int i=0; i<m_squads.length; ++i)
        {
            m_squads[i].getMembers().clear();
        }        
    }     
    
    public void resetSquadsHistoryList()
    {
        for (int i=0; i<m_squads.length; ++i)
        {
            m_squads[i].getHistory().clear();
        }        
    }

    public void resetSquadsLeaderName()
    {
        for (int i=0; i<m_squads.length; ++i)
        {
            m_squads[i].getLeader().setFirstName("");
            m_squads[i].getLeader().setLastName("");
        }        
    }
    
    public void resetMembersName()
    {
        for (int i=0; i<m_members.length; ++i)
        {
            m_members[i].setFirstName("");
            m_members[i].setLastName("");
        }        
    }
    
    public void resetJoinedSquadList()
    {
        for (int i=0; i<m_members.length; ++i)
        {
            m_members[i].getJoinedSquads().clear();
        }        
    }
   
    public void resetRoundIndex()
    {
        m_prefs.putInt(ROUND_INDEX, 0);
    }
    
    public void removeRangeSquadsHistory()
    {
        for (int i=0; i<m_squads.length; ++i)
        {
            m_squads[i].getHistory().subList(0,
                    m_squads[i].getHistory().size()-4).clear();
        }
    }    
    
    public void removeRangeJoinedSquads()
    {
        for (int i=0; i<m_members.length; ++i)
        {
            m_members[i].getJoinedSquads().subList(0,
                    m_members[i].getJoinedSquads().size()-1).clear();
        }
    }      
    
    private void removeJoinedSquadsListLastEntries(Lieutnant[] members)
    {
        for (int i=0; i<members.length; ++i)
        {
            if (members[i].getJoinedSquads().size() >  m_roundIndex)
            {
                members[i].getJoinedSquads().remove(members[i].getJoinedSquads().size()-1);
            }            
        }        
    }        

    private Boolean joinASquad (Lieutnant member, ArrayList<Integer> availableSquads)
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
                    m_squads[squadIndex].addMember(member.getID());
                    member.getJoinedSquads().add(random);
                    joinedASquad = true;
                }
            }
        }
        return joinedASquad;
    }
    
    public void launchRound()
    {        
        boolean joinedSquad = false;
        ArrayList<Integer> availableSquads = new ArrayList();
        
        for (int i=0; i < m_members.length; ++i)
        {
            do
            {
                availableSquads.clear();
                availableSquads = updateSquadAvailability();                
                joinedSquad = joinASquad(m_members[i], availableSquads);
                if (joinedSquad == false)
                {
                    i=0;
                    // Bad luck ! Retry round.
                    resetSquadsMembersList();            
                    removeJoinedSquadsListLastEntries(m_members);
                }
            }while(joinedSquad == false);
        }
        updateSquadsHistoryList();
        resetSquadsMembersList();
    }
      
    public boolean isFreshStart()
    {
        boolean ret = false;
        
        for (int i=0; i<m_squads.length; ++i)
        {
            for (int j=0; j<m_squads[i].getMembers().size(); ++j)
            {
                if ((m_squads[i].getLeader().getFirstName().equals("")) ||
                    (m_squads[i].getLeader().getLastName().equals("")) )
                {
                    ret = true;
                    break;
                }
            }
        }
        
        if (ret == false)
        {
            for (int i=0; i<m_members.length; ++i)
            {
                if ((m_members[i].getFirstName().equals("")) ||
                    (m_members[i].getLastName().equals("")) )
                {
                    ret = true;
                    break;
                }
            }            
        }
        return ret;
    }

    public Date getSavedDate()
    {
        return m_date;
    }
    
    public int getRoundIndex()
    {
        return m_roundIndex;
    }
    
    /**
     *
     * @return
     */
    public Preferences getPrefs()
    {
        return m_prefs;
    }
    
    public void setRoundIndex(int value)
    {
        m_roundIndex = value;
    }
    
    private String printSquadMembers(ArrayList<Integer> history)
    {
        String ret = "";
        
        for (int i=history.size()-4; i<history.size(); ++i)
        {
            ret += ("\t\t    --> " + getMemberName(history.get(i)) + "\n");
        }
        
        return ret;
    }
    
    public String printResult()
    {
        String ret = "";
        
        for (int i=0; i < m_squads.length; ++i)
        {
            ret += "\t\t   *** ["+ m_squads[i].getName() + "] ***\n";
            ret += "\t\tLeader: " + m_squads[i].getLeader().getFirstName() + " " +
                    m_squads[i].getLeader().getLastName()+"\n";
            ret += printSquadMembers(m_squads[i].getHistory())+"\n";
        }
        /* // Debug printing
        for (int i=0; i < m_squads.length; ++i)
        {
            ret += m_squads[i].getHistory()+"\n";        
        }
        ret += m_members[0].getJoinedSquads();*/
        return ret;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public String getMemberName(int id)
    {
        String ret = "";
        for (int i=0; i < m_members.length; ++i)
        {
            if (m_members[i].getID() == id)
            {
                ret = m_members[i].to_string();
            }
        }
        return ret;
    }
    
    public Squad[] getDispatchSquads()
    {
        return m_squads;
    }

    public Lieutnant[] getDispatchMembers()
    {
        return m_members;
    }
    
    public void run()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        m_prefs = Preferences.userNodeForPackage(Dispatch.class);
        
        if(m_roundIndex == -1)
        {
            resetRoundIndex();
            m_roundIndex = 0;
        }
        
        if(sdf.format(m_date).equals(sdf.format(today)) == false)
        {
            if(m_roundIndex < 5)
            {
                launchRound();
                if(m_roundIndex == 0)
                {
                    removeRangeSquadsHistory();
                    removeRangeJoinedSquads();
                }
                
                if(m_roundIndex == 4)
                {
                    m_roundIndex = 0;
                    removeRangeSquadsHistory();
                    removeRangeJoinedSquads();
                }
                else
                {
                    m_roundIndex++;
                }
                saveSquads();
                saveMembers();
            }
            m_prefs.putInt(ROUND_INDEX, m_roundIndex);
        }
    }
        
    public Dispatch()
    {
        m_prefs = Preferences.userNodeForPackage(Dispatch.class);
        m_roundIndex = m_prefs.getInt(ROUND_INDEX, -1);        
        
        // Load squads and members from configuration files
        m_date    = loadDate();
        m_squads  = loadSquads();
        m_members = loadMembers();
    }

    // Variables
    private static final String ROUND_INDEX = "round_index";
    private final File CONF_FILE    = new File("./resources/conf.json");
    private final File MEMBERS_FILE = new File("./resources/members.json");
    private final File DATE_FILE = new File("./resources/scheduler.json");
    private final Squad[]     m_squads;
    private final Lieutnant[] m_members;
    private final Date m_date;
    private int m_roundIndex;
    private Preferences m_prefs;
}