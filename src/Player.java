import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int points;
    private String name;
    private boolean inGame;

    public Player(String name){
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
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

    public String toString(){
        return name + " has " + points + " points. " + name + "'s cards: " + hand;
    }
}