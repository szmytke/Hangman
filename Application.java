import java.util.Scanner;

/**
 * Class with main, contols workflow.
 */
public class Application{

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        
        readFile.openFile();
        readFile.readFile();
        readFile.closeFile();
        
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain){
            
            Game game = new Game();
            System.out.println(game.getCorrectCapital()); // added developer feature
            System.out.println(game.getHiddenWord());
            while (!game.getHiddenWord().equals(game.getCorrectCapital()) && (game.getPlayer().getPlayersLife() > 0)){
                game.printInfo();
                System.out.println("Enter a letter or word: ");
                String letter = scanner.nextLine();
                game.askForAnswer(letter);
                System.out.println(game.getHiddenWord());
                game.displayHint();
            }
            game.isWinCheck(game.getCorrectCapital(), game.getHiddenWord());
            playAgain = game.playAgainCheck();
        }
        scanner.close();
    }
}
