import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame {
    // TODO: Complete this class
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_WIDTH_CENTER = 500;
    public static final int WINDOW_HEIGHT = 800;

    private Game game;

    public GameViewer(Game game) {
        this.game = game;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Card Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
        repaint();
    }

    public void paint(Graphics g) {
        myPaint(g);
    }


    public void myPaint(Graphics g) {
        int gameState = game.getGameState();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 18));
        // Instructions window
        if (gameState == 0){
            g.setFont(new Font("SansSerif", Font.PLAIN, 36));
            g.drawString("This is Blackjack", 20, 100);
            g.setFont(new Font("SansSerif", Font.PLAIN, 18));
            g.drawString("To play this game you need to get as close to 21 as possible without going over", 20, 200);
            g.drawString("However, you must also get a higher value hand than the dealer", 20, 225);
            g.drawImage(Game.CLUBIMAGE, 50, 500, 200, 200, this);
            g.drawImage(Game.DIAMONDIMAGE, 275, 500, 200, 200, this);
            g.drawImage(Game.HEARTIMAGE, 525, 500, 200, 200, this);
            g.drawImage(Game.SPADEIMAGE, 750, 500, 200, 200, this);
        // Game window
        } else if (gameState == 1) {
            g.drawImage(Game.BACKGROUND, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            game.getDealer().drawDealer(g);
            game.getCurrentPlayer().drawPlayer(g);
            g.drawString(game.getCurrentPlayer().getName() + "'s cards", 400, WINDOW_HEIGHT - 250);
        }
        // End screen
        else {
            g.setFont(new Font("SansSerif", Font.PLAIN, 36));
            g.drawString("The game is over!", 20, 100);
            g.drawString("Winners:", 20, 150);
            g.setFont(new Font("SansSerif", Font.PLAIN, 24));
            ArrayList<Player> winners = game.getWinners();
            for (int i = 0; i < winners.size(); i++) {
                g.drawString(winners.get(i).getName(), 20, 200 + i * 40);
            }
            game.getDealer().drawPlayer(g);
            g.drawString("Dealer's Cards:", 400, WINDOW_HEIGHT - 250);
        }

    }
}