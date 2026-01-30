//Vishnuvarthan Kumar, Student ID: 3387042
public class SmartCard
{
    private int cardID;           // the id of the smartcard
    private char type;            // the type of the smartcard (it can be "C", "A" or "S")
    private double balance;       // the balance available on the smartcard (should have a minimum balance of $5)
    private int maxJourneys = 3;
    private Journey[] journeys = new Journey[maxJourneys];
    private int numOfJourneys = 0;// stores number of journeys 
    // sets card id, only takes int 
    public void setCardID(int cardID)
    {
        this.cardID = cardID;
    }
    // returns ID 
    public int getCardID()
    {
        return cardID; 
    }
    //sets type of card
    public void setType(char type)
    {
        this.type = type;
    }
    //returns type
    public char getType()
    {
        return type; 
    }
    //sets balance 
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    //returns balance
    public double getBalance()
    {
        return balance; 
    }
    //sets a journey, and increases num of journey
    public void setJourney(Journey journey){
        //makes sure its there is only an allowed number of journeys
         if (numOfJourneys < journeys.length) {
            journeys[numOfJourneys] = journey;
            numOfJourneys++;
        } 
    }
    //gets journey based on the index which is sent by main system 
    public Journey getJourney(int index) {
         if (index >= 0 && index < numOfJourneys) {
            return journeys[index];
        } else {
            return null; // Index out of bounds
        }
    }
    //sets max journey based on type 
    public void setMaxJourneys(char type){
        if (type == 'C'){
            maxJourneys = 1;
        }
        else if (type == 'A'){
            maxJourneys = 2;
        }
        else if (type == 'S'){
            maxJourneys = 3;
        }
    }
    //returns max journey
     public int getMaxJourneys() {
        return maxJourneys;
    }
    //sets number of journeys
    public void setNumOfJourneys(int num) {
        this.numOfJourneys = numOfJourneys + num;
    }
    //returns number of journeys
     public int getNumOfJourneys() {
        return numOfJourneys;
    }
    //removes journey based on sent index
    public void removeJourney(int index)
    {
        for (int i = 0; i == index; i++){
            journeys[i] = null;
            //check with teacher
            for (int j = i; j < numOfJourneys - 1; j++) {
                journeys[j] = journeys[j + 1];
            }
            numOfJourneys = numOfJourneys - 1;
        }
    }
}
