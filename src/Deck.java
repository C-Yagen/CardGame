import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values) {
        this.cardsLeft = 0;
        this.cards = new ArrayList<Card>();
        for (int i = 0; i < ranks.length; i++){
            for (int j = 0; j < suits.length; j++){
                this.cards.add(new Card(ranks[i], suits[j], values[i]));
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
        return cards.get(--cardsLeft);
    }

    public void shuffleCards(){
        Card copy;
        for (int i = 0; i < cards.size(); i++){
            copy = cards.set((int)(Math.random()*53), cards.get(i));
            cards.set(i, copy);
        }
    }
}
