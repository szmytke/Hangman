import java.util.Scanner;
import java.util.Date;
import java.lang.System;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;


public class LeaderBoard{

    private String playerName;
    private Date date;

    public LeaderBoard(){
        this.date = new Date();
    }

    /**
     * Prepare alle needed information about high score.
     * @param playerName Name of the player.
     * @param elapseTime Time elaipsed for one game.
     * @param correctCapital Looking capital.
     * @param numberOfGuess Number of guess needed to guess the name.
     * @return String with whole information about high score.
     */
    public String prepareHighScore(String playerName, long elapsedTime, String correctCapital, int numberOfGuesses){
        
        SimpleDateFormat ft = 
        new SimpleDateFormat ("dd.MM.yyyy HH:mm");

        String date = ft.format(this.date).toString();
        String infoToSave = playerName + "|" + date + "|" + elapsedTime + "|" + 
                            numberOfGuesses + "|" + correctCapital.toUpperCase();
        return infoToSave;
    }
    /**
     * Saves information about high score to file.
     * @param infoToSave String with whole information about high score.
     * @return nothing
     */
    public void saveHighScore(String infoToSave){
        
        try (FileWriter fw = new FileWriter("high_score.txt", true)){
        PrintWriter out = new PrintWriter(fw);
        out.println(infoToSave);
        out.close();
        }
        catch (IOException ex){
            System.out.println("Thers no file to save!");
        }
    }
}
