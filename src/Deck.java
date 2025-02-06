import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values, GameViewer window) {
        this.cardsLeft = 0;
        this.cards = new ArrayList<Card>();
        // creates every combination of cards with (ranks/values) and suits
        for (int i = 0; i < ranks.length; i++){
            for (int j = 0; j < suits.length; j++){
                this.cards.add(new Card(ranks[i], suits[j], values[i], window));
                this.cardsLeft++;
            }
        }
        this.shuffleCards();
    }

    public boolean isEmpty(){
        if(cardsLeft == 0){
            return true;
        }
        return false;
    }

    public int getCardsLeft(){
        return cardsLeft;
    }

    public Card deal(){
        if(this.isEmpty()){
            return null;
        }
        return cards.get(--cardsLeft);
    }

    public void shuffleCards(){
        Card copy;
        for (int i = 0; i < cardsLeft; i++){
            // sets a random card to the card at index i
            copy = cards.set((int)(Math.random()*(cardsLeft)), cards.get(i));
            // sets the card at index i to the random card
            cards.set(i, copy);
        }
    }
}
