import java.util.Random;
import java.util.ArrayList;
import java.lang.Character;
import java.util.Scanner;
import java.lang.System;


public class Game{

    private String correctCapital;
    private String correctCountry;
    private String hitLetters;
    private String missLetters;
    private Player player;
    private String hiddenWord;
    private int countingGuess;
    private long start;
    private LeaderBoard leaderBoard;
    
    public Game(){
        Random rand = new Random();
        int numbOfCapital = rand.nextInt(183);
        this.correctCapital = Capital.getListCapital().get(numbOfCapital).getName().toLowerCase();
        this.correctCountry = Capital.getListCapital().get(numbOfCapital).getCountry().toLowerCase();
        this.hitLetters = "";
        this.missLetters = "";
        this.player = new Player();
        this.hiddenWord = returnHiddenWord();
        this.countingGuess = 0;
        this.start = System.currentTimeMillis();
        this.leaderBoard = new LeaderBoard();

    }
    /**
     * Takes the word to guess and convert it to hidden word.
     * @return Hidden word.
     */
    public String returnHiddenWord(){
        String hiddenWord = "";
        for (int i=0 ; i < this.correctCapital.length(); i++){
            if (Character.isWhitespace(correctCapital.charAt(i))){
                hiddenWord += " ";
            }
            else{
            hiddenWord += "_";
            }
        }
        return hiddenWord;
    }

    /**
     * Takes letter and check if it is in correct capital, 
     * if yes add letter hidden word.
     * @param hiddenWord 
     * @param letter 
     * @return Hidden word with guessed letter or only hidden word.
     */
    public String unhideLetter(String hiddenWord, char letter){
        for (int i = 0; i < this.correctCapital.length(); i++){
            if (Character.toUpperCase(letter) == Character.toUpperCase(this.correctCapital.charAt(i))) {
                this.hitLetters += letter;
                hiddenWord = hiddenWord.substring(0, i) + this.correctCapital.charAt(i) + hiddenWord.substring(i+1, this.correctCapital.length());
            }
        }
        return hiddenWord; 
    }

    /**
     * Takes a letter and test it, firs if its realy letter then if its not used again.
     * @param letter letter to guess.
     * @return The square root of the given number.
     */
    private boolean testLetter(char letter) {
        boolean letterCheck;
        if (! Character.isLetter(letter)) {
            System.out.println("A letter is required");

            return letterCheck = false;
        }
        letter = Character.toLowerCase(letter);
        if (missLetters.indexOf(letter) != -1 || hitLetters.indexOf(letter) != -1) {
            System.out.format("You use this letter!: %c %n", letter);

            return letterCheck = false;
        }
        return letterCheck = true;
    }

    /**
     * Takes a word and check three things, if word is looking capital, if word has the same 
     * lenght like looking capital, if word is only one letter.
     * @param word The word to check.
     * @return nothing
     */
    public void askForAnswer(String word){
        if (word.toLowerCase().equals(this.correctCapital)){
            setHiddenWord(word);
            setCountingGuess();
        }
        else if (word.length() == this.correctCapital.length()){
            int playersLife = this.player.getPlayersLife() - 2;
            this.player.setPlayersLife(playersLife);
            setCountingGuess();
        }
        else if (word.length() == 1){
            askForAnswer(word.charAt(0));
        }
        else{
            System.out.println("Wrong guess, you can only try one letter or whole word!");
        }
    }

    /**
     * Tahes a letter and check if it is on looking capital, if yes add to looking capital, if not 
     * player lose life.
     * @param letter The letter to check.
     * @return nothing
     */
    public void askForAnswer(char letter){
        boolean letterCheck = testLetter(letter);
        if (this.correctCapital.indexOf(letter) != -1 && letterCheck){
            setHiddenWord(unhideLetter(this.hiddenWord, letter));
            setCountingGuess();
        }

        else if (this.correctCapital.indexOf(letter) == -1 && letterCheck) {
            this.missLetters += letter;
            int playersLife = this.player.getPlayersLife() - 1;
            this.player.setPlayersLife(playersLife);
            setCountingGuess();
        }
    }
    
    /**
     * Print information about players life points and list of letters 
     * used but not found in looking capital.
     * @return nothing
     */
    public void printInfo(){
        System.out.print("Number of your life: ");
        System.out.println(player.getPlayersLife());
        String notInWord = "";
        for (int i = 0; i < missLetters.length(); i++){
            notInWord += missLetters.charAt(i) + "-";
        }
        System.out.print("Letters not in word: ");
        System.out.println(notInWord);
    }

    /**
     * Check if player guess the looking capital and win game.
     * @param word Word containing looking capital.
     * @param hiddenWord Converted looking capital to hidden word.
     * @return nothing
     */
    public void isWinCheck(String word, String hiddenWord){
        if (hiddenWord.equals(word) || getPlayer().getPlayersLife() > 0){
                System.out.println("You win!!!");
                long end = System.currentTimeMillis();
                long elapsedTime = ((end - this.start) / 1000);
                System.out.format("You guessed after %d attempt. It took you %d seconds %n",getCountingGuess(), elapsedTime);
                System.out.println("Please enter your name");
                Scanner scan = new Scanner(System.in);
                String playerName = scan.nextLine();
                String infoToSave = getLeaderBoard().prepareHighScore(playerName, elapsedTime, this.correctCapital, getCountingGuess());
                System.out.println(infoToSave);
                getLeaderBoard().saveHighScore(infoToSave);
        }
        else {
            System.out.format("You loose!!! The capital to guess was: %s%n", word.toUpperCase());
        }
    }
    
    /**
     * Displays hint if player has only one point of life.
     * @return nothing
     */
    public void displayHint(){
        if (this.player.getPlayersLife() == 1){
            System.out.format("The capital of %s%n", this.correctCountry.toUpperCase());
        }
    }

    /**
     * Ask player if he want to play again.
     * @return nothing
     */
    public boolean playAgainCheck(){
        boolean playAgain;
        System.out.println("Do you want to play again? <yes/no>");
        Scanner x = new Scanner(System.in);
        String answer = x.next().toLowerCase(); 
        if(answer.equals("yes")){
            playAgain = true;
        }
        else{
            playAgain = false;
        }
        return playAgain;
    }
    public String getCorrectCapital(){
        return this.correctCapital;
    }
    public String getCorrectCountry(){
        return this.correctCountry;
    }
    public Player getPlayer(){
        return this.player;
    }
    public String getHiddenWord(){
        return this.hiddenWord;
    }
    public void setHiddenWord(String hiddenWord){
        this.hiddenWord = hiddenWord;
    }
    public void setCountingGuess(){
        this.countingGuess += 1;
    }
    public int getCountingGuess(){
        return this.countingGuess;
    }
    public LeaderBoard getLeaderBoard(){
        return this.leaderBoard;
    }

}