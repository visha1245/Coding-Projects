//Vishnuvarthan Kumar, Student ID: 3387042
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class SystemInterface
{
    static Scanner console = new Scanner(System.in);
    static final int maxCards = 10;
    static int smartCardCount = 0;
    static SmartCard[] smartCard = new SmartCard[maxCards];
    //runs the menu system 
    private void run() throws java.io.IOException
    {
        int option;
        do {
            System.out.println(
            "\n"+"(1)Create New Card "+"\n"+
            "(2)Delete smart card"+"\n"+
            "(3)Delete journey"+"\n"+
            "(4)List of smartcards"+"\n"+
            "(5)List of journeys"+"\n"+
            "(6)List of journeys based of transport mode"+"\n"+
            "(7)Summary of total cost"+"\n"+
            "(8)Read from file"+"\n"+
            "(9)Write to file"+"\n"+
            "Exit (0): ");
            option = console.nextInt(); //menu 
            switch(option)
            {
                case 1: createCard();
                break;
                case 2: deleteSmartCard();
                break;
                case 3: deleteJourney();
                break;
                case 4: listAllCards();
                break;
                case 5: listAllJourneys();
                break;
                case 6: listSpecifiedJourneys();
                break;
                case 7: summaryOfCosts();
                break;
                case 8: readFromFile();
                break;
                case 9: writeToFile();
                break;
                case 0: break;
                default: System.out.println("invalid option");
            }
        }
        while(option!=0);
    }
    //allows the program to start
    public static void main(String[] args) throws java.io.IOException
    {
        SystemInterface systemUI = new SystemInterface(); //creates a new iteration of the system and runs it
        systemUI.run();
    }
    //creates new smart card
    public static void createCard() 
    {
         if (smartCardCount >= maxCards) {
            System.out.println("No space for a new SmartCard.");
            return;
        }
        
        int cardID; 
        String type = "";
        double balance; 
        
        for (int i = 0; i < maxCards; i++){
            if (smartCard[i] == null){
                //Card ID entering
                System.out.print("Card ID: "); //asks for card ID
                while(!console.hasNextInt()) {
                    System.out.print("Input must be an integer, Number: ");
                    console.next();
                }
                cardID = console.nextInt();
                if (findSmartCard(cardID) != null) {
                    System.out.println("This card ID already exists, assigning a random ID.");
                    cardID = generateRandomID();
                }
                
                System.out.print("Balance? "); //asks for balance as double
                while(!console.hasNextDouble()) { //checks if its a number
                    System.out.print("Input must be an double, Number: ");
                    console.next();
                }
                balance = console.nextDouble();
                while (balance < 5)
                {
                    System.out.print("Try again, Balance must be above $5: ");
                    balance = console.nextDouble();
                }
                
                //card type entering 
                System.out.print("Type of card? (C, A, S): "); // asks for card type and ensures it's valid
                do 
                {
                    type = console.nextLine().toUpperCase();
                    if (!type.equals("C") && !type.equals("A") && !type.equals("S") && !type.equals("")){
                        System.out.print("Invalid input. Please enter either 'C', 'A', or 'S': ");
                    }
                } 
                while (!type.equals("C") && !type.equals("A") && !type.equals("S"));
                char cardType = type.charAt(0); //sets in to char 
                
                smartCard[i] = new SmartCard();
                // Set attributes for each card as needed
                smartCard[i].setCardID(cardID);
                smartCard[i].setType(cardType);
                smartCard[i].setBalance(balance);
                smartCardCount = smartCardCount + 1;
                smartCard[i].setMaxJourneys(cardType);
                //goes through creating all journeys (until it reaches max journeys)
                for (int j = 0; j < smartCard[i].getMaxJourneys(); j++)
                {
                    String transportMode; 
                    int journeyID, startOfJourney, EndOfJourney, distanceOfJourney; 
                    int tempJourneyCount = 0; 
                    boolean duplicate = false; 
                    System.out.print("Create new journey ID (" + (j+1) + "): ");
                    while(!console.hasNextInt()) {
                        System.out.print("Input must be an integer, Number: ");
                        console.next();
                    }
                    journeyID = console.nextInt();
                    //used to check if the journey id already exists within the card 
                    if (smartCard[i].getNumOfJourneys() > 0) {
                        int numOfJourneys = smartCard[i].getNumOfJourneys();
                        for (int x = 0; x < numOfJourneys; x++) {
                            Journey journey = smartCard[i].getJourney(x);
                            if (journey != null && journeyID == journey.getJourneyID()) {
                                System.out.println("This journey ID already exists, assigning a random ID.");
                                journeyID = generateRandomID();
                                break;
                            }
                        }
                    }
                
                    //enter transport mode and ensuring its valid
                    System.out.print("Journey’s Transport Mode? ");
                    do 
                    {
                        transportMode = console.nextLine().toLowerCase();
                        if (!transportMode.equals("bus") && !transportMode.equals("train") && 
                            !transportMode.equals("tram") && !transportMode.equals("")){
                            System.out.println("Invalid transport mode. Please enter either 'train', 'bus', or 'tram'.");
                            System.out.print("Journey’s Transport Mode? ");
                        }
                    } 
                    while (!transportMode.equals("bus") && !transportMode.equals("train") && !transportMode.equals("tram"));
                    //entering starting point and ensuring its between 1 -10 and not negative
                    System.out.print("Starting point of journey (1 - 10) ");
                    do {
                        while (!console.hasNextInt()) {
                            System.out.println("Invalid, only number's between 1 and 10.");
                            System.out.print("Starting point of journey (1 - 10) ");
                            console.next();
                        }
                        startOfJourney = console.nextInt();
                        if (startOfJourney < 1 || startOfJourney > 10) {
                            System.out.println("Invalid, only number's between 1 and 10.");
                        }
                    } while (startOfJourney < 1 || startOfJourney > 10);
                    //entering end point and ensuring its greater than start
                    do {
                        System.out.print("Ending point of journey (1 - 10) ");
                        while (!console.hasNextInt()) {
                            System.out.println("Invalid, only number's between 1 and 10.");
                            System.out.print("Ending point of journey (1 - 10) ");
                            console.next();
                        }
                        EndOfJourney = console.nextInt();
                        if (EndOfJourney < 1 || EndOfJourney > 10) {
                            System.out.println("Invalid, only number's between 1 and 10.");
                        }
                        else if (EndOfJourney == startOfJourney){
                            System.out.println("Invalid, number can't be the same as start point");
                        }
                    } while (EndOfJourney < 1 || EndOfJourney > 10 || EndOfJourney == startOfJourney);
                    //calculating the distance of journeys
                    if (EndOfJourney > startOfJourney) 
                    {
                        distanceOfJourney = EndOfJourney - startOfJourney; //if the end is bigger number 
                    } else 
                    {
                        distanceOfJourney = startOfJourney - EndOfJourney; //if start is bigger number allows distance to always be positive 
                    }
                    
                    for (int x = 0; x < smartCard[i].getNumOfJourneys(); x++) {
                        Journey journey = smartCard[i].getJourney(x);
                        if (journey != null && transportMode.equals(journey.getTransportMode()) 
                                && startOfJourney == journey.getStartOfJourney() 
                                && EndOfJourney == journey.getEndOfJourney()) {
                            System.out.println("This journey's details are the exact same as a previously created journey.");
                            duplicate = true;
                            break;
                        }
                    }
                 
                    if (duplicate == false){
                        Journey journey = new Journey();
                        journey.setJourneyID(journeyID);
                        journey.setTransportMode(transportMode);
                        journey.setStartOfJourney(startOfJourney);
                        journey.setEndOfJourney(EndOfJourney);
                        journey.setDistanceOfJourney(distanceOfJourney);
                        smartCard[i].setJourney(journey);
                        System.out.println("Created journey with ID: " + journeyID);
                    }
                    else {
                        j = j -1; 
                    }
                
                }
                System.out.println("SmartCard ID: " + smartCard[i].getCardID() + ", Type: " + smartCard[i].getType() + ", Balance: $" + smartCard[i].getBalance());
                break;
            }
        }
    }
    
    //used to delete smart card based on given card ID
    public static void deleteSmartCard()
    {
        int ID; 
        boolean cardFound = false;
        System.out.print("Enter smartcard ID? ");
        ID = console.nextInt();
        if (findSmartCard(ID) == null){
            // Smart card with the given ID not found
            System.out.println("Smart card with ID " + ID + " does not exist.");
            return;
        }
        else {
            SmartCard cardToDelete = findSmartCard(ID);
            // Iterate through the array of smart cards to find and remove the card
            for (int i = 0; i < smartCardCount; i++) {
                if (smartCard[i] == cardToDelete) {
                    smartCard[i] = null;
                    smartCardCount = smartCardCount - 1;
                    System.out.println("Smart card with ID " + ID + " has been deleted.");
                    return; 
                }
            }
        }
    }
    
    //used to delete specified journey
    public static void deleteJourney()
    {
        int ID, journeyID; 
        boolean journeyExists = false; 
        System.out.print("Enter smartcard ID? ");
        ID = console.nextInt();
        SmartCard smartCard = findSmartCard(ID);
        System.out.print("Enter journey ID? ");
        journeyID = console.nextInt();
        
        if (findSmartCard(ID) == null){
            // Smart card with the given ID not found
            System.out.println("Smart card with ID " + ID + " does not exist.");
            return;
        }
        else{
            //makes sure the suggested journey exists, so in the case it doesnt it can send out an error message
            for (int i = 0; i < smartCard.getNumOfJourneys(); i++) {
                if (smartCard.getJourney(i).getJourneyID() == journeyID) {
                    journeyExists = true;
                }  
            }
            //actualy deletes the journey through a method in smartcard
            if (journeyExists = true){
                for (int i = 0; i < smartCard.getNumOfJourneys(); i++) {
                    if (smartCard.getJourney(i).getJourneyID() == journeyID) {
                        smartCard.removeJourney(i);
                        smartCard.setNumOfJourneys(-1); 
                        break;
                    }
                }  
            }
            else {
                  System.out.println("Journey with ID " + journeyID + " does not exist for smart card with ID " + ID + ".");
            }
        }
    }

    public static void listAllCards()
    {
        //goes through all smartCards created
        if (smartCardCount > 0){
             for (int i = 0; i < smartCardCount; i++) {
                SmartCard card = smartCard[i];
                if (card != null){
                    System.out.println("Smartcard " + card.getCardID() + " has type " + card.getType() + " and " + card.getNumOfJourneys() + " journey(s):");
                }
             
                //goes the journeys associated with the smart card
                if (card != null){
                    if (card.getNumOfJourneys() > 0){
                        for (int j = 0; j < card.getNumOfJourneys(); j++) {
                            Journey journey = card.getJourney(j);
                            if (journey != null) {
                                System.out.println("Journey " + journey.getJourneyID() + " has transport mode " + journey.getTransportMode());
                            }
                        }
                    }
                    else{
                        System.out.println("Journeys have not been created!");
                    }
                }
            }
        }
        else{
            System.out.println("Smartcards have not been created!");
            return;
        }
    }
    
    //lists all the journeys within a specific smart card, after entering id 
    public static void listAllJourneys()
    {
        int ID;
        System.out.print("Enter smartcard ID? ");
        ID = console.nextInt();
        SmartCard smartCard = findSmartCard(ID);
        
        //goes through all cards printing out journey info
        if (smartCardCount > 0 && smartCard != null){
            for (int j = 0; j < smartCard.getNumOfJourneys(); j++) {
                Journey journey = smartCard.getJourney(j);
                if (journey != null) {
                            System.out.println("Journey "+ journey.getJourneyID() +" has transport mode "+ journey.getTransportMode() 
                            + " starting from " + journey.getStartOfJourney() + " and ending at " + journey.getEndOfJourney() + 
                            " with journey distance of " + journey.getDistanceOfJourney() + " station(s)/stop(s)");
                }
            }
        }
        else {
            System.out.println("Smartcard(s) does not exist!");
            return;
        }
    }
    
    //finds all journeys with a certain transport mode
        public static void listSpecifiedJourneys() {
        boolean exists = false; 
        System.out.print("Enter the transport mode: ");
        String transportMode = console.next().toLowerCase();
        //validates input as bus, train or tram
        while (!transportMode.equals("bus") && !transportMode.equals("train") && !transportMode.equals("tram")) {
            System.out.println("Invalid transport mode. Please enter either 'bus', 'train', or 'tram'.");
            transportMode = console.next().toLowerCase();
        }
        
        //goes through all cards and finds one where transport mode is the same 
        if (smartCardCount > 0) {
            for (int i = 0; i < smartCardCount; i++) {
                SmartCard card = smartCard[i];
                
                if (card.getNumOfJourneys() > 0) {
                    for (int j = 0; j < card.getNumOfJourneys(); j++) {
                        Journey journey = card.getJourney(j);
                        if (journey != null && journey.getTransportMode().equals(transportMode)) {
                            System.out.println("Journey " + journey.getJourneyID() + " has that transport mode and belongs to SmartCard " + card.getCardID());
                            exists = true; 
                        }
                    }
                }
            }
            if (!exists) {
                System.out.println("Journey with that transport mode does not exist!");
            }
        } else {
            System.out.println("SmartCards have not been created!");
        }    
    }
    
     public static void summaryOfCosts()
    {
        double totalBus = 0.0;
        double totalTrain = 0.0;
        double totalTram = 0.0;
        
        // Calculate total cost for each transport mode
        for (int i = 0; i < smartCardCount; i++) {
            SmartCard card = smartCard[i];
            if (card != null && card.getNumOfJourneys() > 0) {
                for (int j = 0; j < card.getNumOfJourneys(); j++) {
                    Journey journey = card.getJourney(j);
                    if (journey != null) {
                        double fare = calculateFare(journey.getTransportMode(), journey.getDistanceOfJourney());
                        switch (journey.getTransportMode()) {
                            //adds to overall total 
                            case "bus":
                                totalBus += fare;
                                break;
                            case "train":
                                totalTrain += fare;
                                break;
                            case "tram":
                                totalTram += fare;
                                break;
                        }
                    }
                }
            }
        }
    
        // Print summary
        System.out.println("Total transport mode journeys cost/fare:");
        System.out.println("---------------------------------------------------------");
        System.out.println("Total cost of bus journeys is $" + totalBus);
        System.out.println("Total cost of train journeys is $" + totalTrain);
        System.out.println("Total cost of tram journeys is $" + totalTram);
        System.out.println("---------------------------------------------------------");
    
       // Breakdown by smartcard
        System.out.println("Breakdown by smartcard:");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < smartCardCount; i++) {
            SmartCard card = smartCard[i];
            if (card != null && card.getNumOfJourneys() > 0) {
                System.out.println("SmartCard " + card.getCardID() + ":");
                //all the totals for specific cards
                double cardTotal = 0.0;
                double cardBusTotal = 0.0;
                double cardTrainTotal = 0.0;
                double cardTramTotal = 0.0;
                
                for (int j = 0; j < card.getNumOfJourneys(); j++) {
                    Journey journey = card.getJourney(j);
                    if (journey != null) {
                        double fare = calculateFare(journey.getTransportMode(), journey.getDistanceOfJourney());
                        cardTotal += fare;
                        switch (journey.getTransportMode()) {
                            case "bus":
                                cardBusTotal += fare;
                                break;
                            case "train":
                                cardTrainTotal += fare;
                                break;
                            case "tram":
                                cardTramTotal += fare;
                                break;
                        }
                    }
                }
                
                System.out.println("Total cost of bus journeys is $" + cardBusTotal);
                System.out.println("Total cost of train journeys is $" + cardTrainTotal);
                System.out.println("Total cost of tram journeys is $" + cardTramTotal);
                System.out.println("Total cost of all journeys is $" + cardTotal);
            }
        }
        System.out.println("---------------------------------------------------------");
            
    }

    // Method to calculate fare based on transport mode and distance
    public static double calculateFare(String transportMode, double distance) {
        double baseFare = 1.5;
        double rate = 0.0;
        switch (transportMode) {
            case "bus":
                rate = 1.86;
                break;
            case "train":
                rate = 2.24;
                break;
            case "tram":
                rate = 1.6;
                break;
        }
        return baseFare + rate * distance;
    }
    
    //write to file 
    public static void writeToFile() 
    {
        String fileName; 
        Scanner console = new Scanner(System.in);
        System.out.println("Input desired file name (don't add .txt): ");
        fileName = console.nextLine()+".txt";
        
        PrintWriter outputStream;
        try {
            outputStream = new PrintWriter (fileName);
            if (smartCardCount > 0){
                 for (int i = 0; i < smartCardCount; i++) {
                    SmartCard card = smartCard[i];
                    outputStream.println("SmartCard");
                    outputStream.println("ID "+card.getCardID());
                    outputStream.println("Type "+card.getType());
                    outputStream.println("Balance "+card.getBalance());
                    outputStream.println("");
                    //goes the journeys associated with the smart card
                    if (card.getNumOfJourneys() > 0){
                        for (int j = 0; j < card.getNumOfJourneys(); j++) {
                            Journey journey = card.getJourney(j);
                            outputStream.println("Journeys");
                            outputStream.println("ID "+journey.getJourneyID());
                            outputStream.println("Mode "+journey.getTransportMode());
                            outputStream.println("Start "+journey.getStartOfJourney());
                            outputStream.println("End "+journey.getEndOfJourney());
                            outputStream.println("Distance "+journey.getDistanceOfJourney());
                            outputStream.println("");
                        }
                    }
                    else{
                        System.out.println("Journeys have not been created!");
                    }
                }
            }
            else{
                System.out.println("Smartcards have not been created!");
                return;
            }
            outputStream.close();
            System.out.println("Data written to " + fileName);
        } catch (Exception e) {
            System.out.println("Error writing to the file " + fileName);
        }
    }
    
    //read to file
    public static void readFromFile() throws IOException {
        String fileName = "TravelStats.txt";
        Scanner inputStream = new Scanner (new File(fileName)); 
        try {
            int i = 0;
            //used to find the first empty smart card array slot
            while (smartCard[i] != null){
                i++;
            }
            //goes through file reads line by line till end
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine().trim();
                if (line.equals("SmartCard")) {
                    smartCard[i] = new SmartCard();
                    // Set attributes for each card as needed
                    line = inputStream.nextLine().trim();
                    if (line.startsWith("ID")) {
                        int ID = Integer.parseInt(line.split(" ")[1]); //splits the string into two parts 
                        smartCard[i].setCardID(ID); //sets the second part to the variable
                    }
                    // Read Type
                    line = inputStream.nextLine().trim();
                    if (line.startsWith("Type")) {
                        char type = line.split(" ")[1].charAt(0);
                        smartCard[i].setType(type);
                        smartCard[i].setMaxJourneys(type);
                    }
                    // Read Balance
                    line = inputStream.nextLine().trim();
                    if (line.startsWith("Balance")) {
                        int balance = Integer.parseInt(line.split(" ")[1]);
                        smartCard[i].setBalance(balance);
                    }
                    smartCardCount = smartCardCount + 1;
                }
                
                 if (line.equals("Journeys")) { //this section goes through all journeys associated with array
                    while (inputStream.hasNextLine()) {
                        line = inputStream.nextLine().trim();
                
                        while (!line.equals("SmartCard")){
                            while (smartCard[i].getNumOfJourneys() < smartCard[i].getMaxJourneys()){
                                Journey journey = new Journey();
                                // Read Journey ID
                                line = inputStream.nextLine().trim();
                                if (line.startsWith("ID")) {
                                    int journeyID = Integer.parseInt(line.split(" ")[1]);
                                    journey.setJourneyID(journeyID);
                                }
                                // Read Mode
                                line = inputStream.nextLine().trim();
                                if (line.startsWith("Mode")) {
                                    String mode = line.split(" ")[1];
                                    mode = mode.toLowerCase();
                                    journey.setTransportMode(mode);
                                }
                                // Read Start
                                line = inputStream.nextLine().trim();
                                if (line.startsWith("Start")) {
                                    int start = Integer.parseInt(line.split(" ")[1]);
                                    journey.setStartOfJourney(start);
                                }
                                // Read End
                                line = inputStream.nextLine().trim();
                                if (line.startsWith("End")) {
                                    int end = Integer.parseInt(line.split(" ")[1]);
                                    journey.setEndOfJourney(end);
                                }
                                // Read Distance
                                line = inputStream.nextLine().trim();
                                if (line.startsWith("Distance")) {
                                    int distance = Integer.parseInt(line.split(" ")[1]);
                                    journey.setDistanceOfJourney(distance);
                                }
                                smartCard[i].setJourney(journey);
                                line = inputStream.nextLine().trim(); 
                            }
                            break;
                        }
                        //pretty much seperates a smartcard from another
                        //only really works in this exact file format
                         if (line.equals("")) {
                            i++;
                            break;
                        } 
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error reading from the file " + fileName);
        }
        finally {//ends the file reading section
            inputStream.close();
        }
    }
    //find card method which will return a card based on id
     public static SmartCard findSmartCard(int cardID) { 
        //array of smart cards
        for (int i = 0; i < smartCardCount; i++) {
            // Check if the current smart card's ID matches the given ID
            if (smartCard[i] != null){
                if (smartCard[i].getCardID() == cardID) {
                    return smartCard[i];
                }
            }
        }
        // If no smart card with the given ID is found, return null
        return null;
    }
    //generates a random number
    private static int generateRandomID() {
        Random random = new Random();
        int id = random.nextInt(900) + 100; 
        return id;
    }
}