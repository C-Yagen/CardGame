import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private ArrayList<Card> shownCards;
    private Scanner s = new Scanner(System.in);

    public Game(){
        players = new ArrayList<Player>();
        shownCards = new ArrayList<Card>();
        // asks how many players and their names
        System.out.println("How many players:");
        int numPlayers = s.nextInt();
        s.nextLine();
        // initializes players in arraylist
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + " name:");
            String player = s.nextLine();
            players.add(new Player(player));
        }
        // creates deck
        String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        this.deck = new Deck(ranks, suits, values);

    }

    public void printInstructions(){
        System.out.println("");
    }

    public void playGame(){
        // deals 2 cards to each player
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 2; j++) {
                players.get(i).addCard(deck.deal());
            }
        }
        for (int i = 0; i < 3; i++) {
            shownCards.add(deck.deal());
        }
        System.out.println(shownCards);


    }

    public void swapFoldPhase(Player player){
        int n = -2;
        // prompts the user if they want to swap a card from their hand with one of the shown cards or fold
        while (n < -1 || n > shownCards.size()){
            System.out.println("type -1 to fold, 0 to do nothing, or type the number of the card you want to swap with");
            / not done
            n = s.nextInt();
        }
        if (n == -1){
            player.setInGame(false);
            return;
        }
        if (n == 0) {
            return;
        }
    }



    public static void main(String[] args) {
        Game game1 = new Game();
        game1.playGame();
    }

}
