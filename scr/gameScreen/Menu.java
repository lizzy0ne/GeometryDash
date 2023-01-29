package gameScreen;

import util.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Menu {
    private Rectangle playButton = new Rectangle(Constants.SCREEN_WIDTH/2 - 120, 300, 100, 50);
    private Rectangle quitButton = new Rectangle(Constants.SCREEN_WIDTH/2 - 120, 400, 100, 50);
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Image image = null;
        try {
            image = ImageIO.read(new File("pictures/menuBackground.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(image, 0, 200, Constants.SCREEN_WIDTH, 350, null);


        Font font1 = new Font("arial", Font.BOLD, 50);
        g.setFont(font1);
        g.setColor(Color.WHITE);
        g.drawString("MENU", Constants.SCREEN_WIDTH /2 - 120, 250);

        Font font2 = new Font("arial", Font.BOLD, 30 );
        g.setFont(font2);
        g.drawString("Play", playButton.x + 19, playButton.y + 30);
        g2.draw(playButton);
        g.drawString("Exit", quitButton.x + 19, quitButton.y + 30);
        g2.draw(quitButton);

    }
}
