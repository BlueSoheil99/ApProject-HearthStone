package project01.Players;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project01.CartsAndHeros.Cart;
import project01.CartsAndHeros.Hero;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Player {
//    private static Logger logFile;

    private String   userName;
    private String   password; //or make a inner class named password
    private int userId;
    private int  numberOfCoins =50;
    private Cart[]  totalCarts;
    private Hero[]  heroes;
    private String profilePath;
    private String logPath;


    public Player(String newUser , String newPass ) {
        userId = new File("src\\Data\\Logs").list().length + 1 ;
        this.userName = newUser;
        this.password = newPass ;// try to make password as a hashcode( by an inner class or a method)
        profilePath = "src/Data/Profiles/"+ this.getUserName()+".json";
        logPath     = "src/Data/Logs/"+ this.getUserName()+"-"+this.getUserId()+".log";
        //set carts
        //set heroes

                //create log
        this.logInit();
    }


//setter

    public void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    public void setHeroes(Hero[] heroes) {
        this.heroes = heroes;
    }

    public void setTotalCarts(Cart[] totalCarts) {
        this.totalCarts = totalCarts;
    }

//getter

    public int getUserId() {
        return userId;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public String getProfilePath() {
        return profilePath;
    }
    public String getLogPath() {
        return logPath;
    }


    private void logInit(){
        try {
            FileWriter writer = new FileWriter(this.getLogPath());
            PrintWriter printer =new PrintWriter(writer);
            printer.println("USER: "+this.getUserName());
            printer.println("CREATED_AT: " + LocalDateTime.now().format( DateTimeFormatter.ofPattern(" yyyy/MM/dd  HH:mm:ss") ));
            printer.println("PASSWORD: "+this.getPassword());
            printer.println(" ");

            printer.close();
            //or writer.close() ??????
        }catch (Exception e ) {
            System.err.println(e.getMessage());
        }
    }
    void logFinalize(){  // in this method we used the link in README + the way we made logInit method
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(logPath));
            StringBuffer inputBuffer = new StringBuffer();
            String line ;

            while ( (line= file.readLine()) != null) {
                if(line.equals(" ")) {
                    inputBuffer.append("DELETED_AT: " +
                                       LocalDateTime.now().format( DateTimeFormatter.ofPattern(" yyyy/MM/dd  HH:mm:ss"))+"\n \r\n" );
                                        // \r\n to make a distance between header and body
                }else{
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }
            file.close();
            // i tried using FileInputStreamer but it didn't work well so i did the same as i did in logInit method
            String inputStr = inputBuffer.toString();
            FileWriter writer = new FileWriter(this.getLogPath());
            PrintWriter printer =new PrintWriter(writer);
            for (String x:inputStr.split("\n")) {
                printer.println(x);
            }
            printer.close();

        }catch (Exception e){
          System.err.println(e.getMessage());
        }

    }

    /**
     * rewrites the player's information on its profile
     */
    public void saveData(){
        try{
            FileWriter writer = new FileWriter(this.getProfilePath());
            PrintWriter printer = new PrintWriter(writer);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);
            printer.println( json );
            printer.close(); //or flush() ?
        }catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }

}
