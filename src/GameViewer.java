import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // TODO: Complete this class
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_WIDTH_CENTER = 500;
    public static final int WINDOW_HEIGHT = 800;

    public static final Image CLUBIMAGE = new ImageIcon("Resources/club.png").getImage();
    public static final Image DIAMONDIMAGE = new ImageIcon("Resources/diamond.png").getImage();
    public static final Image HEARTIMAGE = new ImageIcon("Resources/heart.png").getImage();
    public static final Image SPADEIMAGE = new ImageIcon("Resources/spade.png").getImage();

    private Game game;
    private Player currentPlayer;
    public GameViewer(Game game) {
        this.game = game;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Card Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        repaint();
    }

    public void paint(Graphics g) {
        myPaint(g);
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void myPaint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 18));

        if(currentPlayer != null){
            game.getDealer().drawDealer(g);
            currentPlayer.drawPlayer(g);
        }


    }
}