package gameScreen;

import gameScreen.Panel;
import util.Constants;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class ML implements MouseListener {
    private Panel panel;
    public ML(Panel panel) {
        this.panel = panel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*
        private Rectangle playButton = new Rectangle(util.Constants.SCREEN_WIDTH/2 - 120, 300, 100, 50);
        private Rectangle quitButton = new Rectangle(util.Constants.SCREEN_WIDTH/2 - 120, 400, 100, 50);

         */
        //buttons
        if (e.getX() >= Constants.SCREEN_WIDTH/2 - 120 && e.getX() <= Constants.SCREEN_WIDTH/2 - 20) {
            if (e.getY() >= 300 && e.getY() <= 350) {
                //Pressed Play button
                try {
                    String temp = (String) JOptionPane.showInputDialog(
                            Window.getWindow(),
                            "Enter your name",
                            "gameObjects.Player name",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Eliza"
                    );
                    panel.getPlayer().setPlayerName(temp);
                    if(panel.getPlayer().getPlayerName().equals("")) panel.getPlayer().setPlayerName("NoName");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Panel.state = Panel.GameState.GAME;
            }

            if (e.getY() >= 400 && e.getY() <= 450) {
                //Pressed Quit button
                Panel.state = Panel.GameState.END;
                System.exit(1);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
