//Importing necessary javaFX packages and util and io
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.image.*;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.*;
import java.io.*;

//Get ready for quite a long program!
public class Main extends Application 
{ 
  //Making a start method to show the stage
  public void start(Stage primaryStage) throws IOException, FileNotFoundException {
 //Declaring variables for the VBox in the starting scene
    Label prompt; 
    Button ERN;
    Button NRC;
    Button NS;
    Button NPC;

    //Declaring variables for the VBox in the sale scene
    Label saleL;
    TextField code;
    Button button;
    Label result;
    ImageView iv;
    Button RI;
    Button R;

    //Declaring variables for the VBox in the New Rewards Client scene
    Label NRCL;
    TextField NRCTF;
    Button NRCenter;
    
    //Declaring variables for the VBox in the Enter Rewards Number scene
    Label ERNL;
    TextField ERNTF;
    Button ERNenter;

    //Declaring variables for the VBox in the New Platinum Client scene
    Label NPCL;
    TextField NPCTF;
    Button NPCenter;

    //Declaring variables for the VBox in the Remove Item scene
    Label RIL;
    TextField RITF;
    TextField RITF2;
    Button RIenter;
    Button PNR;
    
    //Declaring a "back" button to be used to navigate back to the starting scene 
    Button back;
    Button back2;

    //Declaring different VBoxes to be used in each scene
    VBox vbox;
    VBox sale;
    VBox newRewardsClient;
    VBox enterRewardsNumber;
    VBox newPlatinumClient;
    VBox RemoveItem;

    //Declaring new scenes
    Scene scene;
    Scene saleS;
    Scene newRewardsClientS;
    Scene enterRewardsNumberS;
    Scene newPlatinumClientS;
    Scene RemoveItemS;

    //ArrayList to hold the prices of each item added
    ArrayList<Double> prices = new ArrayList<Double>();
    //ArrayList to store discount
    ArrayList<Integer> discountArr = new ArrayList<Integer>();
    discountArr.add(0);
    //ArrayList to store strings to write to receipt file
    ArrayList<String> dataoutArr = new ArrayList<String>();
    //ArrayList to store price of removed item
    ArrayList<Double> removeditemprice = new ArrayList<Double>();
    
    //Making ArrayLists of type Client and Integer to store rewards/platinum clients and rewards numbers, respectively
    ArrayList<Client> clientlist = new ArrayList<Client>();
    ArrayList<Integer> rewardsnumbers = new ArrayList<Integer>();

    //Initializing and adding a new client to the client list and number list to test the program's functionality
    RewardsClient rc = new RewardsClient("Sam Leggett", 2016, 500);
    clientlist.add(rc);
    rewardsnumbers.add(rc.getRewardsNumber());

    //Initializing the TextFields of the various VBoxes and setting their widths
    code = new TextField("Stock Code");
    code.setMaxWidth(200);

    NRCTF = new TextField("Client Name");
    NRCTF.setMaxWidth(200);

    ERNTF = new TextField("Rewards Number");
    ERNTF.setMaxWidth(200);

    NPCTF = new TextField("Platinum Client Name");
    NPCTF.setMaxWidth(200);

    RITF = new TextField("Item Name");
    RITF.setMaxWidth(200);

    RITF2 = new TextField("Item Price");
    RITF2.setMaxWidth(200);

    //Initializing Labels of various VBoxes (empty result label will show the item, department, and price)
    prompt = new Label("Welcome.");
    result = new Label("");
    saleL = new Label("Enter the stock code of the desired item.");
    NRCL = new Label("Enter the name of the new rewards member.");
    ERNL = new Label("Enter the rewards number of the customer.");
    NPCL = new Label("Enter the name of the new platinum rewards member.");
    RIL = new Label("Enter the name and price of the item you wish to remove.");

    //Initializing ImageView as the welcome smiley face
    iv = new ImageView("welcome.png");

    //Initializing buttons of various VBoxes
    button = new Button("Enter"); 
    ERN = new Button("Enter Rewards Number");
    ERNenter = new Button("Enter");
    NRC = new Button("New Rewards Client");
    R = new Button("Print Receipt");
    RI = new Button("Remove Item");
    NS = new Button("New Sale");
    NRCenter = new Button("Enter");
    back = new Button("Back");
    back2 = new Button("Back");
    NPCenter = new Button("Enter");
    NPC = new Button("New Platinum Client");
    RIenter = new Button("Enter");
    PNR = new Button("Print New Receipt");

    //Setting size of image
    iv.setFitHeight(30);
    iv.setFitWidth(30);
  
    //Initializing starting scene VBox, setting its dimensions/location, and adding it to the scene
    vbox = new VBox(prompt, NS, NRC, NPC, ERN, iv);
    vbox.setSpacing(20);
    vbox.setAlignment(Pos.CENTER);
    scene = new Scene(vbox, 400, 300);

    //Initializing sale scene VBox, setting its dimensions/location, and adding it to the sale scene
    sale = new VBox(saleL, code, button, result, iv, R, RI);
    sale.setSpacing(20);
    sale.setAlignment(Pos.CENTER);
    saleS = new Scene(sale, 400, 600);

    //Initializing New Rewards Client scene VBox, setting its dimensions/location, and adding it to the scene
    newRewardsClient = new VBox(NRCL, NRCTF, NRCenter, back);
    newRewardsClient.setSpacing(20);
    newRewardsClient.setAlignment(Pos.CENTER);
    newRewardsClientS = new Scene(newRewardsClient, 400, 600);

    //Initializing New Platinum Client scene VBox, setting its dimensions/location, and adding it to the scene
    newPlatinumClient = new VBox(NPCL, NPCTF, NPCenter, back2);
    newPlatinumClient.setSpacing(20);
    newPlatinumClient.setAlignment(Pos.CENTER);
    newPlatinumClientS = new Scene(newPlatinumClient, 400, 600);

    //Initializing Enter Rewards Number scene VBox, setting its dimensions/location, and adding it to the scene
    enterRewardsNumber = new VBox(ERNL, ERNTF, ERNenter);
    enterRewardsNumber.setSpacing(20);
    enterRewardsNumber.setAlignment(Pos.CENTER);
    enterRewardsNumberS = new Scene(enterRewardsNumber, 400, 600);

    //Initializing Remove Item scene VBox, setting its dimensions/location, and adding it to the scene
    RemoveItem = new VBox(RIL, RITF, RITF2, RIenter, PNR);
    RemoveItem.setSpacing(20);
    RemoveItem.setAlignment(Pos.CENTER);
    RemoveItemS = new Scene(RemoveItem, 400, 600);
    
    //using setOnAction method of enter button to program what should execute when "enter" is pressed in the sale scene
    button.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {

        //New FIS to read file and int to assign to the file data and String to piece together data
        FileInputStream f = null;
        int i = 0;
        String data = new String("");
        //New FOS to write data to the Reciept file and String to piece together data to write
        FileOutputStream fout = null;
        String dataout = new String("");
        //attempting to open Data.dat and Reciept.dat files
        try{
          f = new FileInputStream("Data.dat");
          fout = new FileOutputStream("Receipt.dat", true);
        }
        catch(FileNotFoundException exc){
          System.out.println("File not found.");
        }
        //attempting to read data, organize it, and display it to the screen
        try{
        //do/while loop to read each character in data and add it to string for data
        do{
        i = f.read();
        data += (char)(i);
        }
        while(i!=-1);
        //making an array of each line in the data file as strings
        String lines[] = data.split("\\r?\\n");
        //finding the line of the item based on the stock code entered
        int line = Integer.parseInt(code.getText());
        //separating the desired line by spaces and adding it to a new array of all the words in the line
        Scanner scan = new Scanner(lines[line-1]);
        ArrayList<String> allwords = new ArrayList<String>();
        while(scan.hasNext()){
          String current = scan.next();
          allwords.add(current);
        }
        //variable to hold the department of the item; will be useful in switch statement
        String dept = allwords.get(1);
        //setting the text of the result label to show the name, department, and price of the item
        result.setText("Department: " + dept + " Name: " + allwords.get(2) + " Price: $" + allwords.get(3));
        //Adding price of current item to list of prices
        prices.add(Double.parseDouble(allwords.get(3)));
        //Constructing a String to be written to Receipt file
        dataout = allwords.get(2) + ".....$" + allwords.get(3) + "\n";
        dataoutArr.add(dataout);
        //Writing String to Receipt file
        for(int a = 0; a < dataout.length(); a++){
          fout.write(dataout.charAt(a));
        }
        //setting the correct image based on department
        switch(dept){
          case "Lumber":
            iv.setImage(new Image("wood.jpeg"));
            break;
          case "Electrical":
            iv.setImage(new Image("newlightbulb.png"));
            break;
          case "Tools":
            iv.setImage(new Image("hammer.jpeg"));
            break;
          case "Garden":
            iv.setImage(new Image("garden.jpeg"));
            break;
          case "Plumbing":
            iv.setImage(new Image("plunger.png"));
            break;
        }//end switch
        }//end try

        //Printing appropriate error messages/changing image to validate user input
        catch(IOException exc){
          System.out.println("Error");
        }
        catch(ArrayIndexOutOfBoundsException exc){
          result.setText("Stock code does not exist.");
          iv.setImage(new Image("no.png"));
        }
        catch(NumberFormatException exc){
          result.setText("Please enter a number.");
          iv.setImage(new Image("no.png"));
        }
        
      }//end handle method
    });//end button action setter
    
    //Setting the primaryStage to the new Sale scene when new sale button is pressed
    NS.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        primaryStage.setScene(saleS);
      }
    });

    //Setting the primaryStage to the Remove Item scene when NPC button is pressed
    RI.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        primaryStage.setScene(RemoveItemS);
      }
    });

    //Setting the primaryStage to the new Platinum Client scene when NPC button is pressed
    NPC.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        primaryStage.setScene(newPlatinumClientS);
      }
    });
    
    //Setting the primaryStage to the New Rewards Client scene when NRC button is pressed
    NRC.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        primaryStage.setScene(newRewardsClientS);
      }
    });

    //Specifying what to do when a new rewards client name is entered
    NRCenter.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        //Setting rewardsnumber to a random number between 1 and 9999 and resetting it as long as the number is already taken
        int rewardsnumber = (int)(Math.random() * 10000);
        while(rewardsnumbers.contains(rewardsnumber)){
          rewardsnumber = (int)(Math.random() * 10000);
        }
        //Adding the new rewards number to the list and making a new rewards client and adding it to the list
        rewardsnumbers.add(rewardsnumber);
        RewardsClient c = new RewardsClient(NRCTF.getText(), 2022, rewardsnumber);
        clientlist.add(c);
        //Telling the user the member was added by setting the text of the label
        NRCL.setText("New member added. Rewards number is: " + rewardsnumber);
      }
    });

    //Specifying what to do when a new platinum client name is entered
    NPCenter.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        //Setting rewardsnumber to a random number between 1 and 9999 and resetting it as long as the number is already taken
        int rewardsnumber = (int)(Math.random() * 10000);
        while(rewardsnumbers.contains(rewardsnumber)){
          rewardsnumber = (int)(Math.random() * 10000);
        }
        //Adding the new rewards number to the list and making a new rewards client and adding it to the list
        rewardsnumbers.add(rewardsnumber);
        PlatinumClient c = new PlatinumClient(NPCTF.getText(), 2022, rewardsnumber);
        clientlist.add(c);
        //Telling the user the member was added by setting the text of the label
        NPCL.setText("New platinum member added. Rewards number is: " + rewardsnumber);
      }
    });

    //Switching the primaryStage to the main scene if back button is pressed
    back.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        primaryStage.setScene(scene);
      }
    });

    //Switching the primaryStage to the main scene if back button is pressed
    back2.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        primaryStage.setScene(scene);
      }
    });

    //Setting the primaryStage to the ERN scene when ERN button is pressed
    ERN.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        primaryStage.setScene(enterRewardsNumberS);
      }
    });

    //Specifying what to do when a rewards number is entered
    ERNenter.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        //New FOS to write to receipt file
        FileOutputStream fout = null;
        try{
        fout = new FileOutputStream("Receipt.dat", true);
        //For each number in the rewardsnumbers list...
        for(int i = 0; i<rewardsnumbers.size(); i++){
          //If the number entered is in the list...
          if(rewardsnumbers.get(i) == Integer.parseInt(ERNTF.getText())){
            //String dataout is constructed by the discount
            String dataout = new String(clientlist.get(i).getInfo() + "\nDiscount....." + clientlist.get(i).getDiscount() + "%\n");
            discountArr.remove(0);
            discountArr.add(clientlist.get(i).getDiscount());
            //Attempting to write client info and discount value to the receipt file
            for(int a = 0; a < dataout.length(); a++){
            fout.write(dataout.charAt(a));
            }
            //Setting the primaryStage to the sale scene after rewards client is specified
            primaryStage.setScene(saleS);
          }
        //If number isn't in the list, label text says so
        ERNL.setText("Couldn't find rewards number.");
        }
      }
        catch(FileNotFoundException exc){
          System.out.println("File not found.");
        }
        catch(NumberFormatException exc){
          ERNL.setText("Couldn't find rewards number.");
        }
        catch(IOException b){
          System.out.println("IOException");
        }
      }
    });
    
    //setOnAction method to print receipt
    R.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
      //attempting to...
      try{
        //initialize double to hould the total price
        double Total = 0;
        //initialize FIS to read data from receipt file and int to help read
        FileInputStream f = new FileInputStream("Receipt.dat");
        int i = 0;
        //print out receipt to console
        String receiptS = new String("\n---RECEIPT---\n");
        //adding data from receipt file to string
        do{
          i = f.read();
          receiptS += (char)(i);
        } 
        while(i!=-1);
        //printing string receipt
        System.out.println(receiptS);
        //adding each price to total
        for(int a = 0; a<prices.size(); a++){
          Total+=prices.get(a);
        }
        //printing subtotal and total minus the discount
        System.out.println("Subtotal: $" + Total);
        System.out.println("Total: $" + Math.round(Total*((100-discountArr.get(0))/100.0)*100)/100.0);
      }
        //catching exception
      catch(IOException exc){
        System.out.println("IOException");
      }
      }
    });

    //setOnAction method to remove item 
    RIenter.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        //removing last instance of specified item from arraylist
        int temp = 0;
        double temp2 = 0;
        double removedPrice = 0;
        FileOutputStream fout = null;
        try{
        //for each element in the data written to the receipt...
        for(int b = 0; b<dataoutArr.size(); b++){
          //if the element matches the search...
        if(dataoutArr.get(b).toUpperCase().contains(RITF.getText().toUpperCase())){
            temp = b;
      }
        
        }
          //remove said element
        dataoutArr.remove(temp);

        //if the price entered is in the prices array...
        for(int b = 0; b<prices.size(); b++){
          if(prices.get(b)==Double.parseDouble(RITF2.getText())){
            temp2 = b;
          }
        }
        //remove said price and add to removed price array TO FIX KNOWN BUG
        prices.remove(temp2);
        removeditemprice.add(Double.parseDouble(RITF2.getText()));

        //add all information to dataout string
        String dataout = new String("");
        dataout+="-----MODIFIED RECEIPT-----\n";
        for(int i = 0; i<dataoutArr.size(); i++){
          dataout+=dataoutArr.get(i);
        }
        
        //write string to receipt file
        fout = new FileOutputStream("Receipt.dat", true);
        for(int a = 0; a<dataout.length(); a++){
            fout.write(dataout.charAt(a));
        } 
        }
        catch(IOException exc){
          System.out.println("IOException");
        }
        catch(NumberFormatException exc){
          RIL.setText("Please enter information in the correct format.");
        }
        catch(IndexOutOfBoundsException exc){
          RIL.setText("Item not found.");
        }
      }
    });

    //setOnAction to print new receipt after item is removed
    PNR.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
      //attempting to...
      try{
        //initialize double to hould the total price
        double Total = 0;
        //initialize FIS to read data from receipt file and int to help read
        FileInputStream f = new FileInputStream("Receipt.dat");
        int i = 0;
        //print out receipt to console
        String receiptS = new String("\n---RECEIPT---\n");
        //adding data from receipt file to string
        do{
          i = f.read();
          System.out.print((char)(i));
        } 
        while(i!=-1);
        
        //adding each price to total and removing removed item price
        for(int a = 0; a<prices.size(); a++){
          Total+=prices.get(a);
        }
        Total-=removeditemprice.get(0);
        //printing subtotal and total minus the discount
        System.out.println("Subtotal: $" + Total);
        System.out.println("Total: $" + Math.round(Total*((100-discountArr.get(0))/100.0)*100)/100.0);
      }
        //catching exception
      catch(IOException exc){
        System.out.println("IOException");
      }
      }
    });

    //setting stage title and scene and finally showing it
    primaryStage.setTitle("Cash Register");
    primaryStage.setScene(scene);
    primaryStage.show();

    //Attempting to close Receipt file when program is run so it is empty at the start
    try{
      new FileOutputStream("Receipt.dat").close();
    }
    catch(IOException e){
      System.out.println("IOException");
    }
    
  }//end start method 

  //main method launches application
  public static void main(String[] args) throws IOException, FileNotFoundException{
    launch(args);
  }
} 
