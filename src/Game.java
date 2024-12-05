import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Player dealer;
    private Scanner s = new Scanner(System.in);

    public Game(){
        dealer = new Player("dealer");
        players = new ArrayList<Player>();
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
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1};
        this.deck = new Deck(ranks, suits, values);

    }

    public void printInstructions(){
        System.out.println("This is Blackjack, to play this game you want to get as close to 21 as possible without going over");
    }

    public void playGame(){
        // deals 2 cards to each player
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 2; j++) {
                players.get(i).addCard(deck.deal());
            }
        }
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
        System.out.println("The dealer has a " + dealer.getHand().get(0).toString() + " showing");
        // do players' turns
        for (int i = 0; i < players.size(); i++){
            this.hitOrStay(players.get(i));
        }
        // dealer's turn
        while(sumCardValue(dealer) < 16){
            dealer.addCard(deck.deal());
        }
        int dealerTotal = sumCardValue(dealer);
        System.out.println("The dealer has a total of " + dealerTotal);

        // Runs the case where the dealer busted
        if (dealerTotal > 21){
            System.out.println("The dealer Busted!");
            for (int i = 0; i < players.size(); i++) {
                if(players.get(i).isInGame()){
                    System.out.println(players.get(i).getName() + " won!");
                }
            }
        }
        for (int i = 0; i < players.size(); i++) {
            // checks if the player is still in the game and if their score is better than the dealer
            if (Game.sumCardValue(players.get(i)) > dealerTotal && players.get(i).isInGame()){
                System.out.println(players.get(i).getName() + " won!");
            }
        }
    }

    // does a player's turn
    public void hitOrStay(Player player){
        boolean hit = true;
        while(Game.sumCardValue(player) < 21 && hit){
            System.out.println("Current cards value: " + Game.sumCardValue(player));
            System.out.println("Enter false to stay and true to hit");
            hit = s.nextBoolean();
            s.nextLine();
            if (hit) {
                player.addCard(deck.deal());
            }

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
        for (int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).getValue();
        }
        return sum;
    }


    public static void main(String[] args) {
        Game game1 = new Game();
        game1.playGame();
    }

}
