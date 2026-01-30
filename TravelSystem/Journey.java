//Vishnuvarthan Kumar, Student ID: 3387042
public class Journey 
{
    private int journeyID;                 // the id of the journey
    private String transportMode;          // the public transport mode of the journey (can be only train?, bus? or tram?) 
    private int startOfJourney;            // the starting point of the journey. It can be only a number between [1..10] 
    private int endOfJourney;              // the ending point of the journey. It can be only a number between [1..10] (should be different from the starting point of the journey)
    private int distanceOfJourney;         // the distance of the journey (i.e. the difference in number of stations/stops travelled between startOfJourney and endOfJourney)

    //sets transport method
    public void setTransportMode(String transportMode)
    {
        this.transportMode = transportMode;
    }
    // returns transport mode
    public String getTransportMode()
    {
        return transportMode;
    }
    //sets journey ID
    public void setJourneyID(int journeyID)
    {
        this.journeyID = journeyID;
    }
    //returns journey id 
    public int getJourneyID() {
        return journeyID;
    }
    //sets start of the journey
    public void setStartOfJourney(int startOfJourney)
    {
        this.startOfJourney = startOfJourney;
    }
    //return start of the journey
    public int getStartOfJourney() 
    {
        return startOfJourney;
    }
    //sets end of the journey
    public void setEndOfJourney(int endOfJourney)
    {
        this.endOfJourney = endOfJourney;
    }
    //returns end of the journey
    public int getEndOfJourney() {
        return endOfJourney;
    }
    //sets journey's distance
    public void setDistanceOfJourney(int distanceOfJourney)
    {
        this.distanceOfJourney = distanceOfJourney;
    }
    //returns distance of journey
    public int getDistanceOfJourney() 
    {
        return distanceOfJourney;
    }
}
