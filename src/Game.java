//Poker by Connor Yagen Finalized on 12/5/24
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Player> winners;
    private Deck deck;
    private Player dealer;
    private Scanner s = new Scanner(System.in);
    private GameViewer window;
    private int gameState;
    private Player currentPlayer;

    public Game(){
        gameState = 0;
        window = new GameViewer(this);
        dealer = new Player("Dealer");
        players = new ArrayList<Player>();
        winners = new ArrayList<Player>();
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
        // updates window
        gameState = 1;
        // creates deck
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1};
        this.deck = new Deck(ranks, suits, values, window);

    }

    public int getGameState() {
        return gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Player> getWinners() {
        return winners;
    }

    public void printInstructions(){
        System.out.println("This is Blackjack, to play this game you want to get as close to 21 as possible without going over.\nHowever, you must also get a higher value hand than the dealer");
    }

    public void playGame(){
        this.printInstructions();
        // deals 2 cards to each player
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 2; j++) {
                players.get(i).addCard(deck.deal());
            }
        }
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
        System.out.println("The dealer has a " + dealer.getHand().get(1).toString() + " showing");
        // do players' turns
        for (int i = 0; i < players.size(); i++){
            currentPlayer = players.get(i);
            this.hitOrStay(players.get(i));
        }
        // dealer's turn
        while(sumCardValue(dealer) < 16){
            dealer.addCard(deck.deal());
        }
        // # of point dealer has
        int dealerTotal = sumCardValue(dealer);
        System.out.println("The dealer has a total of " + dealerTotal);

        // Runs the case where the dealer busted
        if (dealerTotal > 21){
            System.out.println("The dealer Busted!");
            for (int i = 0; i < players.size(); i++) {
                if(players.get(i).isInGame()){
                    winners.add(players.get(i));
                    System.out.println(players.get(i).getName() + " won!");
                }
            }
            gameState = 2;
            window.repaint();
            return;
        }
        for (int i = 0; i < players.size(); i++) {
            // checks if the player is still in the game and if their score is better than the dealer
            if (Game.sumCardValue(players.get(i)) > dealerTotal && players.get(i).isInGame()){
                winners.add(players.get(i));
                System.out.println(players.get(i).getName() + " won!");
            }
        }
        if (winners.isEmpty()){
            winners.add(dealer);
            System.out.println("The Dealer Won!");
        }
        gameState = 2;
        window.repaint();
    }

    // does a player's turn
    public void hitOrStay(Player player){
        boolean hit = true;
        window.repaint();
        while(Game.sumCardValue(player) < 21 && hit){
            System.out.println("Current cards value: " + Game.sumCardValue(player));
            System.out.println("Enter false to stay and true to hit");
            hit = s.nextBoolean();
            s.nextLine();
            if (hit) {
                player.addCard(deck.deal());
            }
            window.repaint();

            if(Game.sumCardValue(player) > 21){
                System.out.println("You busted!");
                player.setInGame(false);
                return;
            }
            if(Game.sumCardValue(player) == 21){
                System.out.println("Blackjack!");
                return;
            }
        }
    }

    // returns the value of all the cards in a player's hand
    public static int sumCardValue(Player player){
        ArrayList<Card> hand = player.getHand();
        int sum = 0;
        Boolean ace = false;
        for (int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).getValue();
            if (hand.get(i).getRank().equals("A")){
                ace = true;
            }
        }
        if (sum < 12 && ace){
            return sum + 10;
        }
        return sum;
    }

    public Player getDealer() {
        return dealer;
    }

    public static void main(String[] args) {
        Game game1 = new Game();
        game1.playGame();
    }

}
