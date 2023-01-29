package gameScreen;

import database.PlayerDatabase;
import gameObjects.Ground;
import gameObjects.Obstacles;
import gameObjects.Player;
import gameScreen.Menu;
import util.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel implements Runnable {
    public static enum GameState {
        MENU, GAME, END
    }

    public static GameState state = GameState.MENU;
    private Thread thread;
    private Player player;  //check for static
    private Ground ground;
    private Ground background;
    private Obstacles obstacles;
    private gameScreen.Menu menu;
    private PlayerDatabase playerDatabase;

    public Player getPlayer() {
        return player;
    }

    public Panel() throws IOException {
        thread = new Thread(this);
        player = new Player();
        ground = new Ground("pictures/ground.png", 0, (int) Constants.GROUND_Y, getWidth(), (int) (Constants.SCREEN_HEIGHT - Constants.GROUND_Y), Constants.GROUND_SPEED); // getWidth() delete
        background = new Ground("pictures/background.png", 0, 0, Constants.SCREEN_HEIGHT, Constants.GROUND_Y, Constants.BACKGROUND_SPEED);
        obstacles = new Obstacles(player);
        menu = new Menu();
        playerDatabase = new PlayerDatabase("f.txt");
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                update();

                repaint();
                Thread.sleep(10);

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update() throws IOException, ClassNotFoundException {   //the gameobjects updates only when tha game goes
        if (state == GameState.GAME) {
            ground.update();
            background.update();
            player.update();
            obstacles.update();
            if (!player.isAlive()) {
                playerDatabase.append(player);
                state = GameState.END;
                playerDatabase.print();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        //Graphics2D g2 = (Graphics2D) g;

        switch (state) {
            case MENU -> {

                g.setColor(Color.BLUE);
                g.fillRect(0,0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.drawLine(0, (int)Constants.GROUND_Y, getWidth(), (int)Constants.GROUND_Y);
                ground.draw(g);
                background.draw(g);
                g.setColor(Color.BLACK);
                player.draw(g);
                //obstacles.draw(g);
                menu.draw(g);

            } case GAME -> {
                g.setColor(Color.BLUE);
                g.fillRect(0,0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.drawLine(0, (int)Constants.GROUND_Y, getWidth(), (int)Constants.GROUND_Y);
                ground.draw(g);
                background.draw(g);
                g.setColor(Color.BLACK);
                player.draw(g);
                obstacles.draw(g);

                Font font = new Font("arial", Font.BOLD, 30);
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString("Name: " + player.getPlayerName(), Constants.SCREEN_WIDTH -300, 50);
                g.drawString("Score: " + player.getScore(), Constants.SCREEN_WIDTH -300, 80);
            } case END -> {
                g.setColor(Color.RED);
                g.fillRect(0,0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.drawLine(0, (int)Constants.GROUND_Y, getWidth(), (int)Constants.GROUND_Y);
                ground.draw(g);
                background.draw(g);    // TODO: change to death

                Image image;
                try {
                    image = ImageIO.read(new File("pictures/menuBackground.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(image, 0, 200, Constants.SCREEN_WIDTH, 350, null);


                Font font1 = new Font("arial", Font.BOLD, 50);
                g.setFont(font1);
                g.setColor(Color.WHITE);
                g.drawString("GAME OVER", Constants.SCREEN_WIDTH /2 - 120, 250);
                Font font2 = new Font("arial", Font.BOLD, 30 );
                g.setFont(font2);
                g.drawString("Your place: " + (playerDatabase.getPlayerList().indexOf(player) + 1), Constants.SCREEN_WIDTH /2 - 150, 400);
                
            }
        }
    }
}
