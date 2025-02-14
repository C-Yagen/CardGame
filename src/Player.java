import java.util.ArrayList;
import java.awt.*;

public class Player {
    private ArrayList<Card> hand;
    private int points;
    private String name;
    private boolean inGame;

    public Player(String name){
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
        this.inGame = true;
    }

    public Player(String name, ArrayList<Card> hand){
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
        for (int i = 0; i < hand.size(); i++){
            this.hand.add(hand.get(i));
        }
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isInGame() {
        return inGame;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addPoints(int num){
        points+=num;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    // returns the value of all the cards in a player's hand
    public int sumCardValue(){
        int sum = 0;
        // aces are worth 1 but if they could be worth 11 without busting, they are
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

    public String toString(){
        return name + " has " + points + " points. " + name + "'s cards: " + hand;
    }

    public void drawPlayer(Graphics g){
        // offSet is where the card on the far left is, it's based on # of cards and ensures the hand is centered
        int offSet = GameViewer.WINDOW_WIDTH_CENTER - (int)(0.5*(hand.size()*Card.CARDWIDTH + (hand.size()-1)*Card.CARDWIDTH*0.5));
        for (int i = 0; i < hand.size(); i++){
            hand.get(i).draw(g, offSet + i * (int)(Card.CARDWIDTH * 1.5), GameViewer.WINDOW_HEIGHT - 200);
        }
    }

    public void drawDealer(Graphics g){
        // the dealer is drawn just like other players but is drawn at the top of the screen
        int offSet = GameViewer.WINDOW_WIDTH_CENTER - (int)(0.5*(hand.size()*Card.CARDWIDTH + (hand.size()-1)*Card.CARDWIDTH*0.5));
        for (int i = 0; i < hand.size(); i++){
            if (i == 0){
                hand.get(i).drawHidden(g, offSet + i * (int)(Card.CARDWIDTH * 1.5),100);
            }
            else {
                hand.get(i).draw(g, offSet + i * (int)(Card.CARDWIDTH * 1.5),100);
            }

        }
    }
}