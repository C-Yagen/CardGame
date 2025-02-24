import java.awt.*;
public class Card {

    private String rank;
    private String suit;
    private int value;
    private GameViewer window;
    public static final int CARDWIDTH = 75;
    public static final int CARDHEIGHT = 150;

    public Card(String rank, String suit, int value, GameViewer window) {
        this.window = window;
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    public void draw(Graphics g, int x, int y){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, CARDWIDTH, CARDHEIGHT);
        // sets color scheme for card
        if (suit.equals("Diamonds") || suit.equals("Hearts")){
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawRect(x, y, CARDWIDTH, CARDHEIGHT);
        // Draws the rank and image onto the card
        g.drawString(rank, x + 5, y + CARDWIDTH/2);
        // clubs are drawn slightly differently since they span the entire card
        if (suit.equals("Clubs")){
            g.drawImage(Game.CLUBIMAGE, x + 1, y + CARDWIDTH/2, CARDWIDTH - 2, CARDWIDTH, window);
        } else if (suit.equals("Diamonds")) {
            g.drawImage(Game.DIAMONDIMAGE, x, y + CARDWIDTH/2, CARDWIDTH, CARDWIDTH, window);
        }else if (suit.equals("Hearts")) {
            g.drawImage(Game.HEARTIMAGE, x, y + CARDWIDTH/2, CARDWIDTH, CARDWIDTH, window);
        }else {
            g.drawImage(Game.SPADEIMAGE, x, y + CARDWIDTH/2, CARDWIDTH, CARDWIDTH, window);
        }
        g.setColor(Color.BLACK);
    }

    public void drawHidden(Graphics g, int x, int y){
        // for the dealer's hidden card, just draws a club and diamond on the card
        g.setColor(Color.WHITE);
        g.fillRect(x, y, CARDWIDTH, CARDHEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, CARDWIDTH, CARDHEIGHT);
        g.drawImage(Game.CLUBIMAGE, x + 1, y, CARDWIDTH - 2, CARDWIDTH, window);
        g.drawImage(Game.DIAMONDIMAGE, x, y + CARDWIDTH, CARDWIDTH, CARDWIDTH, window);
    }
}
